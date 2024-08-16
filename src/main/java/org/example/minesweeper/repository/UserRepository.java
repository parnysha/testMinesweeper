package org.example.minesweeper.repository;

import org.example.minesweeper.dto.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<Player, String> {
    Player findByGameId(String game_id);
}
