package org.example.minesweeper.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JSonReqTurn {

    @Column
    private String game_id;
    @Column
    private int col;
    @Column
    private int row;
}