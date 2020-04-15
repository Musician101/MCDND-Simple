package io.musician101.mcdndsimple.sponge.gui;

import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeChestGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public abstract class SpongeMCDNDSimpleGUI extends SpongeChestGUI {

    protected static final ItemStack BACK_ICON = SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK));

    protected SpongeMCDNDSimpleGUI(@Nonnull Player player, @Nonnull String name, int size) {
        super(player, Text.of(name), size, SpongeMCDNDSimple.instance().getPluginContainer(), false);
    }

    @Nonnull
    protected final ItemStack createBook(@Nonnull Player player, @Nonnull String title, List<String> pages) {
        return ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.DISPLAY_NAME, Text.of(title)).add(Keys.BOOK_PAGES, stringListToTextList(pages)).add(Keys.BOOK_AUTHOR, Text.of(player.getName())).build();
    }

    @Nonnull
    protected final List<Text> stringArrayToTextList(@Nonnull String... strings) {
        return stringListToTextList(Arrays.asList(strings));
    }

    @Nonnull
    protected final List<Text> stringListToTextList(@Nonnull List<String> strings) {
        return convert(strings, Text::of);
    }

    @Nonnull
    protected final List<String> textListToStringList(@Nonnull List<Text> texts) {
        return convert(texts, Text::toPlain);
    }

    @Nonnull
    private <S, T> List<T> convert(@Nonnull List<S> list, Function<S, T> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    protected final void broadcastMessage(@Nonnull Text message) {
        SpongeMCDNDSimple.instance().getLogger().info(message.toPlain());
        Sponge.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }

    protected final void openBookCommand(@Nonnull CommandSource source, @Nonnull BioAndInfo bioAndInfo, @Nonnull List<String> pages, @Nonnull Spell spell) {
        if (!(source instanceof Player)) {
            source.sendMessage(Text.of(TextColors.RED, Messages.PLAYER_ONLY));
            return;
        }

        Player player = (Player) source;
        if (!player.getUniqueId().equals(this.player.getUniqueId())) {
            source.sendMessage(Text.of(TextColors.RED, Messages.PREFIX + "You do not have the permission to run this command."));
            return;
        }

        player.sendBookView(BookView.builder().title(Text.of(spell.getName())).addPages(stringListToTextList(pages)).author(Text.of(bioAndInfo.getName())).build());
    }
}
