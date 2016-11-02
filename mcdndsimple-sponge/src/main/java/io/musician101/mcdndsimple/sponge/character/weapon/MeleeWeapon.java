package io.musician101.mcdndsimple.sponge.character.weapon;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.Dice;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;

import javax.annotation.Nonnull;

public class MeleeWeapon extends AbstractWeapon
{
    private boolean plusStat = true;

    public MeleeWeapon()
    {
        setAttackStat("STR");
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
                .set(MCDNDSimpleKeys.PLUS_STAT, plusStat);
    }

    public boolean isPlusStat()
    {
        return plusStat;
    }

    public void setPlusStat(boolean plusStat)
    {
        this.plusStat = plusStat;
    }

    public static MeleeWeapon fromDataContainer(DataContainer dataContainer)
    {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        dataContainer.getBoolean(MCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(meleeWeapon::setPlusStat);
        dataContainer.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(meleeWeapon::setProficient);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data -> meleeWeapon.setCritDamageDice(Dice.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data -> meleeWeapon.setDamageDice(Dice.fromDataContainer(data)));
        dataContainer.getInt(MCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(meleeWeapon::setCritMin);
        dataContainer.getInt(MCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(meleeWeapon::setDamageBonus);
        dataContainer.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(meleeWeapon::setMagicBonus);
        dataContainer.getInt(MCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(meleeWeapon::setToHit);
        dataContainer.getString(MCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(meleeWeapon::setAttackStat);
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(meleeWeapon::setName);
        return meleeWeapon;
    }
}
