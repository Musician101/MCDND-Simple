package io.musician101.mcdndsimple.common.character.nonplayer.skill;

import io.musician101.mcdndsimple.common.character.Skill;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;

public class NonPlayerSkill extends Skill {

    public NonPlayerSkill(String name) {
        super(name);
    }

    public int getTotal(AbilityScore abilityScore) {
        return abilityScore.getMod();
    }
}
