package org.tictactoe.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tictactoe.entity.GameTurn;

public interface GameTurnRepository extends JpaRepository<GameTurn, Long> {
}
