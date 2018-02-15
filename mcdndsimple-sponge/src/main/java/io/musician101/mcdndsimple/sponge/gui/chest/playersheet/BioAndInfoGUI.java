package io.musician101.mcdndsimple.sponge.gui.chest.playersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;

public class BioAndInfoGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;

    public BioAndInfoGUI(Player player, BioAndInfo bioAndInfo, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.BIO_AND_INFO, 9, prevGUI);
        this.bioAndInfo = bioAndInfo;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.name(bioAndInfo))), player -> new StringInputAnvilGUI(player, (p, s) -> bioAndInfo.setName(s)));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.BIO)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.BOOK_PAGES, bioAndInfo.getBio().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                bioAndInfo.setBio(pages.stream().map(LiteralText.class::cast).map(LiteralText::getContent).collect(Collectors.toList()));
                open();
            });
        });

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
