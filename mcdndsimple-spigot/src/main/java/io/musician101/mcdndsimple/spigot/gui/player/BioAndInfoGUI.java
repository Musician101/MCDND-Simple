package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class BioAndInfoGUI extends SpigotMCDNDSimpleGUI {

    public BioAndInfoGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BIO_AND_INFO, 9);
        BioAndInfo bioAndInfo = mcdndPlayer.getBioAndInfo();
        setButton(0, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.name(bioAndInfo)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setName(s);
                new BioAndInfoGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.BOOK, MenuText.BIO), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.BIO, bioAndInfo.getBio()), (ply, pages) -> {
            bioAndInfo.setBio(pages);
            new BioAndInfoGUI(mcdndPlayer, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new PlayerSheetGUI(mcdndPlayer, p)));
    }
}
