package org.tictactoe.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tictactoe.entity.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GameModel {
    private Game game;

    private User currentPlayer;

    private User opponentPlayer;

    private SymbolEnum currentPlayerSymbol;

    private boolean isMyTurn;

    private GameStatusEnum status;

    private SymbolEnum[][] field = new SymbolEnum[3][3];

    public GameModel(Game game, User currentPlayer) {
        this.game = game;
        this.currentPlayer = currentPlayer;
        this.opponentPlayer = findOpponentPlayer();
        this.currentPlayerSymbol = findCurrentPlayerSymbol();
        this.isMyTurn = isMyTurn();
        fillGameField();
    }

    public User findOpponentPlayer() {
        User creatorPlayer = game.getCreatorPlayer();
        if (currentPlayer.sameAs(creatorPlayer)) {
            return game.getSecondPlayer();
        } else {
            return game.getCreatorPlayer();
        }
    }

    public SymbolEnum findCurrentPlayerSymbol() {
        if (currentPlayer.sameAs(game.getCreatorPlayer())) {
            return game.getCreatorSymbol();
        }
        if (game.getCreatorSymbol().equals(SymbolEnum.ZERO)) {
            return SymbolEnum.CROSS;
        } else {
            return SymbolEnum.ZERO;
        }
    }

    public boolean isMyTurn() {
        if (game.getGameTurns().isEmpty()) {
            return game.getCreatorPlayer().sameAs(currentPlayer);
        }
        GameTurn lastTurn = game.getGameTurns().get(game.getGameTurns().size() - 1);
        return !lastTurn.getUserWhoMadeTurn().sameAs(currentPlayer);
    }

    private void fillGameField() {
        for (GameTurn gameTurn : game.getGameTurns()) {
            field[gameTurn.getCoordinateI()][gameTurn.getCoordinateJ()] = gameTurn.getSymbol();
        }
    }

    public void makeTurn(int i, int j) {
        //todo check turn avilability, throw eception if invalid
        field[i][j] = currentPlayerSymbol;
        createTurnInDb(i, j);

        checkWin();
        checkStandoff();
    }

    private void checkStandoff() {
        if (game.getGameTurns().size() == 9 && !game.getStatus().equals(GameStatusEnum.WIN)) {
            setStatus(GameStatusEnum.STANDOFF);
            game.setStatus(GameStatusEnum.STANDOFF);
        }
    }

    private void checkWin() {
        if (game.getGameTurns().size() > 3) {
            if (isCurrentPlayerWin()) {
                setStatus(GameStatusEnum.WIN);
                game.setStatus(GameStatusEnum.WIN);
            }
        }
    }

    private boolean isCurrentPlayerWin() {
        for (int i = 0; i < 3; i++) {
            boolean flagWinHorizonLine = false;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != null && field[i][j].equals(currentPlayerSymbol)) {
                    flagWinHorizonLine = true;
                } else {
                    flagWinHorizonLine = false;
                    break;
                }
            }
            if (flagWinHorizonLine) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            boolean flagWinVerticalLine = false;
            for (int j = 0; j < 3; j++) {
                if (field[j][i] != null && field[j][i].equals(currentPlayerSymbol)) {
                    flagWinVerticalLine = true;
                } else {
                    flagWinVerticalLine = false;
                    break;
                }
            }
            if (flagWinVerticalLine) {
                return true;
            }
        }

        if (currentPlayerSymbol.equals(field[0][0]) && field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2])) {
            return true;
        }
        if (currentPlayerSymbol.equals(field[2][0]) && field[2][0].equals(field[1][1]) && field[1][1].equals(field[0][2])) {
            return true;
        }

        return false;
    }

    private void createTurnInDb(int i, int j) {
        GameTurn newGameTurn = new GameTurn();
        newGameTurn.setSymbol(currentPlayerSymbol);
        newGameTurn.setUserWhoMadeTurn(currentPlayer);
        newGameTurn.setTimestamp(new Date());
        newGameTurn.setCoordinateI(i);
        newGameTurn.setCoordinateJ(j);

        game.addTurn(newGameTurn);
    }
}
