package io.musician101.mcdndsimple.sponge.gui.player;

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
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.util.Color;

public class SpellGUI extends SpongeMCDNDSimpleGUI {

    public SpellGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getName(), 27);
        Spell spell = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(s -> s.getLevel() == level).collect(Collectors.toList()).get(index);
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setName(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.DYE).offer(Keys.COLOR, Color.BLUE).name(Text.of(MenuText.RENAME)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {
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

    spell.setLevel(i);
    new SpellGUI(mcdndPlayer, level, index, player);
}
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.spellType(spell.getSpellType()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellTypeGUI(mcdndPlayer, level, index, p)));
        setButton(3, SpongeIconBuilder.of(ItemTypes.CLOCK, Text.of(MenuText.castTime(spell.getCastTime()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setCastTime(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.concentration(spell))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            spell.setNeedsConcentration(!spell.needsConcentration());
            new SpellGUI(mcdndPlayer, level, index, p);
        }));
        setButton(5, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.ritual(spell))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            spell.setIsRitual(!spell.isRitual());
            new SpellGUI(mcdndPlayer, level, index, p);
        }));
        setButton(7, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.gainedFrom(spell.getGainedFrom()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellcasterClassGUI(mcdndPlayer, level, index, SpellcasterClassGUI.SPELL, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.LINGERING_POTION, Text.of(MenuText.TARGET)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setTargetArea(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(9, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.range(spell))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setRange(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(10, SpongeIconBuilder.of(ItemTypes.STRING, Text.of(MenuText.duration(spell))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spell.setDuration(s);
                new SpellGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(11, SpongeIconBuilder.builder(ItemTypes.PURPLE_SHULKER_BOX).name(Text.of(MenuText.COMPONENTS)).description(Text.of(spell.getComponents())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getComponents()), (ply, pages) -> {
            spell.setComponents(textListToStringList(pages));
            new SpellGUI(mcdndPlayer, level, index, p);
        })));
        setButton(12, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.SPELL_INFO)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            MacroOptions macroOptions = spell.getMacroOptions();
            String newLine = "\n";
            Text.Builder spellHoverText = Text.builder(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()));
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.append(Text.of(newLine + MenuText.components(spell) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell)));
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
                Text.Builder attackText = Text.builder(newLine + "Attack: " + (attackRoll + bonus + experience.getProficiencyBonus(classLevels)));
                if (attackRoll == 1) {
                    attackText.color(TextColors.RED);
                }
                else if (attackRoll == 20) {
                    attackText.color(TextColors.GREEN);
                }

                if (macroOptions.isSecondAttackRollEnabled()) {
                    int secAttackRoll = Dice.total(d20.roll());
                    Text.Builder secAttackText = Text.builder(" | " + (attackRoll + bonus + experience.getProficiencyBonus(classLevels)));
                    if (secAttackRoll == 1) {
                        secAttackText.color(TextColors.RED);
                    }
                    else if (attackRoll == 20) {
                        secAttackText.color(TextColors.GREEN);
                    }

                    attackText.append(Text.of(secAttackText));
                }

                attackText.append(Text.of(" vs AC"));
                spellHoverText.append(attackText.build());
            }

            Text.Builder saveSuccess = Text.builder();
            if (macroOptions.isSavingThrowEnabled()) {
                SpellSave spellSave = spell.getSpellSave();
                spellHoverText.append(Text.of(newLine + "Save: DC " + Dice.total(spellcastingBonus.getSaveDC().roll(), spellSave.getSaveDCType().getSpellSaveDC(classLevels, coreStats, experience)) + " " + spellSave.getSavingStat()));
                saveSuccess = Text.builder(newLine + "Click ");
                Text.Builder here = Text.builder("HERE");
                here.color(TextColors.GREEN);
                here.style(TextStyles.BOLD);
                here.onClick(TextActions.executeCallback(source -> openBookCommand(source, bioAndInfo, spellSave.getOnSuccessfulSave(), spell)));
                saveSuccess.append(here.build());
                saveSuccess.append(Text.of(" to view the effect of a successful save of this spell."));
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

                spellHoverText.append(Text.of(newLine + "Healing: " + Dice.total(spellHealing.getHealAmount().roll(), abilityScore == null ? 0 : abilityScore.getMod()) + " HP healed"));
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
                spellHoverText.append(Text.of(newLine + "Damage: " + Dice.total(dice.roll(), statBonus) + " " + spellDamage.getDamageType()));
                if (spellDamage.canCrit()) {
                    spellHoverText.append(Text.of(newLine + "Crit: Additional " + Dice.total(dice.roll()) + " damage"));
                }
            }

            Text.Builder effect = Text.builder();
            if (macroOptions.isDescriptionEnabled()) {
                effect = Text.builder(newLine + "Click ");
                Text.Builder here = Text.builder("HERE");
                here.color(TextColors.GREEN);
                here.style(TextStyles.BOLD);
                here.onClick(TextActions.executeCallback(source -> openBookCommand(source, bioAndInfo, spell.getEffects(), spell)));
                effect.append(here.build());
                effect.append(Text.of(" to view the spell's effects."));
            }

            Text.Builder spellDescription = Text.builder();
            if (macroOptions.isDescriptionEnabled()) {
                spellDescription = Text.builder(newLine + "Click ");
                Text.Builder here = Text.builder("HERE");
                here.color(TextColors.GREEN);
                here.style(TextStyles.BOLD);
                here.onClick(TextActions.executeCallback(source -> openBookCommand(source, bioAndInfo, spell.getDescription(), spell)));
                spellDescription.append(here.build());
                spellDescription.append(Text.of(" to view the spell's description."));
            }

            Text.Builder atHigherLevels = Text.builder();
            if (macroOptions.isAtHigherLevelsEnabled()) {
                atHigherLevels = Text.builder(newLine + "Click ");
                Text.Builder here = Text.builder("HERE");
                here.color(TextColors.GREEN);
                here.style(TextStyles.BOLD);
                here.onClick(TextActions.executeCallback(source -> openBookCommand(source, bioAndInfo, spell.getAtHigherLevels(), spell)));
                atHigherLevels.append(Text.of(" to view how the spell behaves at higher levels."));
            }

            Text.Builder spellComponent = Text.builder(spell.getName());
            spellComponent.color(TextColors.GREEN);
            spellComponent.style(TextStyles.BOLD);
            spellComponent.onClick(TextActions.executeCallback(source -> source.sendMessage(spellHoverText.build())));
            spellComponent.onHover(TextActions.showText(Text.of("Click to view!")));
            spellComponent.append(spellDescription.build());
            spellComponent.append(atHigherLevels.build());

            Text.Builder message = Text.builder(p.getName() + " (as " + bioAndInfo + ") cast ");
            message.append(spellComponent.build());
            message.append(atHigherLevels.build());
            message.append(saveSuccess.build());
            message.append(effect.build());
            broadcastMessage(message.build());
            p.closeInventory();
        }));
        setButton(14, SpongeIconBuilder.of(ItemTypes.COMPARATOR, Text.of(MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new MacroOptionsGUI(mcdndPlayer, level, index, p)));
        setButton(18, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellDescriptionGUI(mcdndPlayer, level, index, p)));
        setButton(19, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.ATTACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL, p)));
        setButton(20, SpongeIconBuilder.of(ItemTypes.SHIELD, Text.of(MenuText.SAVE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellSaveGUI(mcdndPlayer, level, index, p)));
        setButton(21, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(Text.of(MenuText.HEALING)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new HealingGUI(mcdndPlayer, level, index, p)));
        setButton(22, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.DAMAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellDamageGUI(mcdndPlayer, level, index, p)));
        setButton(23, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.SPELL_EFFECTS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.SPELL_EFFECTS, spell.getEffects()), (ply, pages) -> {
            spell.setEffects(textListToStringList(pages));
            new SpellGUI(mcdndPlayer, level, index, p);
        })));

        ItemStack icon = SpongeIconBuilder.of(ItemTypes.REPEATER, Text.of(MenuText.prepared(spell.getPrepared())));
        if (spell.getLevel() == 0) {
            setButton(6, icon);
        }
        else {
            setButton(6, icon, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PreparedGUI(mcdndPlayer, level, index, p)));
        }
    }

}
