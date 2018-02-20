package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class MacroOptionsGUI extends MCDNDSimpleChestGUI {

    private final MacroOptions macroOptions;

    public MacroOptionsGUI(Player player, MacroOptions macroOptions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, 18, prevGUI);
        this.macroOptions = macroOptions;
    }

    @Override
    protected void build() {
        boolean infoBlockEnabled = macroOptions.isInfoBlockEnabled();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.INFO_BLOCK)), () -> infoBlockEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!infoBlockEnabled);
            open();
        });

        boolean descriptionEnabled = macroOptions.isDescriptionEnabled();
        set(1, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> descriptionEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!descriptionEnabled);
            open();
        });

        boolean atHigherLevelsEnabled = macroOptions.isAtHigherLevelsEnabled();
        set(2, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> atHigherLevelsEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!atHigherLevelsEnabled);
            open();
        });

        boolean attackRollEnabled = macroOptions.isAttackRollEnabled();
        set(3, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> attackRollEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!attackRollEnabled);
            open();
        });

        boolean secondAttackRollEnabled = macroOptions.isSecondAttackRollEnabled();
        set(4, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> secondAttackRollEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!secondAttackRollEnabled);
            open();
        });

        boolean savingThrowEnabled = macroOptions.isSavingThrowEnabled();
        set(5, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> savingThrowEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!savingThrowEnabled);
            open();
        });

        boolean healingEnabled = macroOptions.isHealingEnabled();
        set(6, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> healingEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!healingEnabled);
            open();
        });

        boolean damageEnabled = macroOptions.isDamageEnabled();
        set(7, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> damageEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!damageEnabled);
            open();
        });

        boolean effectsEnabled = macroOptions.isEffectsEnabled();
        set(8, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.DESCRIPTION)), () -> effectsEnabled), player -> {
            macroOptions.setInfoBlockEnabled(!effectsEnabled);
            open();
        });

        setBackButton(13, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
