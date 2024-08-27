package org.example.minesweeper.service;

import lombok.RequiredArgsConstructor;
import org.example.minesweeper.JSONsample.JSonPlayer;
import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;
import org.example.minesweeper.exceptions.UnacceptableSumMines;
import org.example.minesweeper.gameLogic.*;
import org.example.minesweeper.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public JSonPlayer newGameRequest(int width, int height, int mines_count) throws UnacceptableSumMines,InvalidAction {
        if(width<2||width>30){
            throw new InvalidAction("Ширина поля должна быть не менее 2 и не более 30");
        }
        if(height<2||height>30){
            throw new InvalidAction("Длина поля должна быть не менее 2 и не более 30");
        }
        if (width * height - 1 < mines_count) {
            throw new UnacceptableSumMines("количество мин должно быть не менее 1 и не более " + (width * height - 1));
        }

        //Старт и настройка
        GameStart gameStart = new GameStart(new Character[width][height],new Character[width][height],new Cell[width][height]);
        gameStart.startGame(width,height,mines_count);
        GameInfo gameInfo = userRepository.save(new GameInfo(width,height,mines_count,false, gameStart.getCharsClient(), gameStart.getChars()));
        return new JSonPlayer(gameInfo.getGameId(), gameInfo.getWidth(), gameInfo.getHeight(), gameInfo.getMines_count(), gameInfo.isCompleted(), gameInfo.getField());
    }

    @Override
    public JSonPlayer GameTurnRequest(String game_id, int col, int row) throws InvalidAction{
        GameInfo gameInfo = userRepository.findByGameId(game_id);
        if (gameInfo.isCompleted()){
            throw new InvalidAction("Игра завершена");
        }
        if (gameInfo.getField()[row][col]== gameInfo.getFieldFull()[row][col]){
            throw new InvalidAction("Данная ячейка уже открыта");
        }
        //открытие нажатой клетки
        GameTurn.openCellRef(gameInfo, row,col);
        //Оценка текущего состояния игры
        userRepository.save(gameInfo);
        return new JSonPlayer(gameInfo.getGameId(), gameInfo.getWidth(), gameInfo.getHeight(), gameInfo.getMines_count(), gameInfo.isCompleted(), gameInfo.getField());
    }
}
