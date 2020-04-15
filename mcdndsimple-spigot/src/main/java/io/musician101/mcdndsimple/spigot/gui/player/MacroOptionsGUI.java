package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class MacroOptionsGUI extends SpigotMCDNDSimpleGUI {

    public MacroOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, 18);
        MacroOptions macroOptions = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getMacroOptions();
        updateSlot(0, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isInfoBlockEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.INFO_BLOCK), () -> macroOptions.setInfoBlockEnabled(!macroOptions.isInfoBlockEnabled()));
        updateSlot(1, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isDescriptionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.DESCRIPTION), () -> macroOptions.setDescriptionEnabled(!macroOptions.isDescriptionEnabled()));
        updateSlot(2, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isAtHigherLevelsEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.AT_HIGHER_LEVELS), () -> macroOptions.setAtHigherLevelsEnabled(!macroOptions.isAtHigherLevelsEnabled()));
        updateSlot(3, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isAttackRollEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.ATTACK), () -> macroOptions.setAttackRollEnabled(!macroOptions.isAttackRollEnabled()));
        updateSlot(4, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isSecondAttackRollEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.SECOND_ATTACK), () -> macroOptions.setSecondAttackRollEnabled(!macroOptions.isSecondAttackRollEnabled()));
        updateSlot(5, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isSavingThrowEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.SAVING_THROW), () -> macroOptions.setSavingThrowEnabled(!macroOptions.isSavingThrowEnabled()));
        updateSlot(6, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isHealingEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.HEALING), () -> macroOptions.setHealingEnabled(!macroOptions.isHealingEnabled()));
        updateSlot(7, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isDamageEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.DAMAGE), () -> macroOptions.setDamageEnabled(!macroOptions.isDamageEnabled()));
        updateSlot(8, SpigotIconBuilder.of(Material.REDSTONE_TORCH, (macroOptions.isEffectsEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.EFFECTS), () -> macroOptions.setEffectsEnabled(!macroOptions.isEffectsEnabled()));
        setButton(13, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }

    private void updateSlot(int slot, @Nonnull ItemStack itemStack, @Nonnull Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
