package io.musician101.mcdndsimple.sponge.gui.chest;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongePagedGUI;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

/**
 * @deprecated Make sure to double check if all usages have page navigation
 */
@Deprecated
public abstract class MCDNDSimplePagedGUI extends AbstractSpongePagedGUI<AbstractConfig, SpongeMCDNDSimple> {

    public MCDNDSimplePagedGUI(@Nonnull Player player, @Nonnull String name, int size, int page, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, name, size, page, prevMenu, SpongeMCDNDSimple.instance());
    }

    protected final Text[] convertToText(String... strings) {
        return Arrays.stream(strings).map(Text::of).toArray(Text[]::new);
    }
}
