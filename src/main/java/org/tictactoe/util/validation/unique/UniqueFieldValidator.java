package org.tictactoe.util.validation.unique;

public interface UniqueFieldValidator {
    boolean exist(Object validated, String fieldName);
}
