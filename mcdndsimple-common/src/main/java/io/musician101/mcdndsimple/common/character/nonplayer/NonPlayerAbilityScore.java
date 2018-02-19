package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import javax.annotation.Nonnull;

public class NonPlayerAbilityScore extends AbilityScore {

    private int bonus = 0;

    public NonPlayerAbilityScore(@Nonnull String name, @Nonnull String shortName) {
        super(name, shortName);
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getSaveTotal() {
        return getMod() + getBonus();
    }
}
