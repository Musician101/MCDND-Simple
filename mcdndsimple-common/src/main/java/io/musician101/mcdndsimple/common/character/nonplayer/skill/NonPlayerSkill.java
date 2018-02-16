package io.musician101.mcdndsimple.common.character.nonplayer.skill;

import io.musician101.mcdndsimple.common.character.Skill;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import javax.annotation.Nonnull;

public class NonPlayerSkill extends Skill {

    public NonPlayerSkill(@Nonnull String name) {
        super(name);
    }

    public int getTotal(@Nonnull AbilityScore abilityScore) {
        return abilityScore.getMod();
    }
}
