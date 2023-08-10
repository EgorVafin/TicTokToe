package org.tictactoe.game;

import org.tictactoe.entity.GameTurn;

public interface GamePersister {
    void saveTurn (GameTurn gameTurn);
}
