package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.tab.SpellSlots;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellSlotsGUI extends MCDNDSimpleChestGUI {

    private final SpellSlots spellSlots;

    public SpellSlotsGUI(Player player, SpellSlots spellSlots, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.SPELL_SLOTS, prevGUI);
        this.spellSlots = spellSlots;
    }

    @Override
    protected void build() {
        for (int x = 0; x < 18; x++) {
            set(x, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.level(x), MenuText.used(getUsed(x))), usedConsumer(x));
            set(x + 9, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.level(x), MenuText.maxUses(getMax(x))), maxConsumer(x));
        }

        setBackButton(17, ClickType.LEFT, Material.BARRIER);
    }

    private int getMax(int x) {
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
