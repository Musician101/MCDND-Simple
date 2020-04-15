package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellSaveGUI extends SpigotMCDNDSimpleGUI {

    public SpellSaveGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.SAVE, 9);
        SpellSave spellSave = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave();
        setButton(0, SpigotIconBuilder.of(Material.SHIELD, MenuText.SAVING_STAT), ImmutableMap.of(ClickType.LEFT, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL_SAVE, p)));
        setButton(1, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.SAVE_DC), ImmutableMap.of(ClickType.LEFT, p -> new SpellcasterClassGUI(mcdndPlayer, level, index, SpellcasterClassGUI.SPELL_SAVE, p)));
        setButton(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.customDC(spellSave)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                spellSave.setCustomDC(i);
                new SpellSaveGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.BOOK, MenuText.ON_SUCCESSFUL_SAVE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave()), (ply, pages) -> {
            spellSave.setOnSuccessfulSave(pages);
            new SpellSaveGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
