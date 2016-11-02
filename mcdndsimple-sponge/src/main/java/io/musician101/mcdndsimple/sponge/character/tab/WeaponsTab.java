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

    public void addMeleeWeapon(MeleeWeapon meleeWeapon)
    {
        meleeWeapons.add(meleeWeapon);
    }

    public void removeMeleeWeapon(MeleeWeapon meleeWeapon)
    {
        meleeWeapons.remove(meleeWeapon);
    }

    public void setRangedWeapons(List<RangedWeapon> rangedWeapons)
    {
        this.rangedWeapons = rangedWeapons;
    }

    public void addRangedWeapon(RangedWeapon rangedWeapon)
    {
        rangedWeapons.add(rangedWeapon);
    }

    public void removeRangedWeapon(RangedWeapon rangedWeapon)
    {
        rangedWeapons.remove(rangedWeapon);
    }

    public static WeaponsTab fromDataContainer(DataContainer dataContainer)
    {
        WeaponsTab weaponsTab = new WeaponsTab();
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.MELEE_WEAPONS).ifPresent(list -> list.forEach(data -> weaponsTab.addMeleeWeapon(MeleeWeapon.fromDataContainer(data))));
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.MELEE_WEAPONS).ifPresent(list -> list.forEach(data -> weaponsTab.addRangedWeapon(RangedWeapon.fromDataContainer(data))));
        return weaponsTab;
    }
}
