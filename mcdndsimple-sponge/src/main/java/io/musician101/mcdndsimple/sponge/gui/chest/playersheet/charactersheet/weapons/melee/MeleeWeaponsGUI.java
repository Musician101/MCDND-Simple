package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.melee;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;

public class MeleeWeaponsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final List<MeleeWeapon> weapons;

    public MeleeWeaponsGUI(Player player, List<MeleeWeapon> weapons, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.MELEE_WEAPONS, 54, page, prevGUI);
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

        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
