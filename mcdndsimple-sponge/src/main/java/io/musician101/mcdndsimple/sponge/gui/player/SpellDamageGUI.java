package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
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

public class SpellDamageGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final SpellDamage spellDamage;

    public SpellDamageGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.DAMAGE, 9);
        this.spellDamage = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellDamage();
        updateCanCrit();
        setButton(1, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.DAMAGE_DICE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    p.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                spellDamage.setDamage(dice);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.STICK, Text.of(MenuText.otherBonus(spellDamage))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                spellDamage.setBonus(i);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.damageType(spellDamage.getDamageType()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spellDamage.setDamageType(s);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
    }

    private void updateCanCrit() {
        setButton(0, SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(Text.of(MenuText.CAN_CRIT)).addGlow(spellDamage.canCrit()).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            spellDamage.setCanCrit(!spellDamage.canCrit());
            updateCanCrit();
        }));
    }
}
