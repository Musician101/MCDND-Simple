package io.musician101.mcdndsimple.sponge.command.args;

import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.reference.Reference.Commands;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource src, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        return context.<String>getOne(getKey()).filter(arg -> src instanceof Player).map(arg -> SpongeMCDNDSimple.instance().getPlayerStorage().getCharacters().stream().filter(playerSheet -> playerSheet.isController(((Player) src).getUniqueId())).map(MCDNDPlayer::getID).filter(name -> name.startsWith(arg)).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, @Nonnull CommandArgs args) throws ArgumentParseException {
        if (source instanceof Player) {
            return SpongeMCDNDSimple.instance().getPlayerStorage().getCharacter(args.next()).orElseThrow(() -> args.createError(Text.of(TextColors.RED, Messages.CHARACTER_DNE)));
        }

        throw args.createError(Text.of(TextColors.RED, Messages.PLAYER_ONLY));
    }
}
