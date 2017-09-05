package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.spell.SpellType;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellTypeGUI extends MCDNDSimpleChestGUI {

    private final Spell spell;

    public SpellTypeGUI(Player player, Spell spell, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.SCHOOL, prevGUI);
        this.spell = spell;
    }

    @Override
    protected void build() {
        SpellType[] spellTypes = SpellType.values();
        for (int x = 0; x < spellTypes.length; x++) {
            SpellType spellType = spellTypes[x];
            ItemStack itemStack = createItem(Material.END_CRYSTAL, spellType.getName());
            if (spell.getSpellType() == spellType) {
                itemStack = addGlow(itemStack);
            }

            set(x, itemStack, player -> {
                spell.setSpellType(spellType);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }
        setBackButton(17, Material.BARRIER);
    }
}
