package io.musician101.mcdndsimple.forge;

import io.musician101.mcdndsimple.common.reference.Reference;
import io.musician101.mcdndsimple.common.command.CallbackTracker;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.forge.character.ForgeNonPlayerStorage;
import io.musician101.mcdndsimple.forge.character.ForgePlayerStorage;
import io.musician101.mcdndsimple.forge.command.MCDNDSimpleCommands;
import java.io.File;
import net.minecraft.command.CommandSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class ForgeMCDNDSimple {

    private CallbackTracker<CommandSource> callbackTracker;
    private ForgeNonPlayerStorage nonPlayerSheetStorage;
    private ForgePlayerStorage playerSheetStorage;
    private final Logger logger = LogManager.getLogger(Reference.MOD_ID);

    public ForgeMCDNDSimple() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        MinecraftForge.EVENT_BUS.addListener(this::serverStart);
        MinecraftForge.EVENT_BUS.addListener(this::serverStop);
    }

    public static ForgeMCDNDSimple getInstance() {
        return ModList.get().<ForgeMCDNDSimple>getModObjectById(Reference.MOD_ID).orElseThrow(() -> new IllegalStateException("Mod " + Reference.MOD_ID + " does not exist."));
    }

    public CallbackTracker<CommandSource> getCallbackTracker() {
        return callbackTracker;
    }

    public ForgeNonPlayerStorage getNonPlayerSheetStorage() {
        return nonPlayerSheetStorage;
    }

    public ForgePlayerStorage getPlayerSheetStorage() {
        return playerSheetStorage;
    }

    private void serverStop(FMLServerStoppingEvent event) {
        nonPlayerSheetStorage.save();
        playerSheetStorage.save();
    }

    private void preInit(FMLCommonSetupEvent event) {
        File configDir = new File("config", Reference.MOD_ID);
        playerSheetStorage = new ForgePlayerStorage(new File(configDir, "players"));
        nonPlayerSheetStorage = new ForgeNonPlayerStorage(new File(configDir, "npc"));
        callbackTracker = new CallbackTracker<>();
        getLogger().info(Messages.LOAD_COMPLETE);
    }

    private void serverStart(FMLServerStartingEvent event) {
        MCDNDSimpleCommands.commands().forEach(event.getCommandDispatcher()::register);
    }

    public Logger getLogger() {
        return logger;
    }
}
