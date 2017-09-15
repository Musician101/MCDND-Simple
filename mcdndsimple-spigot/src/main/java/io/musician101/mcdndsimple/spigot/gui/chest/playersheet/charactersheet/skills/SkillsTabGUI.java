package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.tab.SkillsTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class SkillsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final Dice skillBonus;
    private final SkillsTab skillsTab;

    public SkillsTabGUI(Player player, BioAndInfo bioAndInfo, Dice skillBonus, SkillsTab skillsTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 27, MenuText.SKILLS, prevGUI);
        this.skillsTab = skillsTab;
        this.bioAndInfo = bioAndInfo;
        this.skillBonus = skillBonus;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.LEATHER_BOOTS, MenuText.ACROBATICS), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAcrobatics(), this));
        set(1, ClickType.LEFT, createItem(Material.SADDLE, MenuText.ANIMAL_HANDLING), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAnimalHandling(), this));
        set(2, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.ARCANA), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getArcana(), this));
        set(3, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.ATHLETICS), PotionType.SPEED), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAthletics(), this));
        set(4, ClickType.LEFT, createItem(Material.EMERALD, MenuText.DECEPTION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getDeception(), this));
        set(5, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.HISTORY), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getHistory(), this));
        set(6, ClickType.LEFT, createItem(Material.ICE, MenuText.INSIGHT), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getInsight(), this));
        set(7, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.INTIMIDATION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getIntimidation(), this));
        set(8, ClickType.LEFT, createItem(Material.ENCHANTMENT_TABLE, MenuText.INVESTIGATION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getInvestigation(), this));
        set(9, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.MEDICINE), PotionType.INSTANT_HEAL), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getMedicine(), this));
        set(10, ClickType.LEFT, createItem(Material.VINE, MenuText.NATURE), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getNature(), this));
        set(11, ClickType.LEFT, createItem(Material.CARROT_ITEM, MenuText.PERCEPTION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPerception(), this));
        set(12, ClickType.LEFT, createItem(Material.NOTE_BLOCK, MenuText.PERFORMANCE), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPerformance(), this));
        set(13, ClickType.LEFT, createItem(Material.EYE_OF_ENDER, MenuText.PERSUASION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPersuasion(), this));
        set(14, ClickType.LEFT, createItem(Material.NETHER_STAR, MenuText.RELIGION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getReligion(), this));
        set(15, ClickType.LEFT, createItem(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getSleightOfHand(), this));
        set(16, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.STEALTH), PotionType.NIGHT_VISION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getStealth(), this));
        set(16, ClickType.LEFT, createItem(Material.SKULL_ITEM, MenuText.SURVIVAL), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getSurvival(), this));
        setBackButton(26, ClickType.LEFT, Material.BARRIER);
    }
}
