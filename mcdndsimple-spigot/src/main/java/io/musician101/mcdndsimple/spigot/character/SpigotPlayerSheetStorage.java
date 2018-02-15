package io.musician101.mcdndsimple.spigot.character;

import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.PlayerSheetStorage;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class SpigotPlayerSheetStorage extends PlayerSheetStorage {

    public SpigotPlayerSheetStorage(File storageDir) {
        super(storageDir);
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
            if (file.isDirectory() || !file.getName().endsWith(".pc")) {
                continue;
            }

            try {
                SimpleEntry<PlayerSheet, List<UUID>> entry = JsonKeyProcessor.GSON.fromJson(new FileReader(file), PlayerSheetStorage.Serializer.ENTRY_CLASS.getType());
                data.putAll(entry.getKey(), entry.getValue());
            }
            catch (FileNotFoundException e) {
                logger.warning("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        data.keys().forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".pc");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                JsonKeyProcessor.GSON.toJson(new SimpleEntry<>(playerSheet, data.get(playerSheet)), new FileWriter(file));
            }
            catch (IOException e) {
                SpigotMCDNDSimple.instance().getLogger().warning("An error occurred while saving " + file.getName());
            }
        });
    }
}
