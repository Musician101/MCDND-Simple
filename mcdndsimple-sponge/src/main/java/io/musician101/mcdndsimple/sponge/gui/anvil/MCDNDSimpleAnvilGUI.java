package io.musician101.mcdndsimple.sponge.gui.anvil;

import io.musician101.musicianlibrary.java.minecraft.sponge.gui.anvil.SpongeAnvilGUI;
import java.util.function.BiFunction;
import org.spongepowered.api.entity.living.player.Player;

public abstract class MCDNDSimpleAnvilGUI extends SpongeAnvilGUI {

    protected MCDNDSimpleAnvilGUI(Player holder, BiFunction<Player, String, String> biFunction) {
        super(holder, biFunction);
    }
}
