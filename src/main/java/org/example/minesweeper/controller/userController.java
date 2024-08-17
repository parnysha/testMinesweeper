package org.example.minesweeper.controller;


import lombok.RequiredArgsConstructor;
import org.example.minesweeper.dto.JSonReqNew;
import org.example.minesweeper.dto.JSonReqTurn;
import org.example.minesweeper.dto.JsonReqEr;
import org.example.minesweeper.exceptions.InvalidAction;
import org.example.minesweeper.exceptions.UnacceptableSumMines;
import org.example.minesweeper.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class userController {
    private final UserService userService;

    @PostMapping("/new")
    public Object createUser(@RequestBody JSonReqNew reqNew) {
        try {
            return userService.newGameRequest(reqNew.getWidth(), reqNew.getHeight(), reqNew.getMines_count());
        }catch (InvalidAction | UnacceptableSumMines e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            JsonReqEr jsonReqErE = new JsonReqEr(e.getMessage());
            return new ResponseEntity<>(jsonReqErE, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/turn")
    public Object turnUser(@RequestBody JSonReqTurn reqNew) {
        try {
            return userService.GameTurnRequest(reqNew.getGame_id(),reqNew.getCol(),reqNew.getRow());
        } catch (InvalidAction e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            JsonReqEr jsonReqErE = new JsonReqEr(e.getMessage());
            return new ResponseEntity<>(jsonReqErE, HttpStatus.BAD_REQUEST);
        }
    }

}
