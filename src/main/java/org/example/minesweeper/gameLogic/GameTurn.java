package org.example.minesweeper.gameLogic;

import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;



public interface GameTurn {
    //Открытие клеток
    void openCellRef(GameInfo gameInfo, int x, int y) throws InvalidAction;
}
