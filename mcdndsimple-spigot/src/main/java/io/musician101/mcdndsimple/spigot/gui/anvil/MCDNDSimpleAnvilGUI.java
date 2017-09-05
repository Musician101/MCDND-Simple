package io.musician101.mcdndsimple.spigot.gui.anvil;

import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import java.util.function.BiFunction;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.entity.Player;

public class MCDNDSimpleAnvilGUI extends AnvilGUI {

    public MCDNDSimpleAnvilGUI(Player holder, BiFunction<Player, String, String> biFunction) {
        super(SpigotMCDNDSimple.instance(), holder, "Rename me!", biFunction);
    }
}
