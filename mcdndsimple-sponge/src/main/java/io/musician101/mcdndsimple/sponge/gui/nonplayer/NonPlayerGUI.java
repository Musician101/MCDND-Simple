package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.EditControllersGUI;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.gui.player.TraitsBackgroundGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class NonPlayerGUI extends SpongeMCDNDSimpleGUI {

    public NonPlayerGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.name(nonPlayer), 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setName(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, Text.of(MenuText.CLASS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setClazz(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.STONEBRICK, Text.of(MenuText.RACE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setClazz(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.ACTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerActionsGUI(nonPlayer, p)));
        setButton(4, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.CHARACTER_SHEET)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSheetGUI(nonPlayer, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.SKILLS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillsGUI(nonPlayer, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.TRAITS_BACKGROUND)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new TraitsBackgroundGUI(nonPlayer, p)));
        if (player.hasPermission(Permissions.DM)) {
            setButton(6, SpongeIconBuilder.builder(ItemTypes.SKULL).name(Text.of(MenuText.EDIT_CONTROLLERS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new EditControllersGUI(nonPlayer, EditControllersGUI.NPC, p)));
            setButton(7, SpongeIconBuilder.of(ItemTypes.ENDER_CHEST, Text.of(MenuText.DELETE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> SpongeMCDNDSimple.instance().getNonPlayerStorage().remove(nonPlayer)));
        }
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, NonPlayersGUI::new));
    }
}
