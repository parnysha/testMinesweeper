package org.example.minesweeper;
import org.example.minesweeper.JSONsample.JSonPlayer;
import org.example.minesweeper.dto.GameInfo;
import org.example.minesweeper.exceptions.InvalidAction;
import org.example.minesweeper.exceptions.UnacceptableSumMines;
import org.example.minesweeper.repository.GameRepositoryTest;
import org.example.minesweeper.service.GameService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    GameService gameService;

    @Autowired
    GameRepositoryTest gameRepositoryTest;
    @Test
    public void testNewGameRequest() {
        Character[][] boardClientTest = new Character[2][2];
        for (Character[] characters : boardClientTest) {
            Arrays.fill(characters, ' ');
        }

        JSonPlayer jSonPlayer = gameService.newGameRequest(2,2,1);
        JSonPlayer jSonPlayerTest = new JSonPlayer(jSonPlayer.getGame_id(), 2,2,1,false,boardClientTest);

        assertEquals(jSonPlayerTest,jSonPlayer);
    }
    @Test
    public void testNewGameRequestBadWidth() {
        assertThrows(InvalidAction.class, () -> gameService.newGameRequest(2,1,1));
        assertThrows(InvalidAction.class, () -> gameService.newGameRequest(2,31,1));
    }
    @Test
    public void testNewGameRequestBadHeight() {
        assertThrows(InvalidAction.class, () -> gameService.newGameRequest(1,2,1));
        assertThrows(InvalidAction.class, () -> gameService.newGameRequest(31,2,1));
    }
    @Test
    public void testNewGameRequestBadMinesCount() {
        assertThrows(UnacceptableSumMines.class, () -> gameService.newGameRequest(2,2,4));
    }
    @Test
    public void tesTurnGameRequestDefault() {
        Character[][] boardClient = new Character[2][2];
        boardClient[0][0] = ' ';
        boardClient[1][0] = ' ';
        boardClient[0][1] = ' ';
        boardClient[1][1] = ' ';
        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[1][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][1] = 'X';
        GameInfo gameInfo = gameRepositoryTest.save(new GameInfo(2,2,1,false, boardClient,boardServer));

        JSonPlayer jSonPlayer = gameService.gameTurnRequest(gameInfo.getGameId(),0,0);

        Character[][] boardAfterTurn = boardClient.clone();
        boardAfterTurn[0][0] = '1';

        JSonPlayer jSonPlayerAfterTurn = new JSonPlayer(gameInfo.getGameId(),2,2,1,false,boardAfterTurn);

        assertEquals(jSonPlayerAfterTurn, jSonPlayer);
    }
    @Test
    public void tesTurnGameRequestAfterEnd() {
        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[1][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][1] = 'X';
        GameInfo gameInfo = gameRepositoryTest.save(new GameInfo(2,2,1,true, boardServer,boardServer));

        assertThrows(InvalidAction.class, () -> gameService.gameTurnRequest(gameInfo.getGameId(),0,0));
    }
    @Test
    public void tesTurnGameRequestAfterReopenned() {
        Character[][] boardClient = new Character[2][2];
        boardClient[0][0] = '1';
        boardClient[1][0] = ' ';
        boardClient[0][1] = ' ';
        boardClient[1][1] = ' ';
        Character[][] boardServer = new Character[2][2];
        boardServer[0][0] = '1';
        boardServer[1][0] = '1';
        boardServer[0][1] = '1';
        boardServer[1][1] = 'X';
        GameInfo gameInfo = gameRepositoryTest.save(new GameInfo(2,2,1,true, boardClient,boardServer));

        assertThrows(InvalidAction.class, () -> gameService.gameTurnRequest(gameInfo.getGameId(),0,0));
    }
}
