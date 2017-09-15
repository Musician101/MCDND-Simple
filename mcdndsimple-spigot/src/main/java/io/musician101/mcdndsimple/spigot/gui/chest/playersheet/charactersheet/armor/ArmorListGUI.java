package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ArmorListGUI extends MCDNDSimplePagedGUI {

    private final List<Armor> armorList;

    public ArmorListGUI(Player player, List<Armor> armorList, int page, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 54, MenuText.ARMOR, page, prevGUI);
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

        setBackButton(53, ClickType.LEFT, Material.BARRIER);
    }
}
