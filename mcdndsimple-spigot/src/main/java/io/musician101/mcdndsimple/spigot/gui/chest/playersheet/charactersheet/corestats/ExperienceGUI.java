package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ExperienceGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;
    private final Experience experience;

    public ExperienceGUI(Player player, ClassLevels classLevels, Experience experience, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.LEVEL_AND_XP, prevGUI);
        this.classLevels = classLevels;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.BOOK, MenuText.overallLevel(experience, classLevels)));
        set(1, createItem(Material.ANVIL, MenuText.proficiencyBonus(experience, classLevels)));
        set(2, ClickType.LEFT, createItem(Material.EXP_BOTTLE, MenuText.currentXP(experience)), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                experience.setExp(i);
                player.closeInventory();
                open();
            });
        });
        set(3, createItem(Material.EXP_BOTTLE, MenuText.xpForNextLevel(experience, classLevels)));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
