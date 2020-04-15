package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class HitDiceGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;

    public HitDiceGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.HIT_DICE, 18);
        this.mcdndPlayer = mcdndPlayer;
        String[] hitDiceStrings = MenuText.hitDice(mcdndPlayer.getCharacterSheet().getCoreStatsTab().getHitDice());
        setHitDieEditSlot(hitDiceStrings, 0, 6);
        setHitDieEditSlot(hitDiceStrings, 1, 8);
        setHitDieEditSlot(hitDiceStrings, 2, 10);
        setHitDieEditSlot(hitDiceStrings, 3, 12);
        setHitDieRollSlot(9, 6);
        setHitDieRollSlot(10, 8);
        setHitDieRollSlot(11, 10);
        setHitDieRollSlot(12, 12);
        setButton(17, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }

    private void setHitDieEditSlot(String[] strings, int slot, int sides) {
        setButton(slot, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, Text.of(strings[slot])), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                mcdndPlayer.getCharacterSheet().getCoreStatsTab().getHitDice().updateHitDie(sides, i);
                new HitDiceGUI(mcdndPlayer, player);
            }
        }));
    }

    private void setHitDieRollSlot(int slot, int sides) {
        setButton(slot, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.rollHitDie(sides))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                player.closeInventory();
                Dice dice = new Dice(i, sides);
                int conMod = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getCoreStats().getConstitution().getMod();
                player.sendMessage(Text.of(String.join("\n", Messages.rolledHitDice(mcdndPlayer.getBioAndInfo(), player.getName(), Dice.total(dice.roll(), conMod), dice))));
            }
        }));
    }
}
