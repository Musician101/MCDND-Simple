package io.musician101.mcdndsimple.common.character.player.spell;

public enum Prepared {

    ALWAYS("Always"),
    YES("Yes"),
    NO("No");

    private final String name;

    Prepared(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean value() {
        return this != NO;
    }
}
