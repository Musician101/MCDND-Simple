package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class HitPointsGUI extends MCDNDSimpleChestGUI {

    private final HitPoints hitPoints;

    public HitPointsGUI(Player player, HitPoints hitPoints, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.HIT_POINTS, 9, prevGUI);
        this.hitPoints = hitPoints;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.currentHitPoints(hitPoints))), PotionEffectTypes.REGENERATION), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setCurrent(i);
                player.closeInventory();
                open();
            });
        });
        set(1, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.maxHitPoints(hitPoints))), PotionEffectTypes.INSTANT_HEALTH), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setMax(i);
                player.closeInventory();
                open();
            });
        });
        set(2, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.tempHitPoints(hitPoints))), PotionEffectTypes.STRENGTH), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                hitPoints.setTemp(i);
                player.closeInventory();
                open();
            });
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
