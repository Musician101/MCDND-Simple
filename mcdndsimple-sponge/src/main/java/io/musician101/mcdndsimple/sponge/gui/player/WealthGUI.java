package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class WealthGUI extends SpongeMCDNDSimpleGUI {

    public WealthGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.COIN_CARRIED, 9);
        Wealth wealth = mcdndPlayer.getCharacterSheet().getInventoryTab().getWealth();
        setSlot(mcdndPlayer, 0, wealth.getCopper());
        setSlot(mcdndPlayer, 1, wealth.getSilver());
        setSlot(mcdndPlayer, 2, wealth.getElectrum());
        setSlot(mcdndPlayer, 3, wealth.getGold());
        setSlot(mcdndPlayer, 4, wealth.getPlatinum());
        setButton(5, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, Text.of(MenuText.total(wealth))));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, @Nonnull Coin coin) {
        setButton(slot, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, Text.of(MenuText.coin(coin))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                coin.setAmount(i);
                new WealthGUI(mcdndPlayer, player);
            }
        }));
    }
}
