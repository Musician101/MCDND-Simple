package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
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

public class ClassResourcesGUI extends MCDNDSimplePagedGUI {

    private final List<ClassResource> classResources;

    public ClassResourcesGUI(@Nonnull Player player, List<ClassResource> classResources, int page, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, MenuText.CLASS_RESOURCES, 54, page, prevMenu);
        this.classResources = classResources;
    }

    @Override
    protected void build() {
        setContents(classResources, classResource -> createItem(ItemTypes.NETHER_STAR, Text.of(classResource.getName())), (player, classResource) -> p -> new ClassResourceGUI(player, classResource, classResources, this));
        set(49, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.NEW_CLASS_ACTION)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            ClassResource classResource = new ClassResource();
            classResource.setName(s);
            classResources.add(classResource);
            new ClassResourceGUI(player, classResource, classResources, prevGUI);
        }));
        int maxPage = new Double(Math.ceil(classResources.size() / 45)).intValue();
        setJumpToPage(45, maxPage, (player, page) -> new ClassResourcesGUI(player, classResources, page, prevGUI));
        setPageNavigation(48, MenuText.PREVIOUS_PAGE, player -> {
            if (page > 1) {
                new ClassResourcesGUI(player, classResources, page - 1, prevGUI);
            }
        });
        setPageNavigation(50, MenuText.NEXT_PAGE, player -> {
            if (page < maxPage) {
                new ClassResourcesGUI(player, classResources, page + 1, prevGUI);
            }
        });
        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
