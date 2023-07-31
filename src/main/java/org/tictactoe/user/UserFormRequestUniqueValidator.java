package org.tictactoe.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tictactoe.util.validation.unique.UniqueFieldValidator;

@Service
@RequiredArgsConstructor
public class UserFormRequestUniqueValidator implements UniqueFieldValidator {

    private final UserRepository repository;

    @Override
    public boolean exist(Object validated, String fieldName) {
        if (!(validated instanceof UserCreateFormRequest)) {
            throw new RuntimeException("Invalid obj class");
        }

        UserCreateFormRequest dto = (UserCreateFormRequest) validated;
        return switch (fieldName) {
            case "email" -> validateEmail(dto);
            default -> throw new RuntimeException("Invalid validation field: " + fieldName);
        };
    }

    private boolean validateEmail(UserCreateFormRequest dto) {
        if (dto.getId() != null) {
            return repository.findByEmailAndIdNot(dto.getEmail(), dto.getId())
                    .isEmpty();
        } else {
            return repository.findByEmail(dto.getEmail())
                    .isEmpty();
        }
    }
}
