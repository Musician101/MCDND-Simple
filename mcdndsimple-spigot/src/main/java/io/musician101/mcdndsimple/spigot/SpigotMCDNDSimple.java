package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.character.SpigotNonPlayerSheetStorage;
import io.musician101.mcdndsimple.spigot.character.SpigotPlayerSheetStorage;
import io.musician101.mcdndsimple.spigot.command.CallbackTracker;
import io.musician101.mcdndsimple.spigot.command.MCDNDSimpleCommands;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin<AbstractConfig, SpigotMCDNDSimple> {

    private CallbackTracker callbackTracker;
    private SpigotNonPlayerSheetStorage nonPlayerSheetStorage;
    private SpigotPlayerSheetStorage playerSheetStorage;

    public static SpigotMCDNDSimple instance() {
        return getPlugin(SpigotMCDNDSimple.class);
    }

    public CallbackTracker getCallbackTracker() {
        return callbackTracker;
    }

    public SpigotNonPlayerSheetStorage getNonPlayerSheetStorage() {
        return nonPlayerSheetStorage;
    }

    public SpigotPlayerSheetStorage getPlayerSheetStorage() {
        return playerSheetStorage;
    }

    @Override
    public void onDisable() {
        playerSheetStorage.save();
    }

    @Override
    public void onEnable() {
        playerSheetStorage = new SpigotPlayerSheetStorage(new File(getDataFolder(), "players"));
        nonPlayerSheetStorage = new SpigotNonPlayerSheetStorage(new File(getDataFolder(), "npc"));
        callbackTracker = new CallbackTracker();
        commands.addAll(MCDNDSimpleCommands.commands());
        getLogger().info(Messages.LOAD_COMPLETE);
    }
}
