package org.example.minesweeper.repository;

import org.example.minesweeper.dto.FieldsString;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FieldsRepository extends CrudRepository<FieldsString, String> {
    FieldsString findByGameId(String game_id);
}