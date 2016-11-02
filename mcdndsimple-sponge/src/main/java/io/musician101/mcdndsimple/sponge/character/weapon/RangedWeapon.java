package io.musician101.mcdndsimple.sponge.character.weapon;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.Dice;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;

import javax.annotation.Nonnull;

public class RangedWeapon extends AbstractWeapon
{
    private int ammo = 0;

    public RangedWeapon()
    {
        setAttackStat("DEX");
    }

    public int getAmmo()
    {
        return ammo;
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
        return super.toContainer()
                .set(MCDNDSimpleKeys.AMMO, ammo);
    }

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }

    public static RangedWeapon fromDataContainer(DataContainer dataContainer)
    {
        RangedWeapon rangedWeapon = new RangedWeapon();
        dataContainer.getInt(MCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(rangedWeapon::setAmmo);
        dataContainer.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(rangedWeapon::setProficient);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data -> rangedWeapon.setCritDamageDice(Dice.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data -> rangedWeapon.setDamageDice(Dice.fromDataContainer(data)));
        dataContainer.getInt(MCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(rangedWeapon::setCritMin);
        dataContainer.getInt(MCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(rangedWeapon::setDamageBonus);
        dataContainer.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(rangedWeapon::setMagicBonus);
        dataContainer.getInt(MCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(rangedWeapon::setToHit);
        dataContainer.getString(MCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(rangedWeapon::setAttackStat);
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(rangedWeapon::setName);
        return rangedWeapon;
    }
}
