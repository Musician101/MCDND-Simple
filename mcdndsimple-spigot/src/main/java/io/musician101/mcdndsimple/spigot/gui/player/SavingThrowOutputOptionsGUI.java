package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
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

public class SavingThrowOutputOptionsGUI extends SpigotMCDNDSimpleGUI {

    public SavingThrowOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.SAVING_THROW_OUTPUT_OPTIONS, 9);
        SavingThrowOutputOptions savingThrowOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getSavingThrowOutputOptions();
        updateSlot(0, SpigotIconBuilder.builder(Material.IRON_SWORD).name((savingThrowOutputOptions.isStrengthEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.STRENGTH).build(), () -> savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled()));
        updateSlot(1, SpigotIconBuilder.builder(Material.BOW).name((savingThrowOutputOptions.isDexterityEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.DEXTERITY).build(), () -> savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled()));
        updateSlot(2, SpigotIconBuilder.builder(Material.POTION).name((savingThrowOutputOptions.isConstitutionEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.CONSTITUTION).potionEffect(PotionType.INSTANT_HEAL).build(), () -> savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled()));
        updateSlot(3, SpigotIconBuilder.builder(Material.WRITABLE_BOOK).name((savingThrowOutputOptions.isIntelligenceEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.INTELLIGENCE).build(), () -> savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled()));
        updateSlot(4, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name((savingThrowOutputOptions.isWisdomEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.WISDOM).build(), () -> savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled()));
        updateSlot(5, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name((savingThrowOutputOptions.isCharismaEnabled() ? ChatColor.GREEN : ChatColor.RED) + MenuText.CHARISMA).build(), () -> savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled()));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
