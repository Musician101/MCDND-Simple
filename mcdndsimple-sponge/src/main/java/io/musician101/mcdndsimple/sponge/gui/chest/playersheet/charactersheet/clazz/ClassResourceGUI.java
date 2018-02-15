package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ClassResourceGUI extends MCDNDSimpleChestGUI {

    private final ClassResource classResource;
    private final List<ClassResource> classResources;

    public ClassResourceGUI(Player player, ClassResource classResource, List<ClassResource> classResources, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, classResource.getName(), 9, prevGUI);
        this.classResource = classResource;
        this.classResources = classResources;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.NAME_TAG, Text.of(classResource.getName())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classResource.setName(s);
            open();
        }));
        //TODO looking into BookGUI shenanigans
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BED, Text.of(MenuText.recharge(classResource.getRecharge()))), player -> new RechargeGUI<>(player, classResource, () -> new ClassResourceGUI(player, classResource, classResources, prevGUI), this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.current(classResource.getUsesLeft()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classResource.setCurrentCharges(i);
            open();
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.maxUses(classResource.getMaxUses()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classResource.setMaxCharges(i);
            open();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.ENDER_CHEST, Text.of(MenuText.DELETE)), player -> {
            classResources.remove(classResource);
            if (prevGUI != null) {
                prevGUI.open();
            }
            else {
                player.closeInventory();
                player.sendMessage(Text.of(TextColors.GREEN, Messages.CLASS_RESOURCE_DELETED));
            }
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
