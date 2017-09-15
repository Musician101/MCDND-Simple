package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.Recharge;
import io.musician101.mcdndsimple.common.character.Rechargeable;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class RechargeGUI<R extends Rechargeable> extends MCDNDSimpleChestGUI {

    private final Recharge recharge;
    private final R rechargeable;
    private final Runnable runnable;

    public RechargeGUI(Player player, R rechargeable, Runnable runnable, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.recharge(rechargeable.getRecharge()), 9, prevGUI);
        this.rechargeable = rechargeable;
        this.recharge = rechargeable.getRecharge();
        this.runnable = runnable;
    }

    @Override
    protected void build() {
        Recharge[] recharges = Recharge.values();
        for (int i = 0; i < recharges.length; i++) {
            Recharge recharge = recharges[i];
            set(i, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BED, Text.of(recharge.getName())), () -> this.recharge == recharge), player -> {
                rechargeable.setRecharge(recharge);
                runnable.run();
            });
        }

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
