package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class WeaponsSpellMiscOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions;

    public WeaponsSpellMiscOutputOptionsGUI(Player player, WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.WEAPONS_SPELL_MISC, prevGUI);
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }

    @Override
    protected void build() {
        ItemStack initiative = setPotionEffect(createItem(Material.POTION, MenuText.INITIATIVE), PotionType.SPEED);
        if (weaponsSpellMiscOutputOptions.isInitiativeEnabled()) {
            initiative = addGlow(initiative);
        }

        set(0, initiative, player -> {
            weaponsSpellMiscOutputOptions.setInitiativeEnabled(!weaponsSpellMiscOutputOptions.isInitiativeEnabled());
            player.closeInventory();
            open();
        });

        ItemStack hitDice = setPotionEffect(createItem(Material.POTION, MenuText.HIT_DICE), PotionType.REGEN);
        if (weaponsSpellMiscOutputOptions.isHitDiceEnabled()) {
            hitDice = addGlow(hitDice);
        }

        set(1, hitDice, player -> {
            weaponsSpellMiscOutputOptions.setHitDiceEnabled(!weaponsSpellMiscOutputOptions.isHitDiceEnabled());
            player.closeInventory();
            open();
        });

        ItemStack meleeWeapons = createItem(Material.IRON_SWORD, MenuText.MELEE_WEAPONS);
        if (weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled()) {
            meleeWeapons = addGlow(meleeWeapons);
        }

        set(2, meleeWeapons, player -> {
            weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(!weaponsSpellMiscOutputOptions.isMeleeWeaponsEnabled());
            player.closeInventory();
            open();
        });

        ItemStack rangedWeapons = createItem(Material.BOW, MenuText.RANGED_WEAPONS);
        if (weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled()) {
            rangedWeapons = addGlow(rangedWeapons);
        }

        set(3, rangedWeapons, player -> {
            weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(!weaponsSpellMiscOutputOptions.isRangedWeaponsEnabled());
            player.closeInventory();
            open();
        });

        ItemStack spellInfo = createItem(Material.BOW, MenuText.SPELL_INFO);
        if (weaponsSpellMiscOutputOptions.isSpellInfoEnabled()) {
            spellInfo = addGlow(spellInfo);
        }

        set(4, spellInfo, player -> {
            weaponsSpellMiscOutputOptions.setSpellInfoEnabled(!weaponsSpellMiscOutputOptions.isSpellInfoEnabled());
            player.closeInventory();
            open();
        });

        ItemStack spellCast = createItem(Material.BOW, MenuText.SPELL_CAST);
        if (weaponsSpellMiscOutputOptions.isSpellCastEnabled()) {
            spellCast = addGlow(spellCast);
        }

        set(5, spellCast, player -> {
            weaponsSpellMiscOutputOptions.setSpellCastEnabled(!weaponsSpellMiscOutputOptions.isSpellCastEnabled());
            player.closeInventory();
            open();
        });

        setBackButton(8, Material.BARRIER);
    }
}
