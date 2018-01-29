package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.tab.SpellSlots;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.function.Consumer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class SpellSlotsGUI extends MCDNDSimpleChestGUI {

    private final SpellSlots spellSlots;

    public SpellSlotsGUI(Player player, SpellSlots spellSlots, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SPELL_SLOTS, 18, prevGUI);
        this.spellSlots = spellSlots;
    }

    @Override
    protected void build() {
        for (int x = 0; x < 18; x++) {
            set(x, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.level(x)), Text.of(MenuText.used(getUsed(x)))), usedConsumer(x));
            set(x + 9, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.level(x)), Text.of(MenuText.maxUses(getMax(x)))), maxConsumer(x));
        }

        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private int getMax(int x) {
        if (x == 0) {
            return spellSlots.getMaxFirstLevelSpells();
        }
        else if (x == 1) {
            return spellSlots.getMaxSecondLevelSpells();
        }
        else if (x == 2) {
            return spellSlots.getMaxThirdLevelSpells();
        }
        else if (x == 3) {
            return spellSlots.getMaxFourthLevelSpells();
        }
        else if (x == 4) {
            return spellSlots.getMaxFifthLevelSpells();
        }
        else if (x == 5) {
            return spellSlots.getMaxSixthLevelSpells();
        }
        else if (x == 6) {
            return spellSlots.getMaxSeventhLevelSpells();
        }
        else if (x == 7) {
            return spellSlots.getMaxEighthLevelSpells();
        }
        else if (x == 9) {
            return spellSlots.getMaxNinthLevelSpells();
        }

        return 0;
    }

    private int getUsed(int x) {
        if (x == 0) {
            return spellSlots.getFirstLevelSpellsUsed();
        }
        else if (x == 1) {
            return spellSlots.getSecondLevelSpellsUsed();
        }
        else if (x == 2) {
            return spellSlots.getThirdLevelSpellsUsed();
        }
        else if (x == 3) {
            return spellSlots.getFourthLevelSpellsUsed();
        }
        else if (x == 4) {
            return spellSlots.getFifthLevelSpellsUsed();
        }
        else if (x == 5) {
            return spellSlots.getSixthLevelSpellsUsed();
        }
        else if (x == 6) {
            return spellSlots.getSeventhLevelSpellsUsed();
        }
        else if (x == 7) {
            return spellSlots.getEighthLevelSpellsUsed();
        }
        else if (x == 9) {
            return spellSlots.getNinthLevelSpellsUsed();
        }

        return 0;
    }

    private Consumer<Player> maxConsumer(int level) {
        return player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            setMax(level, i);
            delayedOpen();
        });
    }

    private void setMax(int x, int i) {
        if (x == 0) {
            spellSlots.setFirstLevelSpellsUsed(i);
        }
        else if (x == 1) {
            spellSlots.setSecondLevelSpellsUsed(i);
        }
        else if (x == 2) {
            spellSlots.setThirdLevelSpellsUsed(i);
        }
        else if (x == 3) {
            spellSlots.setFourthLevelSpellsUsed(i);
        }
        else if (x == 4) {
            spellSlots.setFifthLevelSpellsUsed(i);
        }
        else if (x == 5) {
            spellSlots.setSixthLevelSpellsUsed(i);
        }
        else if (x == 6) {
            spellSlots.setSeventhLevelSpellsUsed(i);
        }
        else if (x == 7) {
            spellSlots.setEighthLevelSpellsUsed(i);
        }
        else if (x == 9) {
            spellSlots.setNinthLevelSpellsUsed(i);
        }
    }

    private void setUsed(int x, int i) {
        if (x == 0) {
            spellSlots.setFirstLevelSpellsUsed(i);
        }
        else if (x == 1) {
            spellSlots.setSecondLevelSpellsUsed(i);
        }
        else if (x == 2) {
            spellSlots.setThirdLevelSpellsUsed(i);
        }
        else if (x == 3) {
            spellSlots.setFourthLevelSpellsUsed(i);
        }
        else if (x == 4) {
            spellSlots.setFifthLevelSpellsUsed(i);
        }
        else if (x == 5) {
            spellSlots.setSixthLevelSpellsUsed(i);
        }
        else if (x == 6) {
            spellSlots.setSeventhLevelSpellsUsed(i);
        }
        else if (x == 7) {
            spellSlots.setEighthLevelSpellsUsed(i);
        }
        else if (x == 9) {
            spellSlots.setNinthLevelSpellsUsed(i);
        }
    }

    private Consumer<Player> usedConsumer(int level) {
        return player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            setUsed(level, i);
            delayedOpen();
        });
    }
}
