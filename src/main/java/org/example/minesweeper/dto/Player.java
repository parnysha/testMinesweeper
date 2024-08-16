package org.example.minesweeper.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minesweeper.dto.converter.CustomConverter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Player {

    @Id
    @Column
    private String gameId;

    @Column
    private int width;
    @Column
    private int height;
    @Column
    private int mines_count;
    @Column
    private boolean completed;
    @Column
    @Convert(converter = CustomConverter.class)
    private Character[][] field;


    public void setGame_id(String game_id) {
        this.gameId = game_id;
    }

    public String getGame_id() {
        return gameId;
    }
}
