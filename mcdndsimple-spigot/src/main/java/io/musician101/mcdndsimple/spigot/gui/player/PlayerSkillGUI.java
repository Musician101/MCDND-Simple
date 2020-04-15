package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
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

public class PlayerSkillGUI extends SpigotMCDNDSimpleGUI {

    public static final PlayerSkillBridger ACROBATICS = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getDexterity();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getAcrobatics();
        }
    };
    public static final PlayerSkillBridger ANIMAL_HANDLING = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getAnimalHandling();
        }
    };
    public static final PlayerSkillBridger ARCANA = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getArcana();
        }
    };
    public static final PlayerSkillBridger ATHLETICS = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getStrength();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getAthletics();
        }
    };
    public static final PlayerSkillBridger DECEPTION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getCharisma();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getDeception();
        }
    };
    public static final PlayerSkillBridger HISTORY = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getHistory();
        }
    };
    public static final PlayerSkillBridger INSIGHT = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getInsight();
        }
    };
    public static final PlayerSkillBridger INTIMIDATION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getCharisma();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getIntimidation();
        }
    };
    public static final PlayerSkillBridger INVESTIGATION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getInvestigation();
        }
    };
    public static final PlayerSkillBridger MEDICINE = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getMedicine();
        }
    };
    public static final PlayerSkillBridger NATURE = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getNature();
        }
    };
    public static final PlayerSkillBridger PERCEPTION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getPerception();
        }
    };
    public static final PlayerSkillBridger PERFORMANCE = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getCharisma();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getPerformance();
        }
    };
    public static final PlayerSkillBridger PERSUASION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getCharisma();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getPersuasion();
        }
    };
    public static final PlayerSkillBridger RELIGION = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getReligion();
        }
    };
    public static final PlayerSkillBridger SLEIGHTOFHAND = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getDexterity();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getSleightOfHand();
        }
    };
    public static final PlayerSkillBridger STEALTH = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getDexterity();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getStealth();
        }
    };
    public static final PlayerSkillBridger SURVIVAL = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getSurvival();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_CHA = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getCharisma();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledCHA();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_CON = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getConstitution();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledCON();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_DEX = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getDexterity();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledDEX();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_INT = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getIntelligence();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledINT();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_STR = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getStrength();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledSTR();
        }
    };
    public static final PlayerSkillBridger UNSKILLED_WIS = new PlayerSkillBridger() {

        @Override
        public PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCoreStats(mcdndPlayer).getWisdom();
        }

        @Override
        public PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getSkillsTab(mcdndPlayer).getUnskilledWIS();
        }
    };

    public PlayerSkillGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull PlayerSkillBridger bridger, @Nonnull Player player) {
        super(player, bridger.getSkill(mcdndPlayer).getName(), 9);
        PlayerSkill skill = bridger.getSkill(mcdndPlayer);
        setButton(0, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.PROFICIENT).description("- " + skill.getSkillProficiency().getName()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SkillProficiencyGUI(mcdndPlayer, bridger, p)));
        setButton(1, SpigotIconBuilder.builder(Material.INK_SAC).name(MenuText.bonus(skill)).durability(4).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {
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
    new PlayerSkillGUI(mcdndPlayer, bridger, player);
}
        }));
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        setButton(2, SpigotIconBuilder.of(Material.BOOKSHELF, MenuText.total(bridger.getAbilityScore(mcdndPlayer), characterSheet.getClassTab().getClassLevels(), characterSheet.getCoreStatsTab().getExperience(), skill)));
        setButton(3, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.bonus(skill)), ImmutableMap.of(ClickType.LEFT, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int bonus = Dice.total(characterSheet.getCoreStatsTab().getBonuses().getAbilitiesAndSkills().roll()) + skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + mcdndPlayer.getBioAndInfo().getName() + ") has rolled a " + skill.getName() + " check.\nThe results are: ");
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

            sb.append(second + bonus);
            Bukkit.broadcastMessage(sb.toString());
        }));
    }

    protected static abstract class PlayerSkillBridger {

        public abstract PlayerAbilityScore getAbilityScore(@Nonnull MCDNDPlayer mcdndPlayer);

        private CharacterSheet getCharacterSheet(@Nonnull MCDNDPlayer mcdndPlayer) {
            return mcdndPlayer.getCharacterSheet();
        }

        protected CoreStats<PlayerAbilityScore> getCoreStats(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCharacterSheet(mcdndPlayer).getCoreStatsTab().getCoreStats();
        }

        public abstract PlayerSkill getSkill(@Nonnull MCDNDPlayer mcdndPlayer);

        protected SkillsTab getSkillsTab(@Nonnull MCDNDPlayer mcdndPlayer) {
            return getCharacterSheet(mcdndPlayer).getSkillsTab();
        }
    }
}
