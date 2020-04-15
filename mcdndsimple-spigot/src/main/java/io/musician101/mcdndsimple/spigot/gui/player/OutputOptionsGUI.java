package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.clazz.ClassActionGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class OutputOptionsGUI extends SpigotMCDNDSimpleGUI {

    public OutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.OUTPUT_OPTIONS, 9);
        setButton(0, SpigotIconBuilder.of(Material.IRON_INGOT, MenuText.SAVING_THROWS), ImmutableMap.of(ClickType.LEFT, p -> new SavingThrowOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(1, SpigotIconBuilder.of(Material.GOLD_INGOT, MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS), ImmutableMap.of(ClickType.LEFT, p -> new WeaponsSpellMiscOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(2, SpigotIconBuilder.of(Material.NETHER_STAR, MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS), ImmutableMap.of(ClickType.LEFT, p -> new CoreSkillsOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new ClassActionGUI(mcdndPlayer, index, p)));
    }
}
