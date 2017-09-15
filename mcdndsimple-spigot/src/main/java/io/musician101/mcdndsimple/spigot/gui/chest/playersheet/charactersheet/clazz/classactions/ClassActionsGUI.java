package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ClassActionsGUI extends MCDNDSimplePagedGUI {

    private final List<ClassAction> classActions;

    public ClassActionsGUI(@Nonnull Player player, @Nonnull List<ClassAction> classActions, int page, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, 54, MenuText.CLASS_ACTIONS, page, prevMenu);
        this.classActions = classActions;
    }

    @Override
    protected void build() {
        setContents(classActions, classAction -> createItem(Material.NETHER_STAR, classAction.getName()), (player, classAction) -> p -> new ClassActionGUI(player, classAction, classActions, this));
        set(49, ClickType.LEFT, createItem(Material.PAPER, MenuText.NEW_CLASS_ACTION), player -> new StringInputAnvilGUI(player, (p, s) -> {
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
        setBackButton(53, ClickType.LEFT, Material.BARRIER);
    }
}
