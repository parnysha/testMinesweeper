package org.example.minesweeper.gameLogic;

public class Cell {
    private int countMine=0;
    private boolean isMine;
    private boolean isOpen=false;
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
    public void isOpen(){
        this.isOpen=true;
    }
    public boolean getIsOpen(){
        return isOpen;
    }
}
