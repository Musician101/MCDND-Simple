package io.musician101.mcdndsimple.spigot.command;

import io.musician101.mcdndsimple.common.Reference.Commands;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.Reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommand;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandUsage;
import java.util.UUID;
import java.util.function.Consumer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class MCDNDSimpleCommands {

    private MCDNDSimpleCommands() {

    }

    private static SpigotCommand<SpigotMCDNDSimple> callback() {
        return SpigotCommand.<SpigotMCDNDSimple>builder().name(Commands.CALLBACK_NAME).description(Commands.CALLBACK_DESC)
                .usage(SpigotCommandUsage.builder().minArgs(1).addArgument(SpigotCommandArgument.of(Commands.UUID)).build())
                .permissions(SpigotCommandPermissions.builder().permissionNode(Permissions.CALLBACK).isPlayerOnly(true)
                        .noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION)
                        .playerOnlyMessage(ChatColor.RED + Messages.PLAYER_ONLY).build())
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
                })
                .build();
    }
}
