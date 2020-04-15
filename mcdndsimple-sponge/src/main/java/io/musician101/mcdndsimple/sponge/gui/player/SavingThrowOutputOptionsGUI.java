package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SavingThrowOutputOptionsGUI extends SpongeMCDNDSimpleGUI {

    public SavingThrowOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.SAVING_THROW_OUTPUT_OPTIONS, 9);
        SavingThrowOutputOptions savingThrowOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getSavingThrowOutputOptions();
        updateSlot(0, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(Text.of(savingThrowOutputOptions.isStrengthEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.STRENGTH)).build(), () -> savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled()));
        updateSlot(1, SpongeIconBuilder.builder(ItemTypes.BOW).name(Text.of(savingThrowOutputOptions.isDexterityEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.DEXTERITY)).build(), () -> savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled()));
        updateSlot(2, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(savingThrowOutputOptions.isConstitutionEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.CONSTITUTION)).potionEffect(PotionEffectTypes.INSTANT_HEALTH).build(), () -> savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled()));
        updateSlot(3, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(Text.of(savingThrowOutputOptions.isIntelligenceEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.INTELLIGENCE)).build(), () -> savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled()));
        updateSlot(4, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(savingThrowOutputOptions.isWisdomEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.WISDOM)).build(), () -> savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled()));
        updateSlot(5, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(savingThrowOutputOptions.isCharismaEnabled() ? TextColors.GREEN : TextColors.RED,  MenuText.CHARISMA)).build(), () -> savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled()));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
