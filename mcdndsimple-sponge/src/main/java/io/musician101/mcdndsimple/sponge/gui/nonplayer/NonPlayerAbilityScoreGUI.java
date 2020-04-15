package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

public class NonPlayerAbilityScoreGUI extends SpongeMCDNDSimpleGUI {

    public static final NonPlayerAbilityScoreBridger CHA = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getCharisma();
    public static final NonPlayerAbilityScoreBridger CON = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getConstitution();
    public static final NonPlayerAbilityScoreBridger DEX = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getDexterity();
    public static final NonPlayerAbilityScoreBridger INTEL = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getIntelligence();
    public static final NonPlayerAbilityScoreBridger STR = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getStrength();
    public static final NonPlayerAbilityScoreBridger WIS = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getWisdom();

    public NonPlayerAbilityScoreGUI(@Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScoreBridger bridger, @Nonnull Player player) {
        super(player, bridger.apply(nonPlayer).getName(), 9);
        NonPlayerAbilityScore abilityScore = bridger.apply(nonPlayer);
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.score(abilityScore))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                abilityScore.setScore(i);
                new NonPlayerAbilityScoreGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, Text.of(MenuText.mod(abilityScore))));
        setButton(2, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.bonus(abilityScore))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                abilityScore.setBonus(i);
                new NonPlayerAbilityScoreGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, Text.of(MenuText.saveTotal(abilityScore))));
        setButton(4, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_SAVE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveTotal();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(nonPlayer.getName() + " has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            TextColor color = TextColors.RESET;
            if (first == 20) {
                color = TextColors.GREEN;
            }
            else if (first == 1) {
                color = TextColors.RED;
            }

            tb.append(Text.of(color, first + saveMod, TextColors.RESET, " | "));
            if (second == 20) {
                color = TextColors.GREEN;
            }
            else if (second == 1) {
                color = TextColors.RED;
            }

            for (Player ply : Sponge.getServer().getOnlinePlayers()) {
                ply.sendMessage(tb.append(Text.of(color, second + saveMod)).build());
            }
        }));
    }

    private interface NonPlayerAbilityScoreBridger extends Function<NonPlayer, NonPlayerAbilityScore> {

    }
}
