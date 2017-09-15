package io.musician101.mcdndsimple.sponge.gui.anvil;

import io.musician101.musicianlibrary.java.minecraft.sponge.gui.anvil.SpongeAnvilGUI;
import java.util.function.BiFunction;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.api.entity.living.player.Player;

public class MCDNDSimpleAnvilGUI extends SpongeAnvilGUI {

    public MCDNDSimpleAnvilGUI(Player holder, BiFunction<EntityPlayer, String, String> biFunction) {
        super(holder, biFunction);
    }
}
