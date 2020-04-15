package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkill;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Color;

public class NonPlayerSkillGUI extends SpongeMCDNDSimpleGUI {

    public static final NonPlayerSkillBridger ACROBATICS = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getDexterity();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getAcrobatics();
        }
    };
    public static final NonPlayerSkillBridger ANIMAL_HANDLING = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getAnimalHandling();
        }
    };
    public static final NonPlayerSkillBridger ARCANA = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getArcana();
        }
    };
    public static final NonPlayerSkillBridger ATHLETICS = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getStrength();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getAthletics();
        }
    };
    public static final NonPlayerSkillBridger DECEPTION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getCharisma();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getDeception();
        }
    };
    public static final NonPlayerSkillBridger HISTORY = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getHistory();
        }
    };
    public static final NonPlayerSkillBridger INSIGHT = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getInsight();
        }
    };
    public static final NonPlayerSkillBridger INTIMIDATION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getCharisma();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getIntimidation();
        }
    };
    public static final NonPlayerSkillBridger INVESTIGATION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getInvestigation();
        }
    };
    public static final NonPlayerSkillBridger MEDICINE = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getMedicine();
        }
    };
    public static final NonPlayerSkillBridger NATURE = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getNature();
        }
    };
    public static final NonPlayerSkillBridger PERCEPTION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getPerception();
        }
    };
    public static final NonPlayerSkillBridger PERFORMANCE = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getCharisma();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getPerformance();
        }
    };
    public static final NonPlayerSkillBridger PERSUASION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getCharisma();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getPersuasion();
        }
    };
    public static final NonPlayerSkillBridger RELIGION = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getReligion();
        }
    };
    public static final NonPlayerSkillBridger SLEIGHTOFHAND = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getDexterity();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getSleightOfHand();
        }
    };
    public static final NonPlayerSkillBridger STEALTH = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getDexterity();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getStealth();
        }
    };
    public static final NonPlayerSkillBridger SURVIVAL = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getSurvival();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_CHA = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getCharisma();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledCHA();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_CON = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getConstitution();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledCON();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_DEX = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getDexterity();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledDEX();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_INT = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getIntelligence();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledINT();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_STR = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getStrength();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledSTR();
        }
    };
    public static final NonPlayerSkillBridger UNSKILLED_WIS = new NonPlayerSkillBridger() {

        @Override
        public NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer) {
            return getCoreStats(nonPlayer).getWisdom();
        }

        @Override
        public NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer) {
            return getSkillsTab(nonPlayer).getUnskilledWIS();
        }
    };

    public NonPlayerSkillGUI(@Nonnull NonPlayer nonPlayer, NonPlayerSkillBridger bridger, @Nonnull Player player) {
        super(player, bridger.getSkill(nonPlayer).getName(), 9);
        NonPlayerSkill skill = bridger.getSkill(nonPlayer);
        setButton(0, SpongeIconBuilder.builder(ItemTypes.DYE).offer(Keys.COLOR, Color.BLACK).name(Text.of(MenuText.bonus(skill))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                skill.setBonus(i);
                new NonPlayerSkillGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.BOOKSHELF, Text.of(MenuText.total(bridger.getAbilityScore(nonPlayer), skill))));
        setButton(3, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.bonus(skill))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int bonus = skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            List<Object> objects = new ArrayList<>();
            objects.add(nonPlayer.getName() + " has rolled a " + skill.getName() + " check.\nThe results are: ");
            TextColor color = TextColors.WHITE;
            if (first == 20) {
                color = TextColors.GREEN;
            }
            else if (first == 1) {
                color = TextColors.RED;
            }

            objects.add(color);
            objects.add(first + bonus);
            objects.add(TextColors.RESET);
            objects.add(" | ");
            color = TextColors.WHITE;
            if (second == 20) {
                color = TextColors.GREEN;
            }
            else if (second == 1) {
                color = TextColors.RED;
            }

            objects.add(color);
            objects.add(second + bonus);
            broadcastMessage(Text.of(objects.toArray(new Object[0])));
        }));
    }

    protected static abstract class NonPlayerSkillBridger {

        public abstract NonPlayerAbilityScore getAbilityScore(@Nonnull NonPlayer nonPlayer);

        protected CoreStats<NonPlayerAbilityScore> getCoreStats(@Nonnull NonPlayer nonPlayer) {
            return getNonPlayerSheet(nonPlayer).getCoreStats();
        }

        private NonPlayerSheet getNonPlayerSheet(@Nonnull NonPlayer nonPlayer) {
            return nonPlayer.getNonPlayerSheet();
        }

        public abstract NonPlayerSkill getSkill(@Nonnull NonPlayer nonPlayer);

        protected NonPlayerSkills getSkillsTab(@Nonnull NonPlayer nonPlayer) {
            return nonPlayer.getSkills();
        }
    }
}
