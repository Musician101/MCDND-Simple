package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;

public class UnarmoredBonusGUI extends MCDNDSimpleChestGUI {

    private ArmorTab armorTab;

    public UnarmoredBonusGUI(Player player, ArmorTab armorTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.UNARMORED_BONUS, 9, prevGUI);
        this.armorTab = armorTab;
    }

    @Override
    protected void build() {
        UnarmoredBonus[] unarmoredBonuses = UnarmoredBonus.values();
        for (int i = 0; i < unarmoredBonuses.length; i++) {
            UnarmoredBonus unarmoredBonus = unarmoredBonuses[i];
            set(i, ClickInventoryEvent.class, addGlowIfConditionsMet(ItemRepresentation.unarmoredBonus(unarmoredBonus), () -> armorTab.getUnarmoredBonus() == unarmoredBonus), player -> {
                armorTab.setUnarmoredBonus(unarmoredBonus);
                closeGUI();
            });
        }

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
