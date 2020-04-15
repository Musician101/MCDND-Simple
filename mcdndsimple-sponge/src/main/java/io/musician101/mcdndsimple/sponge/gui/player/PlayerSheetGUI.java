package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.EditControllersGUI;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class PlayerSheetGUI extends SpongeMCDNDSimpleGUI {

    public PlayerSheetGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.PLAYER_SHEET, 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setName(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, Text.of(MenuText.CLASS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setClazz(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.STONEBRICK, Text.of(MenuText.RACE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setRace(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.BIO_AND_INFO)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new BioAndInfoGUI(mcdndPlayer, p)));
        setButton(4, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.CHARACTER_SHEET)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
        if (player.hasPermission(Permissions.DM)) {
            setButton(5, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(MenuText.EDIT_CONTROLLERS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new EditControllersGUI(mcdndPlayer, EditControllersGUI.PLAYER, p)));
            setButton(6, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, Text.of(MenuText.DELETE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> SpongeMCDNDSimple.instance().getPlayerStorage().remove(mcdndPlayer)));
        }

        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, PlayerSheetsGUI::new));
    }
}
