package org.example.minesweeper.exceptions;

import org.example.minesweeper.dto.JsonReqEr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
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
                .body(new JsonReqEr("Произошла неизвестная ошибка"));
    }
}
