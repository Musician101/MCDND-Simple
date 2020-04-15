package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class WeaponsTabGUI extends SpongeMCDNDSimpleGUI {

    public WeaponsTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.WEAPONS, 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.MELEE_WEAPONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new MeleeWeaponsGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.RANGED_WEAPONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RangedWeaponsGUI(mcdndPlayer, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
