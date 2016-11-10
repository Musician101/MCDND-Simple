package io.musician101.mcdndsimple.common.character.bonus;

import io.musician101.mcdndsimple.common.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class SpellcastingBonus
{
    private int attack = 0;
    private int damage = 0;
    private int saveDC = 0;

    public int getAttack()
    {
        return attack;
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
                .set(MCDNDSimpleKeys.ATTACK, attack)
                .set(MCDNDSimpleKeys.DAMAGE, damage)
                .set(MCDNDSimpleKeys.SAVE_DC, saveDC);
    }

    public int getDamage()
    {
        return damage;
    }

    public int getSaveDC()
    {
        return saveDC;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public void setSaveDC(int saveDC)
    {
        this.saveDC = saveDC;
    }

    public static SpellcastingBonus fromDataContainer(DataContainer dataContainer)
    {
        SpellcastingBonus meleeBonus = new SpellcastingBonus();
        dataContainer.getInt(MCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(meleeBonus::setAttack);
        dataContainer.getInt(MCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(meleeBonus::setDamage);
        dataContainer.getInt(MCDNDSimpleKeys.SAVE_DC.getQuery()).ifPresent(meleeBonus::setSaveDC);
        return meleeBonus;
    }
}
