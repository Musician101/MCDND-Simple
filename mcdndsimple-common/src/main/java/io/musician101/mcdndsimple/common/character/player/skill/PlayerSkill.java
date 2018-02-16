package io.musician101.mcdndsimple.common.character.player.skill;

import io.musician101.mcdndsimple.common.character.Skill;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import javax.annotation.Nonnull;

public class PlayerSkill extends Skill {

    @Nonnull
    private SkillProficiency skillProficiency = SkillProficiency.NONE;

    public PlayerSkill(@Nonnull String name) {
        super(name);
    }

    @Nonnull
    public SkillProficiency getSkillProficiency() {
        return skillProficiency;
    }

    public void setSkillProficiency(@Nonnull SkillProficiency skillProficiency) {
        this.skillProficiency = skillProficiency;
    }

    public int getTotal(AbilityScore abilityScore, ClassLevels classLevels, Experience experience) {
        return skillProficiency.getBonus(abilityScore, classLevels, experience) + getBonus();
    }
}
