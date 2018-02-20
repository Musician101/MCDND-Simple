package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class CoreSkillsOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final CoreSkillsOutputOptions coreSkillsOutputOptions;

    public CoreSkillsOutputOptionsGUI(Player player, CoreSkillsOutputOptions coreSkillsOutputOptions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS, 27, prevGUI);
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    @Override
    protected void build() {
        boolean acrobaticsEnabled = coreSkillsOutputOptions.isAcrobaticsEnabled();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.LEATHER_BOOTS, Text.of(MenuText.ACROBATICS)), () -> acrobaticsEnabled), player -> {
            coreSkillsOutputOptions.setAcrobaticsEnabled(!acrobaticsEnabled);
            open();
        });

        boolean animalHandlingEnabled = coreSkillsOutputOptions.isAnimalHandlingEnabled();
        set(1, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.LEATHER_BOOTS, Text.of(MenuText.ACROBATICS)), () -> animalHandlingEnabled), player -> {
            coreSkillsOutputOptions.setAnimalHandlingEnabled(!animalHandlingEnabled);
            open();
        });

        boolean arcanaEnabled = coreSkillsOutputOptions.isArcanaEnabled();
        set(2, ClickInventoryEvent.class, addGlowIfConditionsMet(setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.ATHLETICS)), PotionEffectTypes.SPEED), () -> arcanaEnabled), player -> {
            coreSkillsOutputOptions.setArcanaEnabled(!arcanaEnabled);
            open();
        });

        boolean athleticsEnabled = coreSkillsOutputOptions.isAthleticsEnabled();
        set(3, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.EMERALD, Text.of(MenuText.ATHLETICS)), () -> athleticsEnabled), player -> {
            coreSkillsOutputOptions.setAthleticsEnabled(!athleticsEnabled);
            open();
        });

        boolean deceptionEnabled = coreSkillsOutputOptions.isDeceptionEnabled();
        set(4, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.EMERALD, Text.of(MenuText.DECEPTION)), () -> deceptionEnabled), player -> {
            coreSkillsOutputOptions.setDeceptionEnabled(!deceptionEnabled);
            open();
        });

        boolean historyEnabled = coreSkillsOutputOptions.isHistoryEnabled();
        set(5, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.HISTORY)), () -> historyEnabled), player -> {
            coreSkillsOutputOptions.setHistoryEnabled(!historyEnabled);
            open();
        });

        boolean insightEnabled = coreSkillsOutputOptions.isInsightEnabled();
        set(6, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.ICE, Text.of(MenuText.INSIGHT)), () -> insightEnabled), player -> {
            coreSkillsOutputOptions.setInsightEnabled(!insightEnabled);
            open();
        });

        boolean intimidationEnabled = coreSkillsOutputOptions.isIntimidationEnabled();
        set(7, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.INTIMIDATION)), () -> intimidationEnabled), player -> {
            coreSkillsOutputOptions.setIntimidationEnabled(!intimidationEnabled);
            open();
        });

        boolean investigationEnabled = coreSkillsOutputOptions.isInvestigationEnabled();
        set(8, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.INVESTIGATION)), () -> investigationEnabled), player -> {
            coreSkillsOutputOptions.setInvestigationEnabled(!investigationEnabled);
            open();
        });

        boolean medicineEnabled = coreSkillsOutputOptions.isMedicineEnabled();
        set(9, ClickInventoryEvent.class, addGlowIfConditionsMet(setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.MEDICINE)), PotionEffectTypes.INSTANT_HEALTH), () -> medicineEnabled), player -> {
            coreSkillsOutputOptions.setMedicineEnabled(!medicineEnabled);
            open();
        });

        boolean natureEnabled = coreSkillsOutputOptions.isNatureEnabled();
        set(10, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.VINE, Text.of(MenuText.NATURE)), () -> natureEnabled), player -> {
            coreSkillsOutputOptions.setNatureEnabled(!natureEnabled);
            open();
        });

        boolean perceptionEnabled = coreSkillsOutputOptions.isPerceptionEnabled();
        set(11, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.CARROT, Text.of(MenuText.PERCEPTION)), () -> perceptionEnabled), player -> {
            coreSkillsOutputOptions.setPerceptionEnabled(!perceptionEnabled);
            open();
        });

        boolean performanceEnabled = coreSkillsOutputOptions.isPerformanceEnabled();
        set(12, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.NOTEBLOCK, Text.of(MenuText.PERFORMANCE)), () -> performanceEnabled), player -> {
            coreSkillsOutputOptions.setPerformanceEnabled(!perceptionEnabled);
            open();
        });

        boolean persuasionEnabled = coreSkillsOutputOptions.isPersuasionEnabled();
        set(13, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.ENDER_EYE, Text.of(MenuText.PERSUASION)), () -> persuasionEnabled), player -> {
            coreSkillsOutputOptions.setPersuasionEnabled(!perceptionEnabled);
            open();
        });

        boolean religionEnabled = coreSkillsOutputOptions.isReligionEnabled();
        set(14, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.NETHER_STAR, Text.of(MenuText.RELIGION)), () -> religionEnabled), player -> {
            coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled());
            open();
        });

        boolean sleightOfHandEnabled = coreSkillsOutputOptions.isSleightOfHandEnabled();
        set(15, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.TRIPWIRE_HOOK, Text.of(MenuText.SLEIGHT_OF_HAND)), () -> sleightOfHandEnabled), player -> {
            coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled());
            open();
        });

        boolean stealthEnabled = coreSkillsOutputOptions.isStealthEnabled();
        set(16, ClickInventoryEvent.class, addGlowIfConditionsMet(setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.STEALTH)), PotionEffectTypes.NIGHT_VISION), () -> stealthEnabled), player -> {
            coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled());
            open();
        });

        boolean survivalEnabled = coreSkillsOutputOptions.isSurvivalEnabled();
        set(17, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.SKULL, Text.of(MenuText.SURVIVAL)), () -> survivalEnabled), player -> {
            coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled());
            open();
        });

        setBackButton(22, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
