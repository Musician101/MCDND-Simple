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

public class CharacterSheetGUI extends SpongeMCDNDSimpleGUI {

    public CharacterSheetGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CHARACTER_SHEET, 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.ARMOR)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ArmorTabGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.BACKGROUND)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new BackgroundTabGUI(mcdndPlayer, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.CLASS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassTabGUI(mcdndPlayer, p)));
        setButton(3, SpongeIconBuilder.of(ItemTypes.DIAMOND, Text.of(MenuText.CORE_STATS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
        setButton(4, SpongeIconBuilder.of(ItemTypes.CHEST, Text.of(MenuText.INVENTORY)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryTabGUI(mcdndPlayer, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.SKILLS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SkillsTabGUI(mcdndPlayer, p)));
        setButton(6, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SPELLBOOK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellbookTabGUI(mcdndPlayer, p)));
        setButton(7, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.INVENTORY)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new WeaponsTabGUI(mcdndPlayer, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
