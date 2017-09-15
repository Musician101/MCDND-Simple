package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ClassActionGUI extends MCDNDSimpleChestGUI {

    private final ClassAction classAction;
    private final List<ClassAction> classActions;

    public ClassActionGUI(Player player, ClassAction classAction, List<ClassAction> classActions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, classAction.getName(), prevGUI);
        this.classAction = classAction;
        this.classActions = classActions;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.PAPER, classAction.getName()), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classAction.setName(s);
            new ClassActionGUI(player, classAction, classActions, prevGUI);
        }));
        set(1, ClickType.LEFT, createItem(Material.REDSTONE_TORCH_OFF, MenuText.used(classAction.getUsedCharges())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classAction.setUsedCharges(i);
            open();
        }));
        set(2, ClickType.LEFT, createItem(Material.REDSTONE_TORCH_ON, MenuText.maxUses(classAction.getMax())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classAction.setMax(i);
            open();
        }));
        set(3, ClickType.LEFT, createItem(Material.BED, MenuText.recharge(classAction.getRecharge())), player -> new RechargeGUI<>(player, classAction, () -> new ClassActionGUI(player, classAction, classActions, prevGUI), this));
        set(4, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.GAINED_FROM), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classAction.setGainedFrom(s);
            open();
        }));
        ItemStack output = createItem(Material.BOOK, MenuText.OUTPUT);
        BookMeta outputMeta = (BookMeta) output.getItemMeta();
        outputMeta.setPages(classAction.getOutput());
        output.setItemMeta(outputMeta);
        set(5, ClickType.LEFT, output, player -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, output, pages -> {
            classAction.setOutput(pages);
            open();
        }));
        set(6, ClickType.LEFT, createItem(Material.REDSTONE, MenuText.OUTPUT_OPTIONS), player -> new OutputOptionsGUI(player, classAction.getOutputOptions(), this));
        //TODO need to add delete function to classaction, classresource and inventory guis
        set(7, ClickType.LEFT, createItem(Material.ENDER_CHEST, MenuText.DELETE), player -> {
            classActions.remove(classAction);
            if (prevGUI != null) {
                prevGUI.open();
            }
            else {
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + Messages.CLASS_ACTION_DELETED);
            }
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
