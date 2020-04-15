package io.musician101.mcdndsimple.forge.character;

import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerStorage;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.forge.ForgeMCDNDSimple;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Logger;

public class ForgeNonPlayerStorage extends NonPlayerStorage {

    public ForgeNonPlayerStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        characters.clear();
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = ForgeMCDNDSimple.getInstance().getLogger();
        if (files == null) {
            logger.warn("An error occurred while loading PLAYER_NAME character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".npc")) {
                continue;
            }

            try(FileReader fr = new FileReader(file)) {
                characters.add(Keys.GSON.fromJson(fr, NonPlayer.class));
            }
            catch (IOException e) {
                logger.warn("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        characters.forEach(nonPlayerSheet -> {
            File file = new File(storageDir, nonPlayerSheet.getName() + ".npc");
            try {
                if (!file.exists()) {
                    storageDir.mkdirs();
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file);
                Keys.GSON.toJson(nonPlayerSheet, fw);
                fw.close();
            }
            catch (IOException e) {
                ForgeMCDNDSimple.getInstance().getLogger().warn("An error occurred while saving " + file.getName());
            }
        });
    }
}
