package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheetStorage;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpongeNonPlayerSheetStorage extends NonPlayerSheetStorage {

    public SpongeNonPlayerSheetStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        data.clear();
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getLogger).ifPresent(logger -> {
            if (files == null) {
                logger.warn("An error occurred while loading PLAYER_NAME character data.");
                return;
            }

            for (File file : files) {
                if (file.isDirectory() || !file.getName().endsWith(".npc")) {
                    continue;
                }

                try {
                    data.add(JsonKeyProcessor.GSON.fromJson(new FileReader(file), NonPlayer.class));
                }
                catch (FileNotFoundException e) {
                    logger.warn("An error occurred while loading " + file.getName());
                }
            }
        });
    }

    @Override
    public void save() {
        data.forEach(nonPlayerSheet -> {
            File file = new File(storageDir, nonPlayerSheet.getName() + ".npc");
            try {
                if (!file.exists()) {
                    storageDir.mkdirs();
                    file.createNewFile();
                }

                JsonKeyProcessor.GSON.toJson(nonPlayerSheet, new FileWriter(file));
            }
            catch (IOException e) {
                SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getLogger).ifPresent(logger -> logger.warn("An error occurred while saving " + file.getName()));
            }
        });
    }
}
