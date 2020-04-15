package io.musician101.mcdndsimple.forge.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.musician101.mcdndsimple.common.reference.Reference.Commands;
import net.minecraft.util.text.TextComponentString;

public class CharacterTypeArgumentType implements ArgumentType<String> {

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String string = reader.readString().toLowerCase();
        switch (string) {
            case Commands.NPC_NAME:
            case Commands.PC_NAME:
                return string;
        }

        throw new SimpleCommandExceptionType(new TextComponentString("Invalid character type.")).create();
    }
}
