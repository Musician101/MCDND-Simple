package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
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

public class SpellcastingBonusGUI extends SpongeMCDNDSimpleGUI {

    public SpellcastingBonusGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SPELLCASTING_BONUSES, 9);
        SpellcastingBonus spellcastingBonus = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getBonuses().getSpellcasting();
        setButton(0, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.ATTACK_ROLLS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                spellcastingBonus.setAttack(dice);
                new SpellcastingBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(Text.of(MenuText.DAMAGE_ROLLS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                spellcastingBonus.setDamage(dice);
                new SpellcastingBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.LUCK).name(Text.of(MenuText.SAVE_DC_ROLLS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                spellcastingBonus.setSaveDC(dice);
                new SpellcastingBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new BonusesGUI(mcdndPlayer, p)));
    }
}
