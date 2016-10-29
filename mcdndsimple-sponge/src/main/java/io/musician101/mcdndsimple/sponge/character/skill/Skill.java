package io.musician101.mcdndsimple.sponge.character.skill;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class Skill implements DataSerializable
{
    private int bonus = 0;
    private int pass = 10;
    private int total = 0;
    private SkillProficiency skillProficiency = SkillProficiency.NONE;
    private final String name;

    public Skill(String name)
    {
        this.name = name;
    }

    public int getBonus()
    {
        return bonus;
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.BONUS, bonus)
                .set(MCDNDSimpleKeys.PASS, pass)
                .set(MCDNDSimpleKeys.TOTAL, total)
                .set(MCDNDSimpleKeys.SKILL_PROFICIENCY, skillProficiency.toContainer())
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getName()
    {
        return name;
    }

    public int getPass()
    {
        return pass;
    }

    public SkillProficiency getSkillProficiency()
    {
        return skillProficiency;
    }

    public int getTotal()
    {
        return total;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public void update(AbilityScore abilityScore)
    {
        this.total = abilityScore.getMod();
        this.pass = 10 + total;
    }

    public void setSkillProficiency(SkillProficiency skillProficiency)
    {
        this.skillProficiency = skillProficiency;
    }
}
