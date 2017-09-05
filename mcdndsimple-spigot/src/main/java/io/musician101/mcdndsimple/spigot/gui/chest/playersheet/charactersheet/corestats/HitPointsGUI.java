package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

public class HitPointsGUI extends MCDNDSimpleChestGUI {

    private final HitPoints hitPoints;

    public HitPointsGUI(Player player, HitPoints hitPoints, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.HIT_POINTS, prevGUI);
        this.hitPoints = hitPoints;
    }

    @Override
    protected void build() {
        set(0, setPotionEffect(createItem(Material.POTION, MenuText.currentHitPoints(hitPoints)), PotionType.REGEN), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setCurrent(i);
                player.closeInventory();
                open();
            });
        });
        set(1, setPotionEffect(createItem(Material.POTION, MenuText.maxHitPoints(hitPoints)), PotionType.INSTANT_HEAL), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setMax(i);
                player.closeInventory();
                open();
            });
        });
        set(2, setPotionEffect(createItem(Material.POTION, MenuText.tempHitPoints(hitPoints)), PotionType.STRENGTH), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setTemp(i);
                player.closeInventory();
                open();
            });
        });
        setBackButton(8, Material.BARRIER);
    }
}
