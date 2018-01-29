package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.ranged;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;

public class RangedWeaponsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final RangedBonus rangedBonus;
    private final List<RangedWeapon> weapons;

    public RangedWeaponsGUI(Player player, List<RangedWeapon> weapons, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, RangedBonus rangedBonus, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.RANGED_WEAPONS, 54, page, prevGUI);
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

        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
