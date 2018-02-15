package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.spell.Prepared;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class PreparedGUI extends MCDNDSimpleChestGUI {

    private final Spell spell;

    public PreparedGUI(Player player, Spell spell, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.prepared(spell.getPrepared()), 9, prevGUI);
        this.spell = spell;
    }

    @Override
    protected void build() {
        Prepared[] prepareds = Prepared.values();
        for (int x = 0; x < prepareds.length; x++) {
            Prepared prepared = prepareds[x];
            set(x, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BOOK, Text.of(prepared.getName())), () -> spell.getPrepared() == prepared), player -> {
                spell.setPrepared(prepared);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
