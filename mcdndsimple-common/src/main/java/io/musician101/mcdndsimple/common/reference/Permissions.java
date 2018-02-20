package io.musician101.mcdndsimple.common.reference;

public class Permissions {

    private static final String BASE = "mcdnd.";
    public static final String CALLBACK = BASE + Commands.CALLBACK_NAME;
    public static final String CHARACTER = BASE + Commands.PLAYER_SHEET_NAME;
    public static final String DM = BASE + "dm";
    public static final String NPC = BASE + Commands.NPC_NAME;

    private Permissions() {

    }
}
