package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.spigot.character.SpigotNonPlayerSheetStorage;
import io.musician101.mcdndsimple.spigot.character.SpigotPlayerSheetStorage;
import io.musician101.mcdndsimple.spigot.command.CallbackTracker;
import io.musician101.mcdndsimple.spigot.command.MCDNDSimpleCommands;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin<AbstractConfig, SpigotMCDNDSimple> {

    //TODO need to check that all of the list GUIs have the ability to delete objects
    //TODO need to check deprecated methods for other things
    private CallbackTracker callbackTracker;
    private SpigotPlayerSheetStorage characterSheetStorage;
    private SpigotNonPlayerSheetStorage npcStorage;

    public static SpigotMCDNDSimple instance() {
        return getPlugin(SpigotMCDNDSimple.class);
    }

    public CallbackTracker getCallbackTracker() {
        return callbackTracker;
    }

    public SpigotPlayerSheetStorage getCharacterSheetStorage() {
        return characterSheetStorage;
    }

    public SpigotNonPlayerSheetStorage getNPCStorage() {
        return npcStorage;
    }

    @Override
    public void onDisable() {
        characterSheetStorage.save();
    }

    @Override
    public void onEnable() {
        characterSheetStorage = new SpigotPlayerSheetStorage(new File(getDataFolder(), "players"));
        npcStorage = new SpigotNonPlayerSheetStorage(new File(getDataFolder(), "npc"));
        callbackTracker = new CallbackTracker();
        commands.addAll(MCDNDSimpleCommands.commands());
        getLogger().info(Messages.LOAD_COMPLETE);
    }
}
