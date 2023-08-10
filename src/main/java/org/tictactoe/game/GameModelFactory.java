package org.tictactoe.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.User;

@Component
@RequiredArgsConstructor
public class GameModelFactory {
    private final GamePersisterImpl gamePersister;

    public GameModel createGameModel(Game game, User user){
        GameModel gameModel = new GameModel(game, user, gamePersister);

        return gameModel;
    };
}
