package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class WeaponsSpellMiscOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions;

    public WeaponsSpellMiscOutputOptionsGUI(Player player, WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, 9, prevGUI);
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }

    @Override
    protected void build() {
        boolean init = weaponsSpellMiscOutputOptions.isInitiativeEnabled();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.INITIATIVE)), PotionEffectTypes.SPEED), () -> init), player -> {
            weaponsSpellMiscOutputOptions.setInitiativeEnabled(!init);
            open();
        });

        boolean hitDice = weaponsSpellMiscOutputOptions.isHitDiceEnabled();
        set(1, ClickInventoryEvent.class, addGlowIfConditionsMet(setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.HIT_DICE)), PotionEffectTypes.REGENERATION), () -> hitDice), player -> {
            weaponsSpellMiscOutputOptions.setHitDiceEnabled(!hitDice);
            open();
        });

        boolean melee = weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled();
        set(2, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.IRON_SWORD, Text.of(MenuText.MELEE_WEAPONS)), () -> melee), player -> {
            weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!melee);
            open();
        });

        boolean ranged = weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled();
        set(3, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BOW, Text.of(MenuText.RANGED_WEAPONS)), () -> ranged), player -> {
            weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!ranged);
            open();
        });

        boolean spellInfo = weaponsSpellMiscOutputOptions.isSpellInfoEnabled();
        set(4, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BOW, Text.of(MenuText.SPELL_INFO)), () -> spellInfo), player -> {
            weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!spellInfo);
            open();
        });

        boolean spellCast = weaponsSpellMiscOutputOptions.isSpellCastEnabled();
        set(5, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BOW, Text.of(MenuText.SPELL_CAST)), () -> spellCast), player -> {
            weaponsSpellMiscOutputOptions.setSpellCastEnabled(!spellCast);
            open();
        });

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
