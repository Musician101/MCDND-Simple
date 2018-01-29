package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;

public class BackgroundTabGUI extends MCDNDSimpleChestGUI {

    private BackgroundTab backgroundTab;

    public BackgroundTabGUI(Player player, BackgroundTab backgroundTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.BACKGROUND, 27, prevGUI);
        this.backgroundTab = backgroundTab;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.ARROW, Text.of(MenuText.gender(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setGender(s);
            delayedOpen();
        }));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.FISH, Text.of(MenuText.age(backgroundTab))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            backgroundTab.setAge(i);
            delayedOpen();
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.ARMOR_STAND, Text.of(MenuText.height(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setHeight(s);
            delayedOpen();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.IRON_BLOCK, Text.of(MenuText.weight(backgroundTab))), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            backgroundTab.setWeight(d);
            delayedOpen();
        }));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.SPIDER_EYE, Text.of(MenuText.eyes(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setEyes(s);
            delayedOpen();
        }));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.RABBIT_HIDE, Text.of(MenuText.hair(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setHair(s);
            delayedOpen();
        }));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.LEATHER, Text.of(MenuText.size(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setSize(s);
            delayedOpen();
        }));
        set(7, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.vision(backgroundTab))), PotionEffectTypes.NIGHT_VISION), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setVision(s);
            delayedOpen();
        }));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.KNOWN_LANGUAGES)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getLanguages().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setLanguages(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.alignment(backgroundTab))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setAlignment(s);
            open();
        }));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.BACKGROUND)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getBackground().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setBackground(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.RACIAL_TRAITS)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getRacialTraits().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setRacialTraits(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(12, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.PERSONALITY_TRAITS)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                    .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getPersonalityTraits().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                        backgroundTab.setPersonalityTraits(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                        open();
                    });
        });
        set(13, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.IDEALS)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                    .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getIdeals().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                        backgroundTab.setIdeals(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                        open();
                    });
        });
        set(14, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.BONDS)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                    .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getBonds().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                        backgroundTab.setBonds(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                        open();
                    });
        });
        set(15, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.FLAWS)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getFlaws().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setFlaws(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(16, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.ARMOR_PROFICIENCIES)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getArmorProficiencies().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setArmorProficiencies(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(16, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.WEAPON_PROFICIENCIES)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getWeaponProficiencies().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setWeaponProficiencies(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(18, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.TOOL_PROFICIENCIES)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getToolProficiencies().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setToolProficiencies(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        set(19, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.OTHER_NOTES)), player -> SpongeMusicianLibrary.instance().getSpongeBookGUIManager()
                .addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, backgroundTab.getOtherNotes().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                    backgroundTab.setOtherNotes(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                    open();
                }));
        setBackButton(26, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
