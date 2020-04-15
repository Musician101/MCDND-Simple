package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class WeaponsTabGUI extends SpigotMCDNDSimpleGUI {

    public WeaponsTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.WEAPONS, 9);
        setButton(0, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.MELEE_WEAPONS), ImmutableMap.of(ClickType.LEFT, p -> new MeleeWeaponsGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.BOW, MenuText.RANGED_WEAPONS), ImmutableMap.of(ClickType.LEFT, p -> new RangedWeaponsGUI(mcdndPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
