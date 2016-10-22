package io.musician101.mcdndsimple.sponge.character;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ClassAction
{
    private final List<String> gainedFrom = ImmutableList.<String>builder()
            .add("---", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue",
                    "Sorcerer", "Warlock", "Wizard", "Feat", "Other").build();
    private int max = 0;
    private int used = 0;
    private Recharge recharge = Recharge.NONE;
    private String name = "";
}
