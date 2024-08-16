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
        }catch (InvalidAction e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (UnacceptableSumMines e) {
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/turn")
    public Object turnUser(@RequestBody JSonReqTurn reqNew) {
        try {
            return userService.GameTurnRequest(reqNew.getGame_id(),reqNew.getCol(),reqNew.getRow());
        } catch (InvalidAction e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(new JsonReqEr(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @ExceptionHandler(UnacceptableSumMines.class)
    public ResponseEntity<JsonReqEr> handleException(UnacceptableSumMines exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JsonReqEr(exception.getMessage()));
    }

    @ExceptionHandler(InvalidAction.class)
    public ResponseEntity<JsonReqEr> handleException(InvalidAction exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JsonReqEr(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonReqEr> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JsonReqEr(exception.getMessage()));
    }
}
