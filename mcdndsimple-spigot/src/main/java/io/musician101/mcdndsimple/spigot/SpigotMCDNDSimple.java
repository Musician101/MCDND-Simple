package io.musician101.mcdndsimple.spigot;

import io.musician101.mcdndsimple.spigot.character.SpigotCharacterSheetStorage;
import io.musician101.mcdndsimple.spigot.command.CallbackTracker;
import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import java.io.File;

public class SpigotMCDNDSimple extends AbstractSpigotPlugin {

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
    }
}
