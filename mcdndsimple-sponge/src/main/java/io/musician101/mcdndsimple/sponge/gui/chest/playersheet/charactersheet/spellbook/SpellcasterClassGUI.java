package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

public class SpellcasterClassGUI<T> extends MCDNDSimpleChestGUI {

    private final Predicate<SpellcasterClass> glowApplier;
    private final T value;
    private final BiConsumer<T, SpellcasterClass> valueSetter;

    public SpellcasterClassGUI(Player player, T value, BiConsumer<T, SpellcasterClass> valueSetter, Predicate<SpellcasterClass> glowApplier, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.GAINED_FROM, 18, prevGUI);
        this.value = value;
        this.valueSetter = valueSetter;
        this.glowApplier = glowApplier;
    }

    @Override
    protected void build() {
        SpellcasterClass[] spellcasterClasses = SpellcasterClass.values();
        for (int x = 0; x < spellcasterClasses.length; x++) {
            SpellcasterClass spellcasterClass = spellcasterClasses[x];
            ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(spellcasterClass.getName()));
            if (glowApplier.test(spellcasterClass)) {
                addGlow(itemStack);
            }

            set(x, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.ENCHANTED_BOOK, Text.of(spellcasterClass.getName())), () -> glowApplier.test(spellcasterClass)), player -> {
                valueSetter.accept(value, spellcasterClass);
                closeGUI();
            });
        }

        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
