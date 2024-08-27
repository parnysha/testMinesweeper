package org.example.minesweeper.gameLogic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@AllArgsConstructor
@Setter
@Getter
public class GameStart{
    private Character[][] charsClient;
    private Character[][] chars;
    private Cell[][] cells;
    //Отрисовка пустой карты
    private void createFreeMap(Character[][] chars,int width, int height){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                chars[i][j] = ' ';
            }
        }
    }
    //Отрисовка полной карты
    private void generateMap(Character[][] chars,Cell[][] cells){
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                cells[i][j] = new Cell();
                chars[i][j] = String.valueOf(cells[i][j].getCountMine()).charAt(0);
            }
        }
    }
    //Отрисовка колличества мин вокруг клеток
    private void countMine(Cell[][] cells, Character[][] chars,int x,int y){
        for (int k = -1; k < 2; k++){
            for (int l = -1; l < 2; l++){
                if(x+k>=0&&y+l>=0&&x+k<chars.length&&y+l<chars[0].length&&(!cells[x + k][y + l].getIsMine())) {
                    cells[x + k][y + l].countMine();
                    chars[x + k][y + l] = String.valueOf(cells[x + k][y + l].getCountMine()).charAt(0);
                }
            }
        }
    }
    //Создание мин в клетках
    private void createMine(Cell[][] cells,Character[][] chars,int mines_count){
        HashSet<List<Integer>> mineCoords = new HashSet<>();
        Random random = new Random();
        while (mineCoords.size() < mines_count){
            int x = random.nextInt(chars.length);
            int y = random.nextInt(chars[0].length);
            List<Integer> coords = new ArrayList<>(2);
            coords.add(x);
            coords.add(y);
            if(!mineCoords.contains(coords)){
                mineCoords.add(coords);
                chars[x][y] = 'X';
                cells[x][y].isMine();
                //подсчет мин вокруг клетки
                countMine(cells,chars,x,y);
                }
            }
    }
    //Задание настроек для игры
    public void startGame(int width,int height,int mines_count){
        createFreeMap(getCharsClient(),width, height);
        generateMap(getChars(), getCells());
        createMine(getCells(), getChars(), mines_count);
    }
}
