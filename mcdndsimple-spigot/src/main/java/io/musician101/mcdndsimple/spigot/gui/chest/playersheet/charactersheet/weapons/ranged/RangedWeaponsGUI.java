package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons.ranged;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class RangedWeaponsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final RangedBonus rangedBonus;
    private final List<RangedWeapon> weapons;

    public RangedWeaponsGUI(Player player, List<RangedWeapon> weapons, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, RangedBonus rangedBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 54, MenuText.RANGED_WEAPONS, page, prevGUI);
        this.weapons = weapons;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.rangedBonus = rangedBonus;
    }

    @Override
    protected void build() {
        setContents(weapons, rangedWeapon -> ItemRepresentation.rangedWeapon(rangedWeapon, classLevels, coreStats, experience), (player, weapon) -> p -> new RangedWeaponGUI(p, weapon, bioAndInfo, classLevels, coreStats, experience, rangedBonus,
                prevGUI));
        int maxPage = new Double(Math.ceil(weapons.size() / 45)).intValue();
        setJumpToPage(45, maxPage, (player, page) -> new RangedWeaponsGUI(player, weapons, page, bioAndInfo, classLevels, coreStats, experience, rangedBonus, prevGUI));
        setPageNavigation(48, MenuText.PREVIOUS_PAGE, player -> {
            if (page > 1) {
                new RangedWeaponsGUI(player, weapons, page - 1, bioAndInfo, classLevels, coreStats, experience, rangedBonus, prevGUI);
            }
        });

        setPageNavigation(51, MenuText.NEXT_PAGE, player -> {
            if (page < maxPage) {
                new RangedWeaponsGUI(player, weapons, page + 1, bioAndInfo, classLevels, coreStats, experience, rangedBonus, prevGUI);
            }
        });

        setBackButton(53, ClickType.LEFT, Material.BARRIER);
    }
}
