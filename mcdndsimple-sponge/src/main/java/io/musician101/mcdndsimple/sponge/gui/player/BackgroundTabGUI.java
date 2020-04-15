package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.Fishes;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class BackgroundTabGUI extends SpongeMCDNDSimpleGUI {

    public BackgroundTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BACKGROUND, 27);
        BackgroundTab backgroundTab = mcdndPlayer.getCharacterSheet().getBackgroundTab();
        setButton(0, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.gender(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setGender(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.FISH).type(Keys.FISH_TYPE, Fishes.COD).name(Text.of(MenuText.age(backgroundTab))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int number;
                try {
                    number = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                backgroundTab.setAge(number);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ARMOR_STAND, Text.of(MenuText.height(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setHeight(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.IRON_BLOCK, Text.of(MenuText.weight(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double number;
                try {
                    number = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                backgroundTab.setWeight(number);
                new BackgroundTabGUI(mcdndPlayer, player);

            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.SPIDER_EYE, Text.of(MenuText.eyes(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setEyes(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(5, SpongeIconBuilder.of(ItemTypes.RABBIT_HIDE, Text.of(MenuText.hair(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setHair(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(6, SpongeIconBuilder.of(ItemTypes.LEATHER, Text.of(MenuText.size(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setSize(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(7, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.vision(backgroundTab))).potionEffect(PotionEffectTypes.NIGHT_VISION).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setVision(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.KNOWN_LANGUAGES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.KNOWN_LANGUAGES, backgroundTab.getLanguages()), (ply, pages) -> {
            backgroundTab.setLanguages(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(9, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.alignment(backgroundTab))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setAlignment(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(10, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.BACKGROUND)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.BACKGROUND, backgroundTab.getBackground()), (ply, pages) -> {
            backgroundTab.setBackground(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(11, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.RACIAL_TRAITS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.RACIAL_TRAITS, backgroundTab.getRacialTraits()), (ply, pages) -> {
            backgroundTab.setRacialTraits(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(12, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.PERSONALITY_TRAITS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits()), (ply, pages) -> {
            backgroundTab.setPersonalityTraits(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(13, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.IDEALS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.IDEALS, backgroundTab.getIdeals()), (ply, pages) -> {
            backgroundTab.setIdeals(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(14, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.BONDS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.BONDS, backgroundTab.getBonds()), (ply, pages) -> {
            backgroundTab.setBonds(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(15, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.FLAWS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.FLAWS, backgroundTab.getFlaws()), (ply, pages) -> {
            backgroundTab.setFlaws(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(16, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.ARMOR_PROFICIENCIES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies()), (ply, pages) -> {
            backgroundTab.setArmorProficiencies(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(17, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.WEAPON_PROFICIENCIES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies()), (ply, pages) -> {
            backgroundTab.setWeaponProficiencies(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(18, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.TOOL_PROFICIENCIES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies()), (ply, pages) -> {
            backgroundTab.setToolProficiencies(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(19, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.OTHER_NOTES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.OTHER_NOTES, backgroundTab.getOtherNotes()), (ply, pages) -> {
            backgroundTab.setOtherNotes(textListToStringList(pages));
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(22, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
