package org.example.minesweeper.service;

import lombok.RequiredArgsConstructor;
import org.example.minesweeper.dto.FieldsString;
import org.example.minesweeper.dto.Player;
import org.example.minesweeper.dto.converter.CustomConverterBiggerFields;
import org.example.minesweeper.exceptions.InvalidAction;
import org.example.minesweeper.exceptions.UnacceptableSumMines;
import org.example.minesweeper.gameLogic.Game;
import org.example.minesweeper.gameLogic.GameState;
import org.example.minesweeper.repository.FieldsRepository;
import org.example.minesweeper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FieldsRepository fieldsRepository;

    @Override
    public Player newGameRequest(int width, int height, int mines_count) throws UnacceptableSumMines,InvalidAction {
        if(width<2||width>30){
            throw new InvalidAction("Ширина поля должна быть не менее 2 и не более 30");
        }
        if(height<2||height>30){
            throw new InvalidAction("Длина поля должна быть не менее 2 и не более 30");
        }
        if (width * height - 1 < mines_count) {
            throw new UnacceptableSumMines("количество мин должно быть не менее 1 и не более " + (width * height - 1));
        }
        String game_id = UUID.randomUUID().toString();

        //Старт и настройка
        Game game = Game.getInstance();
        game.startGame(width,height,mines_count);

        Player player = new Player(game_id, width, height, mines_count, false, game.getChars());

        if (height * width <= 224) {
            player.setField(game.getChars());
            userRepository.save(player);
            player.setField(game.getCharsClient());
        } else {
            FieldsString fieldsString = CustomConverterBiggerFields.convertCharsToStrings(game_id, game.getChars(), height,width);
            fieldsRepository.save(fieldsString);
            player.setField(new Character[][]{});
            userRepository.save(player);
            player.setField(game.getCharsClient());
        }
        return player;
    }

    @Override
    public Player GameTurnRequest(String game_id, int col, int row) throws InvalidAction{
        Player player = userRepository.findByGameId(game_id);
        Game game = Game.getInstance();
        if (game.getCells()[row][col].getIsOpen()){
            throw new InvalidAction("Данная ячейка уже открыта");
        }
        if (game.getGameState()==GameState.LOSE||game.getGameState()==GameState.WiN){
            throw new InvalidAction("Игра завершена");
        }
        Character[][] conver = new Character[player.getWidth()][player.getHeight()];
        if(player.getHeight()* player.getWidth()>255&& player.getHeight()* player.getWidth()<1275) {
            FieldsString fieldsString = fieldsRepository.findByGameId(game_id);
            conver = CustomConverterBiggerFields.convertStringsToChars(fieldsString);
        }
        //открытие нажатой клетки
        game.openCell(game.getCharsClient(), game.getCells(), row,col, player.getMines_count());
        //Оценка текущего состояния игры
        game.checkStateGame(player,conver);
        return player;
    }
}
