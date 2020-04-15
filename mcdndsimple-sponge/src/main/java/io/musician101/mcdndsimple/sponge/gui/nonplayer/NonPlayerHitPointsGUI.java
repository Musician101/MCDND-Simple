package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerHitPoints;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
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

public class NonPlayerHitPointsGUI extends SpongeMCDNDSimpleGUI {

    public NonPlayerHitPointsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.HIT_POINTS, 9);
        NonPlayerHitPoints hitPoints = nonPlayer.getNonPlayerSheet().getHealth();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.currentHitPoints(hitPoints))).potionEffect(PotionEffectTypes.REGENERATION).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                hitPoints.setCurrent(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.maxHitPoints(hitPoints))).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                hitPoints.setMax(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.tempHitPoints(hitPoints))).potionEffect(PotionEffectTypes.STRENGTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                hitPoints.setTemp(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.hitDice(hitPoints))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                hitPoints.setHitDice(dice);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
    }
}
