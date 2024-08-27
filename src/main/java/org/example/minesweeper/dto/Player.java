package org.example.minesweeper.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minesweeper.dto.converter.CustomConverter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Player {

    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String gameId;

    @Transient
    private String game_id;


    @Column
    private int width;
    @Column
    private int height;
    @Column
    private int mines_count;
    @Column
    private boolean completed;
    @Column(length = 1000)
    @Convert(converter = CustomConverter.class)
    private Character[][] field;

}
