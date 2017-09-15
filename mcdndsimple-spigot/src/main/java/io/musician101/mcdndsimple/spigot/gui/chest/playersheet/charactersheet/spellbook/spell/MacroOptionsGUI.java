package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.MacroOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class MacroOptionsGUI extends MCDNDSimpleChestGUI {

    private final MacroOptions macroOptions;

    public MacroOptionsGUI(Player player, MacroOptions macroOptions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, prevGUI);
        this.macroOptions = macroOptions;
    }

    @Override
    protected void build() {
        boolean infoBlockEnabled = macroOptions.isInfoBlockEnabled();
        ItemStack infoBlock = createItem(Material.REDSTONE_TORCH_ON, MenuText.INFO_BLOCK);
        if (infoBlockEnabled) {
            infoBlock = addGlow(infoBlock);
        }

        set(0, ClickType.LEFT, infoBlock, player -> {
            macroOptions.setInfoBlockEnabled(!infoBlockEnabled);
            open();
        });

        boolean descriptionEnabled = macroOptions.isDescriptionEnabled();
        ItemStack description = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (descriptionEnabled) {
            description = addGlow(infoBlock);
        }

        set(1, ClickType.LEFT, description, player -> {
            macroOptions.setInfoBlockEnabled(!descriptionEnabled);
            open();
        });

        boolean atHigherLevelsEnabled = macroOptions.isAtHigherLevelsEnabled();
        ItemStack atHigherLevels = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (atHigherLevelsEnabled) {
            atHigherLevels = addGlow(infoBlock);
        }

        set(2, ClickType.LEFT, atHigherLevels, player -> {
            macroOptions.setInfoBlockEnabled(!atHigherLevelsEnabled);
            open();
        });

        boolean attackRollEnabled = macroOptions.isAttackRollEnabled();
        ItemStack attackRoll = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (attackRollEnabled) {
            attackRoll = addGlow(infoBlock);
        }

        set(3, ClickType.LEFT, attackRoll, player -> {
            macroOptions.setInfoBlockEnabled(!attackRollEnabled);
            open();
        });

        boolean secondAttackRollEnabled = macroOptions.isSecondAttackRollEnabled();
        ItemStack secondAttackRoll = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (secondAttackRollEnabled) {
            secondAttackRoll = addGlow(infoBlock);
        }

        set(4, ClickType.LEFT, secondAttackRoll, player -> {
            macroOptions.setInfoBlockEnabled(!secondAttackRollEnabled);
            open();
        });

        boolean savingThrowEnabled = macroOptions.isSavingThrowEnabled();
        ItemStack savingThrow = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (savingThrowEnabled) {
            savingThrow = addGlow(infoBlock);
        }

        set(5, ClickType.LEFT, savingThrow, player -> {
            macroOptions.setInfoBlockEnabled(!savingThrowEnabled);
            open();
        });

        boolean healingEnabled = macroOptions.isHealingEnabled();
        ItemStack healing = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (healingEnabled) {
            healing = addGlow(infoBlock);
        }

        set(6, ClickType.LEFT, healing, player -> {
            macroOptions.setInfoBlockEnabled(!healingEnabled);
            open();
        });

        boolean damageEnabled = macroOptions.isDamageEnabled();
        ItemStack damage = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (damageEnabled) {
            damage = addGlow(infoBlock);
        }

        set(7, ClickType.LEFT, damage, player -> {
            macroOptions.setInfoBlockEnabled(!damageEnabled);
            open();
        });

        boolean effectsEnabled = macroOptions.isEffectsEnabled();
        ItemStack effects = createItem(Material.REDSTONE_TORCH_ON, MenuText.DESCRIPTION);
        if (effectsEnabled) {
            effects = addGlow(infoBlock);
        }

        set(8, ClickType.LEFT, effects, player -> {
            macroOptions.setInfoBlockEnabled(!effectsEnabled);
            open();
        });

        setBackButton(13, ClickType.LEFT, Material.BARRIER);
    }
}
