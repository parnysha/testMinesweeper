package org.example.minesweeper.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class JSonReqNew {

    @Column
    private int width;
    @Column
    private int height;
    @Column
    private int mines_count;
}

