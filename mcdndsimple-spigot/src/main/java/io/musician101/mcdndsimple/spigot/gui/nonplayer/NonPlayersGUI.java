package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.character.SpigotNonPlayerStorage;
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

public class NonPlayersGUI extends SpigotMCDNDSimpleGUI {

    private int page = 1;

    public NonPlayersGUI(@Nonnull Player player) {
        super(player, MenuText.NPCS, 54);
        updateSlots();
        if (player.hasPermission(Permissions.DM)) {
            setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

                @Override
                public void process(@Nonnull Player player, @Nonnull String s) {
                    Optional<NonPlayer> nonPlayer = SpigotMCDNDSimple.instance().getNonPlayerStorage().createNewCharacter(s);
                    if (nonPlayer.isPresent()) {
                        new NonPlayerGUI(nonPlayer.get(), player);
                        return;
                    }

                    player.sendMessage(ChatColor.RED + "A non-player character with an ID of " + s + " already exists.");
                }
            }));
        }
        //TODO open player menu?
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, Player::closeInventory));
    }

    private void updateSlots() {
        SpigotNonPlayerStorage nonPlayerStorage = SpigotMCDNDSimple.instance().getNonPlayerStorage();
        List<NonPlayer> nonPlayers = nonPlayerStorage.getCharacters(player.getUniqueId());
        IntStream.of(0, 45).forEach(x -> {
            try {
                NonPlayer nonPlayer = nonPlayers.get(x + (page - 1) * 45);
                ItemStack itemStack = SpigotIconBuilder.builder(Material.WRITTEN_BOOK).name(nonPlayer.getName()).description(ChatColor.GREEN + "LEFT-CLICK" + ChatColor.RESET + " to edit.", ChatColor.RED + "RIGHT-CLICK" + ChatColor.RESET + " to delete.").build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerGUI(nonPlayer, p), ClickType.RIGHT, p -> {
                    nonPlayerStorage.delete(p.getUniqueId(), nonPlayer.getID());
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

        int maxPage = Double.valueOf(Math.ceil(nonPlayers.size() / 45D)).intValue();
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
