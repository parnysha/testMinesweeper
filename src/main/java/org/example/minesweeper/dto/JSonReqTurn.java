package org.example.minesweeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@Getter
@AllArgsConstructor
public class JSonReqTurn {

    private String game_id;
    private int col;
    private int row;
}