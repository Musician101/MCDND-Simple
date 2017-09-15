package io.musician101.mcdndsimple.sponge.gui.anvil.number;

import io.musician101.mcdndsimple.sponge.gui.anvil.MCDNDSimpleAnvilGUI;
import java.util.function.BiConsumer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.api.entity.living.player.Player;

public class IntegerInputAnvilGUI extends MCDNDSimpleAnvilGUI {

    public IntegerInputAnvilGUI(Player holder, BiConsumer<EntityPlayer, Integer> biConsumer) {
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
