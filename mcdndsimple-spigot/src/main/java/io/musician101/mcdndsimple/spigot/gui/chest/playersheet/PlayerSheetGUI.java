package io.musician101.mcdndsimple.spigot.gui.chest.playersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.CharacterSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerSheetGUI extends MCDNDSimpleChestGUI {

    private final PlayerSheet playerSheet;

    public PlayerSheetGUI(Player player, PlayerSheet playerSheet, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.PLAYER_SHEET, prevGUI);
        this.playerSheet = playerSheet;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.BOOK, MenuText.BIO_AND_INFO), player -> new BioAndInfoGUI(player, playerSheet.getBioAndInfo(), this));
        set(1, createItem(Material.DIAMOND_SWORD, MenuText.CHARACTER_SHEET), player -> new CharacterSheetGUI(player, playerSheet.getBioAndInfo(), playerSheet.getCharacterSheet(), this));
        setBackButton(8, Material.BARRIER);
    }
}
