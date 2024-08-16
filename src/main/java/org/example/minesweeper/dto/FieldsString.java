package org.example.minesweeper.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table
public class FieldsString {

    @Id
    @Column
    private String gameId;

    @Column
    private String fieldString0;
    @Column
    private String fieldString1;
    @Column
    private String fieldString2;
    @Column
    private String fieldString3;
    @Column
    private String fieldString4;


    public void setGame_id(String game_id) {
        this.gameId = game_id;
    }

    public String getGame_id() {
        return gameId;
    }
}