package io.musician101.mcdndsimple.sponge;

import com.google.inject.Inject;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.Reference.Commands;
import io.musician101.mcdndsimple.sponge.character.SpongeNonPlayerStorage;
import io.musician101.mcdndsimple.sponge.character.SpongePlayerStorage;
import io.musician101.mcdndsimple.sponge.command.args.CharacterSheetCommandElement;
import io.musician101.musicianlibrary.java.minecraft.common.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.plugin.AbstractSpongePlugin;
import java.io.File;
import javax.annotation.Nonnull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "mcdndsimple", name = "MCDND-Simple", version = "${project.version}", description = "Test", authors = {"Musician101"}, dependencies = {@Dependency(id = "spongemusicianlibrary")})
public class SpongeMCDNDSimple extends AbstractSpongePlugin<AbstractConfig> {

    @ConfigDir(sharedRoot = false)
    @Inject
    private File configDir;
    private SpongeNonPlayerStorage nonPlayerSheetStorage;
    private SpongePlayerStorage playerSheetStorage;
    @Inject
    private PluginContainer pluginContainer;

    public static SpongeMCDNDSimple instance() {
        return Sponge.getPluginManager().getPlugin("mcdndsimple").flatMap(PluginContainer::getInstance).filter(SpongeMCDNDSimple.class::isInstance).map(SpongeMCDNDSimple.class::cast).orElseThrow(() -> new IllegalStateException("MCDND has not been enabled."));
    }

    public SpongeNonPlayerStorage getNonPlayerStorage() {
        return nonPlayerSheetStorage;
    }

    public SpongePlayerStorage getPlayerStorage() {
        return playerSheetStorage;
    }

    @Nonnull
    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    @Listener
    public void init(GameInitializationEvent event) {
        Sponge.getCommandManager().register(this, CommandSpec.builder().arguments(new CharacterSheetCommandElement()).executor((src, args) -> args.<MCDNDPlayer>getOne(Commands.NAME).map(playerSheet -> {
            //TODO need to fix this along with other things
            //new PlayerSheetGUI((Player) src, playerSheet, null);
            return CommandResult.success();
        }).orElse(CommandResult.empty())).build(), "character");
    }

    @Listener
    public void preInit(GamePreInitializationEvent event) {
        playerSheetStorage = new SpongePlayerStorage(new File(configDir, "character_storage"));
        nonPlayerSheetStorage = new SpongeNonPlayerStorage(new File(configDir, "character_storage"));
    }

    @Listener
    public void serverStop(GameStoppingServerEvent event) {
        playerSheetStorage.save();
    }
}
