package io.musician101.mcdndsimple.spigot.gui.chest;

import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotPagedGUI;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.entity.Player;

/**
 * @deprecated Make sure to double check if all usages have page navigation
 */
@Deprecated
public abstract class MCDNDSimplePagedGUI extends AbstractSpigotPagedGUI<SpigotMCDNDSimple> {

    public MCDNDSimplePagedGUI(@Nonnull Player player, int size, @Nonnull String name, int page, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, size, name, page, prevMenu, SpigotMCDNDSimple.instance());
    }
}
