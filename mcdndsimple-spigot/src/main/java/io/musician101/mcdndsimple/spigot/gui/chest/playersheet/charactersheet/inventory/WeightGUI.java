package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class WeightGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final Weight weight;

    public WeightGUI(Player player, Weight weight, CoreStats coreStats, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.WEIGHT, prevGUI);
        this.weight = weight;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.CHEST, MenuText.inventoryWeight(weight.getInventory())));
        set(1, createItem(Material.EMERALD, MenuText.coinWeight(weight.getCoin())));
        set(2, createItem(Material.STICK, MenuText.otherWeight(weight.getOther())), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            weight.setOther(d);
            player.closeInventory();
            open();
        }));
        set(3, createItem(Material.STONE, MenuText.totalWeight(weight)));
        set(4, createItem(Material.CHEST, MenuText.carryingMax(weight.getCarryingMax())));
        set(5, createItem(Material.PISTON_BASE, MenuText.pushDragLift(weight.getPushDragLift())));
        set(6, createItem(Material.STONE, MenuText.encumbered(weight.getEncumbered(coreStats))));
        set(7, createItem(Material.OBSIDIAN, MenuText.heavilyEncumbered(weight.getHeavilyEncumbered(coreStats))));
        setBackButton(8, Material.BARRIER);
    }
}
