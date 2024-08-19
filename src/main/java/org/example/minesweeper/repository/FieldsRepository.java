package org.example.minesweeper.repository;

import org.example.minesweeper.dto.FieldsString;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


@Repository
public interface FieldsRepository extends CrudRepository<FieldsString, String> {
    @Query("SELECT CONCAT(fieldString0,fieldString1,fieldString2,fieldString3,fieldString4) from FieldsString where gameId=:GameId")
    String queryFieldsStringByGameId(@Param("GameId")String game_id);
}
