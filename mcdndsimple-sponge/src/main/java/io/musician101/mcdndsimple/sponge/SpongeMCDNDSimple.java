package io.musician101.mcdndsimple.sponge;

import com.google.inject.Inject;
import io.musician101.mcdndsimple.common.Reference.Commands;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.sponge.character.SpongeCharacterSheetStorage;
import io.musician101.mcdndsimple.sponge.command.args.CharacterSheetCommandElement;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.PlayerSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.plugin.AbstractSpongePlugin;
import java.io.File;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "mcdndsimple", name = "MCDND-Simple", version = "${project.version}", description = "Test", authors = {"Musician101"}, dependencies = {@Dependency(id = "spongemusicianlibrary")})
public class SpongeMCDNDSimple extends AbstractSpongePlugin<AbstractConfig> {

    private static SpongeMCDNDSimple instance;

    @ConfigDir(sharedRoot = false)
    @Inject
    private File configDir;
    @Inject
    private PluginContainer pluginContainer;

    private SpongeCharacterSheetStorage characterSheetStorage;

    public static SpongeMCDNDSimple instance() {
        return instance;
    }

    public SpongeCharacterSheetStorage getCharacterSheetStorage() {
        return characterSheetStorage;
    }

    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    @Listener
    public void construct(GameConstructionEvent event) {
        instance = this;
    }

    @Listener
    public void preInit(GamePreInitializationEvent event) {
        characterSheetStorage = new SpongeCharacterSheetStorage(new File(configDir, "character_storage"));
    }

    @Listener
    public void init(GameInitializationEvent event) {
        Sponge.getCommandManager().register(this, CommandSpec.builder().arguments(new CharacterSheetCommandElement()).executor((src, args) -> args.<PlayerSheet>getOne(Commands.NAME).map(playerSheet -> {
            new PlayerSheetGUI((Player) src, playerSheet, null);
            return CommandResult.success();
        }).orElse(CommandResult.empty())).build(), "character");
    }

    @Listener
    public void serverStop(GameStoppingServerEvent event) {
        characterSheetStorage.save();
    }
}
