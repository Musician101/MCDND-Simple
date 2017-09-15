package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class CoreSkillsOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final CoreSkillsOutputOptions coreSkillsOutputOptions;

    public CoreSkillsOutputOptionsGUI(Player player, CoreSkillsOutputOptions coreSkillsOutputOptions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 27, MenuText.CORE_SKILLS, prevGUI);
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    @Override
    protected void build() {
        //TODO need to go through and use the addGlowIfConditionsMet
        ItemStack acrobatics = createItem(Material.LEATHER_BOOTS, MenuText.ACROBATICS);
        if (coreSkillsOutputOptions.isAcrobaticsEnabled()) {
            acrobatics = addGlow(acrobatics);
        }

        set(0, ClickType.LEFT, acrobatics, player -> {
            coreSkillsOutputOptions.setAcrobaticsEnabled(!coreSkillsOutputOptions.isAcrobaticsEnabled());
            open();
        });

        ItemStack animalHandling = createItem(Material.SADDLE, MenuText.ANIMAL_HANDLING);
        if (coreSkillsOutputOptions.isAnimalHandlingEnabled()) {
            animalHandling = addGlow(animalHandling);
        }

        set(1, ClickType.LEFT, animalHandling, player -> {
            coreSkillsOutputOptions.setAnimalHandlingEnabled(!coreSkillsOutputOptions.isAnimalHandlingEnabled());
            open();
        });

        ItemStack arcana = setPotionEffect(createItem(Material.POTION, MenuText.ATHLETICS), PotionType.SPEED);
        if (coreSkillsOutputOptions.isArcanaEnabled()) {
            arcana = addGlow(arcana);
        }

        set(2, ClickType.LEFT, arcana, player -> {
            coreSkillsOutputOptions.setArcanaEnabled(!coreSkillsOutputOptions.isArcanaEnabled());
            open();
        });

        ItemStack athletics = createItem(Material.EMERALD, MenuText.ATHLETICS);
        if (coreSkillsOutputOptions.isAthleticsEnabled()) {
            athletics = addGlow(athletics);
        }

        set(3, ClickType.LEFT, athletics, player -> {
            coreSkillsOutputOptions.setAthleticsEnabled(!coreSkillsOutputOptions.isAthleticsEnabled());
            open();
        });

        ItemStack deception = createItem(Material.EMERALD, MenuText.DECEPTION);
        if (coreSkillsOutputOptions.isDeceptionEnabled()) {
            deception = addGlow(deception);
        }

        set(4, ClickType.LEFT, deception, player -> {
            coreSkillsOutputOptions.setDeceptionEnabled(!coreSkillsOutputOptions.isDeceptionEnabled());
            open();
        });

        ItemStack history = createItem(Material.BOOK_AND_QUILL, MenuText.HISTORY);
        if (coreSkillsOutputOptions.isHistoryEnabled()) {
            history = addGlow(history);
        }

        set(5, ClickType.LEFT, history, player -> {
            coreSkillsOutputOptions.setHistoryEnabled(!coreSkillsOutputOptions.isHistoryEnabled());
            open();
        });

        ItemStack insight = createItem(Material.ICE, MenuText.INSIGHT);
        if (coreSkillsOutputOptions.isInsightEnabled()) {
            insight = addGlow(insight);
        }

        set(6, ClickType.LEFT, insight, player -> {
            coreSkillsOutputOptions.setInsightEnabled(!coreSkillsOutputOptions.isInsightEnabled());
            open();
        });

        ItemStack intimidation = createItem(Material.DIAMOND_SWORD, MenuText.INTIMIDATION);
        if (coreSkillsOutputOptions.isIntimidationEnabled()) {
            intimidation = addGlow(intimidation);
        }

        set(7, ClickType.LEFT, intimidation, player -> {
            coreSkillsOutputOptions.setIntimidationEnabled(!coreSkillsOutputOptions.isIntimidationEnabled());
            open();
        });

        ItemStack investigation = createItem(Material.ENCHANTMENT_TABLE, MenuText.INVESTIGATION);
        if (coreSkillsOutputOptions.isInvestigationEnabled()) {
            investigation = addGlow(investigation);
        }

        set(8, ClickType.LEFT, investigation, player -> {
            coreSkillsOutputOptions.setInvestigationEnabled(!coreSkillsOutputOptions.isInvestigationEnabled());
            open();
        });

        ItemStack medicine = setPotionEffect(createItem(Material.POTION, MenuText.MEDICINE), PotionType.INSTANT_HEAL);
        if (coreSkillsOutputOptions.isMedicineEnabled()) {
            medicine = addGlow(medicine);
        }

        set(9, ClickType.LEFT, medicine, player -> {
            coreSkillsOutputOptions.setMedicineEnabled(!coreSkillsOutputOptions.isMedicineEnabled());
            open();
        });

        ItemStack nature = createItem(Material.VINE, MenuText.NATURE);
        if (coreSkillsOutputOptions.isNatureEnabled()) {
            nature = addGlow(nature);
        }

        set(10, ClickType.LEFT, nature, player -> {
            coreSkillsOutputOptions.setNatureEnabled(!coreSkillsOutputOptions.isNatureEnabled());
            open();
        });

        ItemStack perception = createItem(Material.CARROT_ITEM, MenuText.PERCEPTION);
        if (coreSkillsOutputOptions.isPerceptionEnabled()) {
            perception = addGlow(perception);
        }

        set(11, ClickType.LEFT, perception, player -> {
            coreSkillsOutputOptions.setPerceptionEnabled(!coreSkillsOutputOptions.isPerceptionEnabled());
            open();
        });

        ItemStack performance = createItem(Material.NOTE_BLOCK, MenuText.PERFORMANCE);
        if (coreSkillsOutputOptions.isPerformanceEnabled()) {
            performance = addGlow(performance);
        }

        set(12, ClickType.LEFT, performance, player -> {
            coreSkillsOutputOptions.setPerformanceEnabled(!coreSkillsOutputOptions.isPerformanceEnabled());
            open();
        });

        ItemStack persuasion = createItem(Material.EYE_OF_ENDER, MenuText.PERSUASION);
        if (coreSkillsOutputOptions.isPersuasionEnabled()) {
            persuasion = addGlow(persuasion);
        }

        set(13, ClickType.LEFT, persuasion, player -> {
            coreSkillsOutputOptions.setPersuasionEnabled(!coreSkillsOutputOptions.isPersuasionEnabled());
            open();
        });

        ItemStack religion = createItem(Material.NETHER_STAR, MenuText.RELIGION);
        if (coreSkillsOutputOptions.isReligionEnabled()) {
            religion = addGlow(religion);
        }

        set(14, ClickType.LEFT, religion, player -> {
            coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled());
            open();
        });

        ItemStack sleightOfHand = createItem(Material.TRIPWIRE_HOOK, MenuText.SLEIGHT_OF_HAND);
        if (coreSkillsOutputOptions.isSleightOfHandEnabled()) {
            sleightOfHand = addGlow(sleightOfHand);
        }

        set(15, ClickType.LEFT, sleightOfHand, player -> {
            coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled());
            open();
        });

        ItemStack stealth = setPotionEffect(createItem(Material.POTION, MenuText.STEALTH), PotionType.NIGHT_VISION);
        if (coreSkillsOutputOptions.isStealthEnabled()) {
            stealth = addGlow(stealth);
        }

        set(16, ClickType.LEFT, stealth, player -> {
            coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled());
            open();
        });

        ItemStack survival = createItem(Material.SKULL_ITEM, MenuText.SURVIVAL);
        if (coreSkillsOutputOptions.isSurvivalEnabled()) {
            survival = addGlow(survival);
        }

        set(17, ClickType.LEFT, survival, player -> {
            coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled());
            open();
        });

        setBackButton(22, ClickType.LEFT, Material.BARRIER);
    }
}
