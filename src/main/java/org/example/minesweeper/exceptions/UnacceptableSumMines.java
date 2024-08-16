package org.example.minesweeper.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Log
public class UnacceptableSumMines extends RuntimeException {

    public UnacceptableSumMines(final String message) {
        super(message);

        log.warning(message);
    }
}