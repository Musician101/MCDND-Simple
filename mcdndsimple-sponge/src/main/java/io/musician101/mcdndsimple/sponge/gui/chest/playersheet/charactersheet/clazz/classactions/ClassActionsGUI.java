package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class ClassActionsGUI extends MCDNDSimplePagedGUI {

    private final List<ClassAction> classActions;

    public ClassActionsGUI(@Nonnull Player player, @Nonnull List<ClassAction> classActions, int page, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, MenuText.CLASS_ACTIONS, 54, page, prevMenu);
        this.classActions = classActions;
    }

    @Override
    protected void build() {
        setContents(classActions, classAction -> createItem(ItemTypes.NETHER_STAR, Text.of(classAction.getName())), (player, classAction) -> p -> new ClassActionGUI(player, classAction, classActions, this));
        set(49, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.NEW_CLASS_ACTION)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            ClassAction classAction = new ClassAction();
            classAction.setName(s);
            classActions.add(classAction);
            new ClassActionGUI(player, classAction, classActions, prevGUI);
        }));
        int maxPage = new Double(Math.ceil(classActions.size() / 45)).intValue();
        setJumpToPage(45, maxPage, (player, page) -> new ClassActionsGUI(player, classActions, page, prevGUI));
        setPageNavigation(48, MenuText.PREVIOUS_PAGE, player -> {
            if (page > 1) {
                new ClassActionsGUI(player, classActions, page - 1, prevGUI);
            }
        });
        setPageNavigation(50, MenuText.NEXT_PAGE, player -> {
            if (page < maxPage) {
                new ClassActionsGUI(player, classActions, page + 1, prevGUI);
            }
        });
        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
