package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CoreStatsGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;

    public CoreStatsGUI(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.CORE_STATS, prevGUI);
        this.bioAndInfo = bioAndInfo;
        this.coreStats = coreStats;
        this.experience = experience;
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        AbilityScore str = coreStats.getStrength();
        set(0, createItem(Material.IRON_SWORD, str.getName(), MenuText.scoreLore(str, classLevels, experience)), player -> new AbilityScoreGUI(player, str, bioAndInfo, classLevels, experience, this));
        AbilityScore dex = coreStats.getDexterity();
        set(1, createItem(Material.BOW, dex.getName(), MenuText.scoreLore(dex, classLevels, experience)), player -> new AbilityScoreGUI(player, dex, bioAndInfo, classLevels, experience, this));
        AbilityScore con = coreStats.getConstitution();
        set(2, createItem(Material.GOLDEN_APPLE, con.getName(), MenuText.scoreLore(con, classLevels, experience)), player -> new AbilityScoreGUI(player, con, bioAndInfo, classLevels, experience, this));
        AbilityScore intel = coreStats.getIntelligence();
        set(3, createItem(Material.BOOK_AND_QUILL, intel.getName(), MenuText.scoreLore(intel, classLevels, experience)), player -> new AbilityScoreGUI(player, intel, bioAndInfo, classLevels, experience, this));
        AbilityScore wis = coreStats.getWisdom();
        set(4, createItem(Material.ENCHANTED_BOOK, wis.getName(), MenuText.scoreLore(wis, classLevels, experience)), player -> new AbilityScoreGUI(player, wis, bioAndInfo, classLevels, experience, this));
        AbilityScore cha = coreStats.getCharisma();
        set(5, setDurability(createItem(Material.SKULL_ITEM, cha.getName(), MenuText.scoreLore(cha, classLevels, experience)), 3), player -> new AbilityScoreGUI(player, cha, bioAndInfo, classLevels, experience, this));
        setBackButton(8, Material.BARRIER);
    }
}
