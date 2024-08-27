package org.example.minesweeper.gameLogic;

import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;

public class GameTurn{
    //Открытие клеток
    public static void openCellRef(GameInfo gameInfo, int x, int y) throws InvalidAction {
        int weight = gameInfo.getWidth();
        int height = gameInfo.getHeight();
        Character[][] charsServer = gameInfo.getFieldFull();
        Character[][] charsClient = gameInfo.getField();
        int minesCount = gameInfo.getMines_count();
        if (x < 0 || y < 0 || x >= weight || y >= height) return;
        if (charsServer[x][y]=='X'){
            charsClient[x][y]='X';
            gameInfo.setCompleted(true);
            gameInfo.setField(charsServer);
            return;
        }
        if (charsClient[x][y]==charsServer[x][y]){
            return;
        }
        charsClient[x][y]=charsServer[x][y];
        int sumClosed = 0;
        for(Character[] characters:charsClient){
            for (Character chara:characters){
                if (chara!=' '){
                    sumClosed+=1;
                }
            }
        }
        if (sumClosed >= height * weight - minesCount){
            gameInfo.setCompleted(true);
            winMap(charsClient,' ');
            return;
        }
        if(charsServer[x][y]=='0') {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    openCellRef(gameInfo,x + i, y + j);
                }
            }
        }
    }
    //Отрисовка карты при победе
    private static void winMap(Character[][] chars, char sravn){
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if(chars[i][j]==sravn){
                    chars[i][j]='М';
                }
            }
        }
    }
}
