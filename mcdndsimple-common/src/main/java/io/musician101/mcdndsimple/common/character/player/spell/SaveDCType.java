package io.musician101.mcdndsimple.common.character.player.spell;

import io.musician101.mcdndsimple.common.character.CoreStats;
import java.util.function.BiFunction;

public class SaveDCType {

    private final String name;
    private BiFunction<CoreStats, Integer, Integer> biFunction;
    private int customDC = 0;

    private SaveDCType(String name) {
        this.name = name;
    }

    public SaveDCType(String name, int customDC) {
        this(name);
        this.customDC = customDC;
    }

    public SaveDCType(String name, BiFunction<CoreStats, Integer, Integer> biFunction) {
        this(name);
        this.biFunction = biFunction;
    }

    public String getName() {
        return name;
    }

    public Integer getSaveDC(CoreStats coreStats, int proficiencyBonus) {
        if (biFunction != null) {
            return biFunction.apply(coreStats, proficiencyBonus);
        }
        else {
            return customDC;
        }
    }
}
