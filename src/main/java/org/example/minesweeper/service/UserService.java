package org.example.minesweeper.service;

import org.example.minesweeper.dto.Player;

public interface UserService {

    Player newGameRequest(int width, int height, int mines_count);
    Player GameTurnRequest(String game_id, int col, int row);
    }
