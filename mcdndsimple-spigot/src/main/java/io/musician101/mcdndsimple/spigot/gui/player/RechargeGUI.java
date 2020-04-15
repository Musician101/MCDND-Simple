package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.Recharge;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.clazz.ClassActionGUI;
import io.musician101.mcdndsimple.spigot.gui.player.clazz.ClassResourceGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.util.TriConsumer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class RechargeGUI<R extends Rechargeable> extends SpigotMCDNDSimpleGUI {

    public static RechargeableFunctions<ClassAction> CLASS_ACTIONS = new RechargeableFunctions<>(playerSheet -> playerSheet.getCharacterSheet().getClassTab().getClassActions(), ClassActionGUI::new);
    public static RechargeableFunctions<ClassResource> CLASS_RESOURCES = new RechargeableFunctions<>(playerSheet -> playerSheet.getCharacterSheet().getClassTab().getClassResources(), ClassResourceGUI::new);

    @Nonnull
    private final Rechargeable rechargeable;
    private int page = 1;

    public RechargeGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull RechargeableFunctions<R> function, int index, @Nonnull Player player) {
        super(player, MenuText.RECHARGE, 54);
        this.rechargeable = function.getList(mcdndPlayer).get(index);
        updateSlots();
        setButton(49, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> function.handleBackButton(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                Recharge recharge = Recharge.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.of(Material.RED_BED, (recharge == rechargeable.getRecharge() ? ChatColor.GREEN : ChatColor.RED) + recharge.getName());
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
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
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(Recharge.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
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
