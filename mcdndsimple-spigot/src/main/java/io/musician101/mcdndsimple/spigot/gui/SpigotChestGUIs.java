package io.musician101.mcdndsimple.spigot.gui;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.Reference.Permissions;
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
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.DoubleAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerAnvilGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.gui.chest.GUIButton;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.SpigotChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.SpigotChestGUIBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.EnumHand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionType;

public class SpigotChestGUIs {

    public static final SpigotChestGUIs INSTANCE = new SpigotChestGUIs();

    private SpigotChestGUIs() {

    }

    public void armor(@Nonnull Player player, @Nonnull Armor armor, @Nonnull List<Armor> armorList, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, armor.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.CHAINMAIL_CHESTPLATE).name(MenuText.isWorn(armor)).addGlow(armor.isWorn()).build(), (g, p) -> {
            armor.setIsWorn(!armor.isWorn());
            g.open();
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.CHAINMAIL_CHESTPLATE).name(MenuText.isUnarmored(armor)).addGlow(armor.isUnarmored()).build(), (g, p) -> {
            armor.setIsUnarmored(!armor.isUnarmored());
            g.open();
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            armor.setName(s);
            armor(ply, armor, armorList, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.baseAC(armor)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            armor.setBaseArmorClass(i);
            g.open();
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_INGOT, armor.getArmorType().getName()), (g, p) -> armorType(player, armor, 1, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.magicBonus(armor)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            armor.setMagicBonus(i);
            g.open();
        })), GUIButton.of(6, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.totalAC(armor))), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.hasStealthPenalty(armor)).potionEffect(PotionType.INVISIBILITY).build(), (g, p) -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            g.open();
        }), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.hasSpeedPenalty(armor)).potionEffect(PotionType.SPEED).build(), (g, p) -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            g.open();
        }), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            armorList.remove(armor);
            g.close();
            p.sendMessage(ChatColor.GREEN + Messages.ARMOR_DELETED);
        })).build();
    }

    public void armorList(@Nonnull Player player, @Nonnull List<Armor> armorList, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.ARMOR, player, prevGUI, page, ItemRepresentation::armor, (armor) -> (g, p) -> armor(p, armor, armorList, g), (p, i) -> armorList(p, armorList, i, prevGUI), armorList).setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_CLASS_ACTION), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Armor armor = new Armor();
            armor.setName(s);
            armorList.add(armor);
            armor(ply, armor, armorList, prevGUI);
        }))).build();
    }

    public void armorTab(@Nonnull Player player, @Nonnull ArmorTab armorTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.ARMOR, player, prevGUI).setButtons(GUIButton.of(0, SpigotIconBuilder.of(Material.CHAINMAIL_CHESTPLATE, MenuText.armoredAC(armorTab))), GUIButton.of(1, SpigotIconBuilder.of(Material.LEATHER_CHESTPLATE, MenuText.unarmoredAC(armorTab))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), (g, p) -> armorList(p, armorTab.getArmorList(), 1, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_CHESTPLATE).name(MenuText.UNARMORED_BONUS).description(MenuText.current(armorTab)).build(), (g, p) -> unarmoredBonus(p, armorTab, 1, g))).build();
    }

    public void armorType(@Nonnull Player player, @Nonnull Armor armor, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.ARMOR_TYPE, player, prevGUI, page, armorType -> SpigotIconBuilder.builder(ItemRepresentation.armorType(armorType)).addGlow(armorType == armor.getArmorType()).build(), armorType -> (g, p) -> {
            armor.setArmorType(armorType);
            g.close();
        }, (p, i) -> armorType(player, armor, i, prevGUI), ArmorType.values()).build();
    }

    public void attackStat(@Nonnull Player player, int page, @Nonnull Weapon weapon, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.ATTACK_STAT, player, prevGUI, page, attackStat -> SpigotIconBuilder.builder(ItemRepresentation.weaponAttackStat(attackStat)).addGlow(attackStat == weapon.getAttackStat()).build(), attackStat -> (g, p) -> {
            weapon.setAttackStat(attackStat);
            attackStat(p, page, weapon, prevGUI);
        }, (p, i) -> attackStat(p, i, weapon, prevGUI), WeaponAttackStat.values());
    }

    public void backgroundTab(@Nonnull Player player, @Nonnull BackgroundTab backgroundTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(27, 26, MenuText.BACKGROUND, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.ARROW, MenuText.gender(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setGender(s);
            g.open();
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.RAW_FISH, MenuText.age(backgroundTab)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            backgroundTab.setAge(i);
            g.open();
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ARMOR_STAND, MenuText.height(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setHeight(s);
            g.open();
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_BLOCK, MenuText.weight(backgroundTab)), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            backgroundTab.setWeight(d);
            g.open();
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.SPIDER_EYE, MenuText.eyes(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setEyes(s);
            g.open();
        })), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.RABBIT_HIDE, MenuText.hair(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setHair(s);
            g.open();
        })), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.LEATHER, MenuText.size(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setSize(s);
            g.open();
        })), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.vision(backgroundTab)).potionEffect(PotionType.NIGHT_VISION).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setVision(s);
            g.open();
        })), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.KNOWN_LANGUAGES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.KNOWN_LANGUAGES);
            bookMeta.setPages(backgroundTab.getLanguages());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setLanguages(pages);
                g.open();
            });
        }), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.TOTEM, MenuText.alignment(backgroundTab)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            backgroundTab.setAlignment(s);
            g.open();
        })), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.BACKGROUND), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BACKGROUND);
            bookMeta.setPages(backgroundTab.getBackground());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setBackground(pages);
                g.open();
            });
        }), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.RACIAL_TRAITS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.RACIAL_TRAITS);
            bookMeta.setPages(backgroundTab.getRacialTraits());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setRacialTraits(pages);
                g.open();
            });
        }), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.PERSONALITY_TRAITS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.PERSONALITY_TRAITS);
            bookMeta.setPages(backgroundTab.getPersonalityTraits());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setPersonalityTraits(pages);
                g.open();
            });
        }), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.IDEALS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.IDEALS);
            bookMeta.setPages(backgroundTab.getIdeals());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setIdeals(pages);
                g.open();
            });
        }), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.BONDS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BONDS);
            bookMeta.setPages(backgroundTab.getBonds());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setBonds(pages);
                g.open();
            });
        }), GUIButton.of(15, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.FLAWS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.FLAWS);
            bookMeta.setPages(backgroundTab.getFlaws());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setFlaws(pages);
                g.open();
            });
        }), GUIButton.of(16, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.ARMOR_PROFICIENCIES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BONDS);
            bookMeta.setPages(backgroundTab.getArmorProficiencies());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setArmorProficiencies(pages);
                g.open();
            });
        }), GUIButton.of(17, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.WEAPON_PROFICIENCIES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BONDS);
            bookMeta.setPages(backgroundTab.getWeaponProficiencies());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setWeaponProficiencies(pages);
                g.open();
            });
        }), GUIButton.of(18, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.TOOL_PROFICIENCIES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BONDS);
            bookMeta.setPages(backgroundTab.getToolProficiencies());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setToolProficiencies(pages);
                g.open();
            });
        }), GUIButton.of(19, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.OTHER_NOTES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.OTHER_NOTES);
            bookMeta.setPages(backgroundTab.getOtherNotes());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                backgroundTab.setOtherNotes(pages);
                g.open();
            });
        })).build();
    }

    public void bioAndInfo(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull PlayerSheet playerSheet, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.BIO_AND_INFO, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.name(bioAndInfo)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setName(s);
            g.open();
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.BIO), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.BIO);
            bookMeta.setPages(bioAndInfo.getBio());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                bioAndInfo.setBio(pages);
                g.open();
            });
        })).build();
    }

    public void bonuses(@Nonnull Player player, @Nonnull Bonuses bonuses, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.BONUSES_PENALTIES, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.MELEE_BONUSES), (g, p) -> meleeBonus(p, bonuses.getMelee(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.RANGED_BONUSES), (g, p) -> rangedBonus(p, bonuses.getRanged(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SPELLCASTING_BONUSES), (g, p) -> spellcastingBonus(p, bonuses.getSpellcasting(), g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.SAVING_THROW_BONUSES).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            bonuses.setSaves(dice);
            bonuses(player, bonuses, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.LUCK).name(MenuText.ABILITY_SKILL_CHECK_ROLLS).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            bonuses.setAbilitiesAndSkills(dice);
            bonuses(player, bonuses, prevGUI);
        }))).build();
    }

    @Nonnull
    private SpigotChestGUIBuilder<SpigotMCDNDSimple> builder(int size, int backButtonSlot, @Nonnull String name, @Nonnull Player player, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        return SpigotChestGUI.<SpigotMCDNDSimple>builder().setSize(size).setName(name).setPlayer(player).setPreviousGUI(prevGUI).setBackButton(backButtonSlot, ClickType.LEFT);
    }

    public void characterSheet(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull CharacterSheet characterSheet, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        ClassTab classTab = characterSheet.getClassTab();
        CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
        Bonuses bonuses = coreStatsTab.getBonuses();
        builder(9, 8, MenuText.CHARACTER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), (g, p) -> armorTab(p, characterSheet.getArmorTab(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.BACKGROUND), (g, p) -> backgroundTab(p, characterSheet.getBackgroundTab(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.CLASS), (g, p) -> classTab(p, characterSheet.getClassTab(), g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND, MenuText.CORE_STATS), (g, p) -> coreStatsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.CHEST, MenuText.INVENTORY), (g, p) -> inventoryTab(p, coreStatsTab.getCoreStats(), characterSheet.getInventoryTab(), g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SKILLS), (g, p) -> skillsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getBonuses().getAbilitiesAndSkills(), coreStatsTab.getExperience(), characterSheet.getSkillsTab(), g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.SPELLBOOK), (g, p) -> spellbookTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), characterSheet.getSpellbookTab(), coreStatsTab.getBonuses().getSpellcasting(), g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.INVENTORY), (g, p) -> weaponsTab(p, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), bonuses.getMelee(), bonuses.getRanged(), characterSheet.getWeaponsTab(), g))).build();
    }

    public void classAction(@Nonnull Player player, @Nonnull ClassAction classAction, @Nonnull List<ClassAction> classActions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, classAction.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            classAction.setName(s);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_TORCH_OFF, MenuText.used(classAction.getUsed())), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classAction.setUsed(i);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_TORCH_ON, MenuText.maxUses(classAction.getMaxUses())), (g, p) -> recharge(p, classAction, 1, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BED, MenuText.recharge(classAction.getRecharge())), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classAction.setUsed(i);
            classAction(ply, classAction, classActions, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.GAINED_FROM), (g, p) -> gainedFrom(p, 1, classAction, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE, MenuText.OUTPUT_OPTIONS), (g, p) -> outputOptions(p, classAction.getOutputOptions(), g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            classActions.remove(classAction);
            g.close();
        })).build();
    }

    public void classActions(@Nonnull Player player, @Nonnull List<ClassAction> classActions, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.CLASS_ACTIONS, player, prevGUI, page, classAction -> SpigotIconBuilder.of(Material.NETHER_STAR, classAction.getName()), classAction -> (g, p) -> classAction(p, classAction, classActions, g), (p, i) -> classActions(p, classActions, page, prevGUI), classActions).setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_CLASS_ACTION), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            ClassAction classAction = new ClassAction();
            classAction.setName(s);
            classActions.add(classAction);
            classAction(ply, classAction, classActions, prevGUI);
        }))).build();
    }

    public void classLevels(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, MenuText.CLASS_LEVELS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.WOOD_SWORD, MenuText.BARBARIAN), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setBarbarian(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.BARD), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setBard(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CLERIC).potionEffect(PotionType.INSTANT_HEAL).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setCleric(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.DRUID).potionEffect(PotionType.LUCK).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setDruid(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.FIGHTER), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setFighter(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.MONK).potionEffect(PotionType.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setMonk(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.PALADIN), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setPaladin(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.RANGER), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setRanger(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_SWORD, MenuText.ROGUE), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setRogue(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.MAGMA_CREAM, MenuText.SORCERER), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setSorcerer(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.of(Material.END_CRYSTAL, MenuText.WARLOCK), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setWarlock(i);
            classLevels(ply, classLevels, prevGUI);
        })), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.TOTEM, MenuText.WIZARD), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classLevels.setWizard(i);
            classLevels(ply, classLevels, prevGUI);
        }))).build();
    }

    public void classResource(@Nonnull Player player, @Nonnull ClassResource classResource, @Nonnull List<ClassResource> classResources, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, classResource.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.NAME_TAG, classResource.getName()), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            classResource.setName(s);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BED, MenuText.recharge(classResource.getRecharge())), (g, p) -> recharge(p, classResource, 1, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_COMPARATOR_OFF, MenuText.current(classResource)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classResource.setCurrentCharges(i);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_COMPARATOR_ON, MenuText.maxUses(classResource.getMaxUses())), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            classResource.setMaxCharges(i);
            classResource(ply, classResource, classResources, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            classResources.remove(classResource);
            g.close();
            p.sendMessage(ChatColor.GREEN + Messages.CLASS_RESOURCE_DELETED);
        })).build();
    }

    public void classResources(@Nonnull Player player, @Nonnull List<ClassResource> classResources, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.CLASS_RESOURCES, player, prevGUI, page, classResource -> SpigotIconBuilder.of(Material.NETHER_STAR, classResource.getName()), classResource -> (g, p) -> classResource(p, classResource, classResources, g), (p, i) -> classResources(p, classResources, i, prevGUI), classResources).setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_CLASS_ACTION), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            ClassResource classResource = new ClassResource();
            classResource.setName(s);
            classResources.add(classResource);
            classResource(ply, classResource, classResources, prevGUI);
        }))).build();
    }

    public void classTab(@Nonnull Player player, @Nonnull ClassTab classTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.CLASS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.CLASS_LEVELS), (g, p) -> classLevels(p, classTab.getClassLevels(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND, MenuText.CLASS_RESOURCES), (g, p) -> classResources(p, classTab.getClassResources(), 1, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.CLASS_FEATURE_NOTES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.CLASS_FEATURE_NOTES);
            bookMeta.setPages(classTab.getClassFeatureNotes());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                classTab.setClassFeatureNotes(pages);
                classTab(p, classTab, prevGUI);
            });
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.OTHER_NOTES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.OTHER_NOTES);
            bookMeta.setPages(classTab.getOtherNotes());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                classTab.setOtherNotes(pages);
                classTab(p, classTab, prevGUI);
            });
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_TORCH_ON, MenuText.CLASS_ACTIONS), (g, p) -> classActions(p, classTab.getClassActions(), 1, g))).build();
    }

    public void coreSkillsOutputOptions(@Nonnull Player player, @Nonnull CoreSkillsOutputOptions coreSkillsOutputOptions, @Nonnull SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(27, 26, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.LEATHER_BOOTS).name(MenuText.ACROBATICS).addGlow(coreSkillsOutputOptions.isAcrobaticsEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAcrobaticsEnabled(!coreSkillsOutputOptions.isAcrobaticsEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.SADDLE).name(MenuText.ANIMAL_HANDLING).addGlow(coreSkillsOutputOptions.isAnimalHandlingEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAnimalHandlingEnabled(!coreSkillsOutputOptions.isAnimalHandlingEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.ARCANA).addGlow(coreSkillsOutputOptions.isArcanaEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setArcanaEnabled(!coreSkillsOutputOptions.isArcanaEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.ATHLETICS).potionEffect(PotionType.SPEED).addGlow(coreSkillsOutputOptions.isAthleticsEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setAthleticsEnabled(!coreSkillsOutputOptions.isAthleticsEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.EMERALD).name(MenuText.DECEPTION).addGlow(coreSkillsOutputOptions.isDeceptionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setDeceptionEnabled(!coreSkillsOutputOptions.isDeceptionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.HISTORY).addGlow(coreSkillsOutputOptions.isHistoryEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setHistoryEnabled(!coreSkillsOutputOptions.isHistoryEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.ICE).name(MenuText.INSIGHT).addGlow(coreSkillsOutputOptions.isInsightEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setInsightEnabled(!coreSkillsOutputOptions.isInsightEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(MenuText.INTIMIDATION).addGlow(coreSkillsOutputOptions.isIntimidationEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setIntimidationEnabled(!coreSkillsOutputOptions.isIntimidationEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTMENT_TABLE).name(MenuText.INVESTIGATION).addGlow(coreSkillsOutputOptions.isInvestigationEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setInvestigationEnabled(!coreSkillsOutputOptions.isInvestigationEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.MEDICINE).potionEffect(PotionType.INSTANT_HEAL).addGlow(coreSkillsOutputOptions.isMedicineEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setMedicineEnabled(!coreSkillsOutputOptions.isMedicineEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.builder(Material.VINE).name(MenuText.NATURE).addGlow(coreSkillsOutputOptions.isNatureEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setNatureEnabled(!coreSkillsOutputOptions.isNatureEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.builder(Material.CARROT_ITEM).name(MenuText.PERCEPTION).addGlow(coreSkillsOutputOptions.isPerceptionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPerceptionEnabled(!coreSkillsOutputOptions.isPerceptionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.builder(Material.NOTE_BLOCK).name(MenuText.PERFORMANCE).addGlow(coreSkillsOutputOptions.isPerformanceEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPerformanceEnabled(!coreSkillsOutputOptions.isPerformanceEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.builder(Material.EYE_OF_ENDER).name(MenuText.PERSUASION).addGlow(coreSkillsOutputOptions.isPersuasionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setPersuasionEnabled(!coreSkillsOutputOptions.isPersuasionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.builder(Material.NETHER_STAR).name(MenuText.RELIGION).addGlow(coreSkillsOutputOptions.isReligionEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(15, ClickType.LEFT, SpigotIconBuilder.builder(Material.TRIPWIRE_HOOK).name(MenuText.SLEIGHT_OF_HAND).addGlow(coreSkillsOutputOptions.isSleightOfHandEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(16, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.STEALTH).potionEffect(PotionType.NIGHT_VISION).addGlow(coreSkillsOutputOptions.isStealthEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        }), GUIButton.of(17, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.SURVIVAL).addGlow(coreSkillsOutputOptions.isSurvivalEnabled()).build(), (g, p) -> {
            coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled());
            coreSkillsOutputOptions(p, coreSkillsOutputOptions, prevGUI);
        })).build();
    }

    public void coreStatsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStatsTab coreStatsTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        CoreStats<PlayerAbilityScore> coreStats = coreStatsTab.getCoreStats();
        PlayerAbilityScore dex = coreStats.getDexterity();
        Experience experience = coreStatsTab.getExperience();
        HitDice hitDice = coreStatsTab.getHitDice();
        HitPoints hitPoints = coreStatsTab.getHitPoints();
        Initiative initiative = coreStatsTab.getInitiative();
        builder(18, 17, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CORE_STATS).potionEffect(PotionType.STRENGTH).build(), (g, p) -> playerCoreStats(p, bioAndInfo, classLevels, coreStats, experience, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(MenuText.HIT_POINTS).description(MenuText.hitPoints(hitPoints)).build(), (g, p) -> hitPoints(p, hitPoints, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.speed(coreStatsTab)).potionEffect(PotionType.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            coreStatsTab.setSpeed(i);
            coreStatsTab(player, bioAndInfo, classLevels, coreStatsTab, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.initiative(initiative, dex)).potionEffect(PotionType.NIGHT_VISION).build(), (g, p) -> initiative(p, dex, bioAndInfo, initiative, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.EXP_BOTTLE).name(MenuText.LEVEL_AND_XP).description(MenuText.experience(experience, classLevels)).build(), (g, p) -> experience(p, classLevels, experience, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.HIT_DICE).potionEffect(PotionType.REGEN).build(), (g, p) -> hitDice(p, coreStats.getConstitution(), bioAndInfo, hitDice, g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.BONUSES_PENALTIES), (g, p) -> bonuses(p, coreStatsTab.getBonuses(), g)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_NUGGET, MenuText.INSPIRATION), (g, p) -> {
            coreStatsTab.setHasInspiration(!coreStatsTab.hasInspiration());
            coreStatsTab(p, bioAndInfo, classLevels, coreStatsTab, prevGUI);
        }), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.DEATH_SAVING_THROWS), (g, p) -> deathSavingThrows(p, bioAndInfo, coreStatsTab.getDeathSavingThrows(), coreStatsTab.getBonuses().getSaves(), hitPoints, g))).build();
    }

    public void deathSavingThrows(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull DeathSavingThrows deathSavingThrows, @Nonnull Dice savingThrowBonus, @Nonnull HitPoints hitPoints, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.DEATH_SAVING_THROWS, player, prevGUI).setButtons(GUIButton.<ClickType, SpigotChestGUI<SpigotMCDNDSimple>, Inventory, SpigotMCDNDSimple, Player, ItemStack>builder().slot(0).icon(SpigotIconBuilder.builder(Material.SKULL_ITEM).durability(3).name(MenuText.successCount(deathSavingThrows.getSuccessCount())).build()).addButton(ClickType.LEFT, (g, p) -> {
            deathSavingThrows.addSuccessCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).addButton(ClickType.RIGHT, (g, p) -> {
            deathSavingThrows.removeSuccessCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).build(), GUIButton.<ClickType, SpigotChestGUI<SpigotMCDNDSimple>, Inventory, SpigotMCDNDSimple, Player, ItemStack>builder().slot(1).icon(SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.failCount(deathSavingThrows.getFailCount()))).addButton(ClickType.LEFT, (g, p) -> {
            deathSavingThrows.addFailCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).addButton(ClickType.RIGHT, (g, p) -> {
            deathSavingThrows.removeFailCount();
            deathSavingThrows(p, bioAndInfo, deathSavingThrows, savingThrowBonus, hitPoints, prevGUI);
        }).build(), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.failCount(deathSavingThrows.getFailCount())), (g, p) -> {
            int result = Dice.total(new Dice(20).roll());
            int total = result + Dice.total(savingThrowBonus.roll());
            String newLine = "\n";
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a Death Saving Throw." + newLine + "The results are ");
            if (result == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (result == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(total).append(ChatColor.RESET).append(".").append(newLine);
            if (total > 10) {
                if (result == 1) {
                    deathSavingThrows.addFailCount();
                }

                deathSavingThrows.addFailCount();
                sb.append(bioAndInfo.getName()).append(" now has ").append(ChatColor.RED).append(deathSavingThrows.getFailCount()).append(ChatColor.RESET).append(" failed Death Saving Throws!");
            }
            else {
                deathSavingThrows.addSuccessCount();
                sb.append(bioAndInfo.getName()).append(" now has ").append(ChatColor.GREEN).append(deathSavingThrows.getSuccessCount()).append(ChatColor.RESET).append(" successful Death Saving Throws!");
                if (result == 20) {
                    sb.append(ChatColor.GOLD).append("They have also gained 1HP.");
                    hitPoints.setCurrent(1);
                }
            }

            Bukkit.broadcastMessage(sb.toString());
        })).build();
    }

    public void editControllers(@Nonnull Player player, int page, @Nonnull AbstractPlayer abstractPlayer, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.EDIT_CONTROLLERS, player, prevGUI, page, uuid -> {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            ItemStack itemStack = SpigotIconBuilder.builder(Material.SKULL_ITEM).name(offlinePlayer.getName()).durability(3).addGlow(abstractPlayer.isController(uuid)).build();
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwningPlayer(offlinePlayer);
            return itemStack;
        }, uuid -> (g, p) -> {
            if (abstractPlayer.isController(uuid)) {
                abstractPlayer.removeController(uuid);
            }
            else {
                abstractPlayer.addController(uuid);
            }
        }, (p, i) -> editControllers(p, i, abstractPlayer, prevGUI), abstractPlayer.getControllers()).build();
    }

    public void experience(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.LEVEL_AND_XP, player, prevGUI).setButtons(GUIButton.of(0, SpigotIconBuilder.of(Material.BOOK, MenuText.overallLevel(experience, classLevels))), GUIButton.of(1, SpigotIconBuilder.of(Material.ANVIL, MenuText.proficiencyBonus(experience, classLevels))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.EXP_BOTTLE, MenuText.currentXP(experience)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            experience.setExp(i);
            experience(ply, classLevels, experience, prevGUI);
        })), GUIButton.of(3, SpigotIconBuilder.of(Material.EXP_BOTTLE, MenuText.xpForNextLevel(experience, classLevels)))).build();
    }

    public void gainedFrom(@Nonnull Player player, int page, @Nonnull ClassAction classAction, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.GAINED_FROM, player, prevGUI, page, gainedFrom -> SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(gainedFrom.getName()).addGlow(classAction.getGainedFrom() == gainedFrom).build(), gainedFrom -> (g, p) -> {
            classAction.setGainedFrom(gainedFrom);
            g.close();
        }, (p, i) -> gainedFrom(p, i, classAction, prevGUI), GainedFrom.values()).build();
    }

    public void healing(@Nonnull Player player, @Nonnull SpellHealing spellHealing, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.HEALING, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.healAmount(spellHealing.getHealAmount())), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellHealing.setHealAmount(dice);
            healing(player, spellHealing, g);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE, MenuText.STAT_BONUS), (g, p) -> statBonus(p, 1, spellHealing::setStatBonus, statBonus -> spellHealing.getStatBonus() == statBonus, g))).build();
    }

    public void hitDice(@Nonnull Player player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, @Nonnull HitDice hitDice, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        Material redstoneLampOff = Material.REDSTONE_LAMP_OFF;
        Material redstoneLampOn = Material.REDSTONE_LAMP_ON;
        String[] hitDiceStrings = MenuText.hitDice(hitDice);
        builder(18, 17, MenuText.HIT_DICE, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOff, hitDiceStrings[0]), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(6, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOff, hitDiceStrings[1]), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(8, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOff, hitDiceStrings[2]), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(10, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOff, hitDiceStrings[3]), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitDice.updateHitDie(12, i);
            hitDice(player, constitution, bioAndInfo, hitDice, prevGUI);
        })), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOn, MenuText.rollHitDie(6)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 6))), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOn, MenuText.rollHitDie(8)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 8))), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOn, MenuText.rollHitDie(10)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 10))), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(redstoneLampOn, MenuText.rollHitDie(12)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> rollHitDie(player, constitution, bioAndInfo, i, 12)))).build();
    }

    public void hitPoints(@Nonnull Player player, @Nonnull HitPoints hitPoints, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.HIT_POINTS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.currentHitPoints(hitPoints)).potionEffect(PotionType.REGEN).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setCurrent(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.maxHitPoints(hitPoints)).potionEffect(PotionType.INSTANT_HEAL).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setMax(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.tempHitPoints(hitPoints)).potionEffect(PotionType.STRENGTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setTemp(i);
            hitPoints(ply, hitPoints, prevGUI);
        }))).build();
    }

    public void initiative(@Nonnull Player player, @Nonnull AbilityScore dex, @Nonnull BioAndInfo bioAndInfo, @Nonnull Initiative initiative, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.INITIATIVE, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.BONUS), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            initiative.setBonus(i);
            initiative(ply, dex, bioAndInfo, initiative, prevGUI);
        })), GUIButton.of(1, SpigotIconBuilder.of(Material.GLOWSTONE, MenuText.total(initiative, dex))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.ROLL_INITIATIVE), (g, p) -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int mod = dex.getMod() + initiative.getBonus();
            int roll = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled for initiative.\nThe result are: ");
            if (roll == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (roll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(roll + mod);
            Bukkit.broadcastMessage(sb.toString());
        })).build();
    }

    public void inventory(@Nonnull Player player, @Nonnull List<MCDNDItem> items, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.INVENTORY, player, prevGUI, page, item -> SpigotIconBuilder.builder(Material.CHEST).name(item.getName()).description(MenuText.itemDesc(item)).build(), item -> (g, p) -> item(p, item, items, g), (p, i) -> inventory(p, items, i, prevGUI), items).setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_ITEM), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            MCDNDItem item = new MCDNDItem();
            item.setName(s);
            items.add(item);
            item(ply, item, items, prevGUI);
        }))).build();
    }

    public void inventoryTab(@Nonnull Player player, @Nonnull CoreStats coreStats, @Nonnull InventoryTab inventoryTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        Wealth wealth = inventoryTab.getWealth();
        builder(9, 8, MenuText.INVENTORY, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.EMERALD).name(MenuText.COIN_CARRIED).description(MenuText.coinCarriedDescription(wealth)).build(), (g, p) -> wealth(p, inventoryTab.getWealth(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.STONE, MenuText.WEIGHT), (g, p) -> weight(p, coreStats, inventoryTab.getInventory(), inventoryTab.getWealth(), inventoryTab.getWeight(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.CHEST, MenuText.INVENTORY), (g, p) -> inventory(p, inventoryTab.getInventory(), 1, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.INVENTORY_NOTES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.INVENTORY_NOTES);
            bookMeta.setPages(inventoryTab.getInventoryNotes());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                inventoryTab.setInventoryNotes(pages);
                inventoryTab(p, coreStats, inventoryTab, prevGUI);
            });
        })).build();
    }

    public void item(@Nonnull Player player, @Nonnull MCDNDItem item, @Nonnull List<MCDNDItem> items, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, item.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            item.setName(s);
            item(player, item, items, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(item.isCarried() ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_ON, MenuText.carried(item)), (g, p) -> {
            item.setIsCarried(!item.isCarried());
            item(p, item, items, prevGUI);
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.CHEST, MenuText.quantity(item)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            item.setQuantity(i);
            item(player, item, items, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.OBSIDIAN, MenuText.weight(item)), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            item.setWeight(d);
            item(player, item, items, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(item.getName());
            bookMeta.setPages(item.getDescription());
            bookMeta.setAuthor(p.getName());
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                item.setDescription(pages);
                item(p, item, items, prevGUI);
            });
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            items.remove(item);
            g.close();
            p.sendMessage(ChatColor.GREEN + Messages.ITEM_DELETED);
        })).build();
    }

    public void macroOptions(@Nonnull Player player, @Nonnull MacroOptions macroOptions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.INFO_BLOCK).addGlow(macroOptions.isInfoBlockEnabled()).build(), (g, p) -> {
            macroOptions.setInfoBlockEnabled(!macroOptions.isInfoBlockEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.DESCRIPTION).addGlow(macroOptions.isDescriptionEnabled()).build(), (g, p) -> {
            macroOptions.setDescriptionEnabled(!macroOptions.isDescriptionEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.AT_HIGHER_LEVELS).addGlow(macroOptions.isAtHigherLevelsEnabled()).build(), (g, p) -> {
            macroOptions.setAtHigherLevelsEnabled(!macroOptions.isAtHigherLevelsEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.ATTACK).addGlow(macroOptions.isAttackRollEnabled()).build(), (g, p) -> {
            macroOptions.setAttackRollEnabled(!macroOptions.isAttackRollEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.SECOND_ATTACK).addGlow(macroOptions.isSecondAttackRollEnabled()).build(), (g, p) -> {
            macroOptions.setSecondAttackRollEnabled(!macroOptions.isSecondAttackRollEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.SAVING_THROW).addGlow(macroOptions.isSavingThrowEnabled()).build(), (g, p) -> {
            macroOptions.setSavingThrowEnabled(!macroOptions.isSavingThrowEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.HEALING).addGlow(macroOptions.isHealingEnabled()).build(), (g, p) -> {
            macroOptions.setHealingEnabled(!macroOptions.isHealingEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.DAMAGE).addGlow(macroOptions.isDamageEnabled()).build(), (g, p) -> {
            macroOptions.setDamageEnabled(!macroOptions.isDamageEnabled());
            macroOptions(p, macroOptions, g);
        }), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.builder(Material.REDSTONE_TORCH_ON).name(MenuText.EFFECTS).addGlow(macroOptions.isEffectsEnabled()).build(), (g, p) -> {
            macroOptions.setEffectsEnabled(!macroOptions.isEffectsEnabled());
            macroOptions(p, macroOptions, g);
        })).build();
    }

    public void meleeBonus(@Nonnull Player player, @Nonnull MeleeBonus meleeBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.MELEE_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            meleeBonus.setAttack(dice);
            meleeBonus(player, meleeBonus, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.DAMAGE_ROLLS).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            meleeBonus.setDamage(dice);
            meleeBonus(player, meleeBonus, prevGUI);
        }))).build();
    }

    public void meleeWeapon(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<MeleeWeapon> weapons, MeleeBonus meleeBonus, MeleeWeapon weapon, SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, weapon.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(weapon.isProficient() ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_OFF).name(MenuText.isProficient(weapon)).addGlow(weapon.isProficient()).build(), (g, p) -> {
            weapon.setIsProficient(!weapon.isProficient());
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setName(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.ATTACK_STAT), (g, p) -> attackStat(p, 1, weapon, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.magicBonus(weapon)), (g, p) -> new IntegerAnvilGUI(player, (ply, i) -> {
            weapon.setMagicBonus(i);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(4, SpigotIconBuilder.of(Material.STONE_SWORD, MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)))), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_SWORD, MenuText.damageDice(weapon.getDamage())), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setDamage(dice);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.GLOWSTONE).name(MenuText.PLUS_STAT).addGlow(weapon.plusStat()).build(), (g, p) -> {
            weapon.setPlusStat(!weapon.plusStat());
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        }), GUIButton.of(7, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.damageBonus(weapon, coreStats))), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND, MenuText.damageType(weapon.getDamageType())), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setDamageType(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.critDamage(weapon)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setCritDamage(dice);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.SULPHUR, MenuText.critOn(weapon)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            weapon.setCritMin(i);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, meleeBonus, weapon, prevGUI);
        })), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_ON, MenuText.ROLL_ATTACK), (g, p) -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(meleeBonus.getAttack().roll());
            String newLine = "\n";
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") meleed with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (firstAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(firstAttackRoll + bonus).append(" | ");
            if (secondAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (secondAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(secondAttackRoll + bonus).append(" vs AC").append(newLine).append("Damage: ").append(Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(meleeBonus.getDamage().roll()))).append(" ").append(weapon.getDamageType());
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                sb.append("Crit: ").append(Dice.total(critDice.roll())).append(newLine).append("Crit (adv roll): ").append(critDice.roll());
            }

            Bukkit.broadcastMessage(sb.toString());
            p.closeInventory();
        }), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            weapons.remove(weapon);
            g.close();
        })).build();
    }

    public void meleeWeapons(@Nonnull Player player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<MeleeWeapon> meleeWeapons, @Nonnull MeleeBonus meleeBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.MELEE_WEAPONS, player, prevGUI, page, meleeWeapon -> ItemRepresentation.meleeWeapon(meleeWeapon, classLevels, coreStats, experience), meleeWeapon -> (g, p) -> meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, meleeWeapon, g), (p, i) -> meleeWeapons(p, i, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, prevGUI), meleeWeapons).setButtons(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_MELEE_WEAPON), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            MeleeWeapon meleeWeapon = new MeleeWeapon();
            meleeWeapon.setName(s);
            meleeWeapon(p, bioAndInfo, classLevels, coreStats, experience, meleeWeapons, meleeBonus, meleeWeapon, g);
        }))).build();
    }

    public void nonPlayer(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        SpigotChestGUIBuilder<SpigotMCDNDSimple> builder = builder(9, 8, MenuText.name(nonPlayer), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setName(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.CLASS), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setClazz(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.MONSTER_EGG, MenuText.RACE), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayer.setClazz(s);
            nonPlayer(ply, nonPlayer, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_TORCH_ON, MenuText.ACTIONS), (g, p) -> nonPlayerActions(p, nonPlayer.getNonPlayerActions(), g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.CHARACTER_SHEET), (g, p) -> nonPlayerSheet(p, nonPlayer, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SKILLS), (g, p) -> nonPlayerSkills(p, nonPlayer.getNonPlayerSheet().getCoreStats(), nonPlayer, nonPlayer.getSkills(), g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.TRAITS_BACKGROUND), (g, p) -> traitsBackground(p, nonPlayer.getTraitsBackground(), g)));
        if (player.hasPermission(Permissions.DM)) {
            builder.setButtons(GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.EDIT_CONTROLLERS).durability(3).build(), (g, p) -> editControllers(p, 1, nonPlayer, g)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> SpigotMCDNDSimple.instance().getNonPlayerStorage().remove(nonPlayer)));
        }

        builder.build();
    }

    public void nonPlayerAbilityScore(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, abilityScore.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.score(abilityScore)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setScore(i);
            nonPlayerAbilityScore(ply, nonPlayer, abilityScore, prevGUI);
        })), GUIButton.of(1, SpigotIconBuilder.of(Material.SULPHUR, MenuText.mod(abilityScore))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.bonus(abilityScore)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setBonus(i);
            nonPlayerAbilityScore(ply, nonPlayer, abilityScore, prevGUI);
        })), GUIButton.of(3, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.saveTotal(abilityScore))), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.ROLL_SAVE), (g, p) -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveTotal();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(nonPlayer.getName() + " has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (first == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(first + saveMod).append(ChatColor.RESET).append(" | ");
            if (second == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (second == 1) {
                sb.append(ChatColor.RED);
            }

            Bukkit.broadcastMessage(sb.append(second + saveMod).toString());
        })).build();
    }

    public void nonPlayerAction(@Nonnull Player player, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, nonPlayerAction.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerAction.setName(s);
            nonPlayerAction(ply, nonPlayerAction, nonPlayerActions, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.actionType(nonPlayerAction.getActionType())), (g, p) -> nonPlayerActionType(p, 1, nonPlayerAction, nonPlayerActions, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(nonPlayerAction.getDescription());
            bookMeta.setTitle(nonPlayerAction.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                nonPlayerAction.setDescription(pages);
                g.open();
            });
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.EFFECT), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(nonPlayerAction.getEffect());
            bookMeta.setTitle(nonPlayerAction.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                nonPlayerAction.setEffect(pages);
                g.open();
            });
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.GOLD_SWORD).name(MenuText.EFFECT).addGlow(nonPlayerAction.isMultiAttack()).build(), (g, p) -> {
            nonPlayerAction.setIsMultiAttack(!nonPlayerAction.isMultiAttack());
            nonPlayerAction(p, nonPlayerAction, nonPlayerActions, prevGUI);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            nonPlayerActions.remove(nonPlayerAction);
            g.close();
        })).build();
    }

    public void nonPlayerActionType(@Nonnull Player player, int page, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.ACTION_TYPE, player, prevGUI, page, actionType -> SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(actionType.getName()).addGlow(nonPlayerAction.getActionType() == actionType).build(), actionType -> (g, p) -> {
            nonPlayerAction.setActionType(actionType);
            nonPlayerAction(p, nonPlayerAction, nonPlayerActions, g.getPreviousGUI());
        }, (p, i) -> nonPlayerActionType(p, i, nonPlayerAction, nonPlayerActions, prevGUI), NonPlayerActionType.values()).build();
    }

    public void nonPlayerActions(@Nonnull Player player, @Nonnull NonPlayerActions nonPlayerActions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.ACTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_SWORD).name(MenuText.MULTI_ATTACK).description(nonPlayerActions.getMultiAttack()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerActions.setMultiAttack(s);
            nonPlayerActions(ply, nonPlayerActions, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_SWORD, MenuText.ACTIONS), (g, p) -> nonPlayerActions(p, 1, nonPlayerActions.getActions(), g))).build();
    }

    public void nonPlayerActions(@Nonnull Player player, int page, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.ACTIONS, player, prevGUI, page, nonPlayerAction -> SpigotIconBuilder.of(Material.NETHER_STAR, nonPlayerAction.getName()), nonPlayerAction -> (g, p) -> nonPlayerAction(p, nonPlayerAction, nonPlayerActions, g), (p, i) -> nonPlayerActions(p, i, nonPlayerActions, prevGUI), nonPlayerActions).setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.MULTI_ATTACK), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            NonPlayerAction nonPlayerAction = new NonPlayerAction();
            nonPlayerAction.setName(s);
            nonPlayerActions.add(nonPlayerAction);
            nonPlayerAction(ply, nonPlayerAction, nonPlayerActions, g);
        }))).build();
    }

    public void nonPlayerCoreStats(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        NonPlayerAbilityScore str = coreStats.getStrength();
        NonPlayerAbilityScore dex = coreStats.getDexterity();
        NonPlayerAbilityScore con = coreStats.getConstitution();
        NonPlayerAbilityScore intel = coreStats.getIntelligence();
        NonPlayerAbilityScore wis = coreStats.getWisdom();
        NonPlayerAbilityScore cha = coreStats.getCharisma();
        builder(9, 8, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_SWORD).name(str.getName()).description(MenuText.scoreLore(str)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, str, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOW).name(dex.getName()).description(MenuText.scoreLore(dex)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, dex, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(con.getName()).description(MenuText.scoreLore(con)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, con, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(intel.getName()).description(MenuText.scoreLore(intel)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, intel, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(wis.getName()).description(MenuText.scoreLore(wis)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, wis, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).durability(3).name(cha.getName()).description(MenuText.scoreLore(cha)).build(), (g, p) -> nonPlayerAbilityScore(p, nonPlayer, cha, g))).build();
    }

    public void nonPlayerHitPoints(@Nonnull Player player, @Nonnull NonPlayerHitPoints hitPoints, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.HIT_POINTS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.currentHitPoints(hitPoints)).potionEffect(PotionType.REGEN).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setCurrent(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.maxHitPoints(hitPoints)).potionEffect(PotionType.INSTANT_HEAL).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setMax(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.tempHitPoints(hitPoints)).potionEffect(PotionType.STRENGTH).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            hitPoints.setTemp(i);
            hitPoints(ply, hitPoints, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.hitDice(hitPoints)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            hitPoints.setHitDice(dice);
            hitPoints(ply, hitPoints, prevGUI);
        }))).build();
    }

    public void nonPlayerSheet(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        builder(18, 17, MenuText.CHARACTER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.TOTEM, MenuText.alignment(nonPlayerSheet)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setAlignment(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.armorClass(nonPlayerSheet)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setArmorClass(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK).name(MenuText.ARMOR_CLASS_NOTE).description(nonPlayerSheet.getArmorClassNote()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setArmorClassNote(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.challengeRating(nonPlayerSheet)), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            nonPlayerSheet.setChallengeRating(d);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.climbSpeed(nonPlayerSheet)).potionEffect(PotionType.JUMP).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setClimbSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.ELYTRA, MenuText.flySpeed(nonPlayerSheet)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setFlySpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.speed(nonPlayerSheet)).potionEffect(PotionType.SPEED).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.swimSpeed(nonPlayerSheet)).potionEffect(PotionType.WATER_BREATHING).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setSwimSpeed(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CORE_STATS).potionEffect(PotionType.STRENGTH).build(), (g, p) -> nonPlayerCoreStats(p, nonPlayer, nonPlayerSheet.getCoreStats(), g)), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.builder(Material.DIAMOND).name(MenuText.dmOutputOnly(nonPlayerSheet)).addGlow(nonPlayerSheet.isDMOutputOnly()).build(), (g, p) -> {
            nonPlayerSheet.setDMOutputOnly(!nonPlayerSheet.isDMOutputOnly());
            nonPlayerSheet(p, nonPlayer, prevGUI);
        }), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.swimSpeed(nonPlayerSheet)).potionEffect(PotionType.WATER_BREATHING).build(), (g, p) -> nonPlayerHitPoints(p, nonPlayerSheet.getHealth(), g)), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.KNOWN_LANGUAGES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(nonPlayerSheet.getLanguages());
            bookMeta.setTitle(MenuText.KNOWN_LANGUAGES);
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                nonPlayerSheet.setLanguages(pages);
                nonPlayerSheet(p, nonPlayer, prevGUI);
            });
        }), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.SENSES), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(nonPlayerSheet.getSenses());
            bookMeta.setTitle(MenuText.SENSES);
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                nonPlayerSheet.setSenses(pages);
                nonPlayerSheet(p, nonPlayer, prevGUI);
            });
        }), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.of(Material.LEATHER, MenuText.size(nonPlayerSheet)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setSize(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.MONSTER_EGG, MenuText.typeRace(nonPlayerSheet)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            nonPlayerSheet.setTypeRace(s);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        })), GUIButton.of(15, ClickType.LEFT, SpigotIconBuilder.of(Material.EXP_BOTTLE, MenuText.experience(nonPlayerSheet)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            nonPlayerSheet.setXP(i);
            nonPlayerSheet(ply, nonPlayer, prevGUI);
        }))).build();
    }

    public void nonPlayerSkills(@Nonnull Player player, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerSkills skills, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(27, 26, MenuText.SKILLS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.LEATHER_BOOTS, MenuText.ACROBATICS), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getAcrobatics(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.SADDLE, MenuText.ANIMAL_HANDLING), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getAnimalHandling(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.ARCANA), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getArcana(), g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.SPEED).name(MenuText.ATHLETICS).build(), (g, p) -> skill(p, nonPlayer, coreStats.getStrength(), skills.getAthletics(), g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.EMERALD, MenuText.DECEPTION), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getDeception(), g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.HISTORY), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getHistory(), g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ICE, MenuText.INSIGHT), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getInsight(), g)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.INTIMIDATION), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getIntimidation(), g)), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.INVESTIGATION), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getInvestigation(), g)), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.MEDICINE).build(), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getMedicine(), g)), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.VINE, MenuText.NATURE), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getNature(), g)), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.CARROT_ITEM, MenuText.PERCEPTION), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getPerception(), g)), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.PERFORMANCE), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getPerformance(), g)), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.of(Material.EYE_OF_ENDER, MenuText.PERSUASION), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getPersuasion(), g)), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.RELIGION), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getReligion(), g)), GUIButton.of(15, ClickType.LEFT, SpigotIconBuilder.of(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getSleightOfHand(), g)), GUIButton.of(16, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.NIGHT_VISION).name(MenuText.STEALTH).build(), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getStealth(), g)), GUIButton.of(17, ClickType.LEFT, SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.SURVIVAL), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getSurvival(), g)), GUIButton.of(18, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.STRENGTH), (g, p) -> skill(p, nonPlayer, coreStats.getStrength(), skills.getUnskilledSTR(), g)), GUIButton.of(19, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.DEXTERITY), (g, p) -> skill(p, nonPlayer, coreStats.getDexterity(), skills.getUnskilledDEX(), g)), GUIButton.of(20, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLDEN_APPLE, MenuText.CONSTITUTION), (g, p) -> skill(p, nonPlayer, coreStats.getConstitution(), skills.getUnskilledCON(), g)), GUIButton.of(21, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.INTELLIGENCE), (g, p) -> skill(p, nonPlayer, coreStats.getIntelligence(), skills.getUnskilledINT(), g)), GUIButton.of(22, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.WISDOM), (g, p) -> skill(p, nonPlayer, coreStats.getWisdom(), skills.getUnskilledWIS(), g)), GUIButton.of(23, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.CHARISMA).durability(3).build(), (g, p) -> skill(p, nonPlayer, coreStats.getCharisma(), skills.getUnskilledCHA(), g))).build();
    }

    public void nonPlayers(@Nonnull Player player, @Nonnull List<NonPlayer> nonPlayers, int page) {
        SpigotChestGUIBuilder<SpigotMCDNDSimple> builder = paged(MenuText.PLAYER_SHEETS, player, null, page, playerSheet -> SpigotIconBuilder.of(Material.WRITTEN_BOOK, playerSheet.getName()), nonPlayer -> (g, p) -> nonPlayer(player, nonPlayer, g), (p, i) -> nonPlayers(p, nonPlayers, i), nonPlayers);
        if (player.hasPermission(Permissions.DM)) {
            builder.setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_PLAYER_SHEET), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
                PlayerSheet playerSheet = new PlayerSheet();
                playerSheet.setName(s);
                playerSheet(ply, playerSheet, g);
            })));
        }

        builder.build();
    }

    @Nonnull
    private String openBookCommand(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull List<String> pages, @Nonnull Spell spell) {
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

    public void outputOptions(@Nonnull Player player, @Nonnull OutputOptions outputOptions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_INGOT, MenuText.SAVING_THROWS), (g, p) -> savingThrowOutputOptions(p, outputOptions.getSavingThrowOutputOptions(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_INGOT, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS), (g, p) -> weaponsSpellMiscOutputOptions(p, outputOptions.getWeaponsSpellMiscOutputOptions(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS), (g, p) -> coreSkillsOutputOptions(p, outputOptions.getCoreSkillsOutputOptions(), g))).build();
    }

    @Nonnull
    private <O> SpigotChestGUIBuilder<SpigotMCDNDSimple> paged(@Nonnull String name, @Nonnull Player player, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI, int page, @Nonnull Function<O, ItemStack> itemStackMapper, @Nullable Function<O, BiConsumer<SpigotChestGUI<SpigotMCDNDSimple>, Player>> actionMapper, @Nonnull BiConsumer<Player, Integer> pageNavigator, @Nonnull List<O> contents) {
        int maxPage = new Double(Math.ceil(contents.size() / 45)).intValue();
        return builder(54, 53, name, player, prevGUI).setPage(page).setContents(ClickType.LEFT, itemStackMapper, actionMapper, contents).setJumpToPage(45, maxPage, pageNavigator).setPageNavigation(48, MenuText.PREVIOUS_PAGE, (gui, p) -> {
            if (page > 1) {
                pageNavigator.accept(p, page - 1);
            }
        }).setPageNavigation(50, MenuText.NEXT_PAGE, (gui, p) -> {
            if (page + 1 > maxPage) {
                pageNavigator.accept(p, page + 1);
            }
        });
    }

    @SafeVarargs
    @Nonnull
    private final <O> SpigotChestGUIBuilder<SpigotMCDNDSimple> paged(@Nonnull String name, @Nonnull Player player, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI, int page, @Nonnull Function<O, ItemStack> itemStackMapper, @Nullable Function<O, BiConsumer<SpigotChestGUI<SpigotMCDNDSimple>, Player>> actionMapper, @Nonnull BiConsumer<Player, Integer> pageNavigator, @Nonnull O... contents) {
        return paged(name, player, prevGUI, page, itemStackMapper, actionMapper, pageNavigator, Arrays.asList(contents));
    }

    public void playerAbilityScore(@Nonnull Player player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, abilityScore.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.score(abilityScore)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            abilityScore.setScore(i);
            playerAbilityScore(ply, abilityScore, bioAndInfo, classLevels, experience, prevGUI);
        })), GUIButton.of(1, SpigotIconBuilder.of(Material.SULPHUR, MenuText.mod(abilityScore))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.proficient(abilityScore)), (g, p) -> {
            abilityScore.setIsProficient(!abilityScore.isProficient());
            playerAbilityScore(p, abilityScore, bioAndInfo, classLevels, experience, prevGUI);
        }), GUIButton.of(3, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.saveMod(abilityScore, classLevels, experience))), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.ROLL_SAVE), (g, p) -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveMod(classLevels, experience);
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (first == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(first + saveMod).append(ChatColor.RESET).append(" | ");
            if (second == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (second == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(second + saveMod);
            Bukkit.broadcastMessage(sb.toString());
        })).build();
    }

    public void playerCoreStats(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Experience experience, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        PlayerAbilityScore str = coreStats.getStrength();
        PlayerAbilityScore dex = coreStats.getDexterity();
        PlayerAbilityScore con = coreStats.getConstitution();
        PlayerAbilityScore intel = coreStats.getIntelligence();
        PlayerAbilityScore wis = coreStats.getWisdom();
        PlayerAbilityScore cha = coreStats.getCharisma();
        builder(9, 8, MenuText.CORE_STATS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_SWORD).name(str.getName()).description(MenuText.scoreLore(str, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, str, bioAndInfo, classLevels, experience, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOW).name(dex.getName()).description(MenuText.scoreLore(dex, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, dex, bioAndInfo, classLevels, experience, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(con.getName()).description(MenuText.scoreLore(con, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, con, bioAndInfo, classLevels, experience, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(intel.getName()).description(MenuText.scoreLore(intel, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, intel, bioAndInfo, classLevels, experience, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(wis.getName()).description(MenuText.scoreLore(wis, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, wis, bioAndInfo, classLevels, experience, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).durability(3).name(cha.getName()).description(MenuText.scoreLore(cha, classLevels, experience)).build(), (g, p) -> playerAbilityScore(p, cha, bioAndInfo, classLevels, experience, g))).build();
    }

    public void playerSheet(@Nonnull Player player, @Nonnull PlayerSheet playerSheet, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        SpigotChestGUIBuilder<SpigotMCDNDSimple> builder = builder(9, 8, MenuText.PLAYER_SHEET, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setName(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.CLASS), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setClazz(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.MONSTER_EGG, MenuText.RACE), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            playerSheet.setClazz(s);
            playerSheet(ply, playerSheet, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.BIO_AND_INFO), (g, p) -> bioAndInfo(p, playerSheet.getBioAndInfo(), playerSheet, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.CHARACTER_SHEET), (g, p) -> characterSheet(p, playerSheet.getBioAndInfo(), playerSheet.getCharacterSheet(), g)));
        if (player.hasPermission(Permissions.DM)) {
            builder.setButtons(GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.EDIT_CONTROLLERS).durability(3).build(), (g, p) -> editControllers(p, 1, playerSheet, g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> SpigotMCDNDSimple.instance().getPlayerStorage().remove(playerSheet)));
        }

        builder.build();
    }

    public void players(@Nonnull Player player, @Nonnull List<PlayerSheet> playerSheets, int page) {
        SpigotChestGUIBuilder<SpigotMCDNDSimple> builder = paged(MenuText.PLAYER_SHEETS, player, null, page, playerSheet -> SpigotIconBuilder.of(Material.WRITTEN_BOOK, playerSheet.getName()), playerSheet -> (g, p) -> playerSheet(player, playerSheet, g), (p, i) -> players(p, playerSheets, i), playerSheets);
        if (player.hasPermission(Permissions.DM)) {
            builder.setButton(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_PLAYER_SHEET), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
                PlayerSheet playerSheet = new PlayerSheet();
                playerSheet.setName(s);
                playerSheet(ply, playerSheet, g);
            })));
        }

        builder.build();
    }

    public void prepared(@Nonnull Player player, int page, @Nonnull Spell spell, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.prepared(spell.getPrepared()), player, prevGUI, page, prepared -> SpigotIconBuilder.builder(Material.BOOK).name(prepared.getName()).addGlow(spell.getPrepared() == prepared).build(), prepared -> (g, p) -> {
            spell.setPrepared(prepared);
            prepared(player, page, spell, prevGUI);
        }, (p, i) -> prepared(player, i, spell, prevGUI), Prepared.values()).build();
    }

    public void rangedBonus(@Nonnull Player player, @Nonnull RangedBonus rangedBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.RANGED_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            rangedBonus.setAttack(dice);
            rangedBonus(player, rangedBonus, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.DAMAGE_ROLLS).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            rangedBonus.setDamage(dice);
            rangedBonus(player, rangedBonus, prevGUI);
        }))).build();
    }

    public void rangedWeapon(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<RangedWeapon> weapons, RangedBonus rangedBonus, RangedWeapon weapon, SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, weapon.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(weapon.isProficient() ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_OFF).name(MenuText.isProficient(weapon)).addGlow(weapon.isProficient()).build(), (g, p) -> {
            weapon.setIsProficient(!weapon.isProficient());
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setName(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.ATTACK_STAT), (g, p) -> attackStat(p, 1, weapon, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.magicBonus(weapon)), (g, p) -> new IntegerAnvilGUI(player, (ply, i) -> {
            weapon.setMagicBonus(i);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(4, SpigotIconBuilder.of(Material.STONE_SWORD, MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)))), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_SWORD, MenuText.damageDice(weapon.getDamage())), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setDamage(dice);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(6, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.damageBonus(weapon, coreStats))), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND, MenuText.damageType(weapon.getDamageType())), (g, p) -> new StringAnvilGUI(player, (ply, s) -> {
            weapon.setDamageType(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.critDamage(weapon)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setCritDamage(dice);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.SULPHUR, MenuText.critOn(weapon)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            weapon.setCritMin(i);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, weapons, rangedBonus, weapon, prevGUI);
        })), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_ON, MenuText.ROLL_ATTACK), (g, p) -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(rangedBonus.getAttack().roll());
            String newLine = "\n";
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") ranged with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (firstAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(firstAttackRoll + bonus).append(" | ");
            if (secondAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (secondAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(secondAttackRoll + bonus).append(" vs AC").append(newLine).append("Damage: ").append(Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(rangedBonus.getDamage().roll()))).append(" ").append(weapon.getDamageType());
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                sb.append("Crit: ").append(Dice.total(critDice.roll())).append(newLine).append("Crit (adv roll): ").append(critDice.roll());
            }

            Bukkit.broadcastMessage(sb.toString());
            p.closeInventory();
        }), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), (g, p) -> {
            weapons.remove(weapon);
            g.close();
        })).build();
    }

    public void rangedWeapons(@Nonnull Player player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<RangedWeapon> rangedWeapons, @Nonnull RangedBonus rangedBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.RANGED_WEAPONS, player, prevGUI, page, rangedWeapon -> ItemRepresentation.rangedWeapon(rangedWeapon, classLevels, coreStats, experience), rangedWeapon -> (g, p) -> rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, rangedWeapon, g), (p, i) -> rangedWeapons(p, i, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, prevGUI), rangedWeapons).setButtons(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_RANGED_WEAPON), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            RangedWeapon rangedWeapon = new RangedWeapon();
            rangedWeapon.setName(s);
            rangedWeapon(p, bioAndInfo, classLevels, coreStats, experience, rangedWeapons, rangedBonus, rangedWeapon, g);
        }))).build();
    }

    public <R extends Rechargeable> void recharge(@Nonnull Player player, @Nonnull R rechargeable, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.RECHARGE, player, prevGUI, page, recharge -> SpigotIconBuilder.builder(Material.BED).name(rechargeable.getRecharge().getName()).addGlow(rechargeable.getRecharge() == recharge).build(), recharge -> (g, p) -> {
            rechargeable.setRecharge(recharge);
            g.close();
        }, (p, i) -> recharge(p, rechargeable, i, prevGUI), Recharge.values()).build();
    }

    private void rollHitDie(@Nonnull Player player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, int amount, int sides) {
        player.closeInventory();
        Dice dice = new Dice(amount, sides);
        player.sendMessage(Messages.rolledHitDice(bioAndInfo, player.getName(), Dice.total(dice.roll(), constitution.getMod()), dice));
    }

    public void savingThrowOutputOptions(@Nonnull Player player, @Nonnull SavingThrowOutputOptions savingThrowOutputOptions, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.SAVING_THROW_OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_SWORD).name(MenuText.STRENGTH).addGlow(savingThrowOutputOptions.isStrengthEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOW).name(MenuText.DEXTERITY).addGlow(savingThrowOutputOptions.isDexterityEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CONSTITUTION).potionEffect(PotionType.INSTANT_HEAL).addGlow(savingThrowOutputOptions.isConstitutionEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.INTELLIGENCE).addGlow(savingThrowOutputOptions.isIntelligenceEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.WISDOM).addGlow(savingThrowOutputOptions.isWisdomEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.CHARISMA).durability(3).addGlow(savingThrowOutputOptions.isCharismaEnabled()).build(), (g, p) -> {
            savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled());
            savingThrowOutputOptions(p, savingThrowOutputOptions, prevGUI);
        })).build();
    }

    public void skill(@Nonnull Player player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nonnull NonPlayerSkill skill, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, skill.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.INK_SACK).name(MenuText.bonus(skill)).durability(4).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            skill.setBonus(i);
            skill(p, nonPlayer, abilityScore, skill, prevGUI);
        })), GUIButton.of(2, SpigotIconBuilder.of(Material.BOOKSHELF, MenuText.total(abilityScore, skill))), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_ON, MenuText.bonus(skill)), (g, p) -> {
            g.close();
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
        })).build();
    }

    public void skill(@Nonnull Player player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nonnull Dice skillBonus, @Nonnull PlayerSkill skill, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, skill.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.PROFICIENT).description("- " + skill.getSkillProficiency().getName()).build(), (g, p) -> skillProficiency(p, skill, 1, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.INK_SACK).name(MenuText.bonus(skill)).durability(4).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            skill.setBonus(i);
            skill(p, abilityScore, bioAndInfo, classLevels, experience, skillBonus, skill, prevGUI);
        })), GUIButton.of(2, SpigotIconBuilder.of(Material.BOOKSHELF, MenuText.total(abilityScore, classLevels, experience, skill))), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_ON, MenuText.bonus(skill)), (g, p) -> {
            g.close();
            Dice dice = new Dice(20);
            int bonus = Dice.total(skillBonus.roll()) + skill.getBonus();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + skill.getName() + " check.\nThe results are: ");
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
        })).build();
    }

    public void skillProficiency(@Nonnull Player player, @Nonnull PlayerSkill skill, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.PROFICIENT, player, prevGUI, page, skillProficiency -> SpigotIconBuilder.of(Material.BOOK, skillProficiency.getName()), skillProficiency -> (g, p) -> {
            skill.setSkillProficiency(skillProficiency);
            g.close();
        }, (p, i) -> skillProficiency(p, skill, i, prevGUI), SkillProficiency.values()).build();
    }

    private void skillsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Dice skillBonus, @Nonnull Experience experience, @Nonnull SkillsTab skillsTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(27, 26, MenuText.SKILLS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.LEATHER_BOOTS, MenuText.ACROBATICS), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAcrobatics(), g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.SADDLE, MenuText.ANIMAL_HANDLING), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAnimalHandling(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.ARCANA), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getArcana(), g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.SPEED).name(MenuText.ATHLETICS).build(), (g, p) -> skill(p, coreStats.getStrength(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getAthletics(), g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.EMERALD, MenuText.DECEPTION), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getDeception(), g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.HISTORY), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getHistory(), g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.of(Material.ICE, MenuText.INSIGHT), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getInsight(), g)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.INTIMIDATION), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getIntimidation(), g)), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.INVESTIGATION), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getInvestigation(), g)), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.MEDICINE).build(), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getMedicine(), g)), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.VINE, MenuText.NATURE), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getNature(), g)), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.of(Material.CARROT_ITEM, MenuText.PERCEPTION), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPerception(), g)), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.PERFORMANCE), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPerformance(), g)), GUIButton.of(13, ClickType.LEFT, SpigotIconBuilder.of(Material.EYE_OF_ENDER, MenuText.PERSUASION), (g, p) -> skill(p, coreStats.getCharisma(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getPersuasion(), g)), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.RELIGION), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getReligion(), g)), GUIButton.of(15, ClickType.LEFT, SpigotIconBuilder.of(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getSleightOfHand(), g)), GUIButton.of(16, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.NIGHT_VISION).name(MenuText.STEALTH).build(), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getStealth(), g)), GUIButton.of(17, ClickType.LEFT, SpigotIconBuilder.of(Material.SKULL_ITEM, MenuText.SURVIVAL), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getSurvival(), g)), GUIButton.of(18, ClickType.LEFT, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.STRENGTH), (g, p) -> skill(p, coreStats.getStrength(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledSTR(), g)), GUIButton.of(19, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.DEXTERITY), (g, p) -> skill(p, coreStats.getDexterity(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledDEX(), g)), GUIButton.of(20, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLDEN_APPLE, MenuText.CONSTITUTION), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g)), GUIButton.of(21, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.INTELLIGENCE), (g, p) -> skill(p, coreStats.getIntelligence(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledINT(), g)), GUIButton.of(22, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.WISDOM), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g)), GUIButton.of(23, ClickType.LEFT, SpigotIconBuilder.builder(Material.SKULL_ITEM).name(MenuText.CHARISMA).durability(3).build(), (g, p) -> skill(p, coreStats.getWisdom(), bioAndInfo, classLevels, experience, skillBonus, skillsTab.getUnskilledWIS(), g))).build();
    }

    public void spell(@Nonnull Player player, @Nonnull Spell spell, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        SpigotChestGUIBuilder<SpigotMCDNDSimple> builder = builder(27, 26, spell.getName(), player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setName(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.INK_SACK).durability(4).name(MenuText.RENAME).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spell.setLevel(i);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.spellType(spell.getSpellType())), (g, p) -> spellType(p, 1, spell, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.WATCH, MenuText.castTime(spell.getCastTime())), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setCastTime(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_TORCH_ON, MenuText.concentration(spell)), (g, p) -> {
            spell.setNeedsConcentration(!spell.needsConcentration());
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.of(Material.TOTEM, MenuText.ritual(spell)), (g, p) -> {
            spell.setIsRitual(!spell.isRitual());
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        }), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.gainedFrom(spell.getGainedFrom())), (g, p) -> spellcasterClass(player, 1, spell::setGainedFrom, spellCasterClass -> spellCasterClass == spell.getGainedFrom(), g)), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.of(Material.LINGERING_POTION, MenuText.TARGET), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setTargetArea(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.range(spell)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setRange(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(10, ClickType.LEFT, SpigotIconBuilder.of(Material.STRING, MenuText.duration(spell)), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spell.setDuration(s);
            spell(ply, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
        })), GUIButton.of(11, ClickType.LEFT, SpigotIconBuilder.builder(Material.PURPLE_SHULKER_BOX).name(MenuText.COMPONENTS).description(spell.getComponents()).build(), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(spell.getName());
            bookMeta.setPages(spell.getComponents());
            bookMeta.setAuthor(p.getName());
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                spell.setComponents(pages);
                spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI);
            });
        }), GUIButton.of(12, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.SPELL_INFO), (g, p) -> {
            MacroOptions macroOptions = spell.getMacroOptions();
            String newLine = "\n";
            TextComponent spellHoverText = new TextComponent(MenuText.spellType(spell.getSpellType()) + newLine + MenuText.spellLevel(spell.getLevel()));
            if (macroOptions.isInfoBlockEnabled()) {
                spellHoverText.addExtra(newLine + MenuText.components(spell) + newLine + MenuText.castTime(spell.getCastTime()) + newLine + MenuText.duration(spell) + newLine + MenuText.target(spell.getTargetArea()) + newLine + MenuText.range(spell));
            }

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
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(player, bioAndInfo, spellSave.getOnSuccessfulSave(), spell)));
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
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(p, bioAndInfo, spell.getEffects(), spell)));
                effect.addExtra(here);
                effect.addExtra(" to view the spell's effects.");
            }

            TextComponent spellDescription = new TextComponent();
            if (macroOptions.isDescriptionEnabled()) {
                spellDescription.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(p, bioAndInfo, spell.getDescription(), spell)));
                spellDescription.addExtra(here);
                spellDescription.addExtra(" to view the spell's description.");
            }

            TextComponent atHigherLevels = new TextComponent();
            if (macroOptions.isAtHigherLevelsEnabled()) {
                atHigherLevels.setText(newLine + "Click ");
                TextComponent here = new TextComponent("HERE");
                here.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                here.setBold(true);
                here.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, openBookCommand(p, bioAndInfo, spell.getAtHigherLevels(), spell)));
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
        }), GUIButton.of(14, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_COMPARATOR_OFF, MenuText.SPELL_CAST_MACRO_DISPLAY_OPTIONS), (g, p) -> macroOptions(p, spell.getMacroOptions(), g)), GUIButton.of(18, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), (g, p) -> spellDescription(p, spell, g)), GUIButton.of(19, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK), (g, p) -> statBonus(p, 1, spell::setAttackStat, statBonus -> spell.getAttackStat() == statBonus, g)), GUIButton.of(20, ClickType.LEFT, SpigotIconBuilder.of(Material.SHIELD, MenuText.SAVE), (g, p) -> spellSave(p, spell.getSpellSave(), g)), GUIButton.of(21, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.HEALING).build(), (g, p) -> healing(p, spell.getSpellHealing(), g)), GUIButton.of(22, ClickType.LEFT, SpigotIconBuilder.of(Material.GOLD_SWORD, MenuText.DAMAGE), (g, p) -> spellDamage(p, spell.getSpellDamage(), g)), GUIButton.of(23, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.SPELL_EFFECTS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookmeta = (BookMeta) book.getItemMeta();
            bookmeta.setTitle(MenuText.SPELL_EFFECTS);
            bookmeta.setPages(spell.getEffects());
            book.setItemMeta(bookmeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                spell.setEffects(pages);
                g.open();
            });
        }));

        ItemStack icon = SpigotIconBuilder.of(Material.DIODE, MenuText.prepared(spell.getPrepared()));
        if (spell.getLevel() == 0) {
            builder.setButton(GUIButton.of(6, icon));
        }
        else {
            builder.setButton(GUIButton.of(6, ClickType.LEFT, icon, (g, p) -> prepared(p, 1, spell, g)));
        }

        builder.build();
    }

    public void spellBook(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<Spell> spells, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(18, 17, MenuText.SPELLS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.CANTRIPS).description(MenuText.total(0, spells)).build(), (g, p) -> spells(p, 0, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_1).description(MenuText.total(1, spells)).build(), (g, p) -> spells(p, 1, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_2).description(MenuText.total(2, spells)).build(), (g, p) -> spells(p, 2, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_3).description(MenuText.total(3, spells)).build(), (g, p) -> spells(p, 3, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_4).description(MenuText.total(4, spells)).build(), (g, p) -> spells(p, 4, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_5).description(MenuText.total(5, spells)).build(), (g, p) -> spells(p, 5, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(6, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_6).description(MenuText.total(6, spells)).build(), (g, p) -> spells(p, 6, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_7).description(MenuText.total(7, spells)).build(), (g, p) -> spells(p, 7, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(8, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_8).description(MenuText.total(8, spells)).build(), (g, p) -> spells(p, 8, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g)), GUIButton.of(9, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOOK_AND_QUILL).name(MenuText.SPELL_SLOT_9).description(MenuText.total(9, spells)).build(), (g, p) -> spells(p, 9, spells, 1, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g))).build();
    }

    public void spellDamage(@Nonnull Player player, @Nonnull SpellDamage spellDamage, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.DAMAGE, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(MenuText.CAN_CRIT).addGlow(spellDamage.canCrit()).build(), (g, p) -> {
            spellDamage.setCanCrit(!spellDamage.canCrit());
            spellDamage(player, spellDamage, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.REDSTONE_LAMP_OFF, MenuText.DAMAGE_DICE), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellDamage.setDamage(dice);
            g.open();
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.STICK, MenuText.otherBonus(spellDamage)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellDamage.setBonus(i);
            spellDamage(player, spellDamage, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.damageType(spellDamage.getDamageType())), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            spellDamage.setDamageType(s);
            spellDamage(player, spellDamage, prevGUI);
        }))).build();
    }

    public void spellDashboard(@Nonnull Player player, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(27, 26, MenuText.SPELLS_DASHBOARD, player, prevGUI).setButtons(GUIButton.of(0, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.ARCANE_TRICKSTER)), GUIButton.of(1, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.BARD)), GUIButton.of(2, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.CLERIC)), GUIButton.of(3, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.DRUID)), GUIButton.of(4, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.ELDRITCH_KNIGHT)), GUIButton.of(7, ClickType.LEFT, SpigotIconBuilder.builder(Material.LINGERING_POTION).name(MenuText.SORCERY_POINTS).description(MenuText.sorceryPoints(classLevels, spellbookTab)).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellbookTab.setSorceryPointsUsed(i);
            spellDashboard(player, classLevels, coreStats, experience, spellbookTab, prevGUI);
        })), GUIButton.of(8, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.PALADIN)), GUIButton.of(9, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.RANGER)), GUIButton.of(10, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.SORCERER)), GUIButton.of(11, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.WARLOCK)), GUIButton.of(12, spellcasterIcon(classLevels, coreStats, experience, spellbookTab, SpellcasterClass.WIZARD)), GUIButton.of(16, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTMENT_TABLE).name(MenuText.SPELL_SLOTS).description(MenuText.spellSlots(classLevels, spellbookTab)).build(), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellbookTab.setWarlockSpellSlotsUsed(i);
            spellDashboard(player, classLevels, coreStats, experience, spellbookTab, prevGUI);
        }))).build();
    }

    public void spellDescription(@Nonnull Player player, @Nonnull Spell spell, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.DESCRIPTION, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(spell.getDescription());
            bookMeta.setTitle(spell.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                spell.setDescription(pages);
                g.open();
            });
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.AT_HIGHER_LEVELS), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(spell.getAtHigherLevels());
            bookMeta.setTitle(spell.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                spell.setAtHigherLevels(pages);
                g.open();
            });
        })).build();
    }

    public void spellSave(@Nonnull Player player, @Nonnull SpellSave spellSave, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.SAVE, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.SHIELD, MenuText.SAVING_STAT), (g, p) -> statBonus(p, 1, spellSave::setSavingStat, statBonus -> spellSave.getSavingStat() == statBonus, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTMENT_TABLE, MenuText.SAVE_DC), (g, p) -> spellcasterClass(p, 1, spellSave::setSaveDCType, spellCasterClass -> spellCasterClass == spellSave.getSaveDCType(), g)), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.customDC(spellSave)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> {
            spellSave.setCustomDC(i);
            spellSave(player, spellSave, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK, MenuText.ON_SUCCESSFUL_SAVE), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setTitle(MenuText.ON_SUCCESSFUL_SAVE);
            bookMeta.setPages(spellSave.getOnSuccessfulSave());
            bookMeta.setAuthor(p.getName());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, spellSave::setOnSuccessfulSave);
        })).build();
    }

    public void spellType(@Nonnull Player player, int page, @Nonnull Spell spell, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.SCHOOL, player, prevGUI, page, spellType -> SpigotIconBuilder.builder(Material.END_CRYSTAL).name(spellType.getName()).addGlow(spell.getSpellType() == spellType).build(), spellType -> (g, p) -> {
            spell.setSpellType(spellType);
            spellType(p, page, spell, prevGUI);
        }, (p, i) -> spellType(p, i, spell, prevGUI), SpellType.values()).build();
    }

    public void spellbookTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.SPELL_DASHBOARD, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.BOOK_AND_QUILL, MenuText.SPELL_DASHBOARD), (g, p) -> spellDashboard(p, classLevels, coreStats, experience, spellbookTab, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SPELLS), (g, p) -> spellBook(p, bioAndInfo, classLevels, coreStats, experience, spellbookTab.getSpells(), spellcastingBonus, g))).build();
    }

    public void spellcasterClass(@Nonnull Player player, int page, @Nonnull Consumer<SpellcasterClass> valueSetter, @Nonnull Predicate<SpellcasterClass> glowApplier, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.SAVE_DC, player, prevGUI, page, spellcasterClass -> spellcasterIcon(spellcasterClass, glowApplier), spellcasterClass -> (g, p) -> {
            valueSetter.accept(spellcasterClass);
            spellcasterClass(p, page, valueSetter, glowApplier, prevGUI);
        }, (p, i) -> spellcasterClass(p, i, valueSetter, glowApplier, prevGUI), SpellcasterClass.values());
    }

    @Nonnull
    private ItemStack spellcasterIcon(@Nonnull SpellcasterClass spellCasterClass, @Nonnull Predicate<SpellcasterClass> glowApplier) {
        return SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(spellCasterClass.getName()).addGlow(glowApplier.test(spellCasterClass)).build();
    }

    @Nonnull
    private ItemStack spellcasterIcon(@Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcasterClass spellcasterClass) {
        return SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(spellcasterClass.getName()).description(MenuText.spellcastingTable(classLevels, coreStats, experience, spellcasterClass)).addGlow(spellbookTab.getSpellcasterClasses(classLevels).contains(spellcasterClass)).build();
    }

    public void spellcastingBonus(@Nonnull Player player, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.SPELLCASTING_BONUSES, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setAttack(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.DAMAGE_ROLLS).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setDamage(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.LUCK).name(MenuText.SAVE_DC_ROLLS).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                ply.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setSaveDC(dice);
            spellcastingBonus(player, spellcastingBonus, prevGUI);
        }))).build();
    }

    public void spells(@Nonnull Player player, int level, @Nonnull List<Spell> spells, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.spellLevel(level), player, prevGUI, page, ItemRepresentation::spell, spell -> (g, p) -> spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g), (p, i) -> spells(player, level, spells, page, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, prevGUI), spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList())).setButtons(GUIButton.of(49, ClickType.LEFT, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_MELEE_WEAPON), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            Spell spell = new Spell();
            spell.setName(s);
            spell(p, spell, bioAndInfo, classLevels, coreStats, experience, spellcastingBonus, g);
        }))).build();
    }

    public void statBonus(@Nonnull Player player, int page, Consumer<StatBonus> valueSetter, @Nonnull Predicate<StatBonus> glowApplier, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.STAT_BONUS, player, prevGUI, page, statBonus -> statBonusIcon(statBonus, glowApplier), statBonus -> (g, p) -> {
            valueSetter.accept(statBonus);
            statBonus(player, page, valueSetter, glowApplier, prevGUI);
        }, (p, i) -> statBonus(player, page, valueSetter, glowApplier, prevGUI), StatBonus.values()).build();
    }

    @Nonnull
    private ItemStack statBonusIcon(@Nonnull StatBonus statBonus, @Nonnull Predicate<StatBonus> glowApplier) {
        SpigotIconBuilder builder;
        switch (statBonus) {
            case CHA:
                builder = SpigotIconBuilder.builder(Material.SKULL).durability(3);
                break;
            case CON:
                builder = SpigotIconBuilder.builder(Material.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpigotIconBuilder.builder(Material.BOW);
                break;
            case INT:
                builder = SpigotIconBuilder.builder(Material.BOOK_AND_QUILL);
                break;
            case STR:
                builder = SpigotIconBuilder.builder(Material.IRON_SWORD);
                break;
            case WIS:
                builder = SpigotIconBuilder.builder(Material.ENCHANTED_BOOK);
                break;
            default:
                builder = SpigotIconBuilder.builder(Material.ENDER_CHEST);
                break;
        }

        return builder.name(statBonus.getName()).addGlow(glowApplier.test(statBonus)).build();
    }

    public void traitsBackground(@Nonnull Player player, @Nonnull TraitsBackground traitsBackground, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.TRAITS_BACKGROUND, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.MILK_BUCKET).name(MenuText.CONDITION_IMMUNITY).description(traitsBackground.getConditionImmunity()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setConditionImmunity(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.DIAMOND_CHESTPLATE).name(MenuText.DAMAGE_IMMUNITY).description(traitsBackground.getDamageImmunity()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageImmunity(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.SHIELD).name(MenuText.DAMAGE_RESISTANCE).description(traitsBackground.getDamageResistance()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageResistance(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(MenuText.DAMAGE_VULNERABILITY).description(traitsBackground.getDamageVulnerability()).build(), (g, p) -> new StringAnvilGUI(p, (ply, s) -> {
            traitsBackground.setDamageVulnerability(s);
            traitsBackground(ply, traitsBackground, prevGUI);
        })), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.SHIELD).name(MenuText.TRAITS).description(traitsBackground.getDamageResistance()).build(), (g, p) -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(p.getName());
            bookMeta.setPages(traitsBackground.getTraits());
            bookMeta.setTitle(MenuText.TRAITS);
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, book, pages -> {
                traitsBackground.setTraits(pages);
                traitsBackground(p, traitsBackground, prevGUI);
            });
        })).build();
    }

    public void unarmoredBonus(@Nonnull Player player, @Nonnull ArmorTab armorTab, int page, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        paged(MenuText.UNARMORED_BONUS, player, prevGUI, page, unarmoredBonus -> SpigotIconBuilder.builder(ItemRepresentation.unarmoredBonus(unarmoredBonus)).build(), unarmoredBonus -> (g, p) -> {
            armorTab.setUnarmoredBonus(unarmoredBonus);
            g.close();
        }, (p, i) -> unarmoredBonus(p, armorTab, i, prevGUI), UnarmoredBonus.values());
    }

    public void wealth(@Nonnull Player player, @Nonnull Wealth wealth, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        Coin copper = wealth.getCopper();
        Coin silver = wealth.getSilver();
        Coin electrum = wealth.getElectrum();
        Coin gold = wealth.getGold();
        Coin platinum = wealth.getPlatinum();
        builder(9, 8, MenuText.COIN_CARRIED, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(copper)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> copper.setAmount(i))), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(silver)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> silver.setAmount(i))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(electrum)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> electrum.setAmount(i))), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(gold)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> gold.setAmount(i))), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.coin(platinum)), (g, p) -> new IntegerAnvilGUI(p, (ply, i) -> platinum.setAmount(i))), GUIButton.of(5, SpigotIconBuilder.of(Material.ROTTEN_FLESH, MenuText.total(wealth)))).build();
    }

    public void weaponsSpellMiscOutputOptions(@Nonnull Player player, @Nonnull WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions, @Nonnull SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.INITIATIVE).potionEffect(PotionType.SPEED).addGlow(weaponsSpellMiscOutputOptions.isInitiativeEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setInitiativeEnabled(!weaponsSpellMiscOutputOptions.isInitiativeEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.builder(Material.POTION).name(MenuText.HIT_DICE).potionEffect(PotionType.REGEN).addGlow(weaponsSpellMiscOutputOptions.isHitDiceEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setHitDiceEnabled(!weaponsSpellMiscOutputOptions.isHitDiceEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.builder(Material.IRON_SWORD).name(MenuText.MELEE_WEAPONS).addGlow(weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(3, ClickType.LEFT, SpigotIconBuilder.builder(Material.BOW).name(MenuText.RANGED_WEAPONS).addGlow(weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(4, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.SPELL_INFO).addGlow(weaponsSpellMiscOutputOptions.isSpellInfoEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!weaponsSpellMiscOutputOptions.isSpellInfoEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        }), GUIButton.of(5, ClickType.LEFT, SpigotIconBuilder.builder(Material.ENCHANTMENT_TABLE).name(MenuText.SPELL_CAST).addGlow(weaponsSpellMiscOutputOptions.isSpellCastEnabled()).build(), (g, p) -> {
            weaponsSpellMiscOutputOptions.setSpellCastEnabled(!weaponsSpellMiscOutputOptions.isSpellCastEnabled());
            weaponsSpellMiscOutputOptions(p, weaponsSpellMiscOutputOptions, prevGUI);
        })).build();
    }

    public void weaponsTab(@Nonnull Player player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull MeleeBonus meleeBonus, @Nonnull RangedBonus rangedBonus, @Nonnull WeaponsTab weaponsTab, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.WEAPONS, player, prevGUI).setButtons(GUIButton.of(0, ClickType.LEFT, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.MELEE_WEAPONS), (g, p) -> meleeWeapons(p, 1, bioAndInfo, classLevels, coreStats, experience, weaponsTab.getMeleeWeapons(), meleeBonus, g)), GUIButton.of(1, ClickType.LEFT, SpigotIconBuilder.of(Material.BOW, MenuText.RANGED_WEAPONS), (g, p) -> rangedWeapons(p, 1, bioAndInfo, classLevels, coreStats, experience, weaponsTab.getRangedWeapons(), rangedBonus, g))).build();
    }

    public void weight(@Nonnull Player player, @Nonnull CoreStats coreStats, @Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth, @Nonnull Weight weight, @Nullable SpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        builder(9, 8, MenuText.WEIGHT, player, prevGUI).setButtons(GUIButton.of(0, SpigotIconBuilder.of(Material.CHEST, MenuText.inventoryWeight(items, weight))), GUIButton.of(1, SpigotIconBuilder.of(Material.EMERALD, MenuText.coinWeight(wealth, weight))), GUIButton.of(2, ClickType.LEFT, SpigotIconBuilder.of(Material.STICK, MenuText.otherWeight(weight)), (g, p) -> new DoubleAnvilGUI(p, (ply, d) -> {
            weight.setOther(d);
            weight(player, coreStats, items, wealth, weight, prevGUI);
        })), GUIButton.of(3, SpigotIconBuilder.of(Material.STONE, MenuText.totalWeight(items, wealth, weight))), GUIButton.of(4, SpigotIconBuilder.of(Material.CHEST, MenuText.carryingMax(coreStats, weight))), GUIButton.of(5, SpigotIconBuilder.of(Material.PISTON_BASE, MenuText.pushDragLift(coreStats, weight))), GUIButton.of(6, SpigotIconBuilder.of(Material.STONE, MenuText.encumbered(coreStats, weight))), GUIButton.of(7, SpigotIconBuilder.of(Material.OBSIDIAN, MenuText.heavilyEncumbered(coreStats, weight)))).build();
    }
}
