package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;

public class ArmorTypeGUI extends MCDNDSimpleChestGUI {

    private final Armor armor;

    public ArmorTypeGUI(Player player, Armor armor, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.ARMOR_TYPE, 9, prevGUI);
        this.armor = armor;
    }

    @Override
    protected void build() {
        ArmorType[] armorTypes = ArmorType.values();
        for (int i = 0; i < armorTypes.length; i++) {
            ArmorType armorType = armorTypes[i];
            set(i, ClickInventoryEvent.class, addGlowIfConditionsMet(ItemRepresentation.armorType(armorType), () -> armorType == armor.getArmorType()), player -> {
                armor.setArmorType(armorType);
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
