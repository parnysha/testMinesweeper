package org.example.minesweeper.gameLogic;

import lombok.Getter;


public class Cell {
    @Getter
    private int countMine=0;
    private boolean isMine=false;
    public void countMine(){
        countMine++;
    }

    public void isMine(){
        this.isMine=true;
    }

    public boolean getIsMine(){
        return this.isMine;
    }
}
