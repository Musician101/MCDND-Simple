package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ClassLevelsGUI extends SpongeMCDNDSimpleGUI {

    public ClassLevelsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS_LEVELS, 18);
        ClassLevels classLevels = mcdndPlayer.getCharacterSheet().getClassTab().getClassLevels();
        setButton(0, SpongeIconBuilder.of(ItemTypes.WOODEN_SWORD, Text.of(MenuText.BARBARIAN)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setBarbarian(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, Text.of(MenuText.BARD)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setBard(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.CLERIC)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setCleric(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.DRUID)).potionEffect(PotionEffectTypes.LUCK).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setDruid(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, Text.of(MenuText.FIGHTER)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setFighter(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(5, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.MONK)).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setMonk(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(9, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.PALADIN)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setPaladin(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(10, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.RANGER)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setRanger(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(11, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.ROGUE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setRogue(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(12, SpongeIconBuilder.of(ItemTypes.MAGMA_CREAM, Text.of(MenuText.SORCERER)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setSorcerer(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(13, SpongeIconBuilder.of(ItemTypes.END_CRYSTAL, Text.of(MenuText.WARLOCK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setWarlock(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(14, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.WIZARD)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + s + " is not a number."));
                    return;
                }

                classLevels.setWizard(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(17, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
