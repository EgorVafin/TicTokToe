package org.tictactoe.gameTurn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.GameTurn;

import java.util.List;

public interface GameTurnRepository extends JpaRepository<GameTurn, Long> {

    List<GameTurn> findByGame(Game game);

}
