package io.musician101.mcdndsimple.spigot.character;

import io.musician101.mcdndsimple.common.character.CharacterSheetStorage;
import io.musician101.mcdndsimple.spigot.serialization.SpigotMCDNDDeserializer;
import io.musician101.mcdndsimple.spigot.serialization.SpigotMCDNDSerializer;
import org.bukkit.configuration.MemoryConfiguration;

import java.io.File;

public class SpigotCharacterSheetStorage extends CharacterSheetStorage<SpigotMCDNDSerializer, SpigotMCDNDDeserializer, MemoryConfiguration>
{
    public SpigotCharacterSheetStorage(File storageDir)
    {
        super(storageDir, new SpigotMCDNDSerializer(), new SpigotMCDNDDeserializer());
    }

    @Override
    public void load()
    {

    }

    @Override
    public void save()
    {

    }
}
