package org.example.minesweeper.gameLogic;

import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;
import org.springframework.stereotype.Component;

@Component
public class GameTurnImpl implements GameTurn{
    @Override
    public void openCellRef(GameInfo gameInfo, int x, int y) throws InvalidAction {
        int width = gameInfo.getWidth();
        int height = gameInfo.getHeight();
        Character[][] charsServer = gameInfo.getFieldFull();
        Character[][] charsClient = gameInfo.getField();
        // Проверка на границы поля
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        // Если ячейка уже открыта или флаг стоит
        if (charsClient[x][y] != ' ') return;
        // Если попали на мину
        if (charsServer[x][y] == 'X') {
            charsClient[x][y] = 'X';
            gameInfo.setCompleted(true);
            gameInfo.setField(charsServer);
            return;
        }
        // Открываем ячейку
        charsClient[x][y] = charsServer[x][y];
        // Проверка на победу
        if (isWin(gameInfo)) {
            gameInfo.setCompleted(true);
            winMap(charsClient);
            return;
        }
        // Если ячейка с нулевым значением, открываем соседние
        if (charsServer[x][y] == '0') {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        openCellRef(gameInfo, x + i, y + j);
                    }
                }
            }
        }
    }
    // Вспомогательная функция для проверки победы
    private boolean isWin(GameInfo gameInfo) {
        int width = gameInfo.getWidth();
        int height = gameInfo.getHeight();
        int minesCount = gameInfo.getMines_count();
        Character[][] charsClient = gameInfo.getField();

        int closedCount = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (charsClient[i][j] == ' ') {
                    closedCount++;
                }
            }
        }

        return closedCount == minesCount;
    }
    //Отрисовка карты при победе
    private void winMap(Character[][] chars){
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if(chars[i][j]==' '){
                    chars[i][j]='М';
                }
            }
        }
    }
}
