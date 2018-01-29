package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.SpellcasterClassGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Text.Builder;
import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.serializer.TextSerializers;


public class SpellGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final Spell spell;

    public SpellGUI(Player player, Spell spell, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, spell.getName(), 27, prevGUI);
        this.spell = spell;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.CHANGE_NAME)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setName(s);
            new SpellGUI(player, spell, bioAndInfo, classLevels, coreStats, experience, prevGUI);
        }));
        set(1, ClickInventoryEvent.class, setDurability(createItem(ItemTypes.DYE, Text.of(MenuText.level(spell.getLevel()))), 4), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spell.setLevel(i);
            delayedOpen();
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.spellType(spell.getSpellType()))), player -> new SpellTypeGUI(player, spell, this));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.CLOCK, Text.of(MenuText.castTime(spell.getCastTime()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setCastTime(s);
            delayedOpen();
        }));

        boolean nc = spell.needsConcentration();
        set(4, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.concentration(nc))), () -> nc), player -> {
            spell.setNeedsConcentration(!nc);
            open();
        });

        boolean ir = spell.isRitual();
        set(5, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.ritual(ir))), () -> ir), player -> {
            spell.setIsRitual(!ir);
            open();
        });

        ItemStack ipStack = createItem(ItemTypes.REPEATER, Text.of(MenuText.prepared(spell.getPrepared())));
        if (spell.getLevel() == 0) {
            set(6, ipStack);
        }
        else {
            set(6, ClickInventoryEvent.class, ipStack, player -> new PreparedGUI(player, spell, this));
        }

        set(7, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.gainedFrom(spell.getGainedFrom()))), player -> new SpellcasterClassGUI<>(player, spell, Spell::setGainedFrom, spellcasterClass -> spellcasterClass.equals(spell.getGainedFrom()), this));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.LINGERING_POTION, Text.of(MenuText.TARGET)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setTargetArea(s);
            delayedOpen();
        }));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(MenuText.range(spell.getRange()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setRange(s);
            delayedOpen();
        }));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.STRING, Text.of(MenuText.duration(spell.getDuration()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setDuration(s);
            delayedOpen();
        }));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.PURPLE_SHULKER_BOX, Text.of(MenuText.COMPONENTS), Text.of(spell.getComponents())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setComponents(s);
            delayedOpen();
        }));
        set(12, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.SPELL_INFO)), player -> {
            String newLine = "\n";
            Builder spellComponent = Text.builder(spell.getName());
            spellComponent.color(TextColors.GREEN).style(TextStyles.BOLD).onClick(openBookCommand(spell.getDescription()));
            spellComponent.onHover(TextActions.showText(Text.of(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()) + newLine + MenuText.components(spell.getComponents()) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell.getDuration()) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell.getRange()) + newLine + "Click the spell's name to view the description.")));
            Builder message = Text.builder(player.getName() + " (as " + bioAndInfo.getName() + ") looks at the instructions for ");
            Text finalMessage = Text.join(message.append(spellComponent.build()).build());
            SpongeMCDNDSimple.instance().getLogger().info(TextSerializers.PLAIN.serialize(finalMessage));
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(finalMessage));
        });
        set(13, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.CAST_SPELL)), player -> {
            Builder message = Text.builder(player.getName() + " (as " + bioAndInfo.getName() + ") cast ");
            Builder spellHoverText = Text.of(MenuText.spellType(spell.getSpellType()), Text.NEW_LINE, MenuText.spellType(spell.getSpellType()), Text.NEW_LINE, MenuText.spellLevel(spell.getLevel())).toBuilder();
            MacroOptions macroOptions = spell.getMacroOptions();
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.append(Text.NEW_LINE, Text.of(MenuText.components(spell.getComponents())), Text.NEW_LINE, Text.of(MenuText.castTime(spell.getCastTime())), Text.NEW_LINE, Text.of(MenuText.duration(spell.getDuration())), Text.NEW_LINE, Text.of(spell.getTargetArea()), Text.NEW_LINE, Text.of(MenuText.range(spell.getRange())));
            }

            if (macroOptions.isAttackRollEnabled()) {
                Dice d20 = new Dice(20);
                int attackRoll = d20.roll().get(0).getValue() + experience.getProficiencyBonus(classLevels);
                Builder attackText = Text.builder("Attack: ");
                TextColor color = TextColors.WHITE;
                if (attackRoll == 1) {
                    color = TextColors.RED;
                }
                else if (attackRoll == 20) {
                    color = TextColors.GREEN;
                }

                attackText.append(Text.of(color, attackRoll));

                if (macroOptions.isSecondAttackRollEnabled()) {
                    int secAttackRoll = d20.roll().get(0).getValue() + experience.getProficiencyBonus(classLevels);
                    attackText.append(Text.of(" | "));
                    TextColor secColor = TextColors.WHITE;
                    if (secAttackRoll == 1) {
                        secColor = TextColors.RED;
                    }
                    else if (secAttackRoll == 20) {
                        secColor = TextColors.GREEN;
                    }

                    attackText.append(Text.of(secColor, secAttackRoll));
                }

                spellHoverText.append(Text.NEW_LINE, attackText.append(Text.of(" vs AC")).build());
            }

            if (macroOptions.isHealingEnabled()) {
                SpellHealing spellHealing = spell.getSpellHealing();
                StatBonus statBonus = spellHealing.getStatBonus();
                AbilityScore abilityScore = null;
                switch (statBonus) {
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

                spellHoverText.append(Text.of(Text.NEW_LINE, "Healing: " + Dice.total(spellHealing.getHealAmount().roll(), abilityScore == null ? 0 : abilityScore.getMod()) + " HP healed."));
            }

            if (macroOptions.isDamageEnabled()) {
                SpellDamage spellDamage = spell.getSpellDamage();
                int statBonus = spellDamage.getBonus();
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

                Dice dice = spellDamage.getDice();
                Builder builder = Text.of(Text.NEW_LINE, Text.of("Damage: " + Dice.total(dice.roll(), statBonus) + " " + spellDamage.getDamageType())).toBuilder();
                if (spellDamage.canCrit()) {
                    builder.append(Text.NEW_LINE, Text.of("Crit: Additional " + Dice.total(dice.roll()) + " damage"));
                }

                spellHoverText.append(builder.build());
            }

            if (macroOptions.isSavingThrowEnabled()) {
                SpellSave spellSave = spell.getSpellSave();
                message.append(Text.of(Text.NEW_LINE, "Save: DC " + spellSave.getSpellcasterClass().getSpellSaveDC(classLevels, coreStats, experience) + " " + spellSave.getSavingStat(), Text.NEW_LINE, Text.of("Click "), Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(openBookCommand(spellSave.getOnSuccessfulSave())).build(), Text.of(" to view the effect of a successful save of this spell.")));
            }

            if (macroOptions.isEffectsEnabled()) {
                message.append(Text.of(Text.NEW_LINE, Text.of("Click "), Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(openBookCommand(spell.getEffects())).build(), " to view the spell's effects."));
            }

            if (macroOptions.isDescriptionEnabled()) {
                message.append(Text.of(Text.NEW_LINE, "Click ", Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(openBookCommand(spell.getDescription())).build(), " to view the spell's description."));
            }

            if (macroOptions.isAtHigherLevelsEnabled()) {
                message.append(Text.of(Text.NEW_LINE, "Click ", Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(openBookCommand(spell.getAtHigherLevels())).build(), " to view the spell's effects at higher levels."));
            }

            Text finalMessage = message.build();
            SpongeMCDNDSimple.instance().getLogger().info(TextSerializers.PLAIN.serialize(finalMessage));
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(finalMessage));
        });
        set(14, ClickInventoryEvent.class, createItem(ItemTypes.COMPARATOR, Text.of(MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS)), player -> new MacroOptionsGUI(player, spell.getMacroOptions(), this));
        set(18, ClickInventoryEvent.class,createItem(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), player -> new SpellDescriptionGUI(player, spell, this));
        set(19, ClickInventoryEvent.class,createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.ATTACK)), player -> new StatBonusGUI<>(player, spell, Spell::setAttackStat, (spell, statBonus) -> spell.getAttackStat() == statBonus, this));
        set(20, ClickInventoryEvent.class,createItem(ItemTypes.SHIELD, Text.of(MenuText.SAVE)), player -> new SpellSaveGUI(player, coreStats, spell.getSpellSave(), this));
        set(21, ClickInventoryEvent.class,setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.HEALING)), PotionEffectTypes.INSTANT_HEALTH), player -> new HealingGUI(player, spell.getSpellHealing(), this));
        set(22, ClickInventoryEvent.class,createItem(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.DAMAGE)), player -> new SpellDamageGUI(player, spell.getSpellDamage(), this));
        set(23, ClickInventoryEvent.class,createItem(ItemTypes.BOOK, Text.of(MenuText.SPELL_EFFECTS)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, spell.getEffects().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                spell.setEffects(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });
        setBackButton(26, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private ClickAction openBookCommand(List<String> pages) {
        return TextActions.executeCallback(source -> {
            player.sendBookView(BookView.builder().author(Text.of(bioAndInfo.getName())).title(Text.of(spell.getName())).addPages(pages.stream().map(Text::of).collect(Collectors.toList())).build());
        });
    }
}
