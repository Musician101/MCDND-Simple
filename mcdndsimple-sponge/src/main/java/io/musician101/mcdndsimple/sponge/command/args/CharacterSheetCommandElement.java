package io.musician101.mcdndsimple.sponge.command.args;

import io.musician101.mcdndsimple.common.Reference.Commands;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CharacterSheetCommandElement extends CommandElement {

    public CharacterSheetCommandElement() {
        super(Text.of(Commands.NAME));
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, @Nonnull CommandArgs args) throws ArgumentParseException {
        if (source instanceof Player) {
            return SpongeMCDNDSimple.instance().getCharacterSheetStorage().getPlayerSheet(((Player) source).getUniqueId(), args.peek()).orElseThrow(() -> args.createError(Text.of(TextColors.RED, Messages.CHARACTER_DNE)));
        }

        throw args.createError(Text.of(TextColors.RED, Messages.PLAYER_ONLY));
    }

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource src, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        if (src instanceof Player) {
            List<String> list = new ArrayList<>();
            SpongeMCDNDSimple.instance().getCharacterSheetStorage().getPlayerSheets().forEach((key, value) -> {
                if (((Player) src).getUniqueId().equals(value)) {
                    list.add(key.getName());
                }
            });
            return list;
        }

        return Collections.emptyList();
    }
}
