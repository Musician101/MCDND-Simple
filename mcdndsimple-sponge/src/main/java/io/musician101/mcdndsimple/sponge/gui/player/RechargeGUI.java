package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.Recharge;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.util.TriConsumer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class RechargeGUI<R extends Rechargeable> extends SpongeMCDNDSimpleGUI {

    public static RechargeableFunctions<ClassAction> CLASS_ACTIONS = new RechargeableFunctions<>(playerSheet -> playerSheet.getCharacterSheet().getClassTab().getClassActions(), ClassActionGUI::new);
    public static RechargeableFunctions<ClassResource> CLASS_RESOURCES = new RechargeableFunctions<>(playerSheet -> playerSheet.getCharacterSheet().getClassTab().getClassResources(), ClassResourceGUI::new);

    @Nonnull
    private final Rechargeable rechargeable;
    private int page = 1;

    public RechargeGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull RechargeableFunctions<R> function, int index, @Nonnull Player player) {
        super(player, MenuText.RECHARGE, 54);
        this.rechargeable = function.getList(mcdndPlayer).get(index);
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> function.handleBackButton(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                Recharge recharge = Recharge.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.of(ItemTypes.BED, Text.of(recharge == rechargeable.getRecharge() ? TextColors.GREEN : TextColors.RED, recharge.getName()));
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                    rechargeable.setRecharge(recharge);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException ignored) {
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

        int maxPage = Double.valueOf(Math.ceil(Recharge.values().length / 45D)).intValue();
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

    private static class RechargeableFunctions<E extends Rechargeable> {

        @Nonnull
        private final TriConsumer<MCDNDPlayer, Integer, Player> backButtonHandler;
        @Nonnull
        private final Function<MCDNDPlayer, List<E>> listGetter;

        public RechargeableFunctions(@Nonnull Function<MCDNDPlayer, List<E>> listGetter, @Nonnull TriConsumer<MCDNDPlayer, Integer, Player> backButtonHandler) {
            this.listGetter = listGetter;
            this.backButtonHandler = backButtonHandler;
        }

        public List<E> getList(MCDNDPlayer MCDNDPlayer) {
            return listGetter.apply(MCDNDPlayer);
        }

        public void handleBackButton(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
            backButtonHandler.accept(mcdndPlayer, index, player);
        }
    }
}
