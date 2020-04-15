package io.musician101.mcdndsimple.sponge.gui;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.nonplayer.NonPlayerGUI;
import io.musician101.mcdndsimple.sponge.gui.player.PlayerSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class EditControllersGUI extends SpongeMCDNDSimpleGUI {

    public static final EditControllersBridger NPC = (abstractPlayer, player) -> new NonPlayerGUI((NonPlayer) abstractPlayer, player);
    public static final EditControllersBridger PLAYER = (abstractPlayer, player) -> new PlayerSheetGUI((MCDNDPlayer) abstractPlayer, player);
    @Nonnull
    private final AbstractPlayer abstractPlayer;
    private int page;

    public EditControllersGUI(@Nonnull AbstractPlayer abstractPlayer, @Nonnull EditControllersBridger bridger, @Nonnull Player player) {
        super(player, MenuText.EDIT_CONTROLLERS, 54);
        this.abstractPlayer = abstractPlayer;
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> bridger.accept(abstractPlayer, p)));
    }

    private void updateSlots() {
        List<UUID> controllers = abstractPlayer.getControllers();
        Server server = Sponge.getServer();
        List<UUID> potentialControllers = Stream.concat(server.getGameProfileManager().getCache().getProfiles().stream(), server.getOnlinePlayers().stream().map(Player::getProfile)).map(GameProfile::getUniqueId).collect(Collectors.toList());
        IntStream.of(0, 45).forEach(x -> {
            try {
                UUID uuid = potentialControllers.get(x + (page - 1) * 45);
                GameProfile gameProfile = GameProfile.of(uuid);
                ItemStack itemStack = SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of((controllers.contains(uuid) ? TextColors.GREEN : TextColors.RED), gameProfile.getName().orElse("NULL"))).addGlow(abstractPlayer.isController(uuid)).build();
                itemStack.offer(Keys.REPRESENTED_PLAYER, gameProfile);
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                    if (controllers.contains(uuid)) {
                        abstractPlayer.removeController(uuid);
                    }
                    else {
                        abstractPlayer.addController(uuid);
                    }

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

        int maxPage = Double.valueOf(Math.ceil(potentialControllers.size() / 45D)).intValue();
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

    private interface EditControllersBridger extends BiConsumer<AbstractPlayer, Player> {

    }
}
