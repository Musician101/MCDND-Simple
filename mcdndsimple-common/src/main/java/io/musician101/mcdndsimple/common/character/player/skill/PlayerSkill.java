package io.musician101.mcdndsimple.common.character.player.skill;

import io.musician101.mcdndsimple.common.character.Skill;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.Experience;

public class PlayerSkill extends Skill {

    private SkillProficiency skillProficiency = SkillProficiency.NONE;

    public PlayerSkill(String name) {
        super(name);
    }

    public int getTotal(AbilityScore abilityScore, ClassLevels classLevels, Experience experience) {
        return skillProficiency.getBonus(abilityScore, classLevels, experience) + getBonus();
    }

    public SkillProficiency getSkillProficiency() {
        return skillProficiency;
    }

    public void setSkillProficiency(SkillProficiency skillProficiency) {
        this.skillProficiency = skillProficiency;
    }
}
