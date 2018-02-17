package io.musician101.mcdndsimple.spigot.gui.anvil.number;

import io.musician101.mcdndsimple.spigot.gui.anvil.MCDNDSimpleAnvilGUI;
import java.util.function.BiConsumer;
import org.bukkit.entity.Player;

public class IntegerAnvilGUI extends MCDNDSimpleAnvilGUI {

    public IntegerAnvilGUI(Player holder, BiConsumer<Player, Integer> biConsumer) {
        super(holder, (player, name) -> {
            int number;
            try {
                number = Integer.parseInt(name);
            }
            catch (NumberFormatException e) {
                return "That is not a number!";
            }

            biConsumer.accept(player, number);
            return null;
        });
    }
}
