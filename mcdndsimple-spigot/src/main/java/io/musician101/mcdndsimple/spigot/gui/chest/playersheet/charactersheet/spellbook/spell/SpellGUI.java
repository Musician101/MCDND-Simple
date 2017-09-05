package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.SpellcasterClassGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.EnumHand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.potion.PotionType;

public class SpellGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final Spell spell;

    public SpellGUI(Player player, Spell spell, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 27, spell.getName(), prevGUI);
        this.spell = spell;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.PAPER, MenuText.CHANGE_NAME), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setName(s);
            new SpellGUI(player, spell, bioAndInfo, classLevels, coreStats, experience, prevGUI);
        }));
        set(1, setDurability(createItem(Material.INK_SACK, MenuText.level(spell.getLevel())), 4), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spell.setLevel(i);
            delayedOpen();
        }));
        set(2, createItem(Material.ENCHANTED_BOOK, MenuText.spellType(spell.getSpellType())), player -> new SpellTypeGUI(player, spell, this));
        set(3, createItem(Material.WATCH, MenuText.castTime(spell.getCastTime())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setCastTime(s);
            delayedOpen();
        }));

        boolean nc = spell.needsConcentration();
        ItemStack ncItemStack = createItem(Material.REDSTONE_TORCH_ON, MenuText.concentration(nc));
        if (nc) {
            ncItemStack = addGlow(ncItemStack);
        }

        set(4, ncItemStack, player -> {
            spell.setNeedsConcentration(!nc);
            open();
        });

        boolean ir = spell.isRitual();
        ItemStack irItemStack = createItem(Material.TOTEM, MenuText.ritual(ir));
        if (ir) {
            irItemStack = addGlow(irItemStack);
        }

        set(5, irItemStack, player -> {
            spell.setIsRitual(!ir);
            open();
        });

        ItemStack ipStack = createItem(Material.DIODE, MenuText.prepared(spell.getPrepared()));
        if (spell.getLevel() == 0) {
            set(6, ipStack);
        }
        else {
            set(6, ipStack, player -> new PreparedGUI(player, spell, this));
        }

        set(7, createItem(Material.ENCHANTMENT_TABLE, MenuText.gainedFrom(spell.getGainedFrom())), player -> new SpellcasterClassGUI<>(player, spell, Spell::setGainedFrom, spellcasterClass -> spellcasterClass.equals(spell.getGainedFrom()), this));
        set(8, createItem(Material.LINGERING_POTION, MenuText.TARGET), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setTargetArea(s);
            delayedOpen();
        }));
        set(9, createItem(Material.BOW, MenuText.range(spell.getRange())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setRange(s);
            delayedOpen();
        }));
        set(10, createItem(Material.STRING, MenuText.duration(spell.getDuration())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setDuration(s);
            delayedOpen();
        }));
        set(11, createItem(Material.PURPLE_SHULKER_BOX, MenuText.COMPONENTS, spell.getComponents()), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spell.setComponents(s);
            delayedOpen();
        }));
        set(12, createItem(Material.REDSTONE_LAMP_OFF, MenuText.SPELL_INFO), player -> {
            ClickEvent spellDescriptionClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spell.getDescription()));

            String newLine = "\n";
            TextComponent spellHoverText = new TextComponent(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()) + newLine + MenuText.components(spell.getComponents()) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell.getDuration()) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell.getRange()) + newLine + "Click the spell's name to view the description.");
            TextComponent spellComponent = new TextComponent(spell.getName());
            spellComponent.setColor(ChatColor.GREEN);
            spellComponent.setBold(true);
            spellComponent.setClickEvent(spellDescriptionClickEvent);
            spellComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new BaseComponent[]{spellHoverText}));

            TextComponent message = new TextComponent(player.getName() + " (as " + bioAndInfo.getName() + ") looks at the instructions for ");
            message.addExtra(spellComponent);

            Bukkit.getOnlinePlayers().forEach(p -> p.spigot().sendMessage(message));
        });
        set(13, createItem(Material.REDSTONE_LAMP_ON, MenuText.CAST_SPELL), player -> {
            MacroOptions macroOptions = spell.getMacroOptions();
            String newLine = "\n";
            TextComponent spellHoverText = new TextComponent(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()));
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.addExtra(newLine + MenuText.components(spell.getComponents()) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell.getDuration()) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell.getRange()));
            }

            if (macroOptions.isAttackRollEnabled()) {
                Dice d20 = new Dice(20);
                int attackRoll = d20.roll().get(0).getValue();
                //TODO need to consider spellcasting bonuses
                TextComponent attackText = new TextComponent(newLine + "Attack: " + (attackRoll + experience.getProficiencyBonus(classLevels)));
                if (attackRoll == 1) {
                    attackText.setColor(ChatColor.RED);
                }
                else if (attackRoll == 20) {
                    attackText.setColor(ChatColor.GREEN);
                }

                if (macroOptions.isSecondAttackRollEnabled()) {
                    int secAttackRoll = d20.roll().get(0).getValue();
                    TextComponent secAttackText = new TextComponent(" | " + (attackRoll + experience.getProficiencyBonus(classLevels)));
                    if (secAttackRoll == 1) {
                        secAttackText.setColor(ChatColor.RED);
                    }
                    else if (attackRoll == 20) {
                        secAttackText.setColor(ChatColor.GREEN);
                    }

                    attackText.addExtra(secAttackText);
                }

                attackText.addExtra(" vs AC");
                spellHoverText.addExtra(attackText);
            }

            TextComponent saveSuccess = new TextComponent();
            if (macroOptions.isSavingThrowEnabled()) {
                SpellSave spellSave = spell.getSpellSave();
                spellHoverText.addExtra(newLine + "Save: DC " + spellSave.getSpellcasterClass().getSpellSaveDC(classLevels, coreStats, experience) + " " + spellSave.getSavingStat());
                saveSuccess.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spellSave.getOnSuccessfulSave())));
                saveSuccess.addExtra(here);
                saveSuccess.addExtra(" to view the effect of a successful save of this spell.");
            }

            if (macroOptions.isHealingEnabled()) {
                SpellHealing spellHealing = spell.getSpellHealing();
                AbilityScore abilityScore = null;
                switch (spellHealing.getStatBonus()) {
                    case "CHA":
                        abilityScore = coreStats.getCharisma();
                        break;
                    case "CON":
                        abilityScore = coreStats.getConstitution();
                        break;
                    case "DEX":
                        abilityScore = coreStats.getDexterity();
                        break;
                    case "INT":
                        abilityScore = coreStats.getIntelligence();
                        break;
                    case "STR":
                        abilityScore = coreStats.getStrength();
                        break;
                    case "WIS":
                        abilityScore = coreStats.getWisdom();
                        break;
                }

                spellHoverText.addExtra(newLine + "Healing: " + Dice.total(spellHealing.getHealAmount().roll(), abilityScore == null ? 0 : abilityScore.getMod()) + " HP healed");
            }

            if (macroOptions.isDamageEnabled()) {
                SpellDamage spellDamage = spell.getSpellDamage();
                int statBonus = spellDamage.getBonus();
                switch (spell.getAttackStat()) {
                    case "CHA":
                        statBonus += coreStats.getCharisma().getMod();
                        break;
                    case "CON":
                        statBonus += coreStats.getConstitution().getMod();
                        break;
                    case "DEX":
                        statBonus += coreStats.getDexterity().getMod();
                        break;
                    case "INT":
                        statBonus += coreStats.getIntelligence().getMod();
                        break;
                    case "STR":
                        statBonus += coreStats.getStrength().getMod();
                        break;
                    case "WIS":
                        statBonus += coreStats.getWisdom().getMod();
                        break;
                }

                Dice dice = spellDamage.getDice();
                spellHoverText.addExtra(newLine + "Damage: " + Dice.total(dice.roll(), statBonus) + " " + spellDamage.getDamageType());
                if (spellDamage.canCrit()) {
                    spellHoverText.addExtra(newLine + "Crit: Additional " + Dice.total(dice.roll()) + " damage");
                }
            }

            TextComponent effect = new TextComponent();
            if (macroOptions.isDescriptionEnabled()) {
                effect.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spell.getEffects())));
                effect.addExtra(here);
                effect.addExtra(" to view the spell's effects.");
            }

            TextComponent spellDescription = new TextComponent();
            if (macroOptions.isDescriptionEnabled()) {
                spellDescription.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spell.getDescription())));
                spellDescription.addExtra(here);
                spellDescription.addExtra(" to view the spell's description.");
            }

            TextComponent atHigherLevels = new TextComponent();
            if (macroOptions.isAtHigherLevelsEnabled()) {
                atHigherLevels.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spell.getAtHigherLevels())));
                atHigherLevels.addExtra(" to view how the spell behaves at higher levels.");
            }

            //TODO need to do effects
            TextComponent effects = new TextComponent();
            if (macroOptions.isEffectsEnabled()) {
                effects.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(spell.getEffects())));
                effects.addExtra(" to view how the spell behaves at higher levels.");
            }

            TextComponent spellComponent = new TextComponent(spell.getName());
            spellComponent.setColor(ChatColor.GREEN);
            spellComponent.setBold(true);
            spellComponent.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new BaseComponent[]{spellHoverText}));
            spellComponent.addExtra(spellDescription);
            spellComponent.addExtra(atHigherLevels);

            TextComponent message = new TextComponent(player.getName() + " (as " + bioAndInfo + ") cast ");
            message.addExtra(spellComponent);
            message.addExtra(atHigherLevels);
            message.addExtra(saveSuccess);
            message.addExtra(effects);

            Bukkit.getOnlinePlayers().forEach(p -> p.spigot().sendMessage(message));
        });
        set(14, createItem(Material.REDSTONE_COMPARATOR_OFF, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS), player -> new MacroOptionsGUI(player, spell.getMacroOptions(), this));
        set(18, createItem(Material.BOOK, MenuText.DESCRIPTION), player -> new SpellDescriptionGUI(player, spell, this));
        set(19, createItem(Material.DIAMOND_SWORD, MenuText.ATTACK), player -> new SpellAttackGUI(player, coreStats, spell, this));
        set(20, createItem(Material.SHIELD, MenuText.SAVE), player -> new SpellSaveGUI(player, coreStats, spell.getSpellSave(), this));
        set(21, setPotionEffect(createItem(Material.POTION, MenuText.HEALING), PotionType.INSTANT_HEAL), player -> new HealingGUI(player, coreStats, spell.getSpellHealing(), this));
        set(22, createItem(Material.GOLD_SWORD, MenuText.DAMAGE), player -> new SpellDamageGUI(player, spell.getSpellDamage(), this));
        set(23, createItem(Material.BOOK, MenuText.SPELL_EFFECTS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(MenuText.SPELL_EFFECTS);
            meta.setPages(spell.getEffects());
            book.setItemMeta(meta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                spell.setEffects(pages);
                open();
            });
        });
        setBackButton(26, Material.BARRIER);
    }

    private String openBookCommand(List<String> pages) {
        return "/callback " + SpigotMCDNDSimple.instance().getCallbackTracker().getIdForCallback(sender -> {
            Player p = (Player) sender;
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setAuthor(bioAndInfo.getName());
            meta.setPages(pages);
            meta.setTitle(spell.getName());
            book.setItemMeta(meta);
            ItemStack old = player.getInventory().getItemInMainHand();
            ((CraftPlayer) p).getHandle().a(CraftItemStack.asNMSCopy(book), EnumHand.MAIN_HAND);
            player.getInventory().setItemInMainHand(old);
        }).toString();
    }
}
