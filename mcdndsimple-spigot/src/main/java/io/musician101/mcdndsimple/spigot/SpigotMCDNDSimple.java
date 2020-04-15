package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.character.SpigotNonPlayerStorage;
import io.musician101.mcdndsimple.spigot.character.SpigotPlayerStorage;
import io.musician101.mcdndsimple.spigot.command.CallbackTracker;
import io.musician101.mcdndsimple.spigot.command.MCDNDSimpleCommands;
import io.musician101.musicianlibrary.java.minecraft.common.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin<AbstractConfig, SpigotMCDNDSimple> {

    private CallbackTracker callbackTracker;
    private SpigotNonPlayerStorage nonPlayerSheetStorage;
    private SpigotPlayerStorage playerSheetStorage;

    public static SpigotMCDNDSimple instance() {
        return getPlugin(SpigotMCDNDSimple.class);
    }

    public CallbackTracker getCallbackTracker() {
        return callbackTracker;
    }

    public SpigotNonPlayerStorage getNonPlayerStorage() {
        return nonPlayerSheetStorage;
    }

    public SpigotPlayerStorage getPlayerStorage() {
        return playerSheetStorage;
    }

    @Override
    public void onDisable() {
        playerSheetStorage.save();
    }

    @Override
    public void onEnable() {
        playerSheetStorage = new SpigotPlayerStorage(new File(getDataFolder(), "players"));
        nonPlayerSheetStorage = new SpigotNonPlayerStorage(new File(getDataFolder(), "npc"));
        callbackTracker = new CallbackTracker();
        commands.addAll(MCDNDSimpleCommands.commands());
        getLogger().info(Messages.LOAD_COMPLETE);
    }
}
