package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import javax.annotation.Nonnull;

public abstract class Skill {

    @Nonnull
    private final String name;
    private int bonus = 0;

    public Skill(@Nonnull String name) {
        this.name = name;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public int getPass(@Nonnull AbilityScore abilityScore) {
        return 10 + abilityScore.getMod();
    }
}
