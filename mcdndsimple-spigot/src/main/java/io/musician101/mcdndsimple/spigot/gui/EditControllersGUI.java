package io.musician101.mcdndsimple.spigot.gui;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.nonplayer.NonPlayerGUI;
import io.musician101.mcdndsimple.spigot.gui.player.PlayerSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class EditControllersGUI extends SpigotMCDNDSimpleGUI {

    public static final EditControllersBridger NPC = (abstractPlayer, player) -> new NonPlayerGUI((NonPlayer) abstractPlayer, player);
    public static final EditControllersBridger PLAYER = (abstractPlayer, player) -> new PlayerSheetGUI((MCDNDPlayer) abstractPlayer, player);
    @Nonnull
    private final AbstractPlayer abstractPlayer;
    private int page;

    public EditControllersGUI(@Nonnull AbstractPlayer abstractPlayer, @Nonnull EditControllersBridger bridger, @Nonnull Player player) {
        super(player, MenuText.EDIT_CONTROLLERS, 54);
        this.abstractPlayer = abstractPlayer;
        updateSlots();
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> bridger.accept(abstractPlayer, p)));
    }

    private void updateSlots() {
        List<UUID> controllers = abstractPlayer.getControllers();
        List<UUID> potentialControllers = Stream.concat(Stream.of(Bukkit.getOfflinePlayers()), Bukkit.getOnlinePlayers().stream()).map(OfflinePlayer::getUniqueId).collect(Collectors.toList());
        IntStream.of(0, 45).forEach(x -> {
            try {
                UUID uuid = potentialControllers.get(x + (page - 1) * 45);
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
                ItemStack itemStack = SpigotIconBuilder.builder(Material.PLAYER_HEAD).name((controllers.contains(uuid) ? ChatColor.GREEN : ChatColor.RED) + offlinePlayer.getName()).addGlow(abstractPlayer.isController(uuid)).build();
                SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
                skullMeta.setOwningPlayer(offlinePlayer);
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    if (controllers.contains(uuid)) {
                        abstractPlayer.removeController(uuid);
                    }
                    else {
                        abstractPlayer.addController(uuid);
                    }

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

        int maxPage = Double.valueOf(Math.ceil(potentialControllers.size() / 45D)).intValue();
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

    private interface EditControllersBridger extends BiConsumer<AbstractPlayer, Player> {

    }
}
