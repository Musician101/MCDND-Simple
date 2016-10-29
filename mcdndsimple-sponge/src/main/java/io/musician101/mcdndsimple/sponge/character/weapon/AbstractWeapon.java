package io.musician101.mcdndsimple.sponge.character.weapon;

import io.musician101.mcdndsimple.sponge.Dice;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public abstract class AbstractWeapon implements DataSerializable
{
    private boolean isProficient = true;
    private Dice critDamageDice = new Dice(0);
    private Dice damageDice = new Dice(0);
    private int critMin = 20;
    private int damageBonus = 0;
    private int magicBonus = 0;
    private int toHit = 0;
    private String attackStat;
    private String name = "";

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.IS_PROFICIENT, isProficient)
                .set(MCDNDSimpleKeys.CRIT_DAMAGE_DICE, critDamageDice.toContainer())
                .set(MCDNDSimpleKeys.DAMAGE_DICE, damageDice.toContainer())
                .set(MCDNDSimpleKeys.CRIT_MINIMUM, critMin)
                .set(MCDNDSimpleKeys.DAMAGE_BONUS, damageBonus)
                .set(MCDNDSimpleKeys.MAGIC_BONUS, magicBonus)
                .set(MCDNDSimpleKeys.TO_HIT, toHit)
                .set(MCDNDSimpleKeys.ATTACK_STAT, attackStat)
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getAttackStat()
    {
        return attackStat;
    }

    public Dice getCritDamageDice()
    {
        return critDamageDice;
    }

    public int getCritMin()
    {
        return critMin;
    }

    public int getDamageBonus()
    {
        return damageBonus;
    }

    public Dice getDamageDice()
    {
        return damageDice;
    }

    public int getMagicBonus()
    {
        return magicBonus;
    }

    public String getName()
    {
        return name;
    }

    public int getToHit()
    {
        return toHit;
    }

    public boolean isProficient()
    {
        return isProficient;
    }

    public void setAttackStat(String attackStat)
    {
        this.attackStat = attackStat;
    }

    public void setCritDamageDice(Dice critDamageDice)
    {
        this.critDamageDice = critDamageDice;
    }

    public void setCritMin(int critMin)
    {
        this.critMin = critMin;
    }

    public void setDamageBonus(int damageBonus)
    {
        this.damageBonus = damageBonus;
    }

    public void setDamageDice(Dice damageDice)
    {
        this.damageDice = damageDice;
    }

    public void setMagicBonus(int magicBonus)
    {
        this.magicBonus = magicBonus;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setProficient(boolean proficient)
    {
        isProficient = proficient;
    }

    public void setToHit(int toHit)
    {
        this.toHit = toHit;
    }
}
