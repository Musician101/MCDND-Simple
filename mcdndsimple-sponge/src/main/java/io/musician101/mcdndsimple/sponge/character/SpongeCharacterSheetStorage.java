package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.common.character.CharacterSheetStorage;
import io.musician101.mcdndsimple.common.serialization.MCDNDSimpleKeys;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.serialization.SpongeMCDNDDeserializer;
import io.musician101.mcdndsimple.sponge.serialization.SpongeMCDNDSerializer;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.slf4j.Logger;

public class SpongeCharacterSheetStorage extends CharacterSheetStorage<SpongeMCDNDSerializer, SpongeMCDNDDeserializer, ConfigurationNode> {

    public SpongeCharacterSheetStorage(File storageDir) {
        super(storageDir, new SpongeMCDNDSerializer(), new SpongeMCDNDDeserializer());
    }

    @Override
    public void load() {
        storageDir.mkdirs();
        File[] files = storageDir.listFiles();
        Logger logger = SpongeMCDNDSimple.instance().getLogger();
        if (files == null) {
            logger.warn("An error occurred while loading player character data.");
            return;
        }

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".conf")) {
                continue;
            }

            try {
                HoconConfigurationLoader loader = HoconConfigurationLoader.builder().setFile(file).build();
                ConfigurationNode cn = loader.load();
                playerSheets.putAll(deserialize(cn), cn.getList(object -> UUID.fromString(object.toString())));
            }
            catch (IOException e) {
                logger.warn("An error occurred while loading " + file.getName());
            }
        }
    }

    @Override
    public void save() {
        playerSheets.keys().forEach(playerSheet -> {
            File file = new File(storageDir, playerSheet.getName() + ".conf");
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                ConfigurationNode cn = SimpleConfigurationNode.root();
                cn.getNode(MCDNDSimpleKeys.PLAYER_SHEET).setValue(cn);
                cn.getNode(MCDNDSimpleKeys.CONTROLLERS).setValue(playerSheets.get(playerSheet).stream().map(UUID::toString).collect(Collectors.toList()));
                HoconConfigurationLoader loader = HoconConfigurationLoader.builder().setFile(file).build();
                loader.save(cn);
            }
            catch (IOException e) {
                SpongeMCDNDSimple.instance().getLogger().warn("An error occurred while saving " + file.getName());
            }
        });
    }
}
