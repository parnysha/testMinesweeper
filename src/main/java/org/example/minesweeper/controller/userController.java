package org.example.minesweeper.controller;


import lombok.RequiredArgsConstructor;
import org.example.minesweeper.dto.JSonReqNew;
import org.example.minesweeper.dto.JSonReqTurn;
import org.example.minesweeper.dto.Player;
import org.example.minesweeper.service.UserService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class userController {
    private final UserService userService;

    @PostMapping("/new")
    public Player createUser(@RequestBody JSonReqNew reqNew) {
        return userService.newGameRequest(reqNew.getWidth(), reqNew.getHeight(), reqNew.getMines_count());
    }

    @PostMapping("/turn")
    public Player turnUser(@RequestBody JSonReqTurn reqNew) {
        return userService.GameTurnRequest(reqNew.getGame_id(),reqNew.getCol(),reqNew.getRow());
    }

}
