package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeys;
import java.lang.reflect.Type;
import java.util.stream.Stream;

@JsonKeys(keys = {Keys.SPELLCASTER_CLASS, Keys.GAINED_FROM}, typeAdapter = SpellcasterClass.Serializer.class)
public enum SpellcasterClass {
    ARCANE_TRICKSTER("Arcane Trickster"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    ELDRITCH_KNIGHT("Eldritch Knight"),
    PALADIN("Paladin"),
    OTHER("Other"),
    RANGER("Ranger"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String name;

    SpellcasterClass(String name) {
        this.name = name;
    }

    public int get1stLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                        return 3;
                    default:
                        return 4;
                }
            case BARD:
                switch (classLevels.getBard()) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    default:
                        return 4;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    default:
                        return 4;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    default:
                        return 4;
                }
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                        return 3;
                    default:
                        return 4;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    default:
                        return 4;
                }
            case PALADIN:
                switch (classLevels.getPaladin()) {
                    case 0:
                    case 1:
                        return 0;
                    case 2:
                        return 2;
                    case 3:
                    case 4:
                    case 5:
                        return 3;
                    default:
                        return 20;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 0:
                        return 0;
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    default:
                        return 4;
                }
            default:
                return 0;
        }
    }

    public int get2ndLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 1;
                    case 5:
                    case 6:
                    case 7:
                        return 2;
                    default:
                        return 3;
                }
            case BARD:
                switch (classLevels.getBard()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        return 3;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        return 3;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        return 3;
                }
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                    case 6:
                    case 7:
                        return 2;
                    default:
                        return 3;
                }
            case PALADIN:
                switch (classLevels.getPaladin()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                    case 6:
                    case 7:
                        return 2;
                    default:
                        return 3;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        return 3;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                        return 2;
                    default:
                        return 3;
                }
            default:
                return 0;
        }
    }

    public int get3rdLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 13:
                    case 14:
                    case 15:
                        return 2;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case BARD:
                switch (classLevels.getBard()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                        return 2;
                    default:
                        return 3;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                        return 2;
                    default:
                        return 3;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                        return 2;
                    default:
                        return 3;
                }
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 13:
                    case 14:
                    case 15:
                        return 2;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case PALADIN:
                switch (classLevels.getPaladin()) {
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                        return 2;
                    default:
                        return 3;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return 0;
                    case 5:
                        return 2;
                    default:
                        return 3;
                }
        }

        return 0;
    }

    public int get4thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case BARD:
                switch (classLevels.getBard()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 0;
                    case 7:
                        return 1;
                    case 8:
                        return 2;
                    default:
                        return 3;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 0;
                    case 7:
                        return 1;
                    case 8:
                        return 2;
                    default:
                        return 3;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 0;
                    case 7:
                        return 1;
                    case 8:
                        return 2;
                    default:
                        return 3;
                }
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 0;
                    case 7:
                        return 1;
                    case 8:
                        return 2;
                    default:
                        return 3;
                }
            case PALADIN:
                switch (classLevels.getPaladin()) {
                    case 13:
                    case 14:
                        return 1;
                    case 15:
                    case 16:
                        return 2;
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 0;
                    case 7:
                        return 1;
                    case 8:
                        return 2;
                    default:
                        return 3;
                }
            default:
                return 0;
        }
    }

    public int get5thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                switch (classLevels.getBard()) {
                    case 9:
                        return 1;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return 2;
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 9:
                        return 1;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return 2;
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 9:
                        return 1;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return 2;
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case PALADIN:
                switch (classLevels.getPaladin()) {
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 9:
                        return 1;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return 2;
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 9:
                        return 1;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return 2;
                    case 18:
                    case 19:
                    case 20:
                        return 3;
                    default:
                        return 0;
                }
        }

        return 0;
    }

    public int get6thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                switch (classLevels.getBard()) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        return 1;
                    case 19:
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    public int get7thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                switch (classLevels.getBard()) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        return 1;
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        return 1;
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        return 1;
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        return 1;
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        return 1;
                    case 20:
                        return 2;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    public int get8thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                switch (classLevels.getBard()) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    public int get9thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                switch (classLevels.getBard()) {
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return 2;
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 1;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    public int getCantripsAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 3;
                    default:
                        return 4;
                }
            case BARD:
                switch (classLevels.getBard()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 3;
                    default:
                        return 4;
                }
            case CLERIC:
                switch (classLevels.getCleric()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 3;
                    default:
                        return 4;
                }
            case DRUID:
                switch (classLevels.getDruid()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 3;
                    default:
                        return 4;
                }
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 2;
                    default:
                        return 3;
                }
            case RANGER:
                switch (classLevels.getRanger()) {
                    case 2:
                        return 2;
                    case 3:
                    case 4:
                        return 3;
                    case 5:
                    case 6:
                        return 4;
                    case 7:
                    case 8:
                        return 5;
                    case 9:
                    case 10:
                        return 6;
                    case 11:
                    case 12:
                        return 7;
                    case 13:
                    case 14:
                        return 8;
                    case 15:
                    case 16:
                        return 9;
                    case 17:
                    case 18:
                        return 10;
                    case 19:
                    case 20:
                        return 11;
                    default:
                        return 0;
                }
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 3;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 4;
                    default:
                        return 5;
                }
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 3;
                    default:
                        return 4;
                }
            case WIZARD:
                switch (classLevels.getWizard()) {
                    case 0:
                        return 0;
                    case 1:
                    case 2:
                    case 3:
                        return 3;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        return 4;
                    default:
                        return 5;
                }
            default:
                return 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getPreparedSpells(CoreStats coreStats, ClassLevels classLevels) {
        switch (this) {
            case CLERIC:
                return classLevels.getCleric() + coreStats.getWisdom().getMod();
            case DRUID:
                return classLevels.getDruid() + coreStats.getWisdom().getMod();
            case PALADIN:
                return classLevels.getPaladin() + coreStats.getCharisma().getMod();
            case WIZARD:
                return classLevels.getWizard() + coreStats.getWisdom().getMod();
        }

        return 0;
    }

    public int getSpellAttack(ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
            case WIZARD:
                return experience.getProficiencyBonus(classLevels) + coreStats.getIntelligence().getMod();
            case BARD:
            case PALADIN:
            case SORCERER:
            case WARLOCK:
                return experience.getProficiencyBonus(classLevels) + coreStats.getCharisma().getMod();
            case CLERIC:
            case DRUID:
            case RANGER:
                return experience.getProficiencyBonus(classLevels) + coreStats.getWisdom().getMod();
        }

        return 0;
    }

    public int getSpellSaveDC(ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
            case WIZARD:
                return 8 + experience.getProficiencyBonus(classLevels) + coreStats.getIntelligence().getMod();
            case BARD:
            case PALADIN:
            case SORCERER:
            case WARLOCK:
                return 8 + experience.getProficiencyBonus(classLevels) + coreStats.getCharisma().getMod();
            case CLERIC:
            case DRUID:
            case RANGER:
                return 8 + experience.getProficiencyBonus(classLevels) + coreStats.getWisdom().getMod();
        }

        return 0;
    }

    public int getSpellsAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                switch (classLevels.getRogue()) {
                    case 3:
                        return 3;
                    case 4:
                    case 5:
                    case 6:
                        return 4;
                    case 7:
                        return 5;
                    case 8:
                    case 9:
                        return 6;
                    case 10:
                        return 7;
                    case 11:
                    case 12:
                        return 8;
                    case 13:
                        return 9;
                    case 14:
                    case 15:
                        return 10;
                    case 16:
                    case 17:
                    case 18:
                        return 11;
                    case 19:
                        return 12;
                    case 20:
                        return 13;
                }
                break;
            case ELDRITCH_KNIGHT:
                switch (classLevels.getFighter()) {
                    case 3:
                        return 3;
                    case 4:
                    case 5:
                    case 6:
                        return 4;
                    case 7:
                        return 5;
                    case 8:
                    case 9:
                        return 6;
                    case 10:
                        return 7;
                    case 11:
                    case 12:
                        return 8;
                    case 13:
                        return 9;
                    case 14:
                    case 15:
                        return 10;
                    case 16:
                    case 17:
                    case 18:
                        return 11;
                    case 19:
                        return 12;
                    case 20:
                        return 13;
                }
                break;
            case BARD:
                switch (classLevels.getBard()) {
                    case 1:
                        return 4;
                    case 2:
                        return 5;
                    case 3:
                        return 6;
                    case 4:
                        return 7;
                    case 5:
                        return 8;
                    case 6:
                        return 9;
                    case 7:
                        return 10;
                    case 8:
                        return 11;
                    case 9:
                        return 12;
                    case 10:
                        return 14;
                    case 11:
                    case 12:
                        return 15;
                    case 13:
                        return 16;
                    case 14:
                        return 18;
                    case 15:
                    case 16:
                        return 19;
                    case 17:
                        return 20;
                    case 18:
                    case 19:
                    case 20:
                        return 22;
                }
                break;
            case RANGER:
                switch (classLevels.getRanger()) {
                    case 2:
                        return 2;
                    case 3:
                    case 4:
                        return 3;
                    case 5:
                    case 6:
                        return 4;
                    case 7:
                    case 8:
                        return 5;
                    case 9:
                    case 10:
                        return 6;
                    case 11:
                    case 12:
                        return 7;
                    case 13:
                    case 14:
                        return 8;
                    case 15:
                    case 16:
                        return 9;
                    case 17:
                    case 18:
                        return 10;
                    case 19:
                    case 20:
                        return 11;
                }
                break;
            case SORCERER:
                switch (classLevels.getSorcerer()) {
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    case 3:
                        return 4;
                    case 4:
                        return 5;
                    case 5:
                        return 6;
                    case 6:
                        return 7;
                    case 7:
                        return 8;
                    case 8:
                        return 9;
                    case 9:
                        return 10;
                    case 10:
                        return 11;
                    case 11:
                    case 12:
                        return 12;
                    case 13:
                    case 14:
                        return 13;
                    case 15:
                    case 16:
                        return 14;
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        return 15;
                }
                break;
            case WARLOCK:
                switch (classLevels.getWarlock()) {
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    case 3:
                        return 4;
                    case 4:
                        return 5;
                    case 5:
                        return 6;
                    case 6:
                        return 7;
                    case 7:
                        return 8;
                    case 8:
                        return 9;
                    case 9:
                    case 10:
                        return 10;
                    case 11:
                    case 12:
                        return 11;
                    case 13:
                    case 14:
                        return 12;
                    case 15:
                    case 16:
                        return 13;
                    case 17:
                    case 18:
                        return 14;
                    case 19:
                    case 20:
                        return 15;
                }
                break;
        }

        return 0;
    }

    public static class Serializer implements JsonDeserializer<SpellcasterClass>, JsonSerializer<SpellcasterClass> {

        @Override
        public SpellcasterClass deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> spellcasterClass.getName().equals(json.getAsString())).findFirst().orElse(SpellcasterClass.OTHER);
        }

        @Override
        public JsonElement serialize(SpellcasterClass src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
