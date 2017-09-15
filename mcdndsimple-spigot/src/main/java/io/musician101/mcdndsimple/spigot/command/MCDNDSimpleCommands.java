package io.musician101.mcdndsimple.spigot.command;

import io.musician101.mcdndsimple.common.Reference.Commands;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.Reference.Permissions;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.PlayerSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommand;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandUsage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCDNDSimpleCommands {

    private MCDNDSimpleCommands() {

    }

    public static List<SpigotCommand<SpigotMCDNDSimple>> commands() {
        return Arrays.asList(callback(), character());
    }

    private static SpigotCommand<SpigotMCDNDSimple> callback() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.CALLBACK_NAME).description(Commands.CALLBACK_DESC).usage(SpigotCommandUsage.builder().minArgs(1).addArgument(SpigotCommandArgument.of(Commands.UUID)).build())
                .permissions(SpigotCommandPermissions.builder().permissionNode(Permissions.CALLBACK).isPlayerOnly(true).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build())
                .function((sender, args) -> {
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
                }).build();
    }

    private static SpigotCommand<SpigotMCDNDSimple> character() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.PLAYER_SHEET_NAME).description(Commands.CHARACTER_DESC).usage(SpigotCommandUsage.builder().minArgs(1).addArgument(SpigotCommandArgument.of(Commands.NAME)).build())
                .permissions(SpigotCommandPermissions.builder().isPlayerOnly(true).permissionNode(Permissions.CHARACTER).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build())
                .function((sender, args) -> {
                    Player player = (Player) sender;
                    return getPlayerSheet(player, args.get(0)).map(playerSheet -> {
                        new PlayerSheetGUI(player, playerSheet, null);
                        return true;
                    }).orElseGet(() -> {
                        player.sendMessage(ChatColor.RED + Messages.CHARACTER_DNE);
                        return false;
                    });
                }).build();
    }

    private static Optional<PlayerSheet> getPlayerSheet(Player player, String characterName) {
        return SpigotMCDNDSimple.instance().getCharacterSheetStorage().getCharacterSheet(player.getUniqueId(), characterName);
    }
}
