package io.musician101.mcdndsimple.spigot.character;

import io.musician101.mcdndsimple.common.character.player.PlayerSheetStorage;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.serialization.SpigotMCDNDDeserializer;
import io.musician101.mcdndsimple.spigot.serialization.SpigotMCDNDSerializer;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SpigotPlayerSheetStorage extends PlayerSheetStorage<MemoryConfiguration, SpigotMCDNDDeserializer, SpigotMCDNDSerializer> {

    public SpigotPlayerSheetStorage(File storageDir) {
        super(storageDir, new SpigotMCDNDSerializer(), new SpigotMCDNDDeserializer());
    }

    @Override
    public void load() {
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = SpigotMCDNDSimple.instance().getLogger();
        if (files == null) {
            logger.warning("An error occurred while loading player character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".yml")) {
                continue;
            }

            try {
                YamlConfiguration yaml = new YamlConfiguration();
                yaml.load(file);
                data.putAll(deserialize(yaml), yaml.getStringList(Keys.CONTROLLERS).stream().map(UUID::fromString).collect(Collectors.toList()));
            }
            catch (InvalidConfigurationException | IOException e) {
                logger.warning("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        data.keys().forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".conf");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                YamlConfiguration yaml = new YamlConfiguration();
                yaml.set(Keys.PLAYER_SHEET, serialize(playerSheet));
                yaml.set(Keys.CONTROLLERS, data.get(playerSheet).stream().map(UUID::toString).collect(Collectors.toList()));
                yaml.save(file);
            }
            catch (IOException e) {
                SpigotMCDNDSimple.instance().getLogger().warning("An error occurred while saving " + file.getName());
            }
        });
    }
}
