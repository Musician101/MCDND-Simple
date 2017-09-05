package io.musician101.mcdndsimple.common.character.spell;

@Deprecated
public class SpellsKnown {

    public static int getCantripsKnown(String clazz, int level) {
        switch (clazz) {
            case "arcane trickster":
                if (level >= 3 && level <= 9) {
                    return 3;
                }
                else if (level >= 10 && level <= 20) {
                    return 4;
                }


                if (level >= 1 && level <= 3) {
                    return 2;
                }
                else if (level >= 4 && level <= 9) {
                    return 3;
                }
                else if (level >= 10 && level <= 20) {
                    return 4;
                }
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
            case "bard":
            case "druid":
            case "warlock":
                if (level >= 1 && level <= 3) {
                    return 2;
                }
                else if (level >= 4 && level <= 9) {
                    return 3;
                }
                else if (level >= 10 && level <= 20) {
                    return 4;
                }
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
            case "cleric":
            case "wizard":
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
            case "eldritch knight":
                if (level >= 3 && level <= 9) {
                    return 2;
                }
                else if (level >= 10 && level <= 20) {
                    return 3;
                }
                break;
            case "ranger":
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
            case "sorcerer":
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

    public static int getSpellsKnown(String clazz, int level) {
        switch (clazz) {
            case "arcane trickster":
            case "eldritch knight":
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
            case "bard":
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
            case "ranger":
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
            case "sorcerer":
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
            case "warlock":
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

    public static int getSpellSlots(String clazz, int level, int spellLevel) {
        switch (clazz) {
            case "arcane trickster":
            case "eldritch knight":
                switch (spellLevel) {
                    case 1:
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
                    case 2:
                        if (level >= 5 && level <= 7) {
                            return 2;
                        }
                        else if (level >= 8 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 3:
                        if (level >= 13 && level <= 15) {
                            return 2;
                        }
                        else if (level >= 16 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 4:
                        if (level == 19 || level == 20) {
                            return 1;
                        }
                        break;
                }
                break;
            case "bard":
            case "cleric":
            case "druid":
            case "sorcerer":
            case "wizard":
                switch (spellLevel) {
                    case 1:
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
                    case 2:
                        if (level == 3) {
                            return 2;
                        }
                        else if (level >= 4 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 3:
                        if (level == 5) {
                            return 2;
                        }
                        else if (level >= 6 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 4:
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
                    case 5:
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
                    case 6:
                        if (level >= 11 && level <= 18) {
                            return 1;
                        }
                        else if (level >= 19 && level <= 20) {
                            return 2;
                        }
                        break;
                    case 7:
                        if (level >= 13 && level <= 19) {
                            return 1;
                        }
                        else if (level == 20) {
                            return 2;
                        }
                        break;
                    case 8:
                        if (level >= 15 && level <= 20) {
                            return 1;
                        }
                        break;
                    case 9:
                        if (level >= 17 && level <= 20) {
                            return 1;
                        }
                        break;
                }
                break;
            case "paladin":
                switch (spellLevel) {
                    case 1:
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
                    case 2:
                        if (level >= 5 && level <= 7) {
                            return 2;
                        }
                        else if (level >= 8 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 3:
                        if (level == 9 || level == 10) {
                            return 2;
                        }
                        else if (level >= 11 && level <= 20) {
                            return 3;
                        }
                        break;
                    case 4:
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
                    case 5:
                        if (level == 17 || level == 18) {
                            return 1;
                        }
                        else if (level == 19 || level == 20) {
                            return 2;
                        }
                        break;
                }
                break;
            case "warlock":
                if (level == 1) {
                    return 1;
                }
                else if (level >= 2 && level <= 10) {
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

    public static int getWarlockSpellLevel(int level) {
        if (level == 1 || level == 2) {
            return 1;
        }
        else if (level == 3 || level == 4) {
            return 2;
        }
        else if (level == 5 || level == 6) {
            return 3;
        }
        else if (level == 7 || level == 8) {
            return 4;
        }

        return 5;
    }
}
