package org.tictactoe.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.User;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

        @Query("FROM Game Where (creatorPlayer = :user or secondPlayer = :user) AND (status = 'CREATED' or status = 'ACTIVE')")
        List<Game> findByCreatorOrSecondPlayerAndCreatedOrActiveStatus(User user);

        @Query("FROM Game Where (creatorPlayer = :user or secondPlayer = :user) AND (status = 'WIN' or status = 'STANDOFF')")
        List<Game> findByCreatorOrSecondPlayerAndWinOrStandoffStatus(User user);

        Game findByuUId(String uUId);


}
