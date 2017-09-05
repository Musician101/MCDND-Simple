package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InventoryGUI extends MCDNDSimplePagedGUI {

    private final List<MCDNDItem> items;

    public InventoryGUI(@Nonnull Player player, List<MCDNDItem> items, int page, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, 45, MenuText.INVENTORY, page, prevMenu);
        this.items = items;
    }

    @Override
    protected void build() {
        setContents(items, item -> createItem(Material.CHEST, item.getName(), MenuText.itemDesc(item)), (player, item) -> p -> new ItemGUI(player, item, items, this));
    }
}
