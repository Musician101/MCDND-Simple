package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.Dice;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class SpellDamage implements DataSerializable
{
    private boolean canCrit = true;
    private Dice dice = new Dice(0);
    private int bonus = 0;
    private String damageType = "";

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
                .set(MCDNDSimpleKeys.CAN_CRIT, canCrit)
                .set(MCDNDSimpleKeys.DICE, dice.toContainer())
                .set(MCDNDSimpleKeys.BONUS, bonus)
                .set(MCDNDSimpleKeys.DAMAGE_TYPE, damageType);
    }

    public String getDamageType()
    {
        return damageType;
    }

    public Dice getDice()
    {
        return dice;
    }

    public boolean isCanCrit()
    {
        return canCrit;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public void setCanCrit(boolean canCrit)
    {
        this.canCrit = canCrit;
    }

    public void setDamageType(String damageType)
    {
        this.damageType = damageType;
    }

    public void setDice(Dice dice)
    {
        this.dice = dice;
    }
}
