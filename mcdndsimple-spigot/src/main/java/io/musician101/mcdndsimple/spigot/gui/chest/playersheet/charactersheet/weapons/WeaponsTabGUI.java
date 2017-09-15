package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.tab.WeaponsTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons.melee.MeleeWeaponsGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons.ranged.RangedWeaponsGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class WeaponsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final RangedBonus rangedBonus;
    private final WeaponsTab weaponsTab;

    public WeaponsTabGUI(Player player, WeaponsTab weaponsTab, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, RangedBonus rangedBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.WEAPONS, prevGUI);
        this.weaponsTab = weaponsTab;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.meleeBonus = meleeBonus;
        this.rangedBonus = rangedBonus;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.MELEE_WEAPONS), player -> new MeleeWeaponsGUI(player, weaponsTab.getMeleeWeapons(), 0, bioAndInfo, classLevels, coreStats, experience, meleeBonus, this));
        set(1, ClickType.LEFT, createItem(Material.BOW, MenuText.RANGED_WEAPONS), player -> new RangedWeaponsGUI(player, weaponsTab.getRangedWeapons(), 0, bioAndInfo, classLevels, coreStats, experience, rangedBonus, this));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
