package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class SpellTypeGUI extends MCDNDSimpleChestGUI {

    private final Spell spell;

    public SpellTypeGUI(Player player, Spell spell, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SCHOOL, 18, prevGUI);
        this.spell = spell;
    }

    @Override
    protected void build() {
        SpellType[] spellTypes = SpellType.values();
        for (int x = 0; x < spellTypes.length; x++) {
            SpellType spellType = spellTypes[x];
            set(x, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.END_CRYSTAL, Text.of(spellType.getName())), () -> spellType == spell.getSpellType()), player -> {
                spell.setSpellType(spellType);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
