package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SavingThrowOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final SavingThrowOutputOptions savingThrowOutputOptions;

    public SavingThrowOutputOptionsGUI(Player player, SavingThrowOutputOptions savingThrowOutputOptions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.SAVING_THROWS, prevGUI);
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    @Override
    protected void build() {
        ItemStack strength = createItem(Material.IRON_SWORD, MenuText.STRENGTH);
        if (savingThrowOutputOptions.isStrengthEnabled()) {
            strength = addGlow(strength);
        }

        set(0, strength, player -> {
            savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled());
            player.closeInventory();
            open();
        });
        
        ItemStack dexterity = createItem(Material.BOW, MenuText.DEXTERITY);
        if (savingThrowOutputOptions.isDexterityEnabled()) {
            dexterity = addGlow(dexterity);
        }

        set(1, dexterity, player -> {
            savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled());
            player.closeInventory();
            open();
        });

        ItemStack constitution = createItem(Material.POTION, MenuText.CONSTITUTION);
        if (savingThrowOutputOptions.isConstitutionEnabled()) {
            constitution = setDurability(constitution, 1);
        }

        set(2, constitution, player -> {
            savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled());
            player.closeInventory();
            open();
        });

        ItemStack intelligence = createItem(Material.BOOK_AND_QUILL, MenuText.INTELLIGENCE);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            intelligence = addGlow(intelligence);
        }

        set(3, intelligence, player -> {
            savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled());
            player.closeInventory();
            open();
        });

        ItemStack wisdom = createItem(Material.ENCHANTED_BOOK, MenuText.INTELLIGENCE);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            wisdom = addGlow(intelligence);
        }

        set(4, wisdom, player -> {
            savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled());
            player.closeInventory();
            open();
        });

        ItemStack charisma = setDurability(createItem(Material.SKULL_ITEM, MenuText.CHARISMA), 3);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            charisma = addGlow(intelligence);
        }

        set(5, charisma, player -> {
            savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled());
            player.closeInventory();
            open();
        });

        ItemStack death = createItem(Material.SKULL_ITEM, MenuText.DEATH);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            death = addGlow(intelligence);
        }
        
        set(6, death, player -> {
            savingThrowOutputOptions.setDeathEnabled(!savingThrowOutputOptions.isDeathEnabled());
            player.closeInventory();
            open();
        });

        setBackButton(8, Material.BARRIER);
    }
}
