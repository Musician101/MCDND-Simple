package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class CoreSkillsOutputOptionsGUI extends SpigotMCDNDSimpleGUI {

    public CoreSkillsOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS, 27);
        CoreSkillsOutputOptions coreSkillsOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getCoreSkillsOutputOptions();
        updateSlot(0, SpigotIconBuilder.of(Material.LEATHER_BOOTS, (coreSkillsOutputOptions.isAcrobaticsEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.ACROBATICS), () -> coreSkillsOutputOptions.setAcrobaticsEnabled(!coreSkillsOutputOptions.isAcrobaticsEnabled()));
        updateSlot(1, SpigotIconBuilder.of(Material.SADDLE, (coreSkillsOutputOptions.isAnimalHandlingEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.ANIMAL_HANDLING), () -> coreSkillsOutputOptions.setAnimalHandlingEnabled(!coreSkillsOutputOptions.isAnimalHandlingEnabled()));
        updateSlot(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, (coreSkillsOutputOptions.isArcanaEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.ARCANA), () -> coreSkillsOutputOptions.setArcanaEnabled(!coreSkillsOutputOptions.isArcanaEnabled()));
        updateSlot(3, SpigotIconBuilder.builder(Material.POTION).name((coreSkillsOutputOptions.isAthleticsEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.ATHLETICS).potionEffect(PotionType.SPEED).build(), () -> coreSkillsOutputOptions.setAthleticsEnabled(!coreSkillsOutputOptions.isAthleticsEnabled()));
        updateSlot(4, SpigotIconBuilder.of(Material.EMERALD, (coreSkillsOutputOptions.isDeceptionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.DECEPTION), () -> coreSkillsOutputOptions.setDeceptionEnabled(!coreSkillsOutputOptions.isDeceptionEnabled()));
        updateSlot(5, SpigotIconBuilder.of(Material.WRITABLE_BOOK, (coreSkillsOutputOptions.isHistoryEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.HISTORY), () -> coreSkillsOutputOptions.setHistoryEnabled(!coreSkillsOutputOptions.isHistoryEnabled()));
        updateSlot(6, SpigotIconBuilder.of(Material.ICE, (coreSkillsOutputOptions.isInsightEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.INSIGHT), () -> coreSkillsOutputOptions.setInsightEnabled(!coreSkillsOutputOptions.isInsightEnabled()));
        updateSlot(7, SpigotIconBuilder.of(Material.DIAMOND_SWORD, (coreSkillsOutputOptions.isIntimidationEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.INTIMIDATION), () -> coreSkillsOutputOptions.setIntimidationEnabled(!coreSkillsOutputOptions.isIntimidationEnabled()));
        updateSlot(8, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, (coreSkillsOutputOptions.isInvestigationEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.INVESTIGATION), () -> coreSkillsOutputOptions.setInvestigationEnabled(!coreSkillsOutputOptions.isInvestigationEnabled()));
        updateSlot(9, SpigotIconBuilder.builder(Material.POTION).name((coreSkillsOutputOptions.isMedicineEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.MEDICINE).potionEffect(PotionType.INSTANT_HEAL).build(), () -> coreSkillsOutputOptions.setMedicineEnabled(!coreSkillsOutputOptions.isMedicineEnabled()));
        updateSlot(10, SpigotIconBuilder.of(Material.VINE, (coreSkillsOutputOptions.isNatureEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.NATURE), () -> coreSkillsOutputOptions.setNatureEnabled(!coreSkillsOutputOptions.isNatureEnabled()));
        updateSlot(11, SpigotIconBuilder.of(Material.CARROT, (coreSkillsOutputOptions.isPerceptionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.PERCEPTION), () -> coreSkillsOutputOptions.setPerceptionEnabled(!coreSkillsOutputOptions.isPerceptionEnabled()));
        updateSlot(12, SpigotIconBuilder.of(Material.NOTE_BLOCK, (coreSkillsOutputOptions.isPerformanceEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.PERFORMANCE), () -> coreSkillsOutputOptions.setPerformanceEnabled(!coreSkillsOutputOptions.isPerformanceEnabled()));
        updateSlot(13, SpigotIconBuilder.of(Material.ENDER_EYE, (coreSkillsOutputOptions.isPersuasionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.PERSUASION), () -> coreSkillsOutputOptions.setPersuasionEnabled(!coreSkillsOutputOptions.isPersuasionEnabled()));
        updateSlot(14, SpigotIconBuilder.of(Material.NETHER_STAR, (coreSkillsOutputOptions.isReligionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.RELIGION), () -> coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled()));
        updateSlot(15, SpigotIconBuilder.of(Material.TRIPWIRE_HOOK, (coreSkillsOutputOptions.isSleightOfHandEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.SLEIGHT_OF_HAND), () -> coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled()));
        updateSlot(16, SpigotIconBuilder.builder(Material.POTION).name((coreSkillsOutputOptions.isStealthEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.STEALTH).potionEffect(PotionType.NIGHT_VISION).build(), () -> coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled()));
        updateSlot(17, SpigotIconBuilder.of(Material.PLAYER_HEAD, (coreSkillsOutputOptions.isSurvivalEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.SURVIVAL), () -> coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled()));
        setButton(22, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
