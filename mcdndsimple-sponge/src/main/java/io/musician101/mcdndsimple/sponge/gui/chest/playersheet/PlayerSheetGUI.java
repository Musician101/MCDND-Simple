package io.musician101.mcdndsimple.sponge.gui.chest.playersheet;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.CharacterSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class PlayerSheetGUI extends MCDNDSimpleChestGUI {

    private final PlayerSheet playerSheet;

    public PlayerSheetGUI(Player player, PlayerSheet playerSheet, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.PLAYER_SHEET, 9, prevGUI);
        this.playerSheet = playerSheet;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.BIO_AND_INFO)), player -> new BioAndInfoGUI(player, playerSheet.getBioAndInfo(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.CHARACTER_SHEET)), player -> new CharacterSheetGUI(player, playerSheet.getBioAndInfo(), playerSheet.getCharacterSheet(), this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
