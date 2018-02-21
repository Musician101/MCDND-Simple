package io.musician101.mcdndsimple.sponge.command.args;

import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.reference.Commands;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.character.SpongePlayerSheetStorage;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        return context.<String>getOne(getKey()).filter(arg -> src instanceof Player).map(arg -> SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getPlayerSheetStorage).map(SpongePlayerSheetStorage::getCharacters).map(playerSheets -> playerSheets.stream().filter(playerSheet -> playerSheet.isController(((Player) src).getUniqueId())).map(PlayerSheet::getName).filter(name -> name.startsWith(arg)).collect(Collectors.toList())).orElse(Collections.emptyList())).orElse(Collections.emptyList());
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, @Nonnull CommandArgs args) throws ArgumentParseException {
        if (source instanceof Player) {
            Optional<SpongePlayerSheetStorage> spongePlayerSheetStorage = SpongeMCDNDSimple.instance().map(SpongeMCDNDSimple::getPlayerSheetStorage);
            if (spongePlayerSheetStorage.isPresent()) {
                Optional<PlayerSheet> playerSheet = spongePlayerSheetStorage.get().getCharacter(((Player) source).getUniqueId(), args.next());
                if (playerSheet.isPresent()) {
                    return playerSheet.get();
                }
            }

            throw args.createError(Text.of(TextColors.RED, Messages.CHARACTER_DNE));
        }

        throw args.createError(Text.of(TextColors.RED, Messages.PLAYER_ONLY));
    }
}
