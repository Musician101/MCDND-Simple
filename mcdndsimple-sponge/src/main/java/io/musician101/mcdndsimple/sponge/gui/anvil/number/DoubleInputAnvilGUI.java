package io.musician101.mcdndsimple.sponge.gui.anvil.number;

import io.musician101.mcdndsimple.sponge.gui.anvil.MCDNDSimpleAnvilGUI;
import java.util.function.BiConsumer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.api.entity.living.player.Player;

public class DoubleInputAnvilGUI extends MCDNDSimpleAnvilGUI {

    public DoubleInputAnvilGUI(Player holder, BiConsumer<EntityPlayer, Double> biConsumer) {
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
