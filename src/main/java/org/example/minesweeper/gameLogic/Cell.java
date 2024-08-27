package org.example.minesweeper.gameLogic;

public class Cell {
    private int countMine=0;
    private boolean isMine;
    public void countMine(){
        countMine++;
    }
    public int getCountMine(){
        return countMine;
    }
    public void isMine(){
        this.isMine=true;
    }
    public boolean getIsMine(){
        return isMine;
    }
}
