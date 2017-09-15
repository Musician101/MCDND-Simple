package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.StatBonus;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class StatBonusGUI<T> extends MCDNDSimpleChestGUI {
    
    private final T value;
    private final BiConsumer<T, StatBonus> valueSetter;
    private final BiPredicate<T, StatBonus> glowApplier;

    public StatBonusGUI(Player player, T value, BiConsumer<T, StatBonus> valueSetter, BiPredicate<T, StatBonus> glowApplier, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.STAT_BONUS, prevGUI);
        this.value = value;
        this.valueSetter = valueSetter;
        this.glowApplier = glowApplier;
    }

    @Override
    protected void build() {
        StatBonus[] statBonuses = StatBonus.values();
        for (int i = 0; i < statBonuses.length; i++) {
            StatBonus statBonus = statBonuses[i];
            set(i, ClickType.LEFT, addGlowIfConditionsMet(getItemStack(statBonus), () -> glowApplier.test(value, statBonus)), player -> {
                valueSetter.accept(value, statBonus);
                closeGUI();
            });
        }
        
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }

    private ItemStack getItemStack(StatBonus statBonus) {
        Material itemType = Material.ENDER_CHEST;
        switch (statBonus) {
            case CHA:
                itemType = Material.SKULL;
                break;
            case CON:
                itemType = Material.GOLDEN_APPLE;
                break;
            case DEX:
                itemType = Material.BOW;
                break;
            case INT:
                itemType = Material.BOOK_AND_QUILL;
                break;
            case STR:
                itemType = Material.IRON_SWORD;
                break;
            case WIS:
                itemType = Material.ENCHANTED_BOOK;
                break;
        }

        ItemStack itemStack = createItem(itemType, statBonus.getName());
        if (statBonus == StatBonus.CHA) {
            itemStack = setDurability(itemStack, 3);
        }

        return itemStack;
    }
}
