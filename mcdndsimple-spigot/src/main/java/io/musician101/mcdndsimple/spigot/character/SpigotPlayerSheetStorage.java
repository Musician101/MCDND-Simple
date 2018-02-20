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
            logger.warning("An error occurred while loading PLAYER_NAME character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".pc")) {
                continue;
            }

            try {
                data.add(JsonKeyProcessor.GSON.fromJson(new FileReader(file), PlayerSheet.class));
            }
            catch (FileNotFoundException e) {
                logger.warning("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        data.forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".pc");
            try {
                if (!file.exists()) {
                    storageDir.mkdirs();
                    file.createNewFile();
                }

                JsonKeyProcessor.GSON.toJson(playerSheet, new FileWriter(file));
            }
            catch (IOException e) {
                SpigotMCDNDSimple.instance().getLogger().warning("An error occurred while saving " + file.getName());
            }
        });
    }
}
