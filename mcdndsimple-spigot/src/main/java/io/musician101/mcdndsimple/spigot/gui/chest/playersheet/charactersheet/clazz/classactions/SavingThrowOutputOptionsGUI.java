package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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

        set(0, ClickType.LEFT, strength, player -> {
            savingThrowOutputOptions.setStrengthEnabled(!savingThrowOutputOptions.isStrengthEnabled());
            open();
        });

        ItemStack dexterity = createItem(Material.BOW, MenuText.DEXTERITY);
        if (savingThrowOutputOptions.isDexterityEnabled()) {
            dexterity = addGlow(dexterity);
        }

        set(1, ClickType.LEFT, dexterity, player -> {
            savingThrowOutputOptions.setDexterityEnabled(!savingThrowOutputOptions.isDexterityEnabled());
            open();
        });

        ItemStack constitution = createItem(Material.POTION, MenuText.CONSTITUTION);
        if (savingThrowOutputOptions.isConstitutionEnabled()) {
            constitution = setDurability(constitution, 1);
        }

        set(2, ClickType.LEFT, constitution, player -> {
            savingThrowOutputOptions.setConstitutionEnabled(!savingThrowOutputOptions.isConstitutionEnabled());
            open();
        });

        ItemStack intelligence = createItem(Material.BOOK_AND_QUILL, MenuText.INTELLIGENCE);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            intelligence = addGlow(intelligence);
        }

        set(3, ClickType.LEFT, intelligence, player -> {
            savingThrowOutputOptions.setIntelligenceEnabled(!savingThrowOutputOptions.isIntelligenceEnabled());
            open();
        });

        ItemStack wisdom = createItem(Material.ENCHANTED_BOOK, MenuText.INTELLIGENCE);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            wisdom = addGlow(intelligence);
        }

        set(4, ClickType.LEFT, wisdom, player -> {
            savingThrowOutputOptions.setWisdomEnabled(!savingThrowOutputOptions.isWisdomEnabled());
            open();
        });

        ItemStack charisma = setDurability(createItem(Material.SKULL_ITEM, MenuText.CHARISMA), 3);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            charisma = addGlow(intelligence);
        }

        set(5, ClickType.LEFT, charisma, player -> {
            savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled());
            open();
        });

        ItemStack death = createItem(Material.SKULL_ITEM, MenuText.DEATH);
        if (savingThrowOutputOptions.isIntelligenceEnabled()) {
            death = addGlow(intelligence);
        }

        set(6, ClickType.LEFT, death, player -> {
            savingThrowOutputOptions.setDeathEnabled(!savingThrowOutputOptions.isDeathEnabled());
            open();
        });

        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
