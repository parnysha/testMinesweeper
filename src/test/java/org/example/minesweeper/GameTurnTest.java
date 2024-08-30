package org.example.minesweeper;
import static org.junit.jupiter.api.Assertions.*;

import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.gameLogic.GameTurn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
@SpringBootTest
public class GameTurnTest {
    @Autowired
    GameTurn gameTurn;

    @Test
    public void testGameTurnDefault() {
        Character[][] boardClient = new Character[2][2];
        for (Character[] characters : boardClient) {
            Arrays.fill(characters, ' ');
        }
        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[1][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][1] = 'X';

        Character[][] boardAfterTurn = new Character[2][2];
        boardAfterTurn[0][0] = '1';
        boardAfterTurn[1][0] = ' ';
        boardAfterTurn[0][1] = ' ';
        boardAfterTurn[1][1] = ' ';

        GameInfo gameInfo = new GameInfo(2,2,1,false,boardClient,boardServer);
        GameInfo gameInfoTest = new GameInfo(2,2,1,false,boardAfterTurn,boardServer);

        gameTurn.openCellRef(gameInfo,0,0);

        assertEquals(gameInfoTest,gameInfo);
    }
    @Test
    public void testGameTurnWin() {
        Character[][] boardClient = new Character[2][2];
        boardClient[0][0] = ' ';
        boardClient[0][1] = '1';
        boardClient[1][0] = '1';
        boardClient[1][1] = ' ';

        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][0] = '1';
        boardServer[1][1] = 'X';

        Character[][] boardAfterTurn = boardServer.clone();
        boardAfterTurn[1][1] = 'М';

        GameInfo gameInfo = new GameInfo(2,2,1,false,boardClient,boardServer);
        GameInfo gameInfoTest = new GameInfo(2,2,1,true,boardAfterTurn,boardServer);

        gameTurn.openCellRef(gameInfo,0,0);

        assertEquals(gameInfoTest,gameInfo);
    }
    @Test
    public void testGameTurnLose() {
        Character[][] boardClient = new Character[2][2];
        boardClient[0][0] = ' ';
        boardClient[0][1] = '1';
        boardClient[1][0] = '1';
        boardClient[1][1] = ' ';

        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][0] = '1';
        boardServer[1][1] = 'X';

        Character[][] boardAfterTurn = boardServer.clone();

        GameInfo gameInfo = new GameInfo(2,2,1,false,boardClient,boardServer);
        GameInfo gameInfoTest = new GameInfo(2,2,1,true,boardAfterTurn,boardServer);

        gameTurn.openCellRef(gameInfo,1,1);

        assertEquals(gameInfoTest,gameInfo);
    }

}
