package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.PlayerSkillGUI.PlayerSkillBridger;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class SkillsTabGUI extends SpigotMCDNDSimpleGUI {

    public SkillsTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SKILLS, 27);
        setSlot(mcdndPlayer, 0, SpigotIconBuilder.of(Material.LEATHER_BOOTS, MenuText.ACROBATICS), PlayerSkillGUI.ACROBATICS);
        setSlot(mcdndPlayer, 1, SpigotIconBuilder.of(Material.SADDLE, MenuText.ANIMAL_HANDLING), PlayerSkillGUI.ANIMAL_HANDLING);
        setSlot(mcdndPlayer, 2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.ARCANA), PlayerSkillGUI.ARCANA);
        setSlot(mcdndPlayer, 3, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.SPEED).name(MenuText.ATHLETICS).build(), PlayerSkillGUI.ATHLETICS);
        setSlot(mcdndPlayer, 4, SpigotIconBuilder.of(Material.EMERALD, MenuText.DECEPTION), PlayerSkillGUI.DECEPTION);
        setSlot(mcdndPlayer, 5, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.HISTORY), PlayerSkillGUI.HISTORY);
        setSlot(mcdndPlayer, 6, SpigotIconBuilder.of(Material.ICE, MenuText.INSIGHT), PlayerSkillGUI.INSIGHT);
        setSlot(mcdndPlayer, 7, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.INTIMIDATION), PlayerSkillGUI.INTIMIDATION);
        setSlot(mcdndPlayer, 8, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.INVESTIGATION), PlayerSkillGUI.INVESTIGATION);
        setSlot(mcdndPlayer, 9, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.INSTANT_HEAL).name(MenuText.MEDICINE).build(), PlayerSkillGUI.MEDICINE);
        setSlot(mcdndPlayer, 10, SpigotIconBuilder.of(Material.VINE, MenuText.NATURE), PlayerSkillGUI.NATURE);
        setSlot(mcdndPlayer, 11, SpigotIconBuilder.of(Material.CARROT, MenuText.PERCEPTION), PlayerSkillGUI.PERCEPTION);
        setSlot(mcdndPlayer, 12, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.PERFORMANCE), PlayerSkillGUI.PERFORMANCE);
        setSlot(mcdndPlayer, 13, SpigotIconBuilder.of(Material.ENDER_EYE, MenuText.PERSUASION), PlayerSkillGUI.PERSUASION);
        setSlot(mcdndPlayer, 14, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.RELIGION), PlayerSkillGUI.RELIGION);
        setSlot(mcdndPlayer, 15, SpigotIconBuilder.of(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND), PlayerSkillGUI.SLEIGHTOFHAND);
        setSlot(mcdndPlayer, 16, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.NIGHT_VISION).name(MenuText.STEALTH).build(), PlayerSkillGUI.STEALTH);
        setSlot(mcdndPlayer, 17, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.SURVIVAL), PlayerSkillGUI.SURVIVAL);
        setSlot(mcdndPlayer, 18, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.STRENGTH), PlayerSkillGUI.UNSKILLED_STR);
        setSlot(mcdndPlayer, 19, SpigotIconBuilder.of(Material.BOW, MenuText.DEXTERITY), PlayerSkillGUI.UNSKILLED_DEX);
        setSlot(mcdndPlayer, 20, SpigotIconBuilder.of(Material.GOLDEN_APPLE, MenuText.CONSTITUTION), PlayerSkillGUI.UNSKILLED_CON);
        setSlot(mcdndPlayer, 21, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.INTELLIGENCE), PlayerSkillGUI.UNSKILLED_INT);
        setSlot(mcdndPlayer, 22, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.WISDOM), PlayerSkillGUI.UNSKILLED_WIS);
        setSlot(mcdndPlayer, 23, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(MenuText.CHARISMA).build(), PlayerSkillGUI.UNSKILLED_CHA);
        setButton(26, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, @Nonnull ItemStack itemStack, @Nonnull PlayerSkillBridger bridger) {
        setButton(slot, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new PlayerSkillGUI(mcdndPlayer, bridger, p)));
    }
}
