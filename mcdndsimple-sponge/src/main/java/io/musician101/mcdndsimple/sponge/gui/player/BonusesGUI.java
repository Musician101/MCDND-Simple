package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
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

public class BonusesGUI extends SpongeMCDNDSimpleGUI {

    public BonusesGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BONUSES_PENALTIES, 9);
        Bonuses bonuses = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getBonuses();
        setButton(0, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.MELEE_BONUSES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new MeleeBonusGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.RANGED_BONUSES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RangedBonusGUI(mcdndPlayer, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.SPELLCASTING_BONUSES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellcastingBonusGUI(mcdndPlayer, p)));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(Text.of(MenuText.SAVING_THROW_BONUSES)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                bonuses.setSaves(dice);
                new BonusesGUI(mcdndPlayer, player);
            }
        }));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.LUCK).name(Text.of(MenuText.ABILITY_SKILL_CHECK_ROLLS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                bonuses.setAbilitiesAndSkills(dice);
                new BonusesGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
