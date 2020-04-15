package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
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

public class WeaponsSpellMiscOutputOptionsGUI extends SpongeMCDNDSimpleGUI {

    public WeaponsSpellMiscOutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, 9);
        WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getOutputOptions().getWeaponsSpellMiscOutputOptions();
        updateSlot(0, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.INITIATIVE)).potionEffect(PotionEffectTypes.SPEED).addGlow(weaponsSpellMiscOutputOptions.isInitiativeEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setInitiativeEnabled(!weaponsSpellMiscOutputOptions.isInitiativeEnabled()));
        updateSlot(1, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.HIT_DICE)).potionEffect(PotionEffectTypes.REGENERATION).addGlow(weaponsSpellMiscOutputOptions.isHitDiceEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setHitDiceEnabled(!weaponsSpellMiscOutputOptions.isHitDiceEnabled()));
        updateSlot(2, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(Text.of(MenuText.MELEE_WEAPONS)).addGlow(weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()));
        updateSlot(3, SpongeIconBuilder.builder(ItemTypes.BOW).name(Text.of(MenuText.RANGED_WEAPONS)).addGlow(weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()));
        updateSlot(4, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(MenuText.SPELL_INFO)).addGlow(weaponsSpellMiscOutputOptions.isSpellInfoEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!weaponsSpellMiscOutputOptions.isSpellInfoEnabled()));
        updateSlot(5, SpongeIconBuilder.builder(ItemTypes.ENCHANTING_TABLE).name(Text.of(MenuText.SPELL_CAST)).addGlow(weaponsSpellMiscOutputOptions.isSpellCastEnabled()).build(), () -> weaponsSpellMiscOutputOptions.setSpellCastEnabled(!weaponsSpellMiscOutputOptions.isSpellCastEnabled()));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
    }

    private void updateSlot(int slot, ItemStack itemStack, Runnable runnable) {
        setButton(slot, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            runnable.run();
            updateSlot(slot, itemStack, runnable);
        }));
    }
}
