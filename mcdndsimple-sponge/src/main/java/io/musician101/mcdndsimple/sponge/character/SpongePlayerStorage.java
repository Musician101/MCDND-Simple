package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerStorage;
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

public class SpongePlayerStorage extends PlayerStorage {

    public SpongePlayerStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    public void load() {
        Logger logger = SpongeMCDNDSimple.instance().getLogger();
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        if (files == null) {
            logger.warn("An error occurred while loading player character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".json")) {
                continue;
            }

            try {
                characters.add(Keys.GSON.fromJson(new FileReader(file), MCDNDPlayer.class));
            }
            catch (FileNotFoundException e) {
                logger.warn("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        characters.forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".json");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                Keys.GSON.toJson(playerSheet, new FileWriter(file));
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
