package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class RangedWeaponsGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;
    public RangedWeaponsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.RANGED_WEAPONS, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_RANGED_WEAPON), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                RangedWeapon rangedWeapon = new RangedWeapon();
                rangedWeapon.setName(s);
                List<RangedWeapon> rangedWeapons = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons();
                new RangedWeaponGUI(mcdndPlayer, rangedWeapons.size() - 1, player);
            }
        }));
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new WeaponsTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<RangedWeapon> rangedWeapons = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                RangedWeapon rangedWeapon = rangedWeapons.get(index);
                CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
                CoreStatsTab coreStats = characterSheet.getCoreStatsTab();
                ItemStack itemStack = ItemRepresentation.rangedWeapon(rangedWeapon, characterSheet.getClassTab().getClassLevels(), coreStats.getCoreStats(), coreStats.getExperience());
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GREEN + "LEFT-CLICK: " + ChatColor.RESET + "to select.");
                lore.add(ChatColor.RED + "RIGHT-CLICK: " + ChatColor.RESET + "to delete.");
                lore.addAll(itemStack.getItemMeta().getLore());
                setButton(x, SpigotIconBuilder.builder(itemStack).description(lore).build(), ImmutableMap.of(ClickType.LEFT, p -> new RangedWeaponGUI(mcdndPlayer, index, p), ClickType.RIGHT, p -> {
                    rangedWeapons.remove(index);
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

        int maxPage = Double.valueOf(Math.ceil(rangedWeapons.size() / 45D)).intValue();
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
