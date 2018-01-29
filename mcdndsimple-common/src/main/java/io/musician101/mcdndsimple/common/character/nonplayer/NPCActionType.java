package io.musician101.mcdndsimple.common.character.nonplayer;

public enum NPCActionType {

    BONUS("Bonus"),
    LAIR("Lair"),
    LEGENDARY("Legendary"),
    NORMAL("Normal"),
    OTHER("Other"),
    REACTION("Reaction"),
    SPECIAL("Special");

    private final String name;

    NPCActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
