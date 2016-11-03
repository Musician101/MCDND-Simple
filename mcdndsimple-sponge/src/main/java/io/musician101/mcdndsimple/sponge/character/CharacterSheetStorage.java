package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.data.persistence.DataTranslators;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class CharacterSheetStorage
{
    private final File storageDir;
    private final Map<UUID, CharacterSheet> map = new HashMap<>();

    public CharacterSheetStorage(File storageDir)
    {
        this.storageDir = storageDir;
        load();
    }

    public CharacterSheet getCharacterSheet(UUID uuid)
    {
        return map.containsKey(uuid) ? map.get(uuid) : map.put(uuid, new CharacterSheet());
    }

    public Map<UUID, CharacterSheet> getCharacterSheets()
    {
        return map;
    }

    public void createNewCharacterSheet(UUID uuid)
    {
        if (!map.containsKey(uuid))
            map.put(uuid, new CharacterSheet());
    }

    public void removeCharacterSheet(UUID uuid)
    {
        map.remove(uuid);
    }

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
                map.put(uuid, CharacterSheet.fromDataContainer(DataTranslators.CONFIGURATION_NODE.translate(cn)));
            }
            catch (IOException e)//NOSONAR
            {
                logger.warn("An error occurred while loading " + file.getName());
            }
        }
    }

    public void save()
    {
        for (Entry<UUID, CharacterSheet> entry : map.entrySet())
        {
            File file = new File(storageDir, entry.getKey().toString() + ".cfg");
            try
            {
                ConfigurationNode cn = DataTranslators.CONFIGURATION_NODE.translate(entry.getValue().toContainer());
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
