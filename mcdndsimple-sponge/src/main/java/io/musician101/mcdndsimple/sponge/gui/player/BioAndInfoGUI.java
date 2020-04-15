package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class BioAndInfoGUI extends SpongeMCDNDSimpleGUI {

    public BioAndInfoGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BIO_AND_INFO, 9);
        BioAndInfo bioAndInfo = mcdndPlayer.getBioAndInfo();
        setButton(0, SpongeIconBuilder.of(ItemTypes.NAME_TAG, Text.of(MenuText.name(bioAndInfo))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setName(s);
                new BioAndInfoGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.BIO)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.BIO, bioAndInfo.getBio()), (ply, pages) -> {
            bioAndInfo.setBio(textListToStringList(pages));
            new BioAndInfoGUI(mcdndPlayer, ply);
        })));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerSheetGUI(mcdndPlayer, p)));
    }
}
