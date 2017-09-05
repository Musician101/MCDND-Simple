package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpellAttackGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final Spell spell;

    public SpellAttackGUI(Player player, CoreStats coreStats, Spell spell, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.ATTACK, prevGUI);
        this.spell = spell;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.ENDER_CHEST, MenuText.NONE), setAttackStat("None"));
        String str = getAbilityScoreShortName(coreStats.getStrength());
        set(1, createItem(Material.IRON_SWORD, str), setAttackStat(str));
        String dex = getAbilityScoreShortName(coreStats.getDexterity());
        set(2, createItem(Material.BOW, dex), setAttackStat(dex));
        String con = getAbilityScoreShortName(coreStats.getConstitution());
        set(3, createItem(Material.GOLDEN_APPLE, con), setAttackStat(con));
        String intel = getAbilityScoreShortName(coreStats.getIntelligence());
        set(4, createItem(Material.BOOK_AND_QUILL, intel), setAttackStat(intel));
        String wis = getAbilityScoreShortName(coreStats.getWisdom());
        set(5, createItem(Material.ENCHANTED_BOOK, wis), setAttackStat(wis));
        String cha = getAbilityScoreShortName(coreStats.getCharisma());
        set(6, setDurability(createItem(Material.SKULL_ITEM, cha), 3), setAttackStat(cha));
        setBackButton(8, Material.BARRIER);
    }

    private String getAbilityScoreShortName(AbilityScore abilityScore) {
        return abilityScore.getShortName().toUpperCase();
    }

    private Consumer<Player> setAttackStat(String attackStat) {
        return player -> {
            spell.setAttackStat(attackStat);
            if (prevGUI == null) {
                player.closeInventory();
            }
            else {
                prevGUI.open();
            }
        };
    }
}
