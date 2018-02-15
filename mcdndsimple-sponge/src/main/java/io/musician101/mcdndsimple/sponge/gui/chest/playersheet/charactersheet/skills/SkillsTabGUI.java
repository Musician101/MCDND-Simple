package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class SkillsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final Dice skillBonus;
    private final SkillsTab skillsTab;

    public SkillsTabGUI(Player player, BioAndInfo bioAndInfo, Dice skillBonus, SkillsTab skillsTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SKILLS, 27, prevGUI);
        this.skillsTab = skillsTab;
        this.bioAndInfo = bioAndInfo;
        this.skillBonus = skillBonus;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.LEATHER_BOOTS, Text.of(MenuText.ACROBATICS)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAcrobatics(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.SADDLE, Text.of(MenuText.ANIMAL_HANDLING)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAnimalHandling(), this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.ARCANA)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getArcana(), this));
        set(3, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.ATHLETICS)), PotionEffectTypes.SPEED), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getAthletics(), this));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.EMERALD, Text.of(MenuText.DECEPTION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getDeception(), this));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.HISTORY)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getHistory(), this));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.ICE, Text.of(MenuText.INSIGHT)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getInsight(), this));
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.INTIMIDATION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getIntimidation(), this));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.INVESTIGATION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getInvestigation(), this));
        set(9, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.MEDICINE)), PotionEffectTypes.INSTANT_HEALTH), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getMedicine(), this));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.VINE, Text.of(MenuText.NATURE)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getNature(), this));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.CARROT, Text.of(MenuText.PERCEPTION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPerception(), this));
        set(12, ClickInventoryEvent.class, createItem(ItemTypes.NOTEBLOCK, Text.of(MenuText.PERFORMANCE)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPerformance(), this));
        set(13, ClickInventoryEvent.class, createItem(ItemTypes.ENDER_EYE, Text.of(MenuText.PERSUASION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getPersuasion(), this));
        set(14, ClickInventoryEvent.class, createItem(ItemTypes.NETHER_STAR, Text.of(MenuText.RELIGION)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getReligion(), this));
        set(15, ClickInventoryEvent.class, createItem(ItemTypes.TRIPWIRE_HOOK, Text.of(MenuText.SLEIGHT_OF_HAND)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getSleightOfHand(), this));
        set(16, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.STEALTH)), PotionEffectTypes.NIGHT_VISION), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getStealth(), this));
        set(16, ClickInventoryEvent.class, createItem(ItemTypes.SKULL, Text.of(MenuText.SURVIVAL)), player -> new SkillGUI(player, bioAndInfo, skillBonus, skillsTab.getSurvival(), this));
        setBackButton(26, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
