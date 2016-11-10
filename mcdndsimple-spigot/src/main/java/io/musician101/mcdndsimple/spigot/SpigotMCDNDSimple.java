package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.spigot.character.SpigotCharacterSheetStorage;
import io.musician101.musicianlibrary.java.minecraft.spigot.AbstractSpigotPlugin;

import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin
{
    private SpigotCharacterSheetStorage characterSheetStorage;

    @Override
    public void onEnable()
    {
        characterSheetStorage = new SpigotCharacterSheetStorage(new File(getDataFolder(), "players"));
    }

    @Override
    public void onDisable()
    {
        characterSheetStorage.save();
    }

    public SpigotCharacterSheetStorage getCharacterSheetStorage()
    {
        return characterSheetStorage;
    }

    public static SpigotMCDNDSimple instance()
    {
        return getPlugin(SpigotMCDNDSimple.class);
    }
}
