package io.musician101.mcdndsimple.common.character;

public enum Recharge {
    LONG_REST("Long Rest"), NONE("None"), OTHER("Other"), SHORT_REST("Short Rest");

    private final String name;

    Recharge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
