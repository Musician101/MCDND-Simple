package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class SpellGUI extends SpigotMCDNDSimpleGUI {

    public SpellGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getName(), 27);
        Spell spell = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(s -> s.getLevel() == level).collect(Collectors.toList()).get(index);
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setName(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(1, SpigotIconBuilder.builder(Material.LAPIS_LAZULI).name(MenuText.RENAME).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {
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

    spell.setLevel(i);
    new SpellGUI(mcdndPlayer, level, index, player);
}
        }));
        setButton(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.spellType(spell.getSpellType())), ImmutableMap.of(ClickType.LEFT, p -> new SpellTypeGUI(mcdndPlayer, level, index, p)));
        setButton(3, SpigotIconBuilder.of(Material.CLOCK, MenuText.castTime(spell.getCastTime())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setCastTime(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.REDSTONE_TORCH, MenuText.concentration(spell)), ImmutableMap.of(ClickType.LEFT, p -> {
            spell.setNeedsConcentration(!spell.needsConcentration());
            new SpellGUI(mcdndPlayer, level, index, p);
        }));
        setButton(5, SpigotIconBuilder.of(Material.TOTEM_OF_UNDYING, MenuText.ritual(spell)), ImmutableMap.of(ClickType.LEFT, p -> {
            spell.setIsRitual(!spell.isRitual());
            new SpellGUI(mcdndPlayer, level, index, p);
        }));
        setButton(7, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.gainedFrom(spell.getGainedFrom())), ImmutableMap.of(ClickType.LEFT, p -> new SpellcasterClassGUI(mcdndPlayer, level, index, SpellcasterClassGUI.SPELL, p)));
        setButton(8, SpigotIconBuilder.of(Material.LINGERING_POTION, MenuText.TARGET), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setTargetArea(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(9, SpigotIconBuilder.of(Material.BOW, MenuText.range(spell)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setRange(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(10, SpigotIconBuilder.of(Material.STRING, MenuText.duration(spell)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setDuration(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(11, SpigotIconBuilder.builder(Material.PURPLE_SHULKER_BOX).name(MenuText.COMPONENTS).description(spell.getComponents()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getComponents()), (ply, pages) -> {
            spell.setComponents(pages);
            new SpellGUI(mcdndPlayer, level, index, p);
        })));
        setButton(12, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.SPELL_INFO), ImmutableMap.of(ClickType.LEFT, p -> {
            MacroOptions macroOptions = spell.getMacroOptions();
            String newLine = "\n";
            TextComponent spellHoverText = new TextComponent(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()));
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.addExtra(newLine + MenuText.components(spell) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell));
            }

            BioAndInfo bioAndInfo = mcdndPlayer.getBioAndInfo();
            CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
            ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
            CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
            CoreStats<PlayerAbilityScore> coreStats = coreStatsTab.getCoreStats();
            Experience experience = coreStatsTab.getExperience();
            SpellcastingBonus spellcastingBonus = coreStatsTab.getBonuses().getSpellcasting();
            if (macroOptions.isAttackRollEnabled()) {
                Dice d20 = new Dice(20);
                int attackRoll = Dice.total(d20.roll());
                int bonus = Dice.total(spellcastingBonus.getAttack().roll());
                TextComponent attackText = new TextComponent(newLine + "Attack: " + (attackRoll + bonus + experience.getProficiencyBonus(classLevels)));
                if (attackRoll == 1) {
                    attackText.setColor(net.md_5.bungee.api.ChatColor.RED);
                }
                else if (attackRoll == 20) {
                    attackText.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                }

                if (macroOptions.isSecondAttackRollEnabled()) {
                    int secAttackRoll = Dice.total(d20.roll());
                    TextComponent secAttackText = new TextComponent(" | " + (attackRoll + bonus + experience.getProficiencyBonus(classLevels)));
                    if (secAttackRoll == 1) {
                        secAttackText.setColor(net.md_5.bungee.api.ChatColor.RED);
                    }
                    else if (attackRoll == 20) {
                        secAttackText.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    }

                    attackText.addExtra(secAttackText);
                }

                attackText.addExtra(" vs AC");
                spellHoverText.addExtra(attackText);
            }

            TextComponent saveSuccess = new TextComponent();
            if (macroOptions.isSavingThrowEnabled()) {
                SpellSave spellSave = spell.getSpellSave();
                spellHoverText.addExtra(newLine + "Save: DC " + Dice.total(spellcastingBonus.getSaveDC().roll(), spellSave.getSaveDCType().getSpellSaveDC(classLevels, coreStats, experience)) + " " + spellSave.getSavingStat());
                saveSuccess.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(bioAndInfo, spellSave.getOnSuccessfulSave(), spell)));
                saveSuccess.addExtra(here);
                saveSuccess.addExtra(" to view the effect of a successful save of this spell.");
            }

            if (macroOptions.isHealingEnabled()) {
                SpellHealing spellHealing = spell.getSpellHealing();
                AbilityScore abilityScore = null;
                switch (spellHealing.getStatBonus()) {
                    case CHA:
                        abilityScore = coreStats.getCharisma();
                        break;
                    case CON:
                        abilityScore = coreStats.getConstitution();
                        break;
                    case DEX:
                        abilityScore = coreStats.getDexterity();
                        break;
                    case INT:
                        abilityScore = coreStats.getIntelligence();
                        break;
                    case STR:
                        abilityScore = coreStats.getStrength();
                        break;
                    case WIS:
                        abilityScore = coreStats.getWisdom();
                        break;
                }

                spellHoverText.addExtra(newLine + "Healing: " + Dice.total(spellHealing.getHealAmount().roll(), abilityScore == null ? 0 : abilityScore.getMod()) + " HP healed");
            }

            if (macroOptions.isDamageEnabled()) {
                SpellDamage spellDamage = spell.getSpellDamage();
                int statBonus = Dice.total(spellcastingBonus.getDamage().roll(), spellDamage.getBonus());
                switch (spell.getAttackStat()) {
                    case CHA:
                        statBonus += coreStats.getCharisma().getMod();
                        break;
                    case CON:
                        statBonus += coreStats.getConstitution().getMod();
                        break;
                    case DEX:
                        statBonus += coreStats.getDexterity().getMod();
                        break;
                    case INT:
                        statBonus += coreStats.getIntelligence().getMod();
                        break;
                    case STR:
                        statBonus += coreStats.getStrength().getMod();
                        break;
                    case WIS:
                        statBonus += coreStats.getWisdom().getMod();
                        break;
                }

                Dice dice = spellDamage.getDamage();
                spellHoverText.addExtra(newLine + "Damage: " + Dice.total(dice.roll(), statBonus) + " " + spellDamage.getDamageType());
                if (spellDamage.canCrit()) {
                    spellHoverText.addExtra(newLine + "Crit: Additional " + Dice.total(dice.roll()) + " damage");
                }
            }

            TextComponent effect = new TextComponent();
            if (macroOptions.isDescriptionEnabled()) {
                effect.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(bioAndInfo, spell.getEffects(), spell)));
                effect.addExtra(here);
                effect.addExtra(" to view the spell's effects.");
            }

            TextComponent spellDescription = new TextComponent();
            if (macroOptions.isDescriptionEnabled()) {
                spellDescription.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(bioAndInfo, spell.getDescription(), spell)));
                spellDescription.addExtra(here);
                spellDescription.addExtra(" to view the spell's description.");
            }

            TextComponent atHigherLevels = new TextComponent();
            if (macroOptions.isAtHigherLevelsEnabled()) {
                atHigherLevels.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(bioAndInfo, spell.getAtHigherLevels(), spell)));
                atHigherLevels.addExtra(" to view how the spell behaves at higher levels.");
            }

            TextComponent spellComponent = new TextComponent(spell.getName());
            spellComponent.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            spellComponent.setBold(true);
            spellComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/callback " + SpigotMCDNDSimple.instance().getCallbackTracker().getIdForCallback(sender -> sender.spigot().sendMessage(spellHoverText)).toString()));
            spellComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Click to view!")}));
            spellComponent.addExtra(spellDescription);
            spellComponent.addExtra(atHigherLevels);

            TextComponent message = new TextComponent(p.getName() + " (as " + bioAndInfo + ") cast ");
            message.addExtra(spellComponent);
            message.addExtra(atHigherLevels);
            message.addExtra(saveSuccess);
            message.addExtra(effect);
            Bukkit.spigot().broadcast(message);
            p.closeInventory();
        }));
        setButton(14, SpigotIconBuilder.of(Material.COMPARATOR, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS), ImmutableMap.of(ClickType.LEFT, p -> new MacroOptionsGUI(mcdndPlayer, level, index, p)));
        setButton(18, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), ImmutableMap.of(ClickType.LEFT, p -> new SpellDescriptionGUI(mcdndPlayer, level, index, p)));
        setButton(19, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK), ImmutableMap.of(ClickType.LEFT, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL, p)));
        setButton(20, SpigotIconBuilder.of(Material.SHIELD, MenuText.SAVE), ImmutableMap.of(ClickType.LEFT, p -> new SpellSaveGUI(mcdndPlayer, level, index, p)));
        setButton(21, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.HEALING).build(), ImmutableMap.of(ClickType.LEFT, p -> new HealingGUI(mcdndPlayer, level, index, p)));
        setButton(22, SpigotIconBuilder.of(Material.GOLDEN_SWORD, MenuText.DAMAGE), ImmutableMap.of(ClickType.LEFT, p -> new SpellDamageGUI(mcdndPlayer, level, index, p)));
        setButton(23, SpigotIconBuilder.of(Material.BOOK, MenuText.SPELL_EFFECTS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.SPELL_EFFECTS, spell.getEffects()), (ply, pages) -> {
            spell.setEffects(pages);
            new SpellGUI(mcdndPlayer, level, index, p);
        })));

        ItemStack icon = SpigotIconBuilder.of(Material.REPEATER, MenuText.prepared(spell.getPrepared()));
        if (spell.getLevel() == 0) {
            setButton(6, icon);
        }
        else {
            setButton(6, icon, ImmutableMap.of(ClickType.LEFT, p -> new PreparedGUI(mcdndPlayer, level, index, p)));
        }
    }

}
