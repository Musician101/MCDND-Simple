package io.musician101.mcdndsimple.spigot.gui.anvil.number;

import io.musician101.mcdndsimple.spigot.gui.anvil.MCDNDSimpleAnvilGUI;
import java.util.function.BiConsumer;
import org.bukkit.entity.Player;

public class DoubleInputAnvilGUI extends MCDNDSimpleAnvilGUI {

    public DoubleInputAnvilGUI(Player holder, BiConsumer<Player, Double> biConsumer) {
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
