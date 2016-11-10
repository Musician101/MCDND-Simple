package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.CharacterSheet;
import io.musician101.mcdndsimple.common.character.CharacterSheetStorage;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.serialization.SpongeMCDNDDeserializer;
import io.musician101.mcdndsimple.sponge.serialization.SpongeMCDNDSerializer;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.persistence.DataTranslators;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.UUID;

public class SpongeCharacterSheetStorage extends CharacterSheetStorage<SpongeMCDNDSerializer, SpongeMCDNDDeserializer, DataContainer>
{
    public SpongeCharacterSheetStorage(File storageDir)
    {
        super(storageDir, new SpongeMCDNDSerializer(), new SpongeMCDNDDeserializer());
    }

    @Override
    public void load()
    {
        //noinspection ResultOfMethodCallIgnored
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = SpongeMCDNDSimple.getPluginContainer().getLogger();
        if (files == null)
        {
            logger.warn("An error occurred while loading player character data.");
            return;
        }

        for (File file : files)
        {
            if (file.isDirectory() || !file.getName().endsWith(".cfg"))
                continue;

            try
            {
                ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
                ConfigurationNode cn = loader.load();
                UUID uuid = UUID.fromString(file.getName().replace(".cfg", ""));
                map.put(uuid, deserialize(DataTranslators.CONFIGURATION_NODE.translate(cn)));
            }
            catch (IOException e)//NOSONAR
            {
                logger.warn("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save()
    {
        for (Entry<UUID, CharacterSheet> entry : map.entrySet())
        {
            File file = new File(storageDir, entry.getKey().toString() + ".cfg");
            try
            {
                ConfigurationNode cn = DataTranslators.CONFIGURATION_NODE.translate(serialize(entry.getValue()));
                ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build();
                loader.save(cn);
            }
            catch (IOException e)//NOSONAR
            {
                SpongeMCDNDSimple.getPluginContainer().getLogger().warn("An error occurred while saving " + file.getName());
            }
        }
    }
}
