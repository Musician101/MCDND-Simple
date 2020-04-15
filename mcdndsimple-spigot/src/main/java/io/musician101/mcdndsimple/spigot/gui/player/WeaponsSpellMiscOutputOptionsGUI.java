package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class WeaponsSpellMiscOutputOptionsGUI extends SpigotMCDNDSimpleGUI {

    public WeaponsSpellMiscOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, 9);
        WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getWeaponsSpellMiscOutputOptions();
        updateSlot(0, SpigotIconBuilder.builder(Material.POTION).name(MenuText.INITIATIVE).potionEffect(PotionType.SPEED).addGlow(weaponsSpellMiscOutputOptions.isInitiativeEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setInitiativeEnabled(!weaponsSpellMiscOutputOptions.isInitiativeEnabled()));
        updateSlot(1, SpigotIconBuilder.builder(Material.POTION).name(MenuText.HIT_DICE).potionEffect(PotionType.REGEN).addGlow(weaponsSpellMiscOutputOptions.isHitDiceEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setHitDiceEnabled(!weaponsSpellMiscOutputOptions.isHitDiceEnabled()));
        updateSlot(2, SpigotIconBuilder.builder(Material.IRON_SWORD).name(MenuText.MELEE_WEAPONS).addGlow(weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()));
        updateSlot(3, SpigotIconBuilder.builder(Material.BOW).name(MenuText.RANGED_WEAPONS).addGlow(weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()));
        updateSlot(4, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(MenuText.SPELL_INFO).addGlow(weaponsSpellMiscOutputOptions.isSpellInfoEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!weaponsSpellMiscOutputOptions.isSpellInfoEnabled()));
        updateSlot(5, SpigotIconBuilder.builder(Material.ENCHANTING_TABLE).name(MenuText.SPELL_CAST).addGlow(weaponsSpellMiscOutputOptions.isSpellCastEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setSpellCastEnabled(!weaponsSpellMiscOutputOptions.isSpellCastEnabled()));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
