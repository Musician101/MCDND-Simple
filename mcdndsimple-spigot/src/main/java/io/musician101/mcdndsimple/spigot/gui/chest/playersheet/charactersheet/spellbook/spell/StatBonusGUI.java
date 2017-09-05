package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StatBonusGUI<T> extends MCDNDSimpleChestGUI {

    private final BiConsumer<T, String> valueSetter;
    private final CoreStats coreStats;
    private final T value;

    public StatBonusGUI(Player player, CoreStats coreStats, T value, BiConsumer<T, String> valueSetter, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.STAT_BONUS, prevGUI);
        this.coreStats = coreStats;
        this.valueSetter = valueSetter;
        this.value = value;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.ENDER_CHEST, MenuText.NONE), setStatBonus("None"));
        String str = getAbilityScoreShortName(coreStats.getStrength());
        set(1, createItem(Material.IRON_SWORD, str), setStatBonus(str));
        String dex = getAbilityScoreShortName(coreStats.getDexterity());
        set(2, createItem(Material.BOW, dex), setStatBonus(dex));
        String con = getAbilityScoreShortName(coreStats.getConstitution());
        set(3, createItem(Material.GOLDEN_APPLE, con), setStatBonus(con));
        String intel = getAbilityScoreShortName(coreStats.getIntelligence());
        set(4, createItem(Material.BOOK_AND_QUILL, intel), setStatBonus(intel));
        String wis = getAbilityScoreShortName(coreStats.getWisdom());
        set(5, createItem(Material.ENCHANTED_BOOK, wis), setStatBonus(wis));
        String cha = getAbilityScoreShortName(coreStats.getCharisma());
        set(6, setDurability(createItem(Material.SKULL_ITEM, cha), 3), setStatBonus(cha));
        setBackButton(8, Material.BARRIER);
    }

    private String getAbilityScoreShortName(AbilityScore abilityScore) {
        return abilityScore.getShortName().toUpperCase();
    }

    private Consumer<Player> setStatBonus(String statBonus) {
        return player -> {
            valueSetter.accept(value, statBonus);
            if (prevGUI == null) {
                player.closeInventory();
            }
            else {
                prevGUI.open();
            }
        };
    }
}
