package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class HealingGUI extends SpongeMCDNDSimpleGUI {

    public HealingGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.HEALING, 9);
        SpellHealing spellHealing = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellHealing();
        setButton(0, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.healAmount(spellHealing.getHealAmount()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                spellHealing.setHealAmount(dice);
                new HealingGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.REDSTONE, Text.of(MenuText.STAT_BONUS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL_HEALING, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
