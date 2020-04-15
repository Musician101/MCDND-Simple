package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class NonPlayerSkillsGUI extends SpigotMCDNDSimpleGUI {

    public NonPlayerSkillsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.SKILLS, 27);
        setButton(0, SpigotIconBuilder.of(Material.LEATHER_BOOTS, MenuText.ACROBATICS), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ACROBATICS, p)));
        setButton(1, SpigotIconBuilder.of(Material.SADDLE, MenuText.ANIMAL_HANDLING), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ANIMAL_HANDLING, p)));
        setButton(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.ARCANA), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ARCANA, p)));
        setButton(3, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.SPEED).name(MenuText.ATHLETICS).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ATHLETICS, p)));
        setButton(4, SpigotIconBuilder.of(Material.EMERALD, MenuText.DECEPTION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.DECEPTION, p)));
        setButton(5, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.HISTORY), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.HISTORY, p)));
        setButton(6, SpigotIconBuilder.of(Material.ICE, MenuText.INSIGHT), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INSIGHT, p)));
        setButton(7, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.INTIMIDATION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INTIMIDATION, p)));
        setButton(8, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.INVESTIGATION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INVESTIGATION, p)));
        setButton(9, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.MEDICINE).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.MEDICINE, p)));
        setButton(10, SpigotIconBuilder.of(Material.VINE, MenuText.NATURE), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.NATURE, p)));
        setButton(11, SpigotIconBuilder.of(Material.CARROT, MenuText.PERCEPTION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERCEPTION, p)));
        setButton(12, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.PERFORMANCE), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERFORMANCE, p)));
        setButton(13, SpigotIconBuilder.of(Material.ENDER_EYE, MenuText.PERSUASION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERSUASION, p)));
        setButton(14, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.RELIGION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.RELIGION, p)));
        setButton(15, SpigotIconBuilder.of(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.SLEIGHTOFHAND, p)));
        setButton(16, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.NIGHT_VISION).name(MenuText.STEALTH).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.STEALTH, p)));
        setButton(17, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.SURVIVAL), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.SURVIVAL, p)));
        setButton(18, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.STRENGTH), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_STR, p)));
        setButton(19, SpigotIconBuilder.of(Material.BOW, MenuText.DEXTERITY), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_DEX, p)));
        setButton(20, SpigotIconBuilder.of(Material.GOLDEN_APPLE, MenuText.CONSTITUTION), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_CON, p)));
        setButton(21, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.INTELLIGENCE), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_INT, p)));
        setButton(22, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.WISDOM), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_WIS, p)));
        setButton(23, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(MenuText.CHARISMA).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_CHA, p)));
    }
}
