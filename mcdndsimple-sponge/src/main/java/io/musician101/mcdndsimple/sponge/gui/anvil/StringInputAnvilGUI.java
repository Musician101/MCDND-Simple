package io.musician101.mcdndsimple.sponge.gui.anvil;

import java.util.function.BiConsumer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.api.entity.living.player.Player;

/**
 * @deprecated Need to make sure that any new GUI's opening thanks to the biConsumer are delayed.
 */
@Deprecated
public class StringInputAnvilGUI extends MCDNDSimpleAnvilGUI {

    public StringInputAnvilGUI(Player holder, BiConsumer<EntityPlayer, String> biConsumer) {
        super(holder, (p, s) -> {
            biConsumer.accept(p, s);
            return null;
        });
    }
}
