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

public class SpellbookTabGUI extends SpongeMCDNDSimpleGUI {

    public SpellbookTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SPELL_DASHBOARD, 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_DASHBOARD)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellDashboardGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.SPELLS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellBookGUI(mcdndPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
