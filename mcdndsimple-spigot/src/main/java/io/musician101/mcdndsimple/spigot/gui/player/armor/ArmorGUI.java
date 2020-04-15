package io.musician101.mcdndsimple.spigot.gui.player.armor;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class ArmorGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final Armor armor;

    public ArmorGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index).getName(), 18);
        this.armor = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index);
        updateIsWorn();
        updateIsUnarmored();
        setButton(2, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                armor.setName(s);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.baseAC(armor)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                armor.setBaseArmorClass(i);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.IRON_INGOT, armor.getArmorType().getName()), ImmutableMap.of(ClickType.LEFT, p -> new ArmorTypeGUI(mcdndPlayer, index, p)));
        setButton(5, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.magicBonus(armor)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                armor.setMagicBonus(i);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(6, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.totalAC(armor)));
        updateHasStealthPenalty();
        updateHasSpeedPenalty();
        setButton(17, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new ArmorListGUI(mcdndPlayer, p)));
    }

    private void updateHasSpeedPenalty() {
        setButton(8, SpigotIconBuilder.builder(Material.POTION).name(MenuText.hasSpeedPenalty(armor)).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            updateHasSpeedPenalty();
        }));
    }

    private void updateHasStealthPenalty() {
        setButton(7, SpigotIconBuilder.builder(Material.POTION).name((armor.hasStealthPenalty() ? ChatColor.GREEN : ChatColor.RED) + MenuText.hasStealthPenalty(armor)).potionEffect(PotionType.INVISIBILITY).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            updateHasStealthPenalty();
        }));
    }

    private void updateIsUnarmored() {
        setButton(1, SpigotIconBuilder.builder(Material.CHAINMAIL_CHESTPLATE).name((armor.isUnarmored() ? ChatColor.GREEN : ChatColor.RED) + MenuText.isUnarmored(armor)).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            armor.setIsUnarmored(!armor.isUnarmored());
            updateIsUnarmored();
        }));
    }

    private void updateIsWorn() {
        setButton(0, SpigotIconBuilder.builder(Material.CHAINMAIL_CHESTPLATE).name((armor.isWorn() ? ChatColor.GREEN : ChatColor.RED) + MenuText.isWorn(armor)).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            armor.setIsWorn(!armor.isWorn());
            updateIsWorn();
        }));
    }
}
