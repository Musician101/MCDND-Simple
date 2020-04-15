package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CoreSkillsOutputOptionsGUI extends SpongeMCDNDSimpleGUI {

    public CoreSkillsOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS, 27);
        CoreSkillsOutputOptions coreSkillsOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getCoreSkillsOutputOptions();
        updateSlot(0, SpongeIconBuilder.of(ItemTypes.LEATHER_BOOTS, Text.of(coreSkillsOutputOptions.isAcrobaticsEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.ACROBATICS)), () -> coreSkillsOutputOptions.setAcrobaticsEnabled(!coreSkillsOutputOptions.isAcrobaticsEnabled()));
        updateSlot(1, SpongeIconBuilder.of(ItemTypes.SADDLE, Text.of(coreSkillsOutputOptions.isAnimalHandlingEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.ANIMAL_HANDLING)), () -> coreSkillsOutputOptions.setAnimalHandlingEnabled(!coreSkillsOutputOptions.isAnimalHandlingEnabled()));
        updateSlot(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(coreSkillsOutputOptions.isArcanaEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.ARCANA)), () -> coreSkillsOutputOptions.setArcanaEnabled(!coreSkillsOutputOptions.isArcanaEnabled()));
        updateSlot(3, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(coreSkillsOutputOptions.isAthleticsEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.ATHLETICS)).potionEffect(PotionEffectTypes.SPEED).build(), () -> coreSkillsOutputOptions.setAthleticsEnabled(!coreSkillsOutputOptions.isAthleticsEnabled()));
        updateSlot(4, SpongeIconBuilder.of(ItemTypes.EMERALD, Text.of(coreSkillsOutputOptions.isDeceptionEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.DECEPTION)), () -> coreSkillsOutputOptions.setDeceptionEnabled(!coreSkillsOutputOptions.isDeceptionEnabled()));
        updateSlot(5, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(coreSkillsOutputOptions.isHistoryEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.HISTORY)), () -> coreSkillsOutputOptions.setHistoryEnabled(!coreSkillsOutputOptions.isHistoryEnabled()));
        updateSlot(6, SpongeIconBuilder.of(ItemTypes.ICE, Text.of(coreSkillsOutputOptions.isInsightEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.INSIGHT)), () -> coreSkillsOutputOptions.setInsightEnabled(!coreSkillsOutputOptions.isInsightEnabled()));
        updateSlot(7, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(coreSkillsOutputOptions.isIntimidationEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.INTIMIDATION)), () -> coreSkillsOutputOptions.setIntimidationEnabled(!coreSkillsOutputOptions.isIntimidationEnabled()));
        updateSlot(8, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(coreSkillsOutputOptions.isInvestigationEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.INVESTIGATION)), () -> coreSkillsOutputOptions.setInvestigationEnabled(!coreSkillsOutputOptions.isInvestigationEnabled()));
        updateSlot(9, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(coreSkillsOutputOptions.isMedicineEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.MEDICINE)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), () -> coreSkillsOutputOptions.setMedicineEnabled(!coreSkillsOutputOptions.isMedicineEnabled()));
        updateSlot(10, SpongeIconBuilder.of(ItemTypes.VINE, Text.of(coreSkillsOutputOptions.isNatureEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.NATURE)), () -> coreSkillsOutputOptions.setNatureEnabled(!coreSkillsOutputOptions.isNatureEnabled()));
        updateSlot(11, SpongeIconBuilder.of(ItemTypes.CARROT, Text.of(coreSkillsOutputOptions.isPerceptionEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.PERCEPTION)), () -> coreSkillsOutputOptions.setPerceptionEnabled(!coreSkillsOutputOptions.isPerceptionEnabled()));
        updateSlot(12, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, Text.of(coreSkillsOutputOptions.isPerformanceEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.PERFORMANCE)), () -> coreSkillsOutputOptions.setPerformanceEnabled(!coreSkillsOutputOptions.isPerformanceEnabled()));
        updateSlot(13, SpongeIconBuilder.of(ItemTypes.ENDER_EYE, Text.of(coreSkillsOutputOptions.isPersuasionEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.PERSUASION)), () -> coreSkillsOutputOptions.setPersuasionEnabled(!coreSkillsOutputOptions.isPersuasionEnabled()));
        updateSlot(14, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, Text.of(coreSkillsOutputOptions.isReligionEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.RELIGION)), () -> coreSkillsOutputOptions.setReligionEnabled(!coreSkillsOutputOptions.isReligionEnabled()));
        updateSlot(15, SpongeIconBuilder.of(ItemTypes.TRIPWIRE_HOOK, Text.of(coreSkillsOutputOptions.isSleightOfHandEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.SLEIGHT_OF_HAND)), () -> coreSkillsOutputOptions.setSleightOfHandEnabled(!coreSkillsOutputOptions.isSleightOfHandEnabled()));
        updateSlot(16, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(coreSkillsOutputOptions.isStealthEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.STEALTH)).potionEffect(PotionEffectTypes.NIGHT_VISION).build(), () -> coreSkillsOutputOptions.setStealthEnabled(!coreSkillsOutputOptions.isStealthEnabled()));
        updateSlot(17, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(coreSkillsOutputOptions.isSurvivalEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.SURVIVAL)), () -> coreSkillsOutputOptions.setSurvivalEnabled(!coreSkillsOutputOptions.isSurvivalEnabled()));
        setButton(22, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
