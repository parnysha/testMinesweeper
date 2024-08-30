package org.example.minesweeper.controller;


import lombok.RequiredArgsConstructor;
import org.example.minesweeper.JSONsample.JSonReqNew;
import org.example.minesweeper.JSONsample.JSonReqTurn;
import org.example.minesweeper.JSONsample.JSonPlayer;
import org.example.minesweeper.service.GameService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GameController {
    private final GameService gameService;

    @PostMapping("/new")
    public JSonPlayer createUser(@RequestBody JSonReqNew reqNew) {
        return gameService.newGameRequest(reqNew.getWidth(), reqNew.getHeight(), reqNew.getMines_count());
    }

    @PostMapping("/turn")
    public JSonPlayer turnUser(@RequestBody JSonReqTurn reqNew) {
        return gameService.gameTurnRequest(reqNew.getGame_id(),reqNew.getCol(),reqNew.getRow());
    }

}
