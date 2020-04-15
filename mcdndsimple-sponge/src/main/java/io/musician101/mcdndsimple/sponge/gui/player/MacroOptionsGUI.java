package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class MacroOptionsGUI extends SpongeMCDNDSimpleGUI {

    public MacroOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, 18);
        MacroOptions macroOptions = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getMacroOptions();
        updateSlot(0, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isInfoBlockEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.INFO_BLOCK)), () -> macroOptions.setInfoBlockEnabled(!macroOptions.isInfoBlockEnabled()));
        updateSlot(1, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isDescriptionEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.DESCRIPTION)), () -> macroOptions.setDescriptionEnabled(!macroOptions.isDescriptionEnabled()));
        updateSlot(2, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isAtHigherLevelsEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.AT_HIGHER_LEVELS)), () -> macroOptions.setAtHigherLevelsEnabled(!macroOptions.isAtHigherLevelsEnabled()));
        updateSlot(3, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isAttackRollEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.ATTACK)), () -> macroOptions.setAttackRollEnabled(!macroOptions.isAttackRollEnabled()));
        updateSlot(4, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isSecondAttackRollEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.SECOND_ATTACK)), () -> macroOptions.setSecondAttackRollEnabled(!macroOptions.isSecondAttackRollEnabled()));
        updateSlot(5, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isSavingThrowEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.SAVING_THROW)), () -> macroOptions.setSavingThrowEnabled(!macroOptions.isSavingThrowEnabled()));
        updateSlot(6, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isHealingEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.HEALING)), () -> macroOptions.setHealingEnabled(!macroOptions.isHealingEnabled()));
        updateSlot(7, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isDamageEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.DAMAGE)), () -> macroOptions.setDamageEnabled(!macroOptions.isDamageEnabled()));
        updateSlot(8, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(macroOptions.isEffectsEnabled() ? TextColors.GREEN : TextColors.RED, MenuText.EFFECTS)), () -> macroOptions.setEffectsEnabled(!macroOptions.isEffectsEnabled()));
        setButton(13, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }

    private void updateSlot(int slot, @Nonnull ItemStack itemStack, @Nonnull Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
