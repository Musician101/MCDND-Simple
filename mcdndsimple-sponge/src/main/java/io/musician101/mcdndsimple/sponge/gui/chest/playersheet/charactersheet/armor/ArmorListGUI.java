package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;

public class ArmorListGUI extends MCDNDSimplePagedGUI {

    private final List<Armor> armorList;

    public ArmorListGUI(Player player, List<Armor> armorList, int page, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.ARMOR, 54, page, prevGUI);
        this.armorList = armorList;
    }

    @Override
    protected void build() {
        setContents(armorList, ItemRepresentation::armor, (player, armor) -> p -> new ArmorGUI(player, armor, this));
        int maxPage = new Double(Math.ceil(armorList.size() / 45)).intValue();
        setJumpToPage(45, maxPage, (player, page) -> new ArmorListGUI(player, armorList, page, prevGUI));
        setPageNavigation(48, MenuText.PREVIOUS_PAGE, player -> {
            if (page > 1) {
                new ArmorListGUI(player, armorList, page - 1, prevGUI);
            }
        });

        setPageNavigation(50, MenuText.NEXT_PAGE, player -> {
            if (page < maxPage) {
                new ArmorListGUI(player, armorList, page + 1, prevGUI);
            }
        });

        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
