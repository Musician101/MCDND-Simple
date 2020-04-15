package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.InitiativeGUI;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class NonPlayerSheetGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final NonPlayer nonPlayer;

    public NonPlayerSheetGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.CHARACTER_SHEET, 18);
        this.nonPlayer = nonPlayer;
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        setButton(0, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.alignment(nonPlayerSheet))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setAlignment(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, Text.of(MenuText.armorClass(nonPlayerSheet))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setArmorClass(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.BOOK).name(Text.of(MenuText.ARMOR_CLASS_NOTE)).description(Text.of(nonPlayerSheet.getArmorClassNote())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setArmorClassNote(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.SKULL).name(Text.of(MenuText.challengeRating(nonPlayerSheet))).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double d;
                try {
                    d = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setChallengeRating(d);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.climbSpeed(nonPlayerSheet))).potionEffect(PotionEffectTypes.JUMP_BOOST).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setClimbSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(5, SpongeIconBuilder.of(ItemTypes.ELYTRA, Text.of(MenuText.flySpeed(nonPlayerSheet))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setFlySpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(6, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.speed(nonPlayerSheet))).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(7, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.swimSpeed(nonPlayerSheet))).potionEffect(PotionEffectTypes.WATER_BREATHING).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setSwimSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(8, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.CORE_STATS)).potionEffect(PotionEffectTypes.STRENGTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerCoreStatsGUI(nonPlayer, p)));
        updateDMOutputOnly();
        setButton(10, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.HIT_POINTS)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerHitPointsGUI(nonPlayer, p)));
        setButton(11, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.KNOWN_LANGUAGES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.KNOWN_LANGUAGES, nonPlayerSheet.getLanguages()), (ply, pages) -> {
            nonPlayerSheet.setLanguages(textListToStringList(pages));
            new NonPlayerSheetGUI(nonPlayer, ply);
        })));
        setButton(12, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.SENSES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.SENSES, nonPlayerSheet.getSenses()), (ply, pages) -> {
            nonPlayerSheet.setSenses(textListToStringList(pages));
            new NonPlayerSheetGUI(nonPlayer, player);
        })));
        setButton(13, SpongeIconBuilder.of(ItemTypes.LEATHER, Text.of(MenuText.size(nonPlayerSheet))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setSize(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(14, SpongeIconBuilder.builder(ItemTypes.SPAWN_EGG).type(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.VILLAGER).name(Text.of(MenuText.typeRace(nonPlayerSheet))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setTypeRace(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(15, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.experience(nonPlayerSheet))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                nonPlayerSheet.setXP(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(16, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.initiative(nonPlayer.getInitiative(), nonPlayer.getNonPlayerSheet().getCoreStats().getDexterity()))).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InitiativeGUI(nonPlayer, InitiativeGUI.NPC, p)));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerGUI(nonPlayer, p)));
    }

    private void updateDMOutputOnly() {
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        setButton(9, SpongeIconBuilder.builder(ItemTypes.DIAMOND).name(Text.of(nonPlayerSheet.isDMOutputOnly() ? TextColors.GREEN : TextColors.RED, MenuText.dmOutputOnly(nonPlayerSheet))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            nonPlayerSheet.setDMOutputOnly(!nonPlayerSheet.isDMOutputOnly());
            updateDMOutputOnly();
        }));
    }
}
