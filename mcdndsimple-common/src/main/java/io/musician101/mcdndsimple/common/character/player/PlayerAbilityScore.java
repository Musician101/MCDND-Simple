package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import javax.annotation.Nonnull;

public class PlayerAbilityScore extends AbilityScore {

    private boolean proficient = false;

    public PlayerAbilityScore(@Nonnull String name, @Nonnull String shortName) {
        super(name, shortName);
    }

    public int getSaveMod(@Nonnull ClassLevels classLevels, @Nonnull Experience experience) {
        int mod = getMod();
        if (proficient) {
            return mod + experience.getProficiencyBonus(classLevels);
        }

        return mod;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setIsProficient(boolean proficient) {
        this.proficient = proficient;
    }
}
