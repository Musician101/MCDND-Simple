package io.musician101.mcdndsimple.spigot.character;

import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerStorage;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;

public class SpigotPlayerStorage extends PlayerStorage {

    public SpigotPlayerStorage(File storageDir) {
        super(storageDir);
    }

    @Override
    protected boolean isDM(@Nonnull UUID uuid) {
        return Bukkit.getPlayer(uuid).hasPermission(Permissions.DM);
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
                characters.add(Keys.GSON.fromJson(new FileReader(file), MCDNDPlayer.class));
            }
            catch (FileNotFoundException e) {
                logger.warning("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        characters.forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".pc");
            try {
                if (!file.exists()) {
                    storageDir.mkdirs();
                    file.createNewFile();
                }

                Keys.GSON.toJson(playerSheet, new FileWriter(file));
            }
            catch (IOException e) {
                SpigotMCDNDSimple.instance().getLogger().warning("An error occurred while saving " + file.getName());
            }
        });
    }
}
