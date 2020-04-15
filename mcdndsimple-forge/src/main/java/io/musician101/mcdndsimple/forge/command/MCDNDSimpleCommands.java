package io.musician101.mcdndsimple.forge.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.reference.Reference;
import io.musician101.mcdndsimple.forge.ForgeMCDNDSimple;
import io.musician101.mcdndsimple.forge.gui.npc.NonPlayersGui;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

public class MCDNDSimpleCommands {

    private MCDNDSimpleCommands() {

    }

    private static LiteralArgumentBuilder<CommandSource> callback() {
        return Commands.literal(Reference.Commands.CALLBACK_NAME).then(Commands.argument("uuid", StringArgumentType.word()).executes(context -> {
            CommandSource source = context.getSource();
            try {
                Consumer<CommandSource> callback = ForgeMCDNDSimple.getInstance().getCallbackTracker().get(UUID.fromString(context.getArgument("uuid", String.class)));
                if (callback == null) {
                    source.sendFeedback(new TextComponentString(Messages.PREFIX + "A callback with that ID doesn't exist. Callbacks expire after 10 minutes.").setStyle(new Style().setColor(TextFormatting.RED)), true);
                    return 0;
                }

                callback.accept(source);
                return 1;
            }
            catch (IllegalArgumentException e) {
                source.sendFeedback(new TextComponentString(Messages.PREFIX + "The given input was not a valid UUID.").setStyle(new Style().setColor(TextFormatting.RED)), true);
                return 0;
            }
        }).suggests((context, builder) -> ISuggestionProvider.suggest(ForgeMCDNDSimple.getInstance().getCallbackTracker().getKeys().stream().map(UUID::toString), builder)));
    }

    public static List<LiteralArgumentBuilder<CommandSource>> commands() {
        return Arrays.asList(callback(), nonPlayer(), player(), createCharacter());
    }

    private static LiteralArgumentBuilder<CommandSource> createCharacter() {
        return Commands.literal(Reference.Commands.CREATE_COMMAND).requires(source -> source.hasPermissionLevel(3)).then(Commands.argument("character_type", new CharacterTypeArgumentType()).suggests((context, builder) -> ISuggestionProvider.suggest(new String[]{Reference.Commands.NPC_NAME, Reference.Commands.PC_NAME}, builder)).then(Commands.argument("name", StringArgumentType.greedyString()).executes(context -> {
            EntityPlayer player = context.getSource().asPlayer();
            String characterName = context.getArgument("name", String.class);
            ForgeMCDNDSimple plugin = ForgeMCDNDSimple.getInstance();
            switch (context.getArgument("character_type", String.class)) {
                case Reference.Commands.PC_NAME:
                    Optional<MCDNDPlayer> playerSheet = plugin.getPlayerSheetStorage().createNewCharacter(player.getUniqueID(), characterName);
                    if (!playerSheet.isPresent()) {
                        player.sendMessage(new TextComponentString(Messages.CHARACTER_ALREADY_EXISTS).setStyle(new Style().setColor(TextFormatting.RED)));
                        return 0;
                    }

                    player.sendMessage(new TextComponentString(Messages.CHARACTER_CREATED).setStyle(new Style().setColor(TextFormatting.GREEN)));
                    return 1;
                case Reference.Commands.NPC_NAME:
                    Optional<NonPlayer> nonPlayer = plugin.getNonPlayerSheetStorage().createNewCharacter(player.getUniqueID(), characterName);
                    if (!nonPlayer.isPresent()) {
                        player.sendMessage(new TextComponentString(Messages.CHARACTER_ALREADY_EXISTS).setStyle(new Style().setColor(TextFormatting.RED)));
                        return 0;
                    }

                    player.sendMessage(new TextComponentString(Messages.CHARACTER_CREATED).setStyle(new Style().setColor(TextFormatting.GREEN)));
                    return 1;
            }

            return 0;
        })));
    }

    private static LiteralArgumentBuilder<CommandSource> nonPlayer() {
        return Commands.literal(Reference.Commands.NPC_NAME).executes(context -> {
            EntityPlayer player = context.getSource().asPlayer();
            //TODO debug. need to implement proper net code
            if (LogicalSidedProvider.INSTANCE.<MinecraftServer>get(LogicalSide.SERVER).isDedicatedServer()) {

                return 1;
            }

            Minecraft mc = Minecraft.getInstance();
            mc.addScheduledTask(() -> mc.displayGuiScreen(new NonPlayersGui(ForgeMCDNDSimple.getInstance().getNonPlayerSheetStorage().getCharacters().stream().filter(nonPlayer -> nonPlayer.isController(player.getUniqueID()) || player.hasPermissionLevel(3)).map(AbstractPlayer::getName).collect(Collectors.toList()))));
            return 1;
        }).then(Commands.argument("name", StringArgumentType.greedyString()).executes(context -> {
            EntityPlayerMP player = context.getSource().asPlayer();
            Optional<NonPlayer> nonPlayer = ForgeMCDNDSimple.getInstance().getNonPlayerSheetStorage().getCharacter(player.getUniqueID(), context.getArgument("name", String.class));
            if (!nonPlayer.isPresent()) {
                player.sendMessage(new TextComponentString(Messages.CHARACTER_DNE).setStyle(new Style().setColor(TextFormatting.RED)));
                return 0;
            }
            //TODO debug. no gui yet
            player.sendMessage(new TextComponentString("Done"));
            //ForgeChestGUIs.INSTANCE.nonPlayer(player, nonPlayer);
            return 1;
        }));
    }

    private static LiteralArgumentBuilder<CommandSource> player() {
        return Commands.literal(Reference.Commands.PLAYER_NAME).executes(context -> {
            EntityPlayerMP player = context.getSource().asPlayer();
            //ForgeChestGUIs.INSTANCE.players(player, ForgeMCDNDSimple.getInstance().getPlayerSheetStorage().getCharacters().stream().filter(playerSheet -> playerSheet.isController(player.getUniqueID()) || player.hasPermissionLevel(3)).collect(Collectors.toList()), 1);
            return 1;
        }).then(Commands.argument("name", StringArgumentType.greedyString()).executes(context -> {
            EntityPlayerMP player = context.getSource().asPlayer();
            Optional<MCDNDPlayer> playerSheet = ForgeMCDNDSimple.getInstance().getPlayerSheetStorage().getCharacter(player.getUniqueID(), context.getArgument("name", String.class));
            if (!playerSheet.isPresent()) {
                player.sendMessage(new TextComponentString(Messages.CHARACTER_DNE).setStyle(new Style().setColor(TextFormatting.RED)));
                return 0;
            }

            //ForgeChestGUIs.INSTANCE.playerSheet(player, playerSheet);
            return 1;
        }));
    }
}
