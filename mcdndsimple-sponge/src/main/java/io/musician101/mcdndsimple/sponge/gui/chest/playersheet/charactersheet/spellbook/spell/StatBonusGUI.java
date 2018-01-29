package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;


public class StatBonusGUI<T> extends MCDNDSimpleChestGUI {

    private final T value;
    private final BiConsumer<T, StatBonus> valueSetter;
    private final BiPredicate<T, StatBonus> glowApplier;

    public StatBonusGUI(Player player, T value, BiConsumer<T, StatBonus> valueSetter, BiPredicate<T, StatBonus> glowApplier, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.STAT_BONUS, 9, prevGUI);
        this.valueSetter = valueSetter;
        this.value = value;
        this.glowApplier = glowApplier;
    }

    @Override
    protected void build() {
        StatBonus[] statBonuses = StatBonus.values();
        for (int i = 0; i < statBonuses.length; i++) {
            StatBonus statBonus = statBonuses[i];
            set(i, ClickInventoryEvent.class, addGlowIfConditionsMet(getItemStack(statBonus), () -> glowApplier.test(value, statBonus)), player -> {
                valueSetter.accept(value, statBonus);
                closeGUI();
            });
        }

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private ItemStack getItemStack(StatBonus statBonus) {
        ItemType itemType = ItemTypes.ENDER_CHEST;
        switch (statBonus) {
            case CHA:
                itemType = ItemTypes.SKULL;
                break;
            case CON:
                itemType = ItemTypes.GOLDEN_APPLE;
                break;
            case DEX:
                itemType = ItemTypes.BOW;
                break;
            case INT:
                itemType = ItemTypes.WRITABLE_BOOK;
                break;
            case STR:
                itemType = ItemTypes.IRON_SWORD;
                break;
            case WIS:
                itemType = ItemTypes.ENCHANTED_BOOK;
                break;
        }

        ItemStack itemStack = createItem(itemType, Text.of(statBonus.getName()));
        if (statBonus == StatBonus.CHA) {
            itemStack = setType(itemStack, Keys.SKULL_TYPE, SkullTypes.PLAYER);
        }

        return itemStack;
    }
}
