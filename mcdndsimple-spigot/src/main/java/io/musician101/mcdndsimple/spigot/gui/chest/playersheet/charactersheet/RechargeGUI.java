package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.Recharge;
import io.musician101.mcdndsimple.common.character.Rechargeable;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RechargeGUI<R extends Rechargeable> extends MCDNDSimpleChestGUI {

    private final Recharge recharge;
    private final R rechargeable;
    private final Runnable runnable;

    public RechargeGUI(Player player, R rechargeable, Runnable runnable, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.recharge(rechargeable.getRecharge()), prevGUI);
        this.rechargeable = rechargeable;
        this.recharge = rechargeable.getRecharge();
        this.runnable = runnable;
    }

    @Override
    protected void build() {
        Recharge[] recharges = Recharge.values();
        for (int i = 0; i < recharges.length; i++) {
            Recharge recharge = recharges[i];
            ItemStack itemStack = createItem(Material.BED, recharge.getName());
            if (this.recharge == recharge) {
                itemStack = addGlow(itemStack);
            }

            set(i, itemStack, player -> {
                rechargeable.setRecharge(recharge);
                runnable.run();
            });
        }

        setBackButton(8, Material.BARRIER);
    }
}
