package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpellSavingStatGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final SpellSave spellSave;

    public SpellSavingStatGUI(Player player, CoreStats coreStats, SpellSave spellSave, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.SAVING_STAT, prevGUI);
        this.coreStats = coreStats;
        this.spellSave = spellSave;
    }

    @Override
    protected void build() {
        String str = getAbilityScoreShortName(coreStats.getStrength());
        set(0, createItem(Material.IRON_SWORD, str), setSavingStat(str));
        String dex = getAbilityScoreShortName(coreStats.getDexterity());
        set(1, createItem(Material.BOW, dex), setSavingStat(dex));
        String con = getAbilityScoreShortName(coreStats.getConstitution());
        set(2, createItem(Material.GOLDEN_APPLE, con), setSavingStat(con));
        String intel = getAbilityScoreShortName(coreStats.getIntelligence());
        set(3, createItem(Material.BOOK_AND_QUILL, intel), setSavingStat(intel));
        String wis = getAbilityScoreShortName(coreStats.getWisdom());
        set(4, createItem(Material.ENCHANTED_BOOK, wis), setSavingStat(wis));
        String cha = getAbilityScoreShortName(coreStats.getCharisma());
        set(5, setDurability(createItem(Material.SKULL_ITEM, cha), 3), setSavingStat(cha));
        setBackButton(8, Material.BARRIER);
    }

    private String getAbilityScoreShortName(AbilityScore abilityScore) {
        return abilityScore.getShortName().toUpperCase();
    }

    private Consumer<Player> setSavingStat(String attackStat) {
        return player -> {
            spellSave.setSavingStat(attackStat);
            if (prevGUI == null) {
                player.closeInventory();
            }
            else {
                prevGUI.open();
            }
        };
    }
}
