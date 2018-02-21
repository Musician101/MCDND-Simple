package io.musician101.mcdndsimple.sponge.gui.anvil;

import java.util.function.BiConsumer;
import org.spongepowered.api.entity.living.player.Player;

public class StringAnvilGUI extends MCDNDSimpleAnvilGUI {

    public StringAnvilGUI(Player holder, BiConsumer<Player, String> biConsumer) {
        super(holder, (p, s) -> {
            biConsumer.accept(p, s);
            return null;
        });
    }
}
