package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MeleeWeaponsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final List<MeleeWeapon> weapons;

    public MeleeWeaponsGUI(Player player, List<MeleeWeapon> weapons, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 54, MenuText.MELEE_WEAPONS, page, prevGUI);
        this.weapons = weapons;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.meleeBonus = meleeBonus;
    }

    //TODO need to test the jumptopage mechanic using biconsumer
    @Override
    protected void build() {
        setContents(weapons, meleeWeapon -> ItemRepresentation.meleeWeapon(meleeWeapon, classLevels, coreStats, experience), (player, weapon) -> p -> new MeleeWeaponGUI(p, weapon, bioAndInfo, classLevels, coreStats, experience, meleeBonus, prevGUI));
        int maxPage = new Double(Math.ceil(weapons.size() / 45)).intValue();
        setJumpToPage(45, maxPage, (player, page) -> new MeleeWeaponsGUI(player, weapons, page, bioAndInfo, classLevels, coreStats, experience, meleeBonus, prevGUI));
        setPageNavigation(48, MenuText.PREVIOUS_PAGE, player -> {
            if (page > 1) {
                new MeleeWeaponsGUI(player, weapons, page - 1, bioAndInfo, classLevels, coreStats, experience, meleeBonus, prevGUI);
            }
        });

        setPageNavigation(51, MenuText.NEXT_PAGE, player -> {
            if (page < maxPage) {
                new MeleeWeaponsGUI(player, weapons, page + 1, bioAndInfo, classLevels, coreStats, experience, meleeBonus, prevGUI);
            }
        });

        setBackButton(53, Material.BARRIER);
    }
}
