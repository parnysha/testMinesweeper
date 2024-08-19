package org.example.minesweeper.repository;

import org.example.minesweeper.dto.FieldsString;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FieldsRepository extends CrudRepository<FieldsString, String> {
    @Query("SELECT CONCAT(fieldString0,fieldString1,fieldString2,fieldString3,fieldString4) from FieldsString")
    String queryFieldsStringByGameId(String game_id);
}