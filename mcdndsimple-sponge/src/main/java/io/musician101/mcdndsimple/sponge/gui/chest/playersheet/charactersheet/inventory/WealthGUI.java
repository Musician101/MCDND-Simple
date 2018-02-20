package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class WealthGUI extends MCDNDSimpleChestGUI {

    private final Wealth wealth;

    public WealthGUI(Player player, Wealth wealth, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.COIN_CARRIED, 9, prevGUI);
        this.wealth = wealth;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.ROTTEN_FLESH, Text.of(MenuText.coin(wealth.getCopper()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Copper", "CP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.IRON_NUGGET, Text.of(MenuText.coin(wealth.getCopper()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Silver", "SP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.IRON_INGOT, Text.of(MenuText.coin(wealth.getCopper()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Electrum", "EP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.GOLD_NUGGET, Text.of(MenuText.coin(wealth.getCopper()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Gold", "GP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND, Text.of(MenuText.coin(wealth.getCopper()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Platinum", "PP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(5, createItem(ItemTypes.EMERALD, Text.of(MenuText.total(wealth)), Text.of(MenuText.TOTAL_IN_GOLD)));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
