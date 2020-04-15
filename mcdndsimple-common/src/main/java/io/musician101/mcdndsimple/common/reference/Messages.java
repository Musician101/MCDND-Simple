package io.musician101.mcdndsimple.common.reference;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import javax.annotation.Nonnull;

//TODO move to factory for auto formatting
@Deprecated
public class Messages {

    public static final String LOAD_COMPLETE = "Let the adventures begin...";
    public static final String PREFIX = "[MCDNDS] ";
    public static final String CLASS_ACTION_DELETED = PREFIX + "Class action deleted.";
    public static final String CLASS_RESOURCE_DELETED = PREFIX + "Class resource deleted.";
    public static final String ITEM_DELETED = PREFIX + "Item deleted.";
    public static final String NO_PERMISSION = PREFIX + "You do not have permission to run this command.";
    public static final String PLAYER_ONLY = PREFIX + "Only a player can run this command.";
    public static final String CHARACTER_DNE = PREFIX + "That character does not exist or you do not have access to that character.";
    public static final String CHARACTER_ALREADY_EXISTS = PREFIX + "That character already exists.";
    public static final String CHARACTER_CREATED = PREFIX + "Character created.";
    public static final String ARMOR_DELETED = PREFIX + "Armor deleted.";

    private Messages() {

    }

    @Nonnull
    public static String[] initiative(@Nonnull BioAndInfo bioAndInfo, @Nonnull String name, @Nonnull String result) {
        return new String[]{name + " (as " + bioAndInfo.getName() + ") has rolled for Initiative.", "The result is " + result};
    }

    @Nonnull
    public static String malformedDiceInput(@Nonnull String diceInput) {
        return "Invalid dice input: " + diceInput;
    }

    @Nonnull
    public static String[] rolledHitDice(@Nonnull BioAndInfo bioAndInfo, @Nonnull String name, int result, @Nonnull Dice dice) {
        return new String[]{name + " (as " + bioAndInfo.getName() + ") has expended a hit die " + dice.getAmount() + "d" + dice.getAmount() + ".", "The result was " + result + "."};
    }
}
