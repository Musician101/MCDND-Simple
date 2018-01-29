package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.function.Consumer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class SpellSavingStatGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final SpellSave spellSave;

    public SpellSavingStatGUI(Player player, CoreStats coreStats, SpellSave spellSave, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SAVING_STAT, 9, prevGUI);
        this.coreStats = coreStats;
        this.spellSave = spellSave;
    }

    @Override
    protected void build() {
        String str = getAbilityScoreShortName(coreStats.getStrength());
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.IRON_SWORD, Text.of(str)), setSavingStat(str));
        String dex = getAbilityScoreShortName(coreStats.getDexterity());
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(dex)), setSavingStat(dex));
        String con = getAbilityScoreShortName(coreStats.getConstitution());
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.GOLDEN_APPLE, Text.of(con)), setSavingStat(con));
        String intel = getAbilityScoreShortName(coreStats.getIntelligence());
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(intel)), setSavingStat(intel));
        String wis = getAbilityScoreShortName(coreStats.getWisdom());
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(wis)), setSavingStat(wis));
        String cha = getAbilityScoreShortName(coreStats.getCharisma());
        set(5, ClickInventoryEvent.class, setType(createItem(ItemTypes.SKULL, Text.of(cha)), Keys.SKULL_TYPE, SkullTypes.PLAYER), setSavingStat(cha));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
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
