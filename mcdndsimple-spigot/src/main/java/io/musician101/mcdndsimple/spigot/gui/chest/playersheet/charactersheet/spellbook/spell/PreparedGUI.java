package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.Prepared;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PreparedGUI extends MCDNDSimpleChestGUI {

    private final Spell spell;

    public PreparedGUI(Player player, Spell spell, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.prepared(spell.getPrepared()), prevGUI);
        this.spell = spell;
    }

    @Override
    protected void build() {
        Prepared[] prepareds = Prepared.values();
        for (int x = 0; x < prepareds.length; x++) {
            Prepared prepared = prepareds[x];
            ItemStack itemStack = createItem(Material.BOOK, prepared.getName());
            if (spell.getPrepared() == prepared) {
                itemStack = addGlow(itemStack);
            }

            set(x, ClickType.LEFT, itemStack, player -> {
                spell.setPrepared(prepared);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }

        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
