package io.musician101.mcdndsimple.sponge.gui.anvil.number;

import io.musician101.mcdndsimple.sponge.gui.anvil.MCDNDSimpleAnvilGUI;
import java.util.function.BiConsumer;
import org.spongepowered.api.entity.living.player.Player;

public class DoubleAnvilGUI extends MCDNDSimpleAnvilGUI {

    public DoubleAnvilGUI(Player holder, BiConsumer<Player, Double> biConsumer) {
        super(holder, (player, name) -> {
            double number;
            try {
                number = Double.parseDouble(name);
            }
            catch (NumberFormatException e) {
                return "That is not a number.";
            }

            biConsumer.accept(player, number);
            return null;
        });
    }
}
