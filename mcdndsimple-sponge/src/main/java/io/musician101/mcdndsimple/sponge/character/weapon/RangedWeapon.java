package io.musician101.mcdndsimple.sponge.character.weapon;

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
}
