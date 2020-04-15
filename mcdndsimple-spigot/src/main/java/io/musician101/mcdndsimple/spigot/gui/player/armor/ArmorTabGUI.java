package io.musician101.mcdndsimple.spigot.gui.player.armor;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.CharacterSheetGUI;
import io.musician101.mcdndsimple.spigot.gui.player.UnarmoredBonusGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ArmorTabGUI extends SpigotMCDNDSimpleGUI {

    public ArmorTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.ARMOR, 54);
        ArmorTab armorTab = mcdndPlayer.getCharacterSheet().getArmorTab();
        setButton(0, SpigotIconBuilder.of(Material.CHAINMAIL_CHESTPLATE, MenuText.armoredAC(armorTab)));
        setButton(1, SpigotIconBuilder.of(Material.LEATHER_CHESTPLATE, MenuText.unarmoredAC(armorTab)));
        setButton(2, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new ArmorListGUI(mcdndPlayer, p)));
        setButton(3, SpigotIconBuilder.builder(Material.IRON_CHESTPLATE).name(MenuText.UNARMORED_BONUS).description(MenuText.current(armorTab)).build(), ImmutableMap.of(ClickType.LEFT, p -> new UnarmoredBonusGUI(mcdndPlayer, p)));
        setButton(8, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
