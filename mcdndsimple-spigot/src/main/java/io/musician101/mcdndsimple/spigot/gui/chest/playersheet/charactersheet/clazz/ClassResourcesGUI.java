package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ClassResourcesGUI extends MCDNDSimplePagedGUI {

    private final List<ClassResource> classResources;

    public ClassResourcesGUI(@Nonnull Player player, List<ClassResource> classResources, int page, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, 54, MenuText.CLASS_RESOURCES, page, prevMenu);
        this.classResources = classResources;
    }

    @Override
    protected void build() {
        setContents(classResources, classResource -> createItem(Material.NETHER_STAR, classResource.getName()), (player, classResource) -> p -> new ClassResourceGUI(player, classResource, classResources, this));
        set(49, createItem(Material.PAPER, MenuText.NEW_CLASS_ACTION), player -> new StringInputAnvilGUI(player, (p, s) -> {
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
        setBackButton(53, Material.BARRIER);
    }
}
