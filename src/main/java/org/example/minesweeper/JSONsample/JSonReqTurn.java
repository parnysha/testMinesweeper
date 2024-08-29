package org.example.minesweeper.JSONsample;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JSonReqTurn {

    private String game_id;
    private int col;
    private int row;
}