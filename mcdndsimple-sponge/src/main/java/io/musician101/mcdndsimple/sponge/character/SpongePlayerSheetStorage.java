package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.PlayerSheetStorage;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpongePlayerSheetStorage extends PlayerSheetStorage {

    public SpongePlayerSheetStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getLogger).ifPresent(logger -> {
            storageDir.mkdirs();
            File[] files = storageDir.listFiles();
            if (files == null) {
                logger.warn("An error occurred while loading player character data.");
                return;
            }

            for (File file : files) {
                if (file.isDirectory() || !file.getName().endsWith(".conf")) {
                    continue;
                }

                try {
                    data.add(JsonKeyProcessor.GSON.fromJson(new FileReader(file), PlayerSheet.class));
                }
                catch (FileNotFoundException e) {
                    logger.warn("An error occurred while loading " + file.getName());
                }
            }
        });
    }

    @Override
    public void save() {
        SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getLogger).ifPresent(logger -> data.forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".conf");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                JsonKeyProcessor.GSON.toJson(playerSheet, new FileWriter(file));
            }
            catch (IOException e) {
                logger.warn("An error occurred while saving " + file.getName());
            }
        }));
    }
}
