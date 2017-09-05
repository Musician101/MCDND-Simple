package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.tab.WeaponsTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class WeaponsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final WeaponsTab weaponsTab;

    public WeaponsTabGUI(Player player, WeaponsTab weaponsTab, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.WEAPONS, prevGUI);
        this.weaponsTab = weaponsTab;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.meleeBonus = meleeBonus;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.DIAMOND_SWORD, MenuText.MELEE_WEAPONS), player -> new MeleeWeaponsGUI(player, weaponsTab.getMeleeWeapons(), 0, bioAndInfo, classLevels, coreStats, experience, meleeBonus, this));
        setBackButton(8, Material.BARRIER);
    }
}
