package org.example.minesweeper.dto;

import jakarta.persistence.Column;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
public class JsonReqEr {

    @Column
    private String error;
}
