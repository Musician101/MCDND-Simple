package io.musician101.mcdndsimple.common.character.player;

import javax.annotation.Nonnull;

public enum Recharge {
    LONG_REST("Long Rest"),
    NONE("None"),
    OTHER("Other"),
    SHORT_REST("Short Rest");

    @Nonnull
    private final String name;

    Recharge(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }
}
