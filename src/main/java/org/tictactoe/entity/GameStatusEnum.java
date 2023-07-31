package org.tictactoe.entity;

public enum GameStatusEnum {
    CREATED("СОЗДАНА"),
    ACTIVE("АКТИВНА"),
    WIN("ПОБЕДА"),
    STANDOFF("НИЧЬЯ");

    private final String label;

    GameStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
