package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ExperienceGUI extends SpigotMCDNDSimpleGUI {

    public ExperienceGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.LEVEL_AND_XP, 9);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        setButton(0, SpigotIconBuilder.of(Material.BOOK, MenuText.overallLevel(experience, classLevels)));
        setButton(1, SpigotIconBuilder.of(Material.ANVIL, MenuText.proficiencyBonus(experience, classLevels)));
        setButton(2, SpigotIconBuilder.of(Material.EXPERIENCE_BOTTLE, MenuText.currentXP(experience)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                experience.setExp(i);
                new ExperienceGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.EXPERIENCE_BOTTLE, MenuText.xpForNextLevel(experience, classLevels)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
