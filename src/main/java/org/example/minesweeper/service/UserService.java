package org.example.minesweeper.service;

import org.example.minesweeper.JSONsample.JSonPlayer;

public interface UserService {

    JSonPlayer newGameRequest(int width, int height, int mines_count);
    JSonPlayer GameTurnRequest(String game_id, int col, int row);
    }
