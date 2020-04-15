package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class WealthGUI extends SpigotMCDNDSimpleGUI {

    public WealthGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.COIN_CARRIED, 9);
        Wealth wealth = mcdndPlayer.getCharacterSheet().getInventoryTab().getWealth();
        setSlot(mcdndPlayer, 0, wealth.getCopper());
        setSlot(mcdndPlayer, 1, wealth.getSilver());
        setSlot(mcdndPlayer, 2, wealth.getElectrum());
        setSlot(mcdndPlayer, 3, wealth.getGold());
        setSlot(mcdndPlayer, 4, wealth.getPlatinum());
        setButton(5, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.total(wealth)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, @Nonnull Coin coin) {
        setButton(slot, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(coin)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                coin.setAmount(i);
                new WealthGUI(mcdndPlayer, player);
            }
        }));
    }
}
