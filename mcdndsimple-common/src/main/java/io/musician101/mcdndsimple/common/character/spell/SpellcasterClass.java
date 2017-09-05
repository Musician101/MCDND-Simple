package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;

public enum SpellcasterClass {
    ARCANE_TRICKSTER("Arcane Trickster"), BARD("Bard"), CLERIC("Cleric"), DRUID("Druid"), ELDRITCH_KNIGHT("Eldritch Knight"), PALADIN("Paladin"), OTHER("Other"), RANGER("Ranger"), SORCERER("Sorcerer"), WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String name;

    SpellcasterClass(String name) {
        this.name = name;
    }

    public int get1stLevelAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
                if (level == 3) {
                    return 2;
                }
                else if (level >= 4 && level <= 6) {
                    return 3;
                }
                else if (level >= 7 && level <= 20) {
                    return 4;
                }
                break;
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level == 1) {
                    return 2;
                }
                else if (level == 2) {
                    return 3;
                }
                else if (level >= 3 && level <= 20) {
                    return 4;
                }
                break;
            case PALADIN:
                if (level == 2) {
                    return 2;
                }
                else if (level >= 3 && level <= 5) {
                    return 3;
                }
                else if (level >= 6 && level <= 20) {
                    return 4;
                }
                break;
            case WARLOCK:
                if (level == 1) {
                    return 1;
                }
                else if (level == 2) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get2ndLevelAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
                if (level >= 5 && level <= 7) {
                    return 2;
                }
                else if (level >= 8 && level <= 20) {
                    return 3;
                }
                break;
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level == 3) {
                    return 2;
                }
                else if (level >= 4 && level <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (level >= 5 && level <= 7) {
                    return 2;
                }
                else if (level >= 8 && level <= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (level == 3 || level == 4) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get3rdLevelAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
                if (level >= 13 && level <= 15) {
                    return 2;
                }
                else if (level >= 16 && level <= 20) {
                    return 3;
                }
                break;
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level == 5) {
                    return 2;
                }
                else if (level >= 6 && level <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (level == 9 || level == 10) {
                    return 2;
                }
                else if (level >= 11 && level <= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (level == 5 || level == 6) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get4thLevelAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
                if (level == 19 || level == 20) {
                    return 1;
                }
                break;
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level == 7) {
                    return 1;
                }
                else if (level == 8) {
                    return 2;
                }
                else if (level >= 9 && level <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (level == 13 || level == 14) {
                    return 1;
                }
                else if (level == 15 || level == 16) {
                    return 2;
                }
                else if (level >= 17 && level >= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (level == 7 || level == 8) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get5thLevelAmount(int level) {
        switch (this) {
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level == 9) {
                    return 1;
                }
                else if (level >= 10 && level <= 17) {
                    return 2;
                }
                else if (level >= 18 && level <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (level == 17 || level == 18) {
                    return 1;
                }
                else if (level == 19 || level == 20) {
                    return 2;
                }
                break;
            case WARLOCK:
                if (level == 9 || level == 10) {
                    return 2;
                }
                else if (level >= 11 && level <= 16) {
                    return 3;
                }
                else if (level >= 17 && level <= 20) {
                    return 4;
                }
                break;
        }

        return 0;
    }

    public int get6thLevelAmount(int level) {
        switch (this) {
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level >= 11 && level <= 18) {
                    return 1;
                }
                else if (level >= 19 && level <= 20) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get7thLevelAmount(int level) {
        switch (this) {
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level >= 13 && level <= 19) {
                    return 1;
                }
                else if (level == 20) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get8thLevelAmount(int level) {
        switch (this) {
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level >= 15 && level <= 20) {
                    return 1;
                }
                break;
        }

        return 0;
    }

    public int get9thLevelAmount(int level) {
        switch (this) {
            case BARD:
            case CLERIC:
            case DRUID:
            case SORCERER:
            case WIZARD:
                if (level >= 17 && level <= 20) {
                    return 1;
                }
                break;
        }

        return 0;
    }

    public int getCantripsAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
                if (level >= 3 && level <= 9) {
                    return 3;
                }
                else if (level >= 10 && level <= 20) {
                    return 4;
                }
                break;
            case BARD:
            case DRUID:
            case WARLOCK:
                if (level >= 1 && level <= 3) {
                    return 2;
                }
                else if (level >= 4 && level <= 9) {
                    return 3;
                }
                else if (level >= 10 && level <= 20) {
                    return 4;
                }
                break;
            case CLERIC:
            case WIZARD:
                if (level >= 1 && level <= 3) {
                    return 3;
                }
                else if (level >= 4 && level <= 9) {
                    return 4;
                }
                else if (level >= 10 && level <= 20) {
                    return 5;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (level >= 3 && level <= 9) {
                    return 2;
                }
                else if (level >= 10 && level <= 20) {
                    return 3;
                }
                break;
            case RANGER:
                if (level == 2) {
                    return 2;
                }
                else if (level == 3 || level == 4) {
                    return 3;
                }
                else if (level == 5 || level == 6) {
                    return 4;
                }
                else if (level == 7 || level == 8) {
                    return 5;
                }
                else if (level == 9 || level == 10) {
                    return 6;
                }
                else if (level == 11 || level == 12) {
                    return 7;
                }
                else if (level == 13 || level == 14) {
                    return 8;
                }
                else if (level == 15 || level == 16) {
                    return 9;
                }
                else if (level == 17 || level == 18) {
                    return 10;
                }
                else if (level == 19 || level == 20) {
                    return 11;
                }
                break;
            case SORCERER:
                if (level >= 1 && level <= 3) {
                    return 3;
                }
                else if (level >= 4 && level <= 9) {
                    return 4;
                }
                else if (level >= 10 && level <= 20) {
                    return 5;
                }
                break;
        }

        return 0;
    }

    public String getName() {
        return name;
    }

    public int getPreparedSpells(CoreStats coreStats, int level) {
        switch (this) {
            case CLERIC:
            case DRUID:
                return level + coreStats.getWisdom().getMod();
            case PALADIN:
                return level + coreStats.getCharisma().getMod();
            case WIZARD:
                return level + coreStats.getWisdom().getMod();
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

    public int getSpellsAmount(int level) {
        switch (this) {
            case ARCANE_TRICKSTER:
            case ELDRITCH_KNIGHT:
                switch (level) {
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
                switch (level) {
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
                switch (level) {
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
                switch (level) {
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
                switch (level) {
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
}
