package org.example.minesweeper.service;


import lombok.RequiredArgsConstructor;
import org.example.minesweeper.JSONsample.JSonPlayer;
import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;
import org.example.minesweeper.exceptions.UnacceptableSumMines;
import org.example.minesweeper.gameLogic.*;
import org.example.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameStart gameStart;
    private final GameTurn gameTurn;

    @Override
    public JSonPlayer newGameRequest(int width, int height, int mines_count) throws UnacceptableSumMines,InvalidAction {
        final int MIN_WIDTH = 2;
        final int MAX_WIDTH = 30;
        final int MIN_HEIGHT = 2;
        final int MAX_HEIGHT = 30;

        if(width<MIN_WIDTH||width>MAX_WIDTH){
            throw new InvalidAction("Ширина поля должна быть не менее 2 и не более 30");
        }
        if(height<MIN_HEIGHT||height>MAX_HEIGHT){
            throw new InvalidAction("Длина поля должна быть не менее 2 и не более 30");
        }
        if (width * height - 1 < mines_count) {
            throw new UnacceptableSumMines("количество мин должно быть не менее 1 и не более " + (width * height - 1));
        }

        //Старт и настройка
        HashMap<String,Character[][]> chars = gameStart.startGame(width,height,mines_count);
        GameInfo gameInfo = gameRepository.save(new GameInfo(width,height,mines_count,false, chars.get("charsClient"), chars.get("charsServer")));
        return new JSonPlayer(gameInfo.getGameId(), gameInfo.getWidth(), gameInfo.getHeight(), gameInfo.getMines_count(), gameInfo.isCompleted(), gameInfo.getField());
    }

    @Override
    public JSonPlayer GameTurnRequest(String game_id, int col, int row) throws InvalidAction{
        GameInfo gameInfo = gameRepository.findByGameId(game_id);
        if (gameInfo.isCompleted()){
            throw new InvalidAction("Игра завершена");
        }
        if (gameInfo.getField()[row][col]== gameInfo.getFieldFull()[row][col]){
            throw new InvalidAction("Данная ячейка уже открыта");
        }
        //открытие нажатой клетки
        gameTurn.openCellRef(gameInfo, row,col);
        //Оценка текущего состояния игры
        gameRepository.save(gameInfo);
        return new JSonPlayer(gameInfo.getGameId(), gameInfo.getWidth(), gameInfo.getHeight(), gameInfo.getMines_count(), gameInfo.isCompleted(), gameInfo.getField());
    }
}
