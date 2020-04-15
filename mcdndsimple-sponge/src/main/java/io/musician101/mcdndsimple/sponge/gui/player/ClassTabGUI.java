package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class ClassTabGUI extends SpongeMCDNDSimpleGUI {

    public ClassTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS, 9);
        ClassTab classTab = mcdndPlayer.getCharacterSheet().getClassTab();
        setButton(0, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.CLASS_LEVELS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassLevelsGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.DIAMOND, Text.of(MenuText.CLASS_RESOURCES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassResourcesGUI(mcdndPlayer, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.CLASS_FEATURE_NOTES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes()), (ply, pages) -> {
            classTab.setClassFeatureNotes(textListToStringList(pages));
            new ClassTabGUI(mcdndPlayer, p);
        })));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.OTHER_NOTES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.OTHER_NOTES, classTab.getOtherNotes()), (ply, pages) -> {
            classTab.setOtherNotes(textListToStringList(pages));
            new ClassTabGUI(mcdndPlayer, p);
        })));
        setButton(4, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.CLASS_ACTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassActionsGUI(mcdndPlayer, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
