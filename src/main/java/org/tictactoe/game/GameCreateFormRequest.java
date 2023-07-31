package org.tictactoe.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tictactoe.entity.SymbolEnum;
import org.tictactoe.entity.User;

@NoArgsConstructor
@Getter
@Setter
public class GameCreateFormRequest {

    private SymbolEnum symbol;

    private User user;

}
