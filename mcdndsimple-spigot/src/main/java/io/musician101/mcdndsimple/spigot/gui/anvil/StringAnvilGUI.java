package io.musician101.mcdndsimple.spigot.gui.anvil;

import java.util.function.BiConsumer;
import org.bukkit.entity.Player;

public class StringAnvilGUI extends MCDNDSimpleAnvilGUI {

    public StringAnvilGUI(Player holder, BiConsumer<Player, String> biConsumer) {
        super(holder, (p, s) -> {
            biConsumer.accept(p, s);
            return null;
        });
    }
}
