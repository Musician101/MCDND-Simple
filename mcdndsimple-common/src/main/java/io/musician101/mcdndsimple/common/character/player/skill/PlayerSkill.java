package io.musician101.mcdndsimple.common.character.player.skill;

import io.musician101.mcdndsimple.common.character.Skill;

public class PlayerSkill extends Skill {

    private SkillProficiency skillProficiency = SkillProficiency.NONE;

    public PlayerSkill(String name) {
        super(name);
    }

    public SkillProficiency getSkillProficiency() {
        return skillProficiency;
    }

    public void setSkillProficiency(SkillProficiency skillProficiency) {
        this.skillProficiency = skillProficiency;
    }
}
