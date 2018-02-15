package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class ArmorTabGUI extends MCDNDSimpleChestGUI {

    private final ArmorTab armorTab;

    public ArmorTabGUI(Player player, ArmorTab armorTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.ARMOR, 9, prevGUI);
        this.armorTab = armorTab;
    }

    @Override
    protected void build() {
        set(0, createItem(ItemTypes.CHAINMAIL_CHESTPLATE, Text.of(MenuText.armoredAC(armorTab))));
        set(1, createItem(ItemTypes.LEATHER_CHESTPLATE, Text.of(MenuText.unarmoredAC(armorTab))));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.ARMOR)), player -> new ArmorListGUI(player, armorTab.getArmorList(), 0, this));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.IRON_CHESTPLATE, Text.of(MenuText.UNARMORED_BONUS), Text.of(MenuText.current(armorTab))), player -> new UnarmoredBonusGUI(player, armorTab, this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
