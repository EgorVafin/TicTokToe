package org.tictactoe.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.tictactoe.entity.User;

@NoArgsConstructor
@Getter
@Setter
public class GameConnectFormRequest {

    @NotBlank(message = "Не должно быть пустым")
    @Length(max = 255)
    private String uUId;

    private User secondPlayer;
}
