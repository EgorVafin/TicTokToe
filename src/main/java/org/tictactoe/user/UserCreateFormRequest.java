package org.tictactoe.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tictactoe.util.validation.fieldsEquals.FieldEquals;
import org.tictactoe.util.validation.unique.FieldUnique;

@NoArgsConstructor
@Getter
@Setter
@FieldEquals(field = "password", equalsTo = "confirmPassword")
@FieldUnique(field = "email", service = UserFormRequestUniqueValidator.class, message = "Такой email уже используется")
public class UserCreateFormRequest {

    private Long id;

    @Email(message = "Должно иметь формат адреса электронной почты")
    @NotBlank(message = "Не должно быть пустым")
    private String email;

    @NotBlank(message = "Не должно быть пустым")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Не должно быть пустым")
    @Size(min=3, max = 255)
    private String password;

    @NotBlank(message = "Не должно быть пустым")
    @Size(min=3, max = 255)
    private String confirmPassword;
}
