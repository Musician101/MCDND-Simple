package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

public class ClassActionGUI extends MCDNDSimpleChestGUI {

    private final ClassAction classAction;
    private final List<ClassAction> classActions;

    public ClassActionGUI(Player player, ClassAction classAction, List<ClassAction> classActions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, classAction.getName(), 9, prevGUI);
        this.classAction = classAction;
        this.classActions = classActions;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(classAction.getName())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classAction.setName(s);
            new ClassActionGUI(player, classAction, classActions, prevGUI);
        }));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.used(classAction.getUsedCharges()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classAction.setUsedCharges(i);
            open();
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.maxUses(classAction.getMax()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classAction.setMax(i);
            open();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.BED, Text.of(MenuText.recharge(classAction.getRecharge()))), player -> new RechargeGUI<>(player, classAction, () -> new ClassActionGUI(player, classAction, classActions, prevGUI), this));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.GAINED_FROM)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            classAction.setGainedFrom(s);
            open();
        }));
        ItemStack output = createItem(ItemTypes.BOOK, Text.of(MenuText.OUTPUT));
        output.offer(Keys.BOOK_PAGES, classAction.getOutput().stream().map(Text::of).collect(Collectors.toList()));
        SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, output, pages -> {
            classAction.setOutput(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
            open();
        });
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE, Text.of(MenuText.OUTPUT_OPTIONS)), player -> new OutputOptionsGUI(player, classAction.getOutputOptions(), this));
        //TODO need to add delete function to classaction, classresource and inventory guis
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.ENDER_CHEST, Text.of(MenuText.DELETE)), player -> {
            classActions.remove(classAction);
            closeGUI();
            player.sendMessage(Text.of(TextColors.GREEN, Messages.CLASS_ACTION_DELETED));
        });
        setBackButton(9, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
