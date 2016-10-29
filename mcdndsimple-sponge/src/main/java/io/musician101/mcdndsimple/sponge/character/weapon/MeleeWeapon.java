package io.musician101.mcdndsimple.sponge.character.weapon;

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
}
