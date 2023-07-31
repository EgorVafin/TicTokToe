package org.tictactoe.game;

import lombok.RequiredArgsConstructor;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.GameStatusEnum;

@RequiredArgsConstructor
public class GameVoter {

    public boolean isGameStatusCreated(Game game) {
        if (game.getStatus().equals(GameStatusEnum.CREATED)) {
            return true;
        }
        return false;
    }
}
