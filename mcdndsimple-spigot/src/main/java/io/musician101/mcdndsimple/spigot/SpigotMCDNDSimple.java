package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.spigot.character.SpigotCharacterSheetStorage;
import io.musician101.mcdndsimple.spigot.command.CallbackTracker;
import io.musician101.mcdndsimple.spigot.command.MCDNDSimpleCommands;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin<AbstractConfig, SpigotMCDNDSimple> {

    //TODO need to check that all of the list GUIs have the ability to delete objects
    //TODO need to check deprecated methods for other things
    private CallbackTracker callbackTracker;
    private SpigotCharacterSheetStorage characterSheetStorage;

    public static SpigotMCDNDSimple instance() {
        return getPlugin(SpigotMCDNDSimple.class);
    }

    public CallbackTracker getCallbackTracker() {
        return callbackTracker;
    }

    public SpigotCharacterSheetStorage getCharacterSheetStorage() {
        return characterSheetStorage;
    }

    @Override
    public void onDisable() {
        characterSheetStorage.save();
    }

    @Override
    public void onEnable() {
        characterSheetStorage = new SpigotCharacterSheetStorage(new File(getDataFolder(), "players"));
        callbackTracker = new CallbackTracker();
        commands.addAll(MCDNDSimpleCommands.commands());
        getLogger().info(Messages.LOAD_COMPLETE);
    }
}
