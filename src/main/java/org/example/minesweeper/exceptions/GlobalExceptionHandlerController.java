package org.example.minesweeper.exceptions;

import org.example.minesweeper.JSONsample.JSonReqEr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(UnacceptableSumMines.class)
    public ResponseEntity<JSonReqEr> handleException(UnacceptableSumMines exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JSonReqEr(exception.getMessage()));
    }

    @ExceptionHandler(InvalidAction.class)
    public ResponseEntity<JSonReqEr> handleException(InvalidAction exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JSonReqEr(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSonReqEr> handleException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new JSonReqEr("Произошла неизвестная ошибка"));
    }
}
