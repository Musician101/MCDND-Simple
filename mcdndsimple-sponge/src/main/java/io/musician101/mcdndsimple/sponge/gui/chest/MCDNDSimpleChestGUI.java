package io.musician101.mcdndsimple.sponge.gui.chest;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.Arrays;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

/**
 * @deprecated need to change all the biconsumers for the anvil gui's so that they have delayed tasks to open the previous gui
 * need to look through each stat bonus that relies on core stats to make sure it highlights the selected option
 * need to make sure all uses of SpigotBookGUI actually reopens the inventory and doesn't use player.closeInventory() at any point
 * double check all back buttons are set to the proper value
 * need to rewrite GUIs so that I can extend my custom gui AND the abstract gui
 */
@Deprecated
public abstract class MCDNDSimpleChestGUI extends AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> {

    public MCDNDSimpleChestGUI(Player player, String name, int size, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, name, size, prevGUI, SpongeMCDNDSimple.instance());
    }

    protected final Text[] convertToText(String... strings) {
        return Arrays.stream(strings).map(Text::of).toArray(Text[]::new);
    }
}
