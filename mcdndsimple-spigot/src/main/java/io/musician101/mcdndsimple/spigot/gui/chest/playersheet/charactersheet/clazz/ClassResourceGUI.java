package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ClassResourceGUI extends MCDNDSimpleChestGUI {

    private final ClassResource classResource;
    private final List<ClassResource> classResources;

    public ClassResourceGUI(Player player, ClassResource classResource, List<ClassResource> classResources, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, classResource.getName(), prevGUI);
        this.classResource = classResource;
        this.classResources = classResources;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.NAME_TAG, classResource.getName()), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classResource.setName(s);
            player.closeInventory();
            open();
        }));
        set(1, createItem(Material.BED, MenuText.recharge(classResource.getRecharge())), player -> new RechargeGUI<>(player, classResource, () -> new ClassResourceGUI(player, classResource, classResources, prevGUI), this));
        set(2, createItem(Material.REDSTONE_TORCH_OFF, MenuText.current(classResource.getUsesLeft())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classResource.setCurrentCharges(i);
            player.closeInventory();
            open();
        }));
        set(2, createItem(Material.REDSTONE_TORCH_ON, MenuText.maxUses(classResource.getMaxUses())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classResource.setMaxCharges(i);
            player.closeInventory();
            open();
        }));
        set(3, createItem(Material.ENDER_CHEST, MenuText.DELETE), player -> {
            classResources.remove(classResource);
            if (prevGUI != null) {
                prevGUI.open();
            }
            else {
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + Messages.CLASS_RESOURCE_DELETED);
            }
        });
        setBackButton(8, Material.BARRIER);
    }
}
