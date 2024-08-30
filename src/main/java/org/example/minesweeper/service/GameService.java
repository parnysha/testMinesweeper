package org.example.minesweeper.service;

import org.example.minesweeper.JSONsample.JSonPlayer;

public interface GameService {

    JSonPlayer newGameRequest(int width, int height, int mines_count);
    JSonPlayer gameTurnRequest(String game_id, int col, int row);
    }
