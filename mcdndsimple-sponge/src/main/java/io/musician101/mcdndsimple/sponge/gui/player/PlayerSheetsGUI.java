package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.character.SpongePlayerStorage;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PlayerSheetsGUI extends SpongeMCDNDSimpleGUI {

    private int page = 1;

    public PlayerSheetsGUI(@Nonnull Player player) {
        super(player, MenuText.PLAYER_SHEETS, 54);
        updateSlots();
        if (player.hasPermission(Permissions.DM)) {
            setButton(48, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.NEW_ARMOR)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

                @Override
                public void process(@Nonnull Player player, @Nonnull String s) {
                    Optional<MCDNDPlayer> playerSheet = SpongeMCDNDSimple.instance().getPlayerStorage().createNewCharacter(s);
                    if (playerSheet.isPresent()) {
                        new PlayerSheetGUI(playerSheet.get(), player);
                        return;
                    }

                    player.sendMessage(Text.of(TextColors.RED + "A player character with an ID of " + s + " already exists."));
                }
            }));
        }
        //TODO open player menu?
        setButton(50, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, Player::closeInventory));
    }

    private void updateSlots() {
        SpongePlayerStorage playerSheetStorage = SpongeMCDNDSimple.instance().getPlayerStorage();
        List<MCDNDPlayer> mcdndPlayers = playerSheetStorage.getCharacters(player.getUniqueId());
        IntStream.of(0, 45).forEach(x -> {
            try {
                MCDNDPlayer mcdndPlayer = mcdndPlayers.get(x + (page - 1) * 45);
                ItemStack itemStack = SpongeIconBuilder.builder(ItemTypes.WRITTEN_BOOK).name(Text.of(mcdndPlayer.getName())).description(Text.of(TextColors.GREEN, "LEFT-CLICK", TextColors.RESET, " to edit."), Text.of(TextColors.RED, "RIGHT-CLICK", TextColors.RESET, " to delete.")).build();
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerSheetGUI(mcdndPlayer, p), ClickInventoryEvent.Secondary.class, p -> {
                    playerSheetStorage.delete(p.getUniqueId(), mcdndPlayer.getID());
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.PREVIOUS_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(mcdndPlayers.size() / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.NEXT_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
