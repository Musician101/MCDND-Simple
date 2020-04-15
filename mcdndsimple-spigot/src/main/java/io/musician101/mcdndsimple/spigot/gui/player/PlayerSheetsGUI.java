package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.character.SpigotPlayerStorage;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PlayerSheetsGUI extends SpigotMCDNDSimpleGUI {

    private int page = 1;

    public PlayerSheetsGUI(@Nonnull Player player) {
        super(player, MenuText.PLAYER_SHEETS, 54);
        updateSlots();
        if (player.hasPermission(Permissions.DM)) {
            setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

                @Override
                public void process(@Nonnull Player player, @Nonnull String s) {
                    Optional<MCDNDPlayer> playerSheet = SpigotMCDNDSimple.instance().getPlayerStorage().createNewCharacter(s);
                    if (playerSheet.isPresent()) {
                        new PlayerSheetGUI(playerSheet.get(), player);
                        return;
                    }

                    player.sendMessage(ChatColor.RED + "A player character with an ID of " + s + " already exists.");
                }
            }));
        }
        //TODO open player menu?
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, Player::closeInventory));
    }

    private void updateSlots() {
        SpigotPlayerStorage playerSheetStorage = SpigotMCDNDSimple.instance().getPlayerStorage();
        List<MCDNDPlayer> mcdndPlayers = playerSheetStorage.getCharacters(player.getUniqueId());
        IntStream.of(0, 45).forEach(x -> {
            try {
                MCDNDPlayer mcdndPlayer = mcdndPlayers.get(x + (page - 1) * 45);
                ItemStack itemStack = SpigotIconBuilder.builder(Material.WRITTEN_BOOK).name(mcdndPlayer.getName()).description(ChatColor.GREEN + "LEFT-CLICK" + ChatColor.RESET + " to edit.", ChatColor.RED + "RIGHT-CLICK" + ChatColor.RESET + " to delete.").build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new PlayerSheetGUI(mcdndPlayer, p), ClickType.RIGHT, p -> {
                    playerSheetStorage.delete(p.getUniqueId(), mcdndPlayer.getID());
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(mcdndPlayers.size() / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
