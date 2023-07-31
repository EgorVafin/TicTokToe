package org.tictactoe.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.GameStatusEnum;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private GameVoter gameVoter = new GameVoter();

    public void createGame(GameCreateFormRequest createFormRequest) {

        Game game = new Game();
        game.setCreatorPlayer(createFormRequest.getUser());
        game.setCreatorSymbol(createFormRequest.getSymbol());
        game.setStartDate(new Date());
        game.setStatus(GameStatusEnum.CREATED);
        UUID uUId = UUID.randomUUID();
        game.setUUId(uUId.toString());
        gameRepository.save(game);
    }

    public void connectToGame(GameConnectFormRequest gameConnectFormRequest) {

        String uUId = gameConnectFormRequest.getUUId();
        Game game = gameRepository.findByuUId(uUId);
        String creatorPlayerEmail = game.getCreatorPlayer().getEmail(); //TODO как лучше сделать проверку на разность юзеров
        String secondPlayerEmail = gameConnectFormRequest.getSecondPlayer().getEmail();

        if (gameVoter.isGameStatusCreated(game)
                && !creatorPlayerEmail.equals(secondPlayerEmail)) {

            game.setSecondPlayer(gameConnectFormRequest.getSecondPlayer());
            game.setStatus(GameStatusEnum.ACTIVE);
            gameRepository.save(game);
        }
    }
}
