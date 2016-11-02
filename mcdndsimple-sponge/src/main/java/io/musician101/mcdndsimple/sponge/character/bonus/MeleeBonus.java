package io.musician101.mcdndsimple.sponge.character.bonus;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class MeleeBonus implements DataSerializable
{
    private int attack = 0;
    private int damage = 0;

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
                .set(MCDNDSimpleKeys.DAMAGE, damage);
    }

    public int getDamage()
    {
        return damage;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public static MeleeBonus fromDataContainer(DataContainer dataContainer)
    {
        MeleeBonus meleeBonus = new MeleeBonus();
        dataContainer.getInt(MCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(meleeBonus::setAttack);
        dataContainer.getInt(MCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(meleeBonus::setDamage);
        return meleeBonus;
    }
}
