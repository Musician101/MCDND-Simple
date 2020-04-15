package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkill;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NonPlayerSkillGUI extends SpigotMCDNDSimpleGUI {

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
        setButton(0, SpigotIconBuilder.builder(Material.INK_SAC).name(MenuText.bonus(skill)).durability(4).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                skill.setBonus(i);
                new NonPlayerSkillGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.BOOKSHELF, MenuText.total(bridger.getAbilityScore(nonPlayer), skill)));
        setButton(3, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.bonus(skill)), ImmutableMap.of(ClickType.LEFT, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int bonus = skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(nonPlayer.getName() + " has rolled a " + skill.getName() + " check.\nThe results are: ");
            if (first == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (first == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(first + bonus).append(ChatColor.RESET).append(" | ");
            if (second == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (second == 1) {
                sb.append(ChatColor.RED);
            }

            Bukkit.broadcastMessage(sb.append(second + bonus).toString());
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
