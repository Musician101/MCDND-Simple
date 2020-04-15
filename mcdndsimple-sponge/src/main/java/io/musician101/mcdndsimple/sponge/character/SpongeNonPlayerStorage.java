package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerStorage;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

public class SpongeNonPlayerStorage extends NonPlayerStorage {

    public SpongeNonPlayerStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        characters.clear();
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = SpongeMCDNDSimple.instance().getLogger();
        if (files == null) {
            logger.warn("An error occurred while loading PLAYER_NAME character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".npc")) {
                continue;
            }

            try {
                characters.add(Keys.GSON.fromJson(new FileReader(file), NonPlayer.class));
            }
            catch (FileNotFoundException e) {
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

                Keys.GSON.toJson(nonPlayerSheet, new FileWriter(file));
            }
            catch (IOException e) {
                SpongeMCDNDSimple.instance().getLogger().warn("An error occurred while saving " + file.getName());
            }
        });
    }

    @Override
    protected boolean isDM(@Nonnull UUID uuid) {
        return Sponge.getServer().getPlayer(uuid).filter(p -> p.hasPermission(Permissions.DM)).isPresent();
    }
}
