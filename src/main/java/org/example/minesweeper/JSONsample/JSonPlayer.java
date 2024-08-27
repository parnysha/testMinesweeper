package org.example.minesweeper.JSONsample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class JSonPlayer {
    private String game_id;
    private int width;
    private int height;
    private int mines_count;
    private boolean completed;
    private Character[][] field;
}
