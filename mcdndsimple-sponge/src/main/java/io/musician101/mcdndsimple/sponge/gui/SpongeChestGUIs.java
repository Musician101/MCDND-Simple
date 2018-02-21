package io.musician101.mcdndsimple.sponge.gui;

import io.musician101.mcdndsimple.common.ChestGUIs;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActionType;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActions;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerHitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkill;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.Recharge;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.character.player.clazz.GainedFrom;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.player.spell.Prepared;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.Weapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.DoubleAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerAnvilGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.gui.chest.GUIButton;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.SpongeChestGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.SpongeChestGUIBuilder;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import static org.spongepowered.api.text.Text.of;

public final class SpongeChestGUIs extends ChestGUIs<SpongeChestGUIBuilder, Class<? extends ClickInventoryEvent>, SpongeChestGUI, Inventory, PluginContainer, Player, ItemStack, Text> {

    public static final ChestGUIs<SpongeChestGUIBuilder, Class<? extends ClickInventoryEvent>, SpongeChestGUI, Inventory, PluginContainer, Player, ItemStack, Text> INSTANCE = new SpongeChestGUIs();

    private SpongeChestGUIs() {

    }

    @Override
    public void armor(@Nonnull Player player, @Nonnull Armor armor, @Nonnull List<Armor> armorList, @Nullable SpongeChestGUI prevGUI) {
        builder(18, 17, armor.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.CHAINMAIL_CHESTPLATE).name(of(MenuText.isWorn(armor))).addGlow(armor.isWorn()).build(), (g, p) -> {
            armor.setIsWorn(!armor.isWorn());
            g.open();
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.CHAINMAIL_CHESTPLATE).name(of(MenuText.isUnarmored(armor))).addGlow(armor.isUnarmored()).build(), (g, p) -> {
            armor.setIsUnarmored(!armor.isUnarmored());
            g.open();
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NAME_TAG, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            armor.setName(s);
            armor(ply, armor, armorList, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, of(MenuText.baseAC(armor))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            armor.setBaseArmorClass(i);
            g.open();
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_INGOT, of(armor.getArmorType().getName())), (g, p) -> armorType(player, armor, 1, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.magicBonus(armor))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            armor.setMagicBonus(i);
            g.open();
        })), GUIButton.of(6, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, of(MenuText.totalAC(armor)))), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.hasStealthPenalty(armor))).potionEffect(PotionEffectTypes.INVISIBILITY).build(), (g, p) -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            g.open();
        }), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.hasSpeedPenalty(armor))).potionEffect(PotionEffectTypes.SPEED).build(), (g, p) -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            g.open();
        }), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            armorList.remove(armor);
            g.close();
            p.sendMessage(of(TextColors.GREEN, Messages.ARMOR_DELETED));
        })).build();
    }

    @Override
    public void armorList(@Nonnull Player player, @Nonnull List<Armor> armorList, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.ARMOR, player, prevGUI, page, ItemRepresentation::armor, (armor) -> (g, p) -> armor(p, armor, armorList, g), (p, i) -> armorList(p, armorList, i, prevGUI), armorList).setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_CLASS_ACTION)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Armor armor = new Armor();
            armor.setName(s);
            armorList.add(armor);
            armor(ply, armor, armorList, prevGUI);
        }))).build();
    }

    @Override
    public void armorTab(@Nonnull Player player, @Nonnull ArmorTab armorTab, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.ARMOR, player, prevGUI).setButtons(GUIButton.of(0, SpongeIconBuilder.of(ItemTypes.CHAINMAIL_CHESTPLATE, of(MenuText.armoredAC(armorTab)))), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.LEATHER_CHESTPLATE, of(MenuText.unarmoredAC(armorTab)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, of(MenuText.ARMOR)), (g, p) -> armorList(p, armorTab.getArmorList(), 1, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_CHESTPLATE).name(of(MenuText.UNARMORED_BONUS)).description(of(MenuText.current(armorTab))).build(), (g, p) -> unarmoredBonus(p, armorTab, 1, g))).build();
    }

    @Override
    public void armorType(@Nonnull Player player, @Nonnull Armor armor, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.ARMOR_TYPE, player, prevGUI, page, armorType -> SpongeIconBuilder.builder(ItemRepresentation.armorType(armorType)).addGlow(armorType == armor.getArmorType()).build(), armorType -> (g, p) -> {
            armor.setArmorType(armorType);
            g.close();
        }, (p, i) -> armorType(player, armor, i, prevGUI), ArmorType.values()).build();
    }

    @Override
    public void attackStat(@Nonnull Player player, int page, @Nonnull Weapon weapon, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.ATTACK_STAT, player, prevGUI, page, attackStat -> SpongeIconBuilder.builder(ItemRepresentation.weaponAttackStat(attackStat)).addGlow(attackStat == weapon.getAttackStat()).build(), attackStat -> (g, p) -> {
            weapon.setAttackStat(attackStat);
            attackStat(p, page, weapon, prevGUI);
        }, (p, i) -> attackStat(p, i, weapon, prevGUI), WeaponAttackStat.values());
    }

    @Override
    public void backgroundTab(@Nonnull Player player, @Nonnull BackgroundTab backgroundTab, @Nullable SpongeChestGUI prevGUI) {
        builder(27, 26, MenuText.BACKGROUND, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ARROW, of(MenuText.gender(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setGender(s);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.FISH, of(MenuText.age(backgroundTab))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            backgroundTab.setAge(i);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ARMOR_STAND, of(MenuText.height(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setHeight(s);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_BLOCK, of(MenuText.weight(backgroundTab))), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            backgroundTab.setWeight(d);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SPIDER_EYE, of(MenuText.eyes(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setEyes(s);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.RABBIT_HIDE, of(MenuText.hair(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setHair(s);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.LEATHER, of(MenuText.size(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setSize(s);
            backgroundTab(p, backgroundTab, prevGUI);
        })), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.vision(backgroundTab))).potionEffect(PotionEffectTypes.NIGHT_VISION).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setVision(s);
            g.open();
        })), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.KNOWN_LANGUAGES)), (g, p) -> {
            //TODO fix these so that they don't use OPTIONAL
            SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.KNOWN_LANGUAGES, backgroundTab.getLanguages()), pages -> {
                backgroundTab.setLanguages(textToString(pages));
                g.open();
            }));
        }), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, of(MenuText.alignment(backgroundTab))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setAlignment(s);
            g.open();
        })), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.BACKGROUND)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.BACKGROUND, backgroundTab.getBackground()), pages -> {
                backgroundTab.setBackground(textToString(pages));
                g.open();
            }))), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.RACIAL_TRAITS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.RACIAL_TRAITS, backgroundTab.getRacialTraits()), pages -> {
                    backgroundTab.setRacialTraits(textToString(pages));
                    g.open();
                }))), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.PERSONALITY_TRAITS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits()), pages -> {
                        backgroundTab.setPersonalityTraits(textToString(pages));
                        g.open();
                    }))), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.IDEALS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.IDEALS, backgroundTab.getIdeals()), pages -> {
                            backgroundTab.setIdeals(textToString(pages));
                            g.open();
                        }))), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.BONDS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.BONDS, backgroundTab.getBonds()), pages -> {
                                backgroundTab.setBonds(textToString(pages));
                                g.open();
                            }))), GUIButton.of(15, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.FLAWS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.FLAWS, backgroundTab.getFlaws()), pages -> {
                                    backgroundTab.setFlaws(textToString(pages));
                                    g.open();
                                }))), GUIButton.of(16, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.ARMOR_PROFICIENCIES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies()), pages -> {
                                        backgroundTab.setArmorProficiencies(textToString(pages));
                                        g.open();
                                    }))), GUIButton.of(17, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.WEAPON_PROFICIENCIES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies()), pages -> {
                                            backgroundTab.setWeaponProficiencies(textToString(pages));
                                            g.open();
                                        }))), GUIButton.of(18, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.TOOL_PROFICIENCIES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies()), pages -> {
                                                backgroundTab.setToolProficiencies(textToString(pages));
                                                g.open();
                                            }))), GUIButton.of(19, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.OTHER_NOTES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.OTHER_NOTES, backgroundTab.getOtherNotes()), pages -> {
                                                    backgroundTab.setOtherNotes(textToString(pages));
                                                    g.open();
                                                })))).build();
    }

    @Override
    public void bioAndInfo(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull PlayerSheet playerSheet, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.BIO_AND_INFO, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NAME_TAG, of(MenuText.name(bioAndInfo))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setName(s);
            g.open();
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.BIO)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.BIO, bioAndInfo.getBio()), pages -> {
                bioAndInfo.setBio(textToString(pages));
                g.open();
            })))).build();
    }

    @Override
    public void bonuses(@Nonnull Player player, @Nonnull Bonuses bonuses, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.BONUSES_PENALTIES, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.MELEE_BONUSES)), (g, p) -> meleeBonus(p, bonuses.getMelee(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.RANGED_BONUSES)), (g, p) -> rangedBonus(p, bonuses.getRanged(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.SPELLCASTING_BONUSES)), (g, p) -> spellcastingBonus(p, bonuses.getSpellcasting(), g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(of(MenuText.SAVING_THROW_BONUSES)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            bonuses.setSaves(dice);
            bonuses(player, bonuses, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.LUCK).name(of(MenuText.ABILITY_SKILL_CHECK_ROLLS)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            bonuses.setAbilitiesAndSkills(dice);
            bonuses(player, bonuses, prevGUI);
        }))).build();
    }

    private void broadcast(@Nonnull Text text) {
        Sponge.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(text));
        SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getLogger).ifPresent(logger -> logger.info(text.toPlain()));
    }

    @Nonnull
    @Override
    protected SpongeChestGUIBuilder builder(int size, int backButtonSlot, @Nonnull String name, @Nonnull Player player, @Nullable SpongeChestGUI prevGUI) {
        return SpongeChestGUI.builder().setSize(size).setName(of(name)).setPlayer(player).setPreviousGUI(prevGUI).setBackButton(backButtonSlot, ClickInventoryEvent.Primary.class);
    }

    @Override
    public void characterSheet(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull CharacterSheet characterSheet, @Nullable SpongeChestGUI prevGUI) {
        ClassTab classTab = characterSheet.getClassTab();
        CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
        Bonuses bonuses = coreStatsTab.getBonuses();
        builder(9, 8, MenuText.CHARACTER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, of(MenuText.ARMOR)), (g, p) -> armorTab(p, characterSheet.getArmorTab(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.BACKGROUND)), (g, p) -> backgroundTab(p, characterSheet.getBackgroundTab(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.CLASS)), (g, p) -> classTab(p, characterSheet.getClassTab(), g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND, of(MenuText.CORE_STATS)), (g, p) -> coreStatsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CHEST, of(MenuText.INVENTORY)), (g, p) -> inventoryTab(p, coreStatsTab.getCoreStats(), characterSheet.getInventoryTab(), g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.SKILLS)), (g, p) -> skillsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getBonuses().getAbilitiesAndSkills(), coreStatsTab.getExperience(), characterSheet.getSkillsTab(), g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.SPELLBOOK)), (g, p) -> spellbookTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), characterSheet.getSpellbookTab(), coreStatsTab.getBonuses().getSpellcasting(), g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.INVENTORY)), (g, p) -> weaponsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), bonuses.getMelee(), bonuses.getRanged(), characterSheet.getWeaponsTab(), g))).build();
    }

    @Override
    public void classAction(@Nonnull Player player, @Nonnull ClassAction classAction, @Nonnull List<ClassAction> classActions, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, classAction.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NAME_TAG, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            classAction.setName(s);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE, of(MenuText.used(classAction.getUsed()))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classAction.setUsed(i);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_BLOCK, of(MenuText.maxUses(classAction.getMaxUses()))), (g, p) -> recharge(p, classAction, 1, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BED, of(MenuText.recharge(classAction.getRecharge()))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classAction.setUsed(i);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.GAINED_FROM)), (g, p) -> gainedFrom(p, 1, classAction, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE, of(MenuText.OUTPUT_OPTIONS)), (g, p) -> outputOptions(p, classAction.getOutputOptions(), g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            classActions.remove(classAction);
            g.close();
        })).build();
    }

    @Override
    public void classActions(@Nonnull Player player, @Nonnull List<ClassAction> classActions, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.CLASS_ACTIONS, player, prevGUI, page, classAction -> SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(classAction.getName())), classAction -> (g, p) -> classAction(p, classAction, classActions, g), (p, i) -> classActions(p, classActions, page, prevGUI), classActions).setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_CLASS_ACTION)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            ClassAction classAction = new ClassAction();
            classAction.setName(s);
            classActions.add(classAction);
            classAction(ply, classAction, classActions, prevGUI);
        }))).build();
    }

    @Override
    public void classLevels(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nullable SpongeChestGUI prevGUI) {
        builder(18, 17, MenuText.CLASS_LEVELS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WOODEN_SWORD, of(MenuText.BARBARIAN)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setBarbarian(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, of(MenuText.BARD)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setBard(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.CLERIC)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setCleric(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.DRUID)).potionEffect(PotionEffectTypes.LUCK).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setDruid(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.FIGHTER)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setFighter(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.MONK)).potionEffect(PotionEffectTypes.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setMonk(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.PALADIN)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setPaladin(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.RANGER)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setRanger(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, of(MenuText.ROGUE)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setRogue(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.MAGMA_CREAM, of(MenuText.SORCERER)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setSorcerer(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.END_CRYSTAL, of(MenuText.WARLOCK)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setWarlock(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, of(MenuText.WIZARD)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setWizard(i);
            classLevels(ply, classLevels, prevGUI);
        }))).build();
    }

    @Override
    public void classResource(@Nonnull Player player, @Nonnull ClassResource classResource, @Nonnull List<ClassResource> classResources, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, classResource.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NAME_TAG, of(classResource.getName())), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            classResource.setName(s);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BED, of(MenuText.recharge(classResource.getRecharge()))), (g, p) -> recharge(p, classResource, 1, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.COMPARATOR, of(MenuText.current(classResource))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classResource.setCurrentCharges(i);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.COMPARATOR, of(MenuText.maxUses(classResource.getMaxUses()))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classResource.setMaxCharges(i);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            classResources.remove(classResource);
            g.close();
            p.sendMessage(Text.of(TextColors.GREEN, Messages.CLASS_RESOURCE_DELETED));
        })).build();
    }

    @Override
    public void classResources(@Nonnull Player player, @Nonnull List<ClassResource> classResources, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.CLASS_RESOURCES, player, prevGUI, page, classResource -> SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(classResource.getName())), classResource -> (g, p) -> classResource(p, classResource, classResources, g), (p, i) -> classResources(p, classResources, i, prevGUI), classResources).setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_CLASS_ACTION)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            ClassResource classResource = new ClassResource();
            classResource.setName(s);
            classResources.add(classResource);
            classResource(ply, classResource, classResources, prevGUI);
        }))).build();
    }

    @Override
    public void classTab(@Nonnull Player player, @Nonnull ClassTab classTab, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.CLASS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.CLASS_LEVELS)), (g, p) -> classLevels(p, classTab.getClassLevels(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND, of(MenuText.CLASS_RESOURCES)), (g, p) -> classResources(p, classTab.getClassResources(), 1, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.CLASS_FEATURE_NOTES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes()), pages -> {
                classTab.setClassFeatureNotes(textToString(pages));
                classTab(p, classTab, prevGUI);
            }))), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.OTHER_NOTES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.OTHER_NOTES, classTab.getOtherNotes()), pages -> {
                    classTab.setOtherNotes(textToString(pages));
                    classTab(p, classTab, prevGUI);
                }))), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, of(MenuText.CLASS_ACTIONS)), (g, p) -> classActions(p, classTab.getClassActions(), 1, g))).build();
    }

    @Override
    public void coreSkillsOutputOptions(@Nonnull Player player, @Nonnull CoreSkillsOutputOptions coreSkillsOutputOptions, @Nonnull SpongeChestGUI prevGUI) {
        builder(27, 26, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.LEATHER_BOOTS).name(of(MenuText.ACROBATICS)).addGlow(coreSkillsOutputOptions.isAcrobaticsEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAcrobaticsEnabled(!coreSkillsOutputOptions.isAcrobaticsEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SADDLE).name(of(MenuText.ANIMAL_HANDLING)).addGlow(coreSkillsOutputOptions.isAnimalHandlingEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAnimalHandlingEnabled(!coreSkillsOutputOptions.isAnimalHandlingEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(MenuText.ARCANA)).addGlow(coreSkillsOutputOptions.isArcanaEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setArcanaEnabled(!coreSkillsOutputOptions.isArcanaEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.ATHLETICS)).potionEffect(PotionEffectTypes.SPEED).addGlow(coreSkillsOutputOptions.isAthleticsEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAthleticsEnabled(!coreSkillsOutputOptions.isAthleticsEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.EMERALD).name(of(MenuText.DECEPTION)).addGlow(coreSkillsOutputOptions.isDeceptionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setDeceptionEnabled(!coreSkillsOutputOptions.isDeceptionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.HISTORY)).addGlow(coreSkillsOutputOptions.isHistoryEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setHistoryEnabled(!coreSkillsOutputOptions.isHistoryEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ICE).name(of(MenuText.INSIGHT)).addGlow(coreSkillsOutputOptions.isInsightEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setInsightEnabled(!coreSkillsOutputOptions.isInsightEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(of(MenuText.INTIMIDATION)).addGlow(coreSkillsOutputOptions.isIntimidationEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setIntimidationEnabled(!coreSkillsOutputOptions.isIntimidationEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTING_TABLE).name(of(MenuText.INVESTIGATION)).addGlow(coreSkillsOutputOptions.isInvestigationEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setInvestigationEnabled(!coreSkillsOutputOptions.isInvestigationEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.MEDICINE)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).addGlow(coreSkillsOutputOptions.isMedicineEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setMedicineEnabled(!coreSkillsOutputOptions.isMedicineEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.VINE).name(of(MenuText.NATURE)).addGlow(coreSkillsOutputOptions.isNatureEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setNatureEnabled(!coreSkillsOutputOptions.isNatureEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.CARROT).name(of(MenuText.PERCEPTION)).addGlow(coreSkillsOutputOptions.isPerceptionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPerceptionEnabled(!coreSkillsOutputOptions.isPerceptionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.NOTEBLOCK).name(of(MenuText.PERFORMANCE)).addGlow(coreSkillsOutputOptions.isPerformanceEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPerformanceEnabled(!coreSkillsOutputOptions.isPerformanceEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENDER_EYE).name(of(MenuText.PERSUASION)).addGlow(coreSkillsOutputOptions.isPersuasionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPersuasionEnabled(!coreSkillsOutputOptions.isPersuasionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.NETHER_STAR).name(of(MenuText.RELIGION)).addGlow(coreSkillsOutputOptions.isReligionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(15, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.TRIPWIRE_HOOK).name(of(MenuText.SLEIGHT_OF_HAND)).addGlow(coreSkillsOutputOptions.isSleightOfHandEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(16, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.STEALTH)).potionEffect(PotionEffectTypes.NIGHT_VISION).addGlow(coreSkillsOutputOptions.isStealthEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(17, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.SURVIVAL)).addGlow(coreSkillsOutputOptions.isSurvivalEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        })).build();
    }

    @Override
    public void coreStatsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStatsTab coreStatsTab, @Nullable SpongeChestGUI prevGUI) {
        CoreStats<PlayerAbilityScore> coreStats = coreStatsTab.getCoreStats();
        PlayerAbilityScore dex = coreStats.getDexterity();
        Experience experience = coreStatsTab.getExperience();
        HitDice hitDice = coreStatsTab.getHitDice();
        HitPoints hitPoints = coreStatsTab.getHitPoints();
        Initiative initiative = coreStatsTab.getInitiative();
        builder(18, 17, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.CORE_STATS)).potionEffect(PotionEffectTypes.STRENGTH).build(), (g, p) -> playerCoreStats(p, bioAndInfo, classLevels, coreStats, experience, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(of(MenuText.HIT_POINTS)).description(stringToText(MenuText.hitPoints(hitPoints))).build(), (g, p) -> hitPoints(p, hitPoints, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.speed(coreStatsTab))).potionEffect(PotionEffectTypes.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            coreStatsTab.setSpeed(i);
            coreStatsTab(player, bioAndInfo, classLevels, coreStatsTab, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.initiative(initiative, dex))).potionEffect(PotionEffectTypes.NIGHT_VISION).build(), (g, p) -> initiative(p, dex, bioAndInfo, initiative, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.EXPERIENCE_BOTTLE).name(of(MenuText.LEVEL_AND_XP)).description(stringToText(MenuText.experience(experience, classLevels))).build(), (g, p) -> experience(p, classLevels, experience, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.HIT_DICE)).potionEffect(PotionEffectTypes.REGENERATION).build(), (g, p) -> hitDice(p, coreStats.getConstitution(), bioAndInfo, hitDice, g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.BONUSES_PENALTIES)), (g, p) -> bonuses(p, coreStatsTab.getBonuses(), g)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLD_NUGGET, of(MenuText.INSPIRATION)), (g, p) -> {
            coreStatsTab.setHasInspiration(!coreStatsTab.hasInspiration());
            coreStatsTab(p, bioAndInfo, classLevels, coreStatsTab, prevGUI);
        }), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.DEATH_SAVING_THROWS)), (g, p) -> deathSavingThrows(p, bioAndInfo, coreStatsTab.getDeathSavingThrows(), coreStatsTab.getBonuses().getSaves(), hitPoints, g))).build();
    }

    @Nonnull
    @Override
    protected ItemStack createBook(@Nonnull Player player, @Nonnull String title, List<String> pages) {
        return ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_AUTHOR, of(player.getName())).add(Keys.DISPLAY_NAME, of(MenuText.KNOWN_LANGUAGES)).add(Keys.BOOK_PAGES, stringToText(pages)).build();
    }

    @Override
    public void deathSavingThrows(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull DeathSavingThrows deathSavingThrows, @Nonnull Dice savingThrowBonus, @Nonnull HitPoints hitPoints, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.DEATH_SAVING_THROWS, player, prevGUI).setButtons(GUIButton.<Class<? extends ClickInventoryEvent>, SpongeChestGUI, Inventory, PluginContainer, Player, ItemStack>builder().slot(0).icon(SpongeIconBuilder.builder(ItemTypes.SKULL).durability(3).name(of(MenuText.successCount(deathSavingThrows.getSuccessCount()))).build()).addButton(ClickInventoryEvent.Primary.class, (g, p) -> {
            deathSavingThrows.addSuccessCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).addButton(ClickInventoryEvent.Secondary.class, (g, p) -> {
            deathSavingThrows.removeSuccessCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).build(), GUIButton.<Class<? extends ClickInventoryEvent>, SpongeChestGUI, Inventory, PluginContainer, Player, ItemStack>builder().slot(1).icon(SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.failCount(deathSavingThrows.getFailCount())))).addButton(ClickInventoryEvent.Primary.class, (g, p) -> {
            deathSavingThrows.addFailCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).addButton(ClickInventoryEvent.Secondary.class, (g, p) -> {
            deathSavingThrows.removeFailCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).build(), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.failCount(deathSavingThrows.getFailCount()))), (g, p) -> {
            int result = Dice.total(new Dice(20).roll());
            int total = result + Dice.total(savingThrowBonus.roll());
            String newLine = "\n";
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a Death Saving Throw." + newLine + "The results are ");
            if (result == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (result == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(total, TextColors.RESET, "." + newLine));
            if (total > 10) {
                if (result == 1) {
                    deathSavingThrows.addFailCount();
                }

                deathSavingThrows.addFailCount();
                tb.append(Text.of(bioAndInfo.getName() + " now has ", TextColors.RED, deathSavingThrows.getFailCount(), TextColors.RESET, " failed Death Saving Throws!"));
            }
            else {
                deathSavingThrows.addSuccessCount();
                tb.append(Text.of(bioAndInfo.getName() + " now has ", TextColors.GREEN, deathSavingThrows.getSuccessCount(), TextColors.RESET, " successful Death Saving Throws!"));
                if (result == 20) {
                    tb.append(Text.of(TextColors.GOLD, "They have also gained 1HP."));
                    hitPoints.setCurrent(1);
                }
            }

            broadcast(tb.build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void editControllers(@Nonnull Player player, int page, @Nonnull AbstractPlayer abstractPlayer, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.EDIT_CONTROLLERS, player, prevGUI, page, uuid -> {
            SpongeIconBuilder builder = SpongeIconBuilder.builder(ItemTypes.SKULL).addGlow(abstractPlayer.isController(uuid));
            try {
                builder.name(of(Sponge.getServer().getGameProfileManager().get(uuid).get().getName()));
            }
            catch (InterruptedException | ExecutionException e) {
                builder.name(of(uuid.toString())).description(of("Could not find player's name!"));
            }

            return builder.build();
        }, uuid -> (g, p) -> {
            if (abstractPlayer.isController(uuid)) {
                abstractPlayer.removeController(uuid);
            }
            else {
                abstractPlayer.addController(uuid);
            }
        }, (p, i) -> editControllers(p, i, abstractPlayer, prevGUI), abstractPlayer.getControllers()).build();
    }

    @Override
    public void experience(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.LEVEL_AND_XP, player, prevGUI).setButtons(GUIButton.of(0, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.overallLevel(experience, classLevels)))), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.ANVIL, of(MenuText.proficiencyBonus(experience, classLevels)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, of(MenuText.currentXP(experience))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            experience.setExp(i);
            experience(ply, classLevels, experience, prevGUI);
        })), GUIButton.of(3, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, of(MenuText.xpForNextLevel(experience, classLevels))))).build();
    }

    @Override
    public void gainedFrom(@Nonnull Player player, int page, @Nonnull ClassAction classAction, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.GAINED_FROM, player, prevGUI, page, gainedFrom -> SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(gainedFrom.getName())).addGlow(classAction.getGainedFrom() == gainedFrom).build(), gainedFrom -> (g, p) -> {
            classAction.setGainedFrom(gainedFrom);
            g.close();
        }, (p, i) -> gainedFrom(p, i, classAction, prevGUI), GainedFrom.values()).build();
    }

    @Override
    public void healing(@Nonnull Player player, @Nonnull SpellHealing spellHealing, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.HEALING, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.healAmount(spellHealing.getHealAmount()))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellHealing.setHealAmount(dice);
            healing(player, spellHealing, g);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE, of(MenuText.STAT_BONUS)), (g, p) -> statBonus(p, 1, spellHealing::setStatBonus, statBonus -> spellHealing.getStatBonus() == statBonus, g))).build();
    }

    @Override
    public void hitDice(@Nonnull Player player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, @Nonnull HitDice hitDice, @Nullable SpongeChestGUI prevGUI) {
        ItemType redstoneLamp = ItemTypes.REDSTONE_LAMP;
        ItemType glowstone = ItemTypes.GLOWSTONE;
        String[] hitDiceStrings = MenuText.hitDice(hitDice);
        builder(18, 17, MenuText.HIT_DICE, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(redstoneLamp, of(hitDiceStrings[0])), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(6, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(redstoneLamp, of(hitDiceStrings[1])), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(8, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(redstoneLamp, of(hitDiceStrings[2])), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(10, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(redstoneLamp, of(hitDiceStrings[3])), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(12, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(glowstone, of(MenuText.rollHitDie(6))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 6))), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(glowstone, of(MenuText.rollHitDie(8))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 8))), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(glowstone, of(MenuText.rollHitDie(10))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 10))), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(glowstone, of(MenuText.rollHitDie(12))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 12)))).build();
    }

    @Override
    public void hitPoints(@Nonnull Player player, @Nonnull HitPoints hitPoints, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.HIT_POINTS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.currentHitPoints(hitPoints))).potionEffect(PotionEffectTypes.REGENERATION).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setCurrent(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.maxHitPoints(hitPoints))).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setMax(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.tempHitPoints(hitPoints))).potionEffect(PotionEffectTypes.STRENGTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setTemp(i);
            hitPoints(ply, hitPoints, prevGUI);
        }))).build();
    }

    @Override
    public void initiative(@Nonnull Player player, @Nonnull AbilityScore dex, @Nonnull BioAndInfo bioAndInfo, @Nonnull Initiative initiative, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.INITIATIVE, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, of(MenuText.BONUS)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            initiative.setBonus(i);
            initiative(ply, dex, bioAndInfo, initiative, prevGUI);
        })), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.GLOWSTONE, of(MenuText.total(initiative, dex)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.ROLL_INITIATIVE)), (g, p) -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int mod = dex.getMod() + initiative.getBonus();
            int roll = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled for initiative.\nThe result are: ");
            if (roll == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (roll == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            broadcast(tb.append(Text.of(roll + mod)).build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void inventory(@Nonnull Player player, @Nonnull List<MCDNDItem> items, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.INVENTORY, player, prevGUI, page, item -> SpongeIconBuilder.builder(ItemTypes.CHEST).name(Text.of(item.getName())).description(stringToText(MenuText.itemDesc(item))).build(), item -> (g, p) -> item(p, item, items, g), (p, i) -> inventory(p, items, i, prevGUI), items).setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_ITEM)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            MCDNDItem item = new MCDNDItem();
            item.setName(s);
            items.add(item);
            item(ply, item, items, prevGUI);
        }))).build();
    }

    @Override
    public void inventoryTab(@Nonnull Player player, @Nonnull CoreStats coreStats, @Nonnull InventoryTab inventoryTab, @Nullable SpongeChestGUI prevGUI) {
        Wealth wealth = inventoryTab.getWealth();
        builder(9, 8, MenuText.INVENTORY, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.EMERALD).name(of(MenuText.COIN_CARRIED)).description(stringToText(MenuText.coinCarriedDescription(wealth))).build(), (g, p) -> wealth(p, inventoryTab.getWealth(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.STONE, of(MenuText.WEIGHT)), (g, p) -> weight(p, coreStats, inventoryTab.getInventory(), inventoryTab.getWealth(), inventoryTab.getWeight(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CHEST, of(MenuText.INVENTORY)), (g, p) -> inventory(p, inventoryTab.getInventory(), 1, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.INVENTORY_NOTES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.INVENTORY_NOTES, inventoryTab.getInventoryNotes()), pages -> {
                inventoryTab.setInventoryNotes(textToString(pages));
                inventoryTab(p, coreStats, inventoryTab, prevGUI);
            })))).build();
    }

    @Override
    public void item(@Nonnull Player player, @Nonnull MCDNDItem item, @Nonnull List<MCDNDItem> items, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, item.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            item.setName(s);
            item(player, item, items, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(item.isCarried() ? ItemTypes.REDSTONE_TORCH : ItemTypes.REDSTONE_TORCH, of(MenuText.carried(item))), (g, p) -> {
            item.setIsCarried(!item.isCarried());
            item(p, item, items, prevGUI);
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CHEST, of(MenuText.quantity(item))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            item.setQuantity(i);
            item(player, item, items, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.OBSIDIAN, of(MenuText.weight(item))), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            item.setWeight(d);
            item(player, item, items, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.DESCRIPTION)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, item.getName(), item.getDescription()), pages -> {
                item.setDescription(textToString(pages));
                item(p, item, items, prevGUI);
            }))), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            items.remove(item);
            g.close();
            p.sendMessage(Text.of(TextColors.GREEN, Messages.ITEM_DELETED));
        })).build();
    }

    @Override
    public void macroOptions(@Nonnull Player player, @Nonnull MacroOptions macroOptions, @Nullable SpongeChestGUI prevGUI) {
        builder(18, 17, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.INFO_BLOCK)).addGlow(macroOptions.isInfoBlockEnabled()).build(), (g, p) -> {
            macroOptions.setInfoBlockEnabled(!macroOptions.isInfoBlockEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.DESCRIPTION)).addGlow(macroOptions.isDescriptionEnabled()).build(), (g, p) -> {
            macroOptions.setDescriptionEnabled(!macroOptions.isDescriptionEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.AT_HIGHER_LEVELS)).addGlow(macroOptions.isAtHigherLevelsEnabled()).build(), (g, p) -> {
            macroOptions.setAtHigherLevelsEnabled(!macroOptions.isAtHigherLevelsEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.ATTACK)).addGlow(macroOptions.isAttackRollEnabled()).build(), (g, p) -> {
            macroOptions.setAttackRollEnabled(!macroOptions.isAttackRollEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.SECOND_ATTACK)).addGlow(macroOptions.isSecondAttackRollEnabled()).build(), (g, p) -> {
            macroOptions.setSecondAttackRollEnabled(!macroOptions.isSecondAttackRollEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.SAVING_THROW)).addGlow(macroOptions.isSavingThrowEnabled()).build(), (g, p) -> {
            macroOptions.setSavingThrowEnabled(!macroOptions.isSavingThrowEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.HEALING)).addGlow(macroOptions.isHealingEnabled()).build(), (g, p) -> {
            macroOptions.setHealingEnabled(!macroOptions.isHealingEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.DAMAGE)).addGlow(macroOptions.isDamageEnabled()).build(), (g, p) -> {
            macroOptions.setDamageEnabled(!macroOptions.isDamageEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.REDSTONE_TORCH).name(of(MenuText.EFFECTS)).addGlow(macroOptions.isEffectsEnabled()).build(), (g, p) -> {
            macroOptions.setEffectsEnabled(!macroOptions.isEffectsEnabled());
            macroOptions(p, macroOptions, g);
        })).build();
    }

    @Override
    public void meleeBonus(@Nonnull Player player, @Nonnull MeleeBonus meleeBonus, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.MELEE_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.ATTACK_ROLLS)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            meleeBonus.setAttack(dice);
            meleeBonus(player, meleeBonus, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(of(MenuText.DAMAGE_ROLLS)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            meleeBonus.setDamage(dice);
            meleeBonus(player, meleeBonus, prevGUI);
        }))).build();
    }

    @Override
    public void meleeWeapon(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<MeleeWeapon> weapons, MeleeBonus meleeBonus, MeleeWeapon weapon, SpongeChestGUI prevGUI) {
        builder(18, 17, weapon.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(weapon.isProficient() ? ItemTypes.REDSTONE_TORCH : ItemTypes.STICK).name(of(MenuText.isProficient(weapon))).addGlow(weapon.isProficient()).build(), (g, p) -> {
            weapon.setIsProficient(!weapon.isProficient());
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setName(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.ATTACK_STAT)), (g, p) -> attackStat(p, 1, weapon, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.magicBonus(weapon))), (g, p) -> new IntegerAnvilGUI(player, (ply, i) -> {
            weapon.setMagicBonus(i);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(4, SpongeIconBuilder.of(ItemTypes.STONE_SWORD, of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))))), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, of(MenuText.damageDice(weapon.getDamage()))), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setDamage(dice);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.GLOWSTONE).name(of(MenuText.PLUS_STAT)).addGlow(weapon.plusStat()).build(), (g, p) -> {
            weapon.setPlusStat(!weapon.plusStat());
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        }), GUIButton.of(7, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.damageBonus(weapon, coreStats)))), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND, of(MenuText.damageType(weapon.getDamageType()))), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setDamageType(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.critDamage(weapon))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setCritDamage(dice);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, of(MenuText.critOn(weapon))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            weapon.setCritMin(i);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.ROLL_ATTACK)), (g, p) -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(meleeBonus.getAttack().roll());
            String newLine = "\n";
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") meleed with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (firstAttackRoll == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(firstAttackRoll + bonus, TextColors.RESET, " | "));
            if (secondAttackRoll >= weapon.getCritMin()) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (secondAttackRoll == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(secondAttackRoll + bonus, TextColors.RESET, " vs AC" + newLine + "Damage: ", Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(meleeBonus.getDamage().roll())), " " + weapon.getDamageType()));
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                tb.append(Text.of("Crit: " + Dice.total(critDice.roll()) + newLine + "Crit (adv roll): " + critDice.roll()));
            }

            broadcast(tb.build());
            p.closeInventory();
        }), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            weapons.remove(weapon);
            g.close();
        })).build();
    }

    @Override
    public void meleeWeapons(@Nonnull Player player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<MeleeWeapon> meleeWeapons, @Nonnull MeleeBonus meleeBonus, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.MELEE_WEAPONS, player, prevGUI, page, meleeWeapon -> ItemRepresentation.meleeWeapon(meleeWeapon, classLevels, coreStats, experience), meleeWeapon -> (g, p) -> meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, meleeWeapon, g), (p, i) -> meleeWeapons(p, i, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, prevGUI), meleeWeapons).setButtons(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_MELEE_WEAPON)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            MeleeWeapon meleeWeapon = new MeleeWeapon();
            meleeWeapon.setName(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, meleeWeapon, g);
        }))).build();
    }

    @Override
    public void nonPlayer(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nullable SpongeChestGUI prevGUI) {
        SpongeChestGUIBuilder builder = builder(9, 8, MenuText.name(nonPlayer), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setName(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, of(MenuText.CLASS)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setClazz(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.MONSTER_EGG, of(MenuText.RACE)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setClazz(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, of(MenuText.ACTIONS)), (g, p) -> nonPlayerActions(p, nonPlayer.getNonPlayerActions(), g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.CHARACTER_SHEET)), (g, p) -> nonPlayerSheet(p, nonPlayer, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.SKILLS)), (g, p) -> nonPlayerSkills(p, nonPlayer.getNonPlayerSheet().getCoreStats(), nonPlayer, nonPlayer.getSkills(), g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.TRAITS_BACKGROUND)), (g, p) -> traitsBackground(p, nonPlayer.getTraitsBackground(), g)));
        if (player.hasPermission(Permissions.DM)) {
            builder.setButtons(GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.EDIT_CONTROLLERS)).durability(3).build(), (g, p) -> editControllers(p, 1, nonPlayer, g)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getNonPlayerSheetStorage).ifPresent(nonPlayerSheetStorage -> nonPlayerSheetStorage.remove(nonPlayer))));
        }

        builder.build();
    }

    @Override
    public void nonPlayerAbilityScore(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, abilityScore.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.score(abilityScore))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setScore(i);
            nonPlayerAbilityScore(ply, nonPlayer, abilityScore, prevGUI);
        })), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, of(MenuText.mod(abilityScore)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.bonus(abilityScore))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setBonus(i);
            nonPlayerAbilityScore(ply, nonPlayer, abilityScore, prevGUI);
        })), GUIButton.of(3, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, of(MenuText.saveTotal(abilityScore)))), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.ROLL_SAVE)), (g, p) -> {
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveTotal();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(nonPlayer.getName() + " has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (first == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(first + saveMod, TextColors.RESET, " | "));
            if (second == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (second == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            broadcast(tb.append(Text.of(second + saveMod)).build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void nonPlayerAction(@Nonnull Player player, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, nonPlayerAction.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerAction.setName(s);
            nonPlayerAction(ply, nonPlayerAction, nonPlayerActions, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.actionType(nonPlayerAction.getActionType()))), (g, p) -> nonPlayerActionType(p, 1, nonPlayerAction, nonPlayerActions, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.DESCRIPTION)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getDescription()), pages -> {
                nonPlayerAction.setDescription(textToString(pages));
                g.open();
            }))), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.EFFECT)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getEffect()), pages -> {
                    nonPlayerAction.setEffect(textToString(pages));
                    g.open();
                }))), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.GOLDEN_SWORD).name(of(MenuText.EFFECT)).addGlow(nonPlayerAction.isMultiAttack()).build(), (g, p) -> {
            nonPlayerAction.setIsMultiAttack(!nonPlayerAction.isMultiAttack());
            nonPlayerAction(p, nonPlayerAction, nonPlayerActions, prevGUI);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            nonPlayerActions.remove(nonPlayerAction);
            g.close();
        })).build();
    }

    @Override
    public void nonPlayerActionType(@Nonnull Player player, int page, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.ACTION_TYPE, player, prevGUI, page, actionType -> SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(actionType.getName())).addGlow(nonPlayerAction.getActionType() == actionType).build(), actionType -> (g, p) -> {
            nonPlayerAction.setActionType(actionType);
            nonPlayerAction(p, nonPlayerAction, nonPlayerActions, g.getPreviousGUI());
        }, (p, i) -> nonPlayerActionType(p, i, nonPlayerAction, nonPlayerActions, prevGUI), NonPlayerActionType.values()).build();
    }

    @Override
    public void nonPlayerActions(@Nonnull Player player, @Nonnull NonPlayerActions nonPlayerActions, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.ACTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(of(MenuText.MULTI_ATTACK)).description(of(nonPlayerActions.getMultiAttack())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerActions.setMultiAttack(s);
            nonPlayerActions(ply, nonPlayerActions, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, of(MenuText.ACTIONS)), (g, p) -> nonPlayerActions(p, 1, nonPlayerActions.getActions(), g))).build();
    }

    @Override
    public void nonPlayerActions(@Nonnull Player player, int page, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.ACTIONS, player, prevGUI, page, nonPlayerAction -> SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(nonPlayerAction.getName())), nonPlayerAction -> (g, p) -> nonPlayerAction(p, nonPlayerAction, nonPlayerActions, g), (p, i) -> nonPlayerActions(p, i, nonPlayerActions, prevGUI), nonPlayerActions).setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.MULTI_ATTACK)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            NonPlayerAction nonPlayerAction = new NonPlayerAction();
            nonPlayerAction.setName(s);
            nonPlayerActions.add(nonPlayerAction);
            nonPlayerAction(ply, nonPlayerAction, nonPlayerActions, g);
        }))).build();
    }

    @Override
    public void nonPlayerCoreStats(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull SpongeChestGUI prevGUI) {
        NonPlayerAbilityScore str = coreStats.getStrength();
        NonPlayerAbilityScore dex = coreStats.getDexterity();
        NonPlayerAbilityScore con = coreStats.getConstitution();
        NonPlayerAbilityScore intel = coreStats.getIntelligence();
        NonPlayerAbilityScore wis = coreStats.getWisdom();
        NonPlayerAbilityScore cha = coreStats.getCharisma();
        builder(9, 8, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(of(str.getName())).description(stringToText(MenuText.scoreLore(str))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, str, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.BOW).name(of(dex.getName())).description(stringToText(MenuText.scoreLore(dex))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, dex, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(of(con.getName())).description(stringToText(MenuText.scoreLore(con))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, con, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(intel.getName())).description(stringToText(MenuText.scoreLore(intel))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, intel, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(wis.getName())).description(stringToText(MenuText.scoreLore(wis))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, wis, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).durability(3).name(of(cha.getName())).description(stringToText(MenuText.scoreLore(cha))).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, cha, g))).build();
    }

    @Override
    public void nonPlayerHitPoints(@Nonnull Player player, @Nonnull NonPlayerHitPoints hitPoints, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.HIT_POINTS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.currentHitPoints(hitPoints))).potionEffect(PotionEffectTypes.REGENERATION).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setCurrent(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.maxHitPoints(hitPoints))).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setMax(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.tempHitPoints(hitPoints))).potionEffect(PotionEffectTypes.STRENGTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setTemp(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.hitDice(hitPoints))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            hitPoints.setHitDice(dice);
            hitPoints(ply, hitPoints, prevGUI);
        }))).build();
    }

    @Override
    public void nonPlayerSheet(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nullable SpongeChestGUI prevGUI) {
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        builder(18, 17, MenuText.CHARACTER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, of(MenuText.alignment(nonPlayerSheet))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setAlignment(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, of(MenuText.armorClass(nonPlayerSheet))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setArmorClass(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.BOOK).name(of(MenuText.ARMOR_CLASS_NOTE)).description(of(nonPlayerSheet.getArmorClassNote())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setArmorClassNote(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.challengeRating(nonPlayerSheet))), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            nonPlayerSheet.setChallengeRating(d);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.climbSpeed(nonPlayerSheet))).potionEffect(PotionEffectTypes.JUMP_BOOST).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setClimbSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ELYTRA, of(MenuText.flySpeed(nonPlayerSheet))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setFlySpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.speed(nonPlayerSheet))).potionEffect(PotionEffectTypes.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.swimSpeed(nonPlayerSheet))).potionEffect(PotionEffectTypes.WATER_BREATHING).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setSwimSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.CORE_STATS)).potionEffect(PotionEffectTypes.STRENGTH).build(), (g, p) -> nonPlayerCoreStats(p, nonPlayer, nonPlayerSheet.getCoreStats(), g)), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DIAMOND).name(of(MenuText.dmOutputOnly(nonPlayerSheet))).addGlow(nonPlayerSheet.isDMOutputOnly()).build(), (g, p) -> {
            nonPlayerSheet.setDMOutputOnly(!nonPlayerSheet.isDMOutputOnly());
            nonPlayerSheet(p, nonPlayer, prevGUI);
        }), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.swimSpeed(nonPlayerSheet))).potionEffect(PotionEffectTypes.WATER_BREATHING).build(), (g, p) -> nonPlayerHitPoints(p, nonPlayerSheet.getHealth(), g)), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.KNOWN_LANGUAGES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.KNOWN_LANGUAGES, nonPlayerSheet.getLanguages()), pages -> {
                nonPlayerSheet.setLanguages(textToString(pages));
                nonPlayerSheet(p, nonPlayer, prevGUI);
            }))), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.SENSES)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.SENSES, nonPlayerSheet.getSenses()), pages -> {
                    nonPlayerSheet.setSenses(textToString(pages));
                    nonPlayerSheet(p, nonPlayer, prevGUI);
                }))), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.LEATHER, of(MenuText.size(nonPlayerSheet))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setSize(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.MONSTER_EGG, of(MenuText.typeRace(nonPlayerSheet))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setTypeRace(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(15, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, of(MenuText.experience(nonPlayerSheet))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setXP(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        }))).build();
    }

    @Override
    public void nonPlayerSkills(@Nonnull Player player, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerSkills skills, @Nullable SpongeChestGUI prevGUI) {
        builder(27, 26, MenuText.SKILLS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.LEATHER_BOOTS, of(MenuText.ACROBATICS)), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getAcrobatics(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SADDLE, of(MenuText.ANIMAL_HANDLING)), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getAnimalHandling(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.ARCANA)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getArcana(), g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.SPEED).name(of(MenuText.ATHLETICS)).build(), (g, p) -> skill(p, nonPlayer, coreStats.getStrength(), skills.getAthletics(), g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.EMERALD, of(MenuText.DECEPTION)), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getDeception(), g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.HISTORY)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getHistory(), g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ICE, of(MenuText.INSIGHT)), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getInsight(), g)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.INTIMIDATION)), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getIntimidation(), g)), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.INVESTIGATION)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getInvestigation(), g)), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(of(MenuText.MEDICINE)).build(), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getMedicine(), g)), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.VINE, of(MenuText.NATURE)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getNature(), g)), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CARROT, of(MenuText.PERCEPTION)), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getPerception(), g)), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, of(MenuText.PERFORMANCE)), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getPerformance(), g)), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_EYE, of(MenuText.PERSUASION)), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getPersuasion(), g)), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(MenuText.RELIGION)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getReligion(), g)), GUIButton.of(15, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TRIPWIRE_HOOK, of(MenuText.SLEIGHT_OF_HAND)), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getSleightOfHand(), g)), GUIButton.of(16, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.NIGHT_VISION).name(of(MenuText.STEALTH)).build(), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getStealth(), g)), GUIButton.of(17, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.SURVIVAL)), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getSurvival(), g)), GUIButton.of(18, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.STRENGTH)), (g, p) -> skill(p, nonPlayer, coreStats.getStrength(), skills.getUnskilledSTR(), g)), GUIButton.of(19, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.DEXTERITY)), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getUnskilledDEX(), g)), GUIButton.of(20, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_APPLE, of(MenuText.CONSTITUTION)), (g, p) -> skill(p, nonPlayer, coreStats.getConstitution(), skills.getUnskilledCON(), g)), GUIButton.of(21, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.INTELLIGENCE)), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getUnskilledINT(), g)), GUIButton.of(22, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.WISDOM)), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getUnskilledWIS(), g)), GUIButton.of(23, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.CHARISMA)).durability(3).build(), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getUnskilledCHA(), g))).build();
    }

    @Override
    public void nonPlayers(@Nonnull Player player, @Nonnull List<NonPlayer> nonPlayers, int page) {
        SpongeChestGUIBuilder builder = paged(MenuText.PLAYER_SHEETS, player, null, page, playerSheet -> SpongeIconBuilder.of(ItemTypes.WRITTEN_BOOK, of(playerSheet.getName())), nonPlayer -> (g, p) -> nonPlayer(player, nonPlayer, g), (p, i) -> nonPlayers(p, nonPlayers, i), nonPlayers);
        if (player.hasPermission(Permissions.DM)) {
            builder.setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_PLAYER_SHEET)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
                PlayerSheet playerSheet = new PlayerSheet();
                playerSheet.setName(s);
                playerSheet(ply, playerSheet, g);
            })));
        }

        builder.build();
    }

    private void openBookCommand(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull List<String> pages, @Nonnull Spell spell) {
        player.sendBookView(BookView.builder().title(of(spell.getName())).addPages(stringToText(pages)).author(of(bioAndInfo.getName())).build());
    }

    @Override
    public void outputOptions(@Nonnull Player player, @Nonnull OutputOptions outputOptions, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_INGOT, of(MenuText.SAVING_THROWS)), (g, p) -> savingThrowOutputOptions(p, outputOptions.getSavingThrowOutputOptions(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLD_INGOT, of(MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS)), (g, p) -> weaponsSpellMiscOutputOptions(p, outputOptions.getWeaponsSpellMiscOutputOptions(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS)), (g, p) -> coreSkillsOutputOptions(p, outputOptions.getCoreSkillsOutputOptions(), g))).build();
    }

    @Nonnull
    @Override
    protected <O> SpongeChestGUIBuilder paged(@Nonnull String name, @Nonnull Player player, @Nullable SpongeChestGUI prevGUI, int page, @Nonnull Function<O, ItemStack> itemStackMapper, @Nullable Function<O, BiConsumer<SpongeChestGUI, Player>> actionMapper, @Nonnull BiConsumer<Player, Integer> pageNavigator, @Nonnull List<O> contents) {
        int maxPage = new Double(Math.ceil(contents.size() / 45)).intValue();
        return builder(54, 53, name, player, prevGUI).setPage(page).setContents(ClickInventoryEvent.Primary.class, itemStackMapper, actionMapper, contents).setJumpToPage(45, maxPage, pageNavigator).setPageNavigation(48, of(MenuText.PREVIOUS_PAGE), (gui, p) -> {
            if (page > 1) {
                pageNavigator.accept(p, page - 1);
            }
        }).setPageNavigation(50, of(MenuText.NEXT_PAGE), (gui, p) -> {
            if (page + 1 > maxPage) {
                pageNavigator.accept(p, page + 1);
            }
        });
    }

    @Override
    public void playerAbilityScore(@Nonnull Player player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, abilityScore.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.score(abilityScore))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setScore(i);
            playerAbilityScore(ply, abilityScore, bioAndInfo, classLevels, experience, prevGUI);
        })), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, of(MenuText.mod(abilityScore)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.proficient(abilityScore))), (g, p) -> {
            abilityScore.setIsProficient(!abilityScore.isProficient());
            playerAbilityScore(p, abilityScore, bioAndInfo, classLevels, experience, prevGUI);
        }), GUIButton.of(3, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, of(MenuText.saveMod(abilityScore, classLevels, experience)))), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.ROLL_SAVE)), (g, p) -> {
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveMod(classLevels, experience);
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (first == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(first + saveMod, TextColors.RESET, " | "));
            if (second == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (second == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            broadcast(tb.append(Text.of(second + saveMod)).build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void playerCoreStats(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Experience experience, @Nullable SpongeChestGUI prevGUI) {
        PlayerAbilityScore str = coreStats.getStrength();
        PlayerAbilityScore dex = coreStats.getDexterity();
        PlayerAbilityScore con = coreStats.getConstitution();
        PlayerAbilityScore intel = coreStats.getIntelligence();
        PlayerAbilityScore wis = coreStats.getWisdom();
        PlayerAbilityScore cha = coreStats.getCharisma();
        builder(9, 8, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(of(str.getName())).description(stringToText(MenuText.scoreLore(str, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, str, bioAndInfo, classLevels, experience, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.BOW).name(of(dex.getName())).description(stringToText(MenuText.scoreLore(dex, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, dex, bioAndInfo, classLevels, experience, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(of(con.getName())).description(stringToText(MenuText.scoreLore(con, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, con, bioAndInfo, classLevels, experience, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(intel.getName())).description(stringToText(MenuText.scoreLore(intel, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, intel, bioAndInfo, classLevels, experience, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(wis.getName())).description(stringToText(MenuText.scoreLore(wis, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, wis, bioAndInfo, classLevels, experience, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).durability(3).name(of(cha.getName())).description(stringToText(MenuText.scoreLore(cha, classLevels, experience))).build(), (g, p) -> playerAbilityScore(p, cha, bioAndInfo, classLevels, experience, g))).build();
    }

    @Override
    public void playerSheet(@Nonnull Player player, @Nonnull PlayerSheet playerSheet, @Nullable SpongeChestGUI prevGUI) {
        SpongeChestGUIBuilder builder = builder(9, 8, MenuText.PLAYER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setName(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, of(MenuText.CLASS)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setClazz(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.MONSTER_EGG, of(MenuText.RACE)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setClazz(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.BIO_AND_INFO)), (g, p) -> bioAndInfo(p, playerSheet.getBioAndInfo(), playerSheet, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.CHARACTER_SHEET)), (g, p) -> characterSheet(p, playerSheet.getBioAndInfo(), playerSheet.getCharacterSheet(), g)));
        if (player.hasPermission(Permissions.DM)) {
            SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getPlayerSheetStorage).ifPresent(playerSheetStorage -> builder.setButtons(GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.EDIT_CONTROLLERS)).durability(3).build(), (g, p) -> editControllers(p, 1, playerSheet, g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> playerSheetStorage.remove(playerSheet))));
        }

        builder.build();
    }

    @Override
    public void players(@Nonnull Player player, @Nonnull List<PlayerSheet> playerSheets, int page) {
        SpongeChestGUIBuilder builder = paged(MenuText.PLAYER_SHEETS, player, null, page, playerSheet -> SpongeIconBuilder.of(ItemTypes.WRITTEN_BOOK, of(playerSheet.getName())), playerSheet -> (g, p) -> playerSheet(player, playerSheet, g), (p, i) -> players(p, playerSheets, i), playerSheets);
        if (player.hasPermission(Permissions.DM)) {
            builder.setButton(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_PLAYER_SHEET)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
                PlayerSheet playerSheet = new PlayerSheet();
                playerSheet.setName(s);
                playerSheet(ply, playerSheet, g);
            })));
        }

        builder.build();
    }

    @Override
    public void prepared(@Nonnull Player player, int page, @Nonnull Spell spell, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.prepared(spell.getPrepared()), player, prevGUI, page, prepared -> SpongeIconBuilder.builder(ItemTypes.BOOK).name(of(prepared.getName())).addGlow(spell.getPrepared() == prepared).build(), prepared -> (g, p) -> {
            spell.setPrepared(prepared);
            prepared(player, page, spell, prevGUI);
        }, (p, i) -> prepared(player, i, spell, prevGUI), Prepared.values()).build();
    }

    @Override
    public void rangedBonus(@Nonnull Player player, @Nonnull RangedBonus rangedBonus, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.RANGED_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.ATTACK_ROLLS)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            rangedBonus.setAttack(dice);
            rangedBonus(player, rangedBonus, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(of(MenuText.DAMAGE_ROLLS)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            rangedBonus.setDamage(dice);
            rangedBonus(player, rangedBonus, prevGUI);
        }))).build();
    }

    @Override
    public void rangedWeapon(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<RangedWeapon> weapons, RangedBonus rangedBonus, RangedWeapon weapon, SpongeChestGUI prevGUI) {
        builder(18, 17, weapon.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(weapon.isProficient() ? ItemTypes.REDSTONE_TORCH : ItemTypes.STICK).name(of(MenuText.isProficient(weapon))).addGlow(weapon.isProficient()).build(), (g, p) -> {
            weapon.setIsProficient(!weapon.isProficient());
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setName(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.ATTACK_STAT)), (g, p) -> attackStat(p, 1, weapon, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.magicBonus(weapon))), (g, p) -> new IntegerAnvilGUI(player, (ply, i) -> {
            weapon.setMagicBonus(i);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(4, SpongeIconBuilder.of(ItemTypes.STONE_SWORD, of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))))), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, of(MenuText.damageDice(weapon.getDamage()))), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setDamage(dice);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(6, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.damageBonus(weapon, coreStats)))), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND, of(MenuText.damageType(weapon.getDamageType()))), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setDamageType(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.critDamage(weapon))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setCritDamage(dice);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, of(MenuText.critOn(weapon))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            weapon.setCritMin(i);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.ROLL_ATTACK)), (g, p) -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(rangedBonus.getAttack().roll());
            String newLine = "\n";
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") ranged with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (firstAttackRoll == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(firstAttackRoll + bonus, TextColors.RESET, " | "));
            if (secondAttackRoll >= weapon.getCritMin()) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (secondAttackRoll == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of((secondAttackRoll + bonus) + " vs AC" + newLine + "Damage: " + Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(rangedBonus.getDamage().roll())) + " " + weapon.getDamageType()));
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                tb.append(Text.of("Crit: " + Dice.total(critDice.roll()) + newLine + "Crit (adv roll): " + critDice.roll()));
            }

            broadcast(tb.build());
            p.closeInventory();
        }), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, of(MenuText.DELETE)), (g, p) -> {
            weapons.remove(weapon);
            g.close();
        })).build();
    }

    @Override
    public void rangedWeapons(@Nonnull Player player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<RangedWeapon> rangedWeapons, @Nonnull RangedBonus rangedBonus, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.RANGED_WEAPONS, player, prevGUI, page, rangedWeapon -> ItemRepresentation.rangedWeapon(rangedWeapon, classLevels, coreStats, experience), rangedWeapon -> (g, p) -> rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, rangedWeapon, g), (p, i) -> rangedWeapons(p, i, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, prevGUI), rangedWeapons).setButtons(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_RANGED_WEAPON)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            RangedWeapon rangedWeapon = new RangedWeapon();
            rangedWeapon.setName(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, rangedWeapon, g);
        }))).build();
    }

    @Override
    public <R extends Rechargeable> void recharge(@Nonnull Player player, @Nonnull R rechargeable, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.RECHARGE, player, prevGUI, page, recharge -> SpongeIconBuilder.builder(ItemTypes.BED).name(of(rechargeable.getRecharge().getName())).addGlow(rechargeable.getRecharge() == recharge).build(), recharge -> (g, p) -> {
            rechargeable.setRecharge(recharge);
            g.close();
        }, (p, i) -> recharge(p, rechargeable, i, prevGUI), Recharge.values()).build();
    }

    @Override
    protected void rollHitDie(@Nonnull Player player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, int amount, int sides) {
        player.closeInventory();
        Dice dice = new Dice(amount, sides);
        player.sendMessage(Text.joinWith(of(" "), stringToText(Messages.rolledHitDice(bioAndInfo, player.getName(), Dice.total(dice.roll(), constitution.getMod()), dice))));
    }

    @Override
    public void savingThrowOutputOptions(@Nonnull Player player, @Nonnull SavingThrowOutputOptions savingThrowOutputOptions, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.SAVING_THROW_OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(of(MenuText.STRENGTH)).addGlow(savingThrowOutputOptions.isStrengthEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.BOW).name(of(MenuText.DEXTERITY)).addGlow(savingThrowOutputOptions.isDexterityEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.CONSTITUTION)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).addGlow(savingThrowOutputOptions.isConstitutionEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.INTELLIGENCE)).addGlow(savingThrowOutputOptions.isIntelligenceEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(MenuText.WISDOM)).addGlow(savingThrowOutputOptions.isWisdomEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.CHARISMA)).durability(3).addGlow(savingThrowOutputOptions.isCharismaEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        })).build();
    }

    @Override
    public void skill(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nonnull NonPlayerSkill skill, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, skill.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DYE).name(of(MenuText.bonus(skill))).durability(4).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            skill.setBonus(i);
            skill(p, nonPlayer, abilityScore, skill, prevGUI);
        })), GUIButton.of(2, SpongeIconBuilder.of(ItemTypes.BOOKSHELF, of(MenuText.total(abilityScore, skill)))), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.bonus(skill))), (g, p) -> {
            g.close();
            Dice dice = new Dice(20);
            int bonus = skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(nonPlayer.getName() + " has rolled a " + skill.getName() + " check.\nThe results are: ");
            if (first == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (first == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(first + bonus, TextColors.RESET, " | "));
            if (second == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (second == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            broadcast(tb.append(Text.of(second + bonus)).build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void skill(@Nonnull Player player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nonnull Dice skillBonus, @Nonnull PlayerSkill skill, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, skill.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(MenuText.PROFICIENT)).description(of("- " + skill.getSkillProficiency().getName())).build(), (g, p) -> skillProficiency(p, skill, 1, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DYE).name(of(MenuText.bonus(skill))).durability(4).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            skill.setBonus(i);
            skill(p, abilityScore, bioAndInfo, classLevels, experience, skillBonus, skill, prevGUI);
        })), GUIButton.of(2, SpongeIconBuilder.of(ItemTypes.BOOKSHELF, of(MenuText.total(abilityScore, classLevels, experience, skill)))), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.bonus(skill))), (g, p) -> {
            Dice dice = new Dice(20);
            int bonus = Dice.total(skillBonus.roll()) + skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            Text.Builder tb = Text.builder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + skill.getName() + " check.\nThe results are: ");
            if (first == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (first == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            tb.append(Text.of(first + bonus, TextColors.RESET, " | "));
            if (second == 20) {
                tb.append(Text.of(TextColors.GREEN));
            }
            else if (second == 1) {
                tb.append(Text.of(TextColors.RED));
            }

            broadcast(tb.append(Text.of(second + bonus)).build());
            p.closeInventory();
        })).build();
    }

    @Override
    public void skillProficiency(@Nonnull Player player, @Nonnull PlayerSkill skill, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.PROFICIENT, player, prevGUI, page, skillProficiency -> SpongeIconBuilder.of(ItemTypes.BOOK, of(skillProficiency.getName())), skillProficiency -> (g, p) -> {
            skill.setSkillProficiency(skillProficiency);
            g.close();
        }, (p, i) -> skillProficiency(p, skill, i, prevGUI), SkillProficiency.values()).build();
    }

    @Override
    public void skillsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Dice skillBonus, @Nonnull Experience experience, @Nonnull SkillsTab skillsTab, @Nullable SpongeChestGUI prevGUI) {
        builder(27, 26, MenuText.SKILLS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.LEATHER_BOOTS, of(MenuText.ACROBATICS)), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAcrobatics(), g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SADDLE, of(MenuText.ANIMAL_HANDLING)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAnimalHandling(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.ARCANA)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getArcana(), g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.SPEED).name(of(MenuText.ATHLETICS)).build(), (g, p) -> skill(p, coreStats.getStrength(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAthletics(), g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.EMERALD, of(MenuText.DECEPTION)), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getDeception(), g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.HISTORY)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getHistory(), g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ICE, of(MenuText.INSIGHT)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getInsight(), g)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.INTIMIDATION)), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getIntimidation(), g)), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.INVESTIGATION)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getInvestigation(), g)), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(of(MenuText.MEDICINE)).build(), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getMedicine(), g)), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.VINE, of(MenuText.NATURE)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getNature(), g)), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CARROT, of(MenuText.PERCEPTION)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPerception(), g)), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, of(MenuText.PERFORMANCE)), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPerformance(), g)), GUIButton.of(13, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENDER_EYE, of(MenuText.PERSUASION)), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPersuasion(), g)), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, of(MenuText.RELIGION)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getReligion(), g)), GUIButton.of(15, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TRIPWIRE_HOOK, of(MenuText.SLEIGHT_OF_HAND)), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getSleightOfHand(), g)), GUIButton.of(16, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.NIGHT_VISION).name(of(MenuText.STEALTH)).build(), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getStealth(), g)), GUIButton.of(17, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SKULL, of(MenuText.SURVIVAL)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getSurvival(), g)), GUIButton.of(18, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, of(MenuText.STRENGTH)), (g, p) -> skill(p, coreStats.getStrength(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledSTR(), g)), GUIButton.of(19, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.DEXTERITY)), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledDEX(), g)), GUIButton.of(20, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_APPLE, of(MenuText.CONSTITUTION)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g)), GUIButton.of(21, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.INTELLIGENCE)), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledINT(), g)), GUIButton.of(22, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.WISDOM)), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g)), GUIButton.of(23, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SKULL).name(of(MenuText.CHARISMA)).durability(3).build(), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g))).build();
    }

    @Override
    public void spell(@Nonnull Player player, @Nonnull Spell spell, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpongeChestGUI prevGUI) {
        SpongeChestGUIBuilder builder = builder(27, 26, spell.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.RENAME)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setName(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DYE).durability(4).name(of(MenuText.RENAME)).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spell.setLevel(i);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.spellType(spell.getSpellType()))), (g, p) -> spellType(p, 1, spell, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.CLOCK, of(MenuText.castTime(spell.getCastTime()))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setCastTime(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, of(MenuText.concentration(spell))), (g, p) -> {
            spell.setNeedsConcentration(!spell.needsConcentration());
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.TOTEM_OF_UNDYING, of(MenuText.ritual(spell))), (g, p) -> {
            spell.setIsRitual(!spell.isRitual());
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        }), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.gainedFrom(spell.getGainedFrom()))), (g, p) -> spellcasterClass(player, 1, spell::setGainedFrom, spellCasterClass -> spellCasterClass == spell.getGainedFrom(), g)), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.LINGERING_POTION, of(MenuText.TARGET)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setTargetArea(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.range(spell))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setRange(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(10, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.STRING, of(MenuText.duration(spell))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setDuration(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(11, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.PURPLE_SHULKER_BOX).name(of(MenuText.COMPONENTS)).description(of(spell.getComponents())).build(), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, spell.getName(), spell.getComponents()), pages -> {
                spell.setComponents(textToString(pages));
                spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
            }))), GUIButton.of(12, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.SPELL_INFO)), (g, p) -> {
            MacroOptions macroOptions = spell.getMacroOptions();
            String newLine = "\n";
            Text.Builder spellHoverText = Text.builder(MenuText.spellType(spell.getSpellType()) + newLine + of(MenuText.spellLevel(spell.getLevel())));
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.append(Text.of(newLine + MenuText.components(spell) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell)));
            }

            if (macroOptions.isAttackRollEnabled()) {
                Dice d20 = new Dice(20);
                int attackRoll = Dice.total(d20.roll());
                int bonus = Dice.total(spellcastingBonus.getAttack().roll());
                spellHoverText.append(of(newLine + "Attack: "));
                if (attackRoll == 1) {
                    spellHoverText.append(Text.of(TextColors.RED));
                }
                else if (attackRoll == 20) {
                    spellHoverText.append(Text.of(TextColors.GREEN));
                }

                spellHoverText.append(Text.of(attackRoll + bonus + experience.getProficiencyBonus(classLevels), TextColors.RESET));
                if (macroOptions.isSecondAttackRollEnabled()) {
                    int secAttackRoll = Dice.total(d20.roll());
                    spellHoverText.append(Text.of(" | "));
                    if (secAttackRoll == 1) {
                        spellHoverText.append(Text.of(TextColors.RED));
                    }
                    else if (attackRoll == 20) {
                        spellHoverText.append(Text.of(TextColors.GREEN));
                    }

                    spellHoverText.append(Text.of(attackRoll + bonus + experience.getProficiencyBonus(classLevels), TextColors.RESET));
                }

                spellHoverText.append(of(" vs AC"));
            }

            if (macroOptions.isSavingThrowEnabled()) {
                SpellSave spellSave = spell.getSpellSave();
                spellHoverText.append(of(newLine + "Save: DC " + Dice.total(spellcastingBonus.getSaveDC().roll(), spellSave.getSaveDCType().getSpellSaveDC(classLevels, coreStats, experience)) + " " + spellSave.getSavingStat() + newLine + "Click "));
                spellHoverText.append(Text.of(Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(TextActions.executeCallback(source -> openBookCommand((Player) source, bioAndInfo, spellSave.getOnSuccessfulSave(), spell))).build(), " to view the effect of a successful save of this spell."));
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

            Text.Builder spellComponent = Text.builder(spell.getName());
            spellComponent.color(TextColors.GREEN);
            spellComponent.style(TextStyles.BOLD);
            spellComponent.onClick(TextActions.executeCallback(source -> source.sendMessage(spellHoverText.build())));
            spellComponent.onHover(TextActions.showText(of("Click to view!")));
            if (macroOptions.isDescriptionEnabled()) {
                spellComponent.append(Text.of(newLine + "Click ", Text.of(Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(TextActions.executeCallback(source -> openBookCommand((Player) source, bioAndInfo, spell.getEffects(), spell))).build(), " to view the spell's effects.")));
            }

            if (macroOptions.isAtHigherLevelsEnabled()) {
                spellComponent.append(Text.of(newLine + "Click ", Text.builder("HERE").color(TextColors.GREEN).style(TextStyles.BOLD).onClick(TextActions.executeCallback(source -> openBookCommand((Player) source, bioAndInfo, spell.getAtHigherLevels(), spell))).build(), " to view how the spell behaves at higher levels."));
            }

            broadcast(Text.of(p.getName() + " (as " + bioAndInfo + ") cast ", spellComponent));
            p.closeInventory();
        }), GUIButton.of(14, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.COMPARATOR, of(MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS)), (g, p) -> macroOptions(p, spell.getMacroOptions(), g)), GUIButton.of(18, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.DESCRIPTION)), (g, p) -> spellDescription(p, spell, g)), GUIButton.of(19, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.ATTACK)), (g, p) -> statBonus(p, 1, spell::setAttackStat, statBonus -> spell.getAttackStat() == statBonus, g)), GUIButton.of(20, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SHIELD, of(MenuText.SAVE)), (g, p) -> spellSave(p, spell.getSpellSave(), g)), GUIButton.of(21, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(of(MenuText.HEALING)).build(), (g, p) -> healing(p, spell.getSpellHealing(), g)), GUIButton.of(22, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, of(MenuText.DAMAGE)), (g, p) -> spellDamage(p, spell.getSpellDamage(), g)), GUIButton.of(23, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.SPELL_EFFECTS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.SPELL_EFFECTS, spell.getEffects()), pages -> {
                spell.setEffects(textToString(pages));
                g.open();
            }))));

        ItemStack icon = SpongeIconBuilder.of(ItemTypes.REPEATER, of(MenuText.prepared(spell.getPrepared())));
        if (spell.getLevel() == 0) {
            builder.setButton(GUIButton.of(6, icon));
        }
        else {
            builder.setButton(GUIButton.of(6, ClickInventoryEvent.Primary.class, icon, (g, p) -> prepared(p, 1, spell, g)));
        }

        builder.build();
    }

    @Override
    public void spellBook(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<Spell> spells, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpongeChestGUI prevGUI) {
        builder(18, 17, MenuText.SPELLS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.CANTRIPS)).description(of(MenuText.total(0, spells))).build(), (g, p) -> spells(p, 0, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_1)).description(of(MenuText.total(1, spells))).build(), (g, p) -> spells(p, 1, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_2)).description(of(MenuText.total(2, spells))).build(), (g, p) -> spells(p, 2, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_3)).description(of(MenuText.total(3, spells))).build(), (g, p) -> spells(p, 3, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_4)).description(of(MenuText.total(4, spells))).build(), (g, p) -> spells(p, 4, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_5)).description(of(MenuText.total(5, spells))).build(), (g, p) -> spells(p, 5, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(6, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_6)).description(of(MenuText.total(6, spells))).build(), (g, p) -> spells(p, 6, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_7)).description(of(MenuText.total(7, spells))).build(), (g, p) -> spells(p, 7, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(8, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_8)).description(of(MenuText.total(8, spells))).build(), (g, p) -> spells(p, 8, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(9, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(of(MenuText.SPELL_SLOT_9)).description(of(MenuText.total(9, spells))).build(), (g, p) -> spells(p, 9, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g))).build();
    }

    @Override
    public void spellDamage(@Nonnull Player player, @Nonnull SpellDamage spellDamage, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.DAMAGE, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(of(MenuText.CAN_CRIT)).addGlow(spellDamage.canCrit()).build(), (g, p) -> {
            spellDamage.setCanCrit(!spellDamage.canCrit());
            spellDamage(player, spellDamage, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, of(MenuText.DAMAGE_DICE)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellDamage.setDamage(dice);
            g.open();
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.STICK, of(MenuText.otherBonus(spellDamage))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellDamage.setBonus(i);
            spellDamage(player, spellDamage, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.damageType(spellDamage.getDamageType()))), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spellDamage.setDamageType(s);
            spellDamage(player, spellDamage, prevGUI);
        }))).build();
    }

    @Override
    public void spellDashboard(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nullable SpongeChestGUI prevGUI) {
        builder(27, 26, MenuText.SPELLS_DASHBOARD, player, prevGUI).setButtons(GUIButton.of(0, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.ARCANE_TRICKSTER)), GUIButton.of(1, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.BARD)), GUIButton.of(2, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.CLERIC)), GUIButton.of(3, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.DRUID)), GUIButton.of(4, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.ELDRITCH_KNIGHT)), GUIButton.of(7, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.LINGERING_POTION).name(of(MenuText.SORCERY_POINTS)).description(stringToText(MenuText.sorceryPoints(classLevels, spellbookTab))).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellbookTab.setSorceryPointsUsed(i);
            spellDashboard(player, classLevels, coreStats, experience, spellbookTab, prevGUI);
        })), GUIButton.of(8, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.PALADIN)), GUIButton.of(9, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.RANGER)), GUIButton.of(10, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.SORCERER)), GUIButton.of(11, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.WARLOCK)), GUIButton.of(12, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.WIZARD)), GUIButton.of(16, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTING_TABLE).name(of(MenuText.SPELL_SLOTS)).description(stringToText(MenuText.spellSlots(classLevels, spellbookTab))).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellbookTab.setWarlockSpellSlotsUsed(i);
            spellDashboard(player, classLevels, coreStats, experience, spellbookTab, prevGUI);
        }))).build();
    }

    @Override
    public void spellDescription(@Nonnull Player player, @Nonnull Spell spell, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.DESCRIPTION, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.DESCRIPTION)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, spell.getName(), spell.getDescription()), pages -> {
                spell.setDescription(textToString(pages));
                g.open();
            }))), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.AT_HIGHER_LEVELS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, spell.getName(), spell.getAtHigherLevels()), pages -> {
                    spell.setAtHigherLevels(textToString(pages));
                    g.open();
                })))).build();
    }

    @Override
    public void spellSave(@Nonnull Player player, @Nonnull SpellSave spellSave, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.SAVE, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SHIELD, of(MenuText.SAVING_STAT)), (g, p) -> statBonus(p, 1, spellSave::setSavingStat, statBonus -> spellSave.getSavingStat() == statBonus, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, of(MenuText.SAVE_DC)), (g, p) -> spellcasterClass(p, 1, spellSave::setSaveDCType, spellCasterClass -> spellCasterClass == spellSave.getSaveDCType(), g)), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.customDC(spellSave))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellSave.setCustomDC(i);
            spellSave(player, spellSave, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOOK, of(MenuText.ON_SUCCESSFUL_SAVE)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave()), onSuccessfulSave -> {
                spellSave.setOnSuccessfulSave(textToString(onSuccessfulSave));
                g.open();
            })))).build();
    }

    @Override
    public void spellType(@Nonnull Player player, int page, @Nonnull Spell spell, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.SCHOOL, player, prevGUI, page, spellType -> SpongeIconBuilder.builder(ItemTypes.END_CRYSTAL).name(of(spellType.getName())).addGlow(spell.getSpellType() == spellType).build(), spellType -> (g, p) -> {
            spell.setSpellType(spellType);
            spellType(p, page, spell, prevGUI);
        }, (p, i) -> spellType(p, i, spell, prevGUI), SpellType.values()).build();
    }

    @Override
    public void spellbookTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.SPELL_DASHBOARD, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, of(MenuText.SPELL_DASHBOARD)), (g, p) -> spellDashboard(p, classLevels, coreStats, experience, spellbookTab, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, of(MenuText.SPELLS)), (g, p) -> spellBook(p, bioAndInfo, classLevels, coreStats, experience, spellbookTab.getSpells(), spellcastingBonus, g))).build();
    }

    @Override
    public void spellcasterClass(@Nonnull Player player, int page, @Nonnull Consumer<SpellcasterClass> valueSetter, @Nonnull Predicate<SpellcasterClass> glowApplier, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.SAVE_DC, player, prevGUI, page, spellcasterClass -> spellcasterIcon(spellcasterClass, glowApplier), spellcasterClass -> (g, p) -> {
            valueSetter.accept(spellcasterClass);
            spellcasterClass(p, page, valueSetter, glowApplier, prevGUI);
        }, (p, i) -> spellcasterClass(p, i, valueSetter, glowApplier, prevGUI), SpellcasterClass.values());
    }

    @Nonnull
    @Override
    protected ItemStack spellcasterIcon(@Nonnull SpellcasterClass spellCasterClass, @Nonnull Predicate<SpellcasterClass> glowApplier) {
        return SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(spellCasterClass.getName())).addGlow(glowApplier.test(spellCasterClass)).build();
    }

    @Nonnull
    @Override
    protected ItemStack spellcasterIcon(@Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcasterClass spellcasterClass) {
        return SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(spellcasterClass.getName())).description(stringToText(MenuText.spellcastingTable(classLevels, coreStats, experience, spellcasterClass))).addGlow(spellbookTab.getSpellcasterClasses(classLevels).contains(spellcasterClass)).build();
    }

    @Override
    public void spellcastingBonus(@Nonnull Player player, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.SPELLCASTING_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.ATTACK_ROLLS)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellcastingBonus.setAttack(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.STRENGTH).name(of(MenuText.DAMAGE_ROLLS)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellcastingBonus.setDamage(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.LUCK).name(of(MenuText.SAVE_DC_ROLLS)).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellcastingBonus.setSaveDC(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        }))).build();
    }

    @Override
    public void spells(@Nonnull Player player, int level, @Nonnull List<Spell> spells, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.spellLevel(level), player, prevGUI, page, ItemRepresentation::spell, spell -> (g, p) -> spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g), (p, i) -> spells(player, level, spells, page, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI), spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList())).setButtons(GUIButton.of(49, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.PAPER, of(MenuText.NEW_MELEE_WEAPON)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Spell spell = new Spell();
            spell.setName(s);
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g);
        }))).build();
    }

    @Override
    public void statBonus(@Nonnull Player player, int page, Consumer<StatBonus> valueSetter, @Nonnull Predicate<StatBonus> glowApplier, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.STAT_BONUS, player, prevGUI, page, statBonus -> statBonusIcon(statBonus, glowApplier), statBonus -> (g, p) -> {
            valueSetter.accept(statBonus);
            statBonus(player, page, valueSetter, glowApplier, prevGUI);
        }, (p, i) -> statBonus(player, page, valueSetter, glowApplier, prevGUI), StatBonus.values()).build();
    }

    @Nonnull
    @Override
    protected ItemStack statBonusIcon(@Nonnull StatBonus statBonus, @Nonnull Predicate<StatBonus> glowApplier) {
        SpongeIconBuilder builder;
        switch (statBonus) {
            case CHA:
                builder = SpongeIconBuilder.builder(ItemTypes.SKULL).durability(3);
                break;
            case CON:
                builder = SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpongeIconBuilder.builder(ItemTypes.BOW);
                break;
            case INT:
                builder = SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK);
                break;
            case STR:
                builder = SpongeIconBuilder.builder(ItemTypes.IRON_SWORD);
                break;
            case WIS:
                builder = SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK);
                break;
            default:
                builder = SpongeIconBuilder.builder(ItemTypes.ENDER_CHEST);
                break;
        }

        return builder.name(of(statBonus.getName())).addGlow(glowApplier.test(statBonus)).build();
    }

    private List<Text> stringToText(List<String> list) {
        return list.stream().map(Text::of).collect(Collectors.toList());
    }

    private Text[] stringToText(String[] array) {
        return Stream.of(array).map(Text::of).toArray(Text[]::new);
    }

    private List<String> textToString(List<Text> list) {
        return list.stream().map(Text::toPlain).collect(Collectors.toList());
    }

    @Override
    public void traitsBackground(@Nonnull Player player, @Nonnull TraitsBackground traitsBackground, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.TRAITS_BACKGROUND, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.MILK_BUCKET).name(of(MenuText.CONDITION_IMMUNITY)).description(of(traitsBackground.getConditionImmunity())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setConditionImmunity(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DIAMOND_CHESTPLATE).name(of(MenuText.DAMAGE_IMMUNITY)).description(of(traitsBackground.getDamageImmunity())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageImmunity(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.SHIELD).name(of(MenuText.DAMAGE_RESISTANCE)).description(of(traitsBackground.getDamageResistance())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageResistance(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(of(MenuText.DAMAGE_VULNERABILITY)).description(of(traitsBackground.getDamageVulnerability())).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageVulnerability(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.SHIELD, of(MenuText.TRAITS)), (g, p) -> SpongeMCDNDSimple.instance().ifPresent(plugin -> new SpongeBookGUI(plugin, p, createBook(p, MenuText.TRAITS, traitsBackground.getTraits()), pages -> {
                traitsBackground.setTraits(textToString(pages));
                traitsBackground(p, traitsBackground, prevGUI);
            })))).build();
    }

    @Override
    public void unarmoredBonus(@Nonnull Player player, @Nonnull ArmorTab armorTab, int page, @Nullable SpongeChestGUI prevGUI) {
        paged(MenuText.UNARMORED_BONUS, player, prevGUI, page, unarmoredBonus -> SpongeIconBuilder.builder(ItemRepresentation.unarmoredBonus(unarmoredBonus)).build(), unarmoredBonus -> (g, p) -> {
            armorTab.setUnarmoredBonus(unarmoredBonus);
            g.close();
        }, (p, i) -> unarmoredBonus(p, armorTab, i, prevGUI), UnarmoredBonus.values());
    }

    @Override
    public void wealth(@Nonnull Player player, @Nonnull Wealth wealth, @Nullable SpongeChestGUI prevGUI) {
        Coin copper = wealth.getCopper();
        Coin silver = wealth.getSilver();
        Coin electrum = wealth.getElectrum();
        Coin gold = wealth.getGold();
        Coin platinum = wealth.getPlatinum();
        builder(9, 8, MenuText.COIN_CARRIED, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.coin(copper))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> copper.setAmount(i))), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.coin(silver))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> silver.setAmount(i))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.coin(electrum))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> electrum.setAmount(i))), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.coin(gold))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> gold.setAmount(i))), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.coin(platinum))), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> platinum.setAmount(i))), GUIButton.of(5, SpongeIconBuilder.of(ItemTypes.ROTTEN_FLESH, of(MenuText.total(wealth))))).build();
    }

    @Override
    public void weaponsSpellMiscOutputOptions(@Nonnull Player player, @Nonnull WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions, @Nonnull SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.INITIATIVE)).potionEffect(PotionEffectTypes.SPEED).addGlow(weaponsSpellMiscOutputOptions.isInitiativeEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setInitiativeEnabled(!weaponsSpellMiscOutputOptions.isInitiativeEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.POTION).name(of(MenuText.HIT_DICE)).potionEffect(PotionEffectTypes.REGENERATION).addGlow(weaponsSpellMiscOutputOptions.isHitDiceEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setHitDiceEnabled(!weaponsSpellMiscOutputOptions.isHitDiceEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(of(MenuText.MELEE_WEAPONS)).addGlow(weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.BOW).name(of(MenuText.RANGED_WEAPONS)).addGlow(weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(MenuText.SPELL_INFO)).addGlow(weaponsSpellMiscOutputOptions.isSpellInfoEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!weaponsSpellMiscOutputOptions.isSpellInfoEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickInventoryEvent.Primary.class, SpongeIconBuilder.builder(ItemTypes.ENCHANTING_TABLE).name(of(MenuText.SPELL_CAST)).addGlow(weaponsSpellMiscOutputOptions.isSpellCastEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setSpellCastEnabled(!weaponsSpellMiscOutputOptions.isSpellCastEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        })).build();
    }

    @Override
    public void weaponsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull MeleeBonus meleeBonus, @Nonnull RangedBonus rangedBonus, @Nonnull WeaponsTab weaponsTab, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.WEAPONS, player, prevGUI).setButtons(GUIButton.of(0, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, of(MenuText.MELEE_WEAPONS)), (g, p) -> meleeWeapons(p, 1, bioAndInfo, classLevels, coreStats, experience, weaponsTab.getMeleeWeapons(), meleeBonus, g)), GUIButton.of(1, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.BOW, of(MenuText.RANGED_WEAPONS)), (g, p) -> rangedWeapons(p, 1, bioAndInfo, classLevels, coreStats, experience, weaponsTab.getRangedWeapons(), rangedBonus, g))).build();
    }

    @Override
    public void weight(@Nonnull Player player, @Nonnull CoreStats coreStats, @Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth, @Nonnull Weight weight, @Nullable SpongeChestGUI prevGUI) {
        builder(9, 8, MenuText.WEIGHT, player, prevGUI).setButtons(GUIButton.of(0, SpongeIconBuilder.of(ItemTypes.CHEST, of(MenuText.inventoryWeight(items, weight)))), GUIButton.of(1, SpongeIconBuilder.of(ItemTypes.EMERALD, of(MenuText.coinWeight(wealth, weight)))), GUIButton.of(2, ClickInventoryEvent.Primary.class, SpongeIconBuilder.of(ItemTypes.STICK, of(MenuText.otherWeight(weight))), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            weight.setOther(d);
            weight(player, coreStats, items, wealth, weight, prevGUI);
        })), GUIButton.of(3, SpongeIconBuilder.of(ItemTypes.STONE, of(MenuText.totalWeight(items, wealth, weight)))), GUIButton.of(4, SpongeIconBuilder.of(ItemTypes.CHEST, of(MenuText.carryingMax(coreStats, weight)))), GUIButton.of(5, SpongeIconBuilder.of(ItemTypes.PISTON, of(MenuText.pushDragLift(coreStats, weight)))), GUIButton.of(6, SpongeIconBuilder.of(ItemTypes.STONE, of(MenuText.encumbered(coreStats, weight)))), GUIButton.of(7, SpongeIconBuilder.of(ItemTypes.OBSIDIAN, of(MenuText.heavilyEncumbered(coreStats, weight))))).build();
    }
}
