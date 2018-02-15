package io.musician101.mcdndsimple.spigot.character;

import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheetStorage;
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

public class SpigotNonPlayerSheetStorage extends NonPlayerSheetStorage {

    public SpigotNonPlayerSheetStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        data.clear();
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = SpigotMCDNDSimple.instance().getLogger();
        if (files == null) {
            logger.warning("An error occurred while loading player character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".npc")) {
                continue;
            }

            try {
                SimpleEntry<NonPlayer, List<UUID>> entry = JsonKeyProcessor.GSON.fromJson(new FileReader(file), NonPlayerSheetStorage.Serializer.ENTRY_CLASS.getType());
                data.putAll(entry.getKey(), entry.getValue());
            }
            catch (FileNotFoundException e) {
                logger.warning("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        storageDir.mkdirs();
        data.keys().forEach(nonPlayerSheet -> {
            File file = new File(storageDir, nonPlayerSheet.getName() + ".npc");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }

                JsonKeyProcessor.GSON.toJson(new SimpleEntry<>(nonPlayerSheet, data.get(nonPlayerSheet)), new FileWriter(file));
            }
            catch (IOException e) {
                SpigotMCDNDSimple.instance().getLogger().warning("An error occurred while saving " + file.getName());
            }
        });
    }
}
