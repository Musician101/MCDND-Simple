package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class CoreStatsGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;

    public CoreStatsGUI(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CORE_STATS, 9, prevGUI);
        this.bioAndInfo = bioAndInfo;
        this.coreStats = coreStats;
        this.experience = experience;
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        AbilityScore str = coreStats.getStrength();
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.IRON_SWORD, Text.of(str.getName()), convertToText(MenuText.scoreLore(str, classLevels, experience))), player -> new AbilityScoreGUI(player, str, bioAndInfo, classLevels, experience, this));
        AbilityScore dex = coreStats.getDexterity();
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(dex.getName()), convertToText(MenuText.scoreLore(dex, classLevels, experience))), player -> new AbilityScoreGUI(player, dex, bioAndInfo, classLevels, experience, this));
        AbilityScore con = coreStats.getConstitution();
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.GOLDEN_APPLE, Text.of(con.getName()), convertToText(MenuText.scoreLore(con, classLevels, experience))), player -> new AbilityScoreGUI(player, con, bioAndInfo, classLevels, experience, this));
        AbilityScore intel = coreStats.getIntelligence();
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(intel.getName()), convertToText(MenuText.scoreLore(intel, classLevels, experience))), player -> new AbilityScoreGUI(player, intel, bioAndInfo, classLevels, experience, this));
        AbilityScore wis = coreStats.getWisdom();
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(wis.getName()), convertToText(MenuText.scoreLore(wis, classLevels, experience))), player -> new AbilityScoreGUI(player, wis, bioAndInfo, classLevels, experience, this));
        AbilityScore cha = coreStats.getCharisma();
        set(5, ClickInventoryEvent.class, setType(createItem(ItemTypes.SKULL, Text.of(cha.getName()), convertToText(MenuText.scoreLore(cha, classLevels, experience))), Keys.SKULL_TYPE, SkullTypes.PLAYER), player -> new AbilityScoreGUI(player, cha, bioAndInfo, classLevels, experience, this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
