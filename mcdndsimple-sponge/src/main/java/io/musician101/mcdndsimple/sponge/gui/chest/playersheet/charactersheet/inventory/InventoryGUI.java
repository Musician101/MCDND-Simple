package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class InventoryGUI extends MCDNDSimplePagedGUI {

    private final List<MCDNDItem> items;

    public InventoryGUI(@Nonnull Player player, List<MCDNDItem> items, int page, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, MenuText.INVENTORY, 54, page, prevMenu);
        this.items = items;
    }

    @Override
    protected void build() {
        setContents(items, item -> createItem(ItemTypes.CHEST, Text.of(item.getName()), convertToText(MenuText.itemDesc(item))), (player, item) -> p -> new ItemGUI(player, item, items, this));
    }
}
