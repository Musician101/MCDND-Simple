package io.musician101.mcdndsimple.spigot.command;

import io.musician101.mcdndsimple.common.Reference.Commands;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.Reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotChestGUIs;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommand;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandUsage;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCDNDSimpleCommands {

    private MCDNDSimpleCommands() {

    }

    private static SpigotCommand<SpigotMCDNDSimple> callback() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.CALLBACK_NAME).description(Commands.CALLBACK_DESC).usage(SpigotCommandUsage.builder().minArgs(1).addArgument(SpigotCommandArgument.of(Commands.UUID)).build()).permissions(SpigotCommandPermissions.builder().permissionNode(Permissions.CALLBACK).isPlayerOnly(true).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build()).function((sender, args) -> {
            try {
                Consumer<CommandSender> callback = SpigotMCDNDSimple.instance().getCallbackTracker().get(UUID.fromString(args.get(0)));
                if (callback == null) {
                    sender.sendMessage(ChatColor.RED + Messages.PREFIX + "A callback with that ID doesn't exist. Callbacks expire after 10 minutes.");
                    return false;
                }

                callback.accept(sender);
                return true;
            }
            catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + Messages.PREFIX + "The given input was not a valid UUID.");
            }

            return false;
        }).build(SpigotMCDNDSimple.instance());
    }

    private static SpigotCommand<SpigotMCDNDSimple> character() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.CHARACTER_NAME).description(Commands.CHARACTER_DESC).usage(SpigotCommandUsage.of(SpigotCommandArgument.of(Commands.NAME))).permissions(SpigotCommandPermissions.builder().isPlayerOnly(true).permissionNode(Permissions.CHARACTER).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build()).function((sender, args) -> {
            Player player = (Player) sender;
            if (!args.isEmpty()) {
                return SpigotMCDNDSimple.instance().getCharacterSheetStorage().getCharacter(player.getUniqueId(), args.get(0)).map(playerSheet -> {
                    SpigotChestGUIs.INSTANCE.playerSheet(player, playerSheet, null);
                    return true;
                }).orElseGet(() -> {
                    player.sendMessage(ChatColor.RED + Messages.CHARACTER_DNE);
                    return false;
                });
            }

            //TODO need guis for opening up multiple characters
            return false;
        }).build(SpigotMCDNDSimple.instance());
    }

    public static List<SpigotCommand<SpigotMCDNDSimple>> commands() {
        return Arrays.asList(callback(), character(), createCharacter());
    }

    private static SpigotCommand<SpigotMCDNDSimple> createCharacter() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.CREATE_COMMAND).description(Commands.CREATE_COMMAND_DESC).usage(SpigotCommandUsage.builder().minArgs(2).addArgument(SpigotCommandArgument.of(Commands.CREATE_ARGUMENT)).addArgument(SpigotCommandArgument.of(Commands.NAME)).build()).permissions(SpigotCommandPermissions.builder().isPlayerOnly(true).permissionNode(Permissions.DM).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build()).function((sender, args) -> {
            Player player = (Player) sender;
            String characterName = args.get(1);
            SpigotMCDNDSimple plugin = SpigotMCDNDSimple.instance();
            if (args.get(0).equals(Commands.PC)) {
                return plugin.getCharacterSheetStorage().createNewCharacter(player.getUniqueId(), characterName).map(playerSheet -> {
                    player.sendMessage(ChatColor.GREEN + Messages.CHARACTER_CREATED);
                    return true;
                }).orElseGet(() -> {
                    player.sendMessage(ChatColor.RED + Messages.CHARACTER_ALREADY_EXISTS);
                    return false;
                });
            }
            else if (args.get(0).equals(Commands.NPC_NAME)) {
                return plugin.getNPCStorage().createNewCharacter(player.getUniqueId(), characterName).map(npc -> {
                    player.sendMessage(ChatColor.GREEN + Messages.CHARACTER_CREATED);
                    return true;
                }).orElseGet(() -> {
                    player.sendMessage(ChatColor.RED + Messages.CHARACTER_ALREADY_EXISTS);
                    return false;
                });
            }

            return false;
        }).build(SpigotMCDNDSimple.instance());
    }

    private static SpigotCommand<SpigotMCDNDSimple> npc() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.NPC_NAME).description(Commands.NPC_DESC).usage(SpigotCommandUsage.of(SpigotCommandArgument.of(Commands.NAME))).permissions(SpigotCommandPermissions.builder().permissionNode(Permissions.NPC).isPlayerOnly(true).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).build()).function((sender, args) -> {
            Player player = (Player) sender;
            if (!args.isEmpty()) {
                return SpigotMCDNDSimple.instance().getNPCStorage().getCharacter(player.getUniqueId(), args.get(0)).map(npc -> {
                    //TODO incomplete
                    //new NPCSheetGUI(player, npc, null);
                    return true;
                }).orElseGet(() -> {
                    player.sendMessage(ChatColor.RED + Messages.CHARACTER_DNE);
                    return false;
                });
            }

            //TODO need guis for opening up multiple characters
            return false;
        }).build(SpigotMCDNDSimple.instance());
    }
}
