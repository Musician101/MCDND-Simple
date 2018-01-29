package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions.ClassActionsGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;


public class ClassTabGUI extends MCDNDSimpleChestGUI {

    private final ClassTab classTab;

    public ClassTabGUI(Player player, ClassTab classTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CLASS, 9, prevGUI);
        this.classTab = classTab;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.CLASS_LEVELS)), player -> new ClassLevelsGUI(player, classTab.getClassLevels(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND, Text.of(MenuText.CLASS_RESOURCES)), player -> new ClassResourcesGUI(player, classTab.getClassResources(), 0, this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.CLASS_FEATURE_NOTES)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, classTab.getClassFeatureNotes().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                classTab.setClassFeatureNotes(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.OTHER_NOTES)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, classTab.getClassFeatureNotes().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                classTab.setClassFeatureNotes(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.CLASS_ACTIONS)), player -> new ClassActionsGUI(player, classTab.getClassActions(), 0, this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
