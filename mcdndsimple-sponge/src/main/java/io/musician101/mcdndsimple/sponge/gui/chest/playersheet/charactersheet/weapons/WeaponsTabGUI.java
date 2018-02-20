package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.melee.MeleeWeaponsGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.ranged.RangedWeaponsGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class WeaponsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final RangedBonus rangedBonus;
    private final WeaponsTab weaponsTab;

    public WeaponsTabGUI(Player player, WeaponsTab weaponsTab, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, RangedBonus rangedBonus, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.WEAPONS, 9, prevGUI);
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
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.MELEE_WEAPONS)), player -> new MeleeWeaponsGUI(player, weaponsTab.getMeleeWeapons(), 0, bioAndInfo, classLevels, coreStats, experience, meleeBonus, this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(MenuText.RANGED_WEAPONS)), player -> new RangedWeaponsGUI(player, weaponsTab.getRangedWeapons(), 0, bioAndInfo, classLevels, coreStats, experience, rangedBonus, this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
