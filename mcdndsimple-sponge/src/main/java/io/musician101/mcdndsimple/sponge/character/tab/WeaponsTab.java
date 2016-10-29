package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.sponge.character.weapon.RangedWeapon;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WeaponsTab implements DataSerializable
{
    private List<MeleeWeapon> meleeWeapons = new ArrayList<>();
    private List<RangedWeapon> rangedWeapons = new ArrayList<>();

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
                .set(MCDNDSimpleKeys.MELEE_WEAPONS, DataUtils.serialize(meleeWeapons))
                .set(MCDNDSimpleKeys.RANGED_WEAPONS, DataUtils.serialize(rangedWeapons));
    }

    public List<MeleeWeapon> getMeleeWeapons()
    {
        return meleeWeapons;
    }

    public List<RangedWeapon> getRangedWeapons()
    {
        return rangedWeapons;
    }

    public void setMeleeWeapons(List<MeleeWeapon> meleeWeapons)
    {
        this.meleeWeapons = meleeWeapons;
    }

    public void setRangedWeapons(List<RangedWeapon> rangedWeapons)
    {
        this.rangedWeapons = rangedWeapons;
    }
}
