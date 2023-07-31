package org.tictactoe.entity;

public enum SymbolEnum {
    CROSS("X"),
    ZERO("O");

    private final String label;

    SymbolEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
