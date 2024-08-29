package org.example.minesweeper.gameLogic;

import java.util.HashMap;


public interface GameStart {
    HashMap<String,Character[][]> startGame(int width, int height, int mines_count);
}
