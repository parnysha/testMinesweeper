package org.example.minesweeper.repository;

import org.example.minesweeper.dto.GameInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepositoryTest extends CrudRepository<GameInfo, String> {
}
