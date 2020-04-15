package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PlayerAbilityScoreGUI extends SpongeMCDNDSimpleGUI {

    public static final PlayerAbilityScoreBridger CHA = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getCharisma();
    public static final PlayerAbilityScoreBridger CON = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getConstitution();
    public static final PlayerAbilityScoreBridger DEX = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getDexterity();
    public static final PlayerAbilityScoreBridger INTEL = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getIntelligence();
    public static final PlayerAbilityScoreBridger STR = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getStrength();
    public static final PlayerAbilityScoreBridger WIS = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getWisdom();
    @Nonnull
    private final PlayerAbilityScore abilityScore;

    public PlayerAbilityScoreGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull PlayerAbilityScoreBridger bridger, @Nonnull Player player) {
        super(player, bridger.apply(mcdndPlayer).getName(), 9);
        this.abilityScore = bridger.apply(mcdndPlayer);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
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
                new PlayerAbilityScoreGUI(mcdndPlayer, bridger, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, Text.of(MenuText.mod(abilityScore))));
        updateIsProficient();
        setButton(3, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, Text.of(MenuText.saveMod(abilityScore, classLevels, experience))));
        setButton(4, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_SAVE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveMod(classLevels, experience);
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            List<Object> objects = new ArrayList<>();
            objects.add(p.getName() + " (as " + mcdndPlayer.getBioAndInfo().getName() + ") has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                objects.add(TextColors.GREEN);
            }
            else if (first == 1) {
                objects.add(TextColors.RED);
            }

            objects.add(first + saveMod);
            objects.add(TextColors.RESET);
            objects.add(" | ");
            if (second == 20) {
                objects.add(TextColors.GREEN);
            }
            else if (second == 1) {
                objects.add(TextColors.RED);
            }

            objects.add(second + saveMod);
            broadcastMessage(Text.of(objects));
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerCoreStatsGUI(mcdndPlayer, p)));
    }

    private void updateIsProficient() {
        setButton(2, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.proficient(abilityScore))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            abilityScore.setIsProficient(!abilityScore.isProficient());
            updateIsProficient();
        }));
    }

    private interface PlayerAbilityScoreBridger extends Function<MCDNDPlayer, PlayerAbilityScore> {

    }
}
