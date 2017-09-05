package io.musician101.mcdndsimple.common.character;

import java.util.function.BiFunction;

public enum UnarmoredBonus {
    BARBARIAN("Barbarian"), DRACONIC_RESILIENCE("Draconic Resilience (Sorcerer Subclass)", (dexMod, secondMod) -> 13 + dexMod), MONK("Monk"), NONE("None", (dexMod, secondMod) -> 0);

    private final BiFunction<Integer, Integer, Integer> biFunction;
    private final String name;

    UnarmoredBonus(String name) {
        this(name, (dexMod, secondMod) -> 10 + dexMod + secondMod);
    }

    UnarmoredBonus(String name, BiFunction<Integer, Integer, Integer> biFunction) {
        this.name = name;
        this.biFunction = biFunction;
    }

    public int getArmorClass(int dexMod, int secondMod) {
        return biFunction.apply(dexMod, secondMod);
    }

    public String getName() {
        return name;
    }
}
