package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class WealthGUI extends MCDNDSimpleChestGUI {

    private final Wealth wealth;

    public WealthGUI(Player player, Wealth wealth, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.COIN_CARRIED, prevGUI);
        this.wealth = wealth;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.ROTTEN_FLESH, MenuText.coin(wealth.getCopper())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Copper", "CP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(1, createItem(Material.IRON_NUGGET, MenuText.coin(wealth.getCopper())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Silver", "SP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(2, createItem(Material.IRON_INGOT, MenuText.coin(wealth.getCopper())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Electrum", "EP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(3, createItem(Material.GOLD_NUGGET, MenuText.coin(wealth.getCopper())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Gold", "GP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(4, createItem(Material.DIAMOND, MenuText.coin(wealth.getCopper())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            Coin coin = new Coin("Platinum", "PP");
            coin.setAmount(i);
            wealth.setCopper(coin);
        }));
        set(5, createItem(Material.EMERALD, MenuText.total(wealth), MenuText.TOTAL_IN_GOLD));
        setBackButton(8, Material.BARRIER);
    }
}
