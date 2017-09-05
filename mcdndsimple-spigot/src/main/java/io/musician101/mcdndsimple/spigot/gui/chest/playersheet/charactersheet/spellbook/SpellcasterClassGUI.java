package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellcasterClassGUI<T> extends MCDNDSimpleChestGUI {

    private final Predicate<SpellcasterClass> glowApplier;
    private final T value;
    private final BiConsumer<T, SpellcasterClass> valueSetter;

    public SpellcasterClassGUI(Player player, T value, BiConsumer<T, SpellcasterClass> valueSetter, Predicate<SpellcasterClass> glowApplier, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.GAINED_FROM, prevGUI);
        this.value = value;
        this.valueSetter = valueSetter;
        this.glowApplier = glowApplier;
    }

    @Override
    protected void build() {
        SpellcasterClass[] spellcasterClasses = SpellcasterClass.values();
        for (int x = 0; x < spellcasterClasses.length; x++) {
            SpellcasterClass spellcasterClass = spellcasterClasses[x];
            ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, spellcasterClass.getName());
            if (glowApplier.test(spellcasterClass)) {
                addGlow(itemStack);
            }

            set(x, itemStack, player -> {
                valueSetter.accept(value, spellcasterClass);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }

        setBackButton(18, Material.BARRIER);
    }
}
