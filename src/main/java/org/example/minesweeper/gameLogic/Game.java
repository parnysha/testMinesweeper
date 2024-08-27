package org.example.minesweeper.gameLogic;

import lombok.Getter;
import lombok.Setter;
import org.example.minesweeper.dto.Player;
import org.example.minesweeper.exceptions.InvalidAction;

import java.util.*;

import static org.example.minesweeper.gameLogic.GameState.NOW;
import static org.example.minesweeper.gameLogic.GameState.WiN;

@Setter
@Getter
public class Game {
    private static Game INSTANCE;
    private Game() {
    }
    private GameState gameState;
    private int opensMines=0;
    private Character[][] charsClient;
    private Character[][] chars;
    private Cell[][] cells;
    public static Game getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Game();
        }
        return INSTANCE;
    }
    //Отрисовка пустой карты
    private void createMap(Character[][] chars,int width, int height){
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
                if(x+k>=0&&y+l>=0&&x+k<chars.length&&y+l<chars[0].length) {
                    if (!cells[x + k][y + l].getIsMine()) {
                        cells[x + k][y + l].countMine();
                        chars[x + k][y + l] = String.valueOf(cells[x + k][y + l].getCountMine()).charAt(0);
                    }
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
            List<Integer> coords = new ArrayList<>();
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
    //Открытие клеток
    public void openCell(Character[][] chars,Cell[][] cells,int x,int y,int mines_count) throws InvalidAction{
        if (x < 0 || y < 0 || x >= chars.length || y >= chars[0].length) return;
        if (cells[x][y].getIsMine()){
            chars[x][y]='X';
            setGameState(GameState.LOSE);
            return;
        }
        if (cells[x][y].getIsOpen()){
            return;
        }
        cells[x][y].isOpen();
        openMine();
        chars[x][y]=String.valueOf(cells[x][y].getCountMine()).charAt(0);
        if (chars.length*chars[0].length-mines_count<=getOpensMines()){
            setGameState(WiN);;
            return;
        }
        if(cells[x][y].getCountMine() == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    openCell(chars, cells, x + i, y + j,mines_count);
                }
            }
        }
    }
    //Отрисовка карты при победе
    public void winMap(Character[][] chars,char sravn){
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if(chars[i][j]==sravn){
                    chars[i][j]='М';
                }
            }
        }
    }
    //увеличение числа открытых мин
    private void openMine(){
        opensMines++;
    }
    //Задание настроек для игры
    public void startGame(int width,int height,int mines_count){
        setGameState(NOW);
        setOpensMines(0);
        setCells(new Cell[width][height]);
        setChars(new Character[width][height]);
        setCharsClient(new Character[width][height]);
        createMap(getCharsClient(),width, height);
        generateMap(getChars(), getCells());
        createMine(getCells(), getChars(), mines_count);
    }

    public void checkStateGame(Player player){
        switch (getGameState()){
            case LOSE -> {
                player.setCompleted(true);
                player.setField(getChars());
                }
            case WiN -> {
                player.setCompleted(true);
                winMap(getCharsClient(),' ');
                player.setField(getCharsClient());
                }
            case NOW -> {
                player.setField(getCharsClient());
            }
        }
    }
}
