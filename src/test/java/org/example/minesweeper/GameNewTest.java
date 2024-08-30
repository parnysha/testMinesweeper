package org.example.minesweeper;

import static org.junit.jupiter.api.Assertions.*;
import org.example.minesweeper.gameLogic.GameStart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
public class GameNewTest {
    @Autowired
    GameStart gameStart;

    @Test
    public void testWidth(){
        HashMap<String,Character[][]> map = gameStart.startGame(2,2,1);

        assertEquals(2,map.get("charsClient").length);
        assertEquals(2,map.get("charsServer").length);
    }

    @Test
    public void testHeight(){
        HashMap<String,Character[][]> map = gameStart.startGame(2,2,1);

        assertEquals(2,map.get("charsClient")[0].length);
        assertEquals(2,map.get("charsServer")[0].length);
    }

    @Test
    public void testMinesCount(){
        HashMap<String,Character[][]> map = gameStart.startGame(2,2,1);
        int mines = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get("charsServer")[0].length; j++) {
                if(map.get("charsServer")[i][j]=='X'){
                    mines++;
                }
            }
        }
        assertEquals(1,mines);
    }

    @Test
    public void testEmptyField(){
        HashMap<String,Character[][]> map = gameStart.startGame(2,2,1);
        Character[][] charTest = new Character[2][2];
        for (Character[] characters : charTest) {
            Arrays.fill(characters, ' ');
        }
        assertArrayEquals(charTest,map.get("charsClient"));
    }
}
