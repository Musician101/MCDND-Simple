package io.musician101.mcdndsimple.sponge;

import io.musician101.mcdndsimple.sponge.character.SpongeCharacterSheetStorage;
import io.musician101.musicianlibrary.java.minecraft.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.AbstractSpongePlugin;
import java.io.File;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "mcdndsimple", name = "MCDND-Simple", version = "${project.version}", description = "Test", authors = {"Musician101"}, dependencies = {@Dependency(id = "spongemusicianlibrary")})
public class SpongeMCDNDSimple extends AbstractSpongePlugin<AbstractConfig> {

    private SpongeCharacterSheetStorage characterSheetStorage;

    public static PluginContainer getPluginContainer() {
        //noinspection OptionalGetWithoutIsPresent
        return Sponge.getPluginManager().getPlugin("mcdndsimple").get();
    }

    public static SpongeMCDNDSimple instance() {
        //noinspection OptionalGetWithoutIsPresent
        return (SpongeMCDNDSimple) getPluginContainer().getInstance().get();
    }

    public SpongeCharacterSheetStorage getCharacterSheetStorage() {
        return characterSheetStorage;
    }

    @Override
    public Logger getLogger() {
        return getPluginContainer().getLogger();
    }

    @Override
    public void preInit(GamePreInitializationEvent event) {
        characterSheetStorage = new SpongeCharacterSheetStorage(new File("mods/mcdndsimple/players"));
    }

    public void serverStop(GameStoppingServerEvent event) {
        characterSheetStorage.save();
    }
}
