package org.tictactoe.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tictactoe.entity.GameTurn;

@Component
@RequiredArgsConstructor
public class GamePersisterImpl implements GamePersister{

    private final GameTurnRepository gameTurnRepository;
    @Override
    public void saveTurn(GameTurn gameTurn) {
        gameTurnRepository.save(gameTurn);
    }
}
