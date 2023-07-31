package org.tictactoe.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GameTurnDto {

    private int coordinateI;

    private int coordinateJ;
}
