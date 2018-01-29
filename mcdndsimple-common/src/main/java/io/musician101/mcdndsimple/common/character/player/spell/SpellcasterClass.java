package io.musician101.mcdndsimple.common.character.player.spell;

import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;

//TODO rewrite if statements as switch statements
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
                if (classLevels.getRogue() == 3) {
                    return 2;
                }
                else if (classLevels.getRogue() >= 4 && classLevels.getRogue() <= 6) {
                    return 3;
                }
                else if (classLevels.getRogue() >= 7 && classLevels.getRogue() <= 20) {
                    return 4;
                }
                break;
            case BARD:
                if (classLevels.getBard() == 1) {
                    return 2;
                }
                else if (classLevels.getBard() == 2) {
                    return 3;
                }
                else if (classLevels.getBard() >= 3 && classLevels.getBard() <= 20) {
                    return 4;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() == 1) {
                    return 2;
                }
                else if (classLevels.getCleric() == 2) {
                    return 3;
                }
                else if (classLevels.getCleric() >= 3 && classLevels.getCleric() <= 20) {
                    return 4;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() == 1) {
                    return 2;
                }
                else if (classLevels.getDruid() == 2) {
                    return 3;
                }
                else if (classLevels.getDruid() >= 3 && classLevels.getDruid() <= 20) {
                    return 4;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (classLevels.getFighter() == 3) {
                    return 2;
                }
                else if (classLevels.getFighter() >= 4 && classLevels.getFighter() <= 6) {
                    return 3;
                }
                else if (classLevels.getFighter() >= 7 && classLevels.getFighter() <= 20) {
                    return 4;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() == 1) {
                    return 2;
                }
                else if (classLevels.getSorcerer() == 2) {
                    return 3;
                }
                else if (classLevels.getSorcerer() >= 3 && classLevels.getSorcerer() <= 20) {
                    return 4;
                }
                break;
            case PALADIN:
                if (classLevels.getPaladin() == 2) {
                    return 2;
                }
                else if (classLevels.getPaladin() >= 3 && classLevels.getPaladin() <= 5) {
                    return 3;
                }
                else if (classLevels.getPaladin() >= 6 && classLevels.getPaladin() <= 20) {
                    return 4;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() == 1) {
                    return 1;
                }
                else if (classLevels.getWarlock() == 2) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() == 1) {
                    return 2;
                }
                else if (classLevels.getWizard() == 2) {
                    return 3;
                }
                else if (classLevels.getWizard() >= 3 && classLevels.getWizard() <= 20) {
                    return 4;
                }
                break;
        }

        return 0;
    }

    public int get2ndLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                if (classLevels.getRogue() >= 5 && classLevels.getRogue() <= 7) {
                    return 2;
                }
                else if (classLevels.getRogue() >= 8 && classLevels.getRogue() <= 20) {
                    return 3;
                }
                break;
            case BARD:
                if (classLevels.getBard() == 3) {
                    return 2;
                }
                else if (classLevels.getBard() >= 4 && classLevels.getBard() <= 20) {
                    return 3;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() == 3) {
                    return 2;
                }
                else if (classLevels.getCleric() >= 4 && classLevels.getCleric() <= 20) {
                    return 3;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() == 3) {
                    return 2;
                }
                else if (classLevels.getDruid() >= 4 && classLevels.getDruid() <= 20) {
                    return 3;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (classLevels.getFighter() >= 5 && classLevels.getFighter() <= 7) {
                    return 2;
                }
                else if (classLevels.getFighter() >= 8 && classLevels.getFighter() <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (classLevels.getPaladin() >= 5 && classLevels.getPaladin() <= 7) {
                    return 2;
                }
                else if (classLevels.getPaladin() >= 8 && classLevels.getPaladin() <= 20) {
                    return 3;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() == 3) {
                    return 2;
                }
                else if (classLevels.getSorcerer() >= 4 && classLevels.getSorcerer() <= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() == 3 || classLevels.getWarlock() == 4) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() == 3) {
                    return 2;
                }
                else if (classLevels.getWizard() >= 4 && classLevels.getWizard() <= 20) {
                    return 3;
                }
                break;
        }

        return 0;
    }

    public int get3rdLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                if (classLevels.getRogue() >= 13 && classLevels.getRogue() <= 15) {
                    return 2;
                }
                else if (classLevels.getRogue() >= 16 && classLevels.getRogue() <= 20) {
                    return 3;
                }
                break;
            case BARD:
                if (classLevels.getBard() == 5) {
                    return 2;
                }
                else if (classLevels.getBard() >= 6 && classLevels.getBard() <= 20) {
                    return 3;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() == 5) {
                    return 2;
                }
                else if (classLevels.getCleric() >= 6 && classLevels.getCleric() <= 20) {
                    return 3;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() == 5) {
                    return 2;
                }
                else if (classLevels.getDruid() >= 6 && classLevels.getDruid() <= 20) {
                    return 3;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (classLevels.getFighter() >= 13 && classLevels.getFighter() <= 15) {
                    return 2;
                }
                else if (classLevels.getFighter() >= 16 && classLevels.getFighter() <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (classLevels.getPaladin() == 9 || classLevels.getPaladin() == 10) {
                    return 2;
                }
                else if (classLevels.getPaladin() >= 11 && classLevels.getPaladin() <= 20) {
                    return 3;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() == 5) {
                    return 2;
                }
                else if (classLevels.getSorcerer() >= 6 && classLevels.getSorcerer() <= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() == 5 || classLevels.getWarlock() == 6) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() == 5) {
                    return 2;
                }
                else if (classLevels.getWizard() >= 6 && classLevels.getWizard() <= 20) {
                    return 3;
                }
                break;
        }

        return 0;
    }

    public int get4thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                if (classLevels.getRogue() == 19 || classLevels.getRogue() == 20) {
                    return 1;
                }
                break;
            case BARD:
                if (classLevels.getBard() == 7) {
                    return 1;
                }
                else if (classLevels.getBard() == 8) {
                    return 2;
                }
                else if (classLevels.getBard() >= 9 && classLevels.getBard() <= 20) {
                    return 3;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() == 7) {
                    return 1;
                }
                else if (classLevels.getCleric() == 8) {
                    return 2;
                }
                else if (classLevels.getCleric() >= 9 && classLevels.getCleric() <= 20) {
                    return 3;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() == 7) {
                    return 1;
                }
                else if (classLevels.getDruid() == 8) {
                    return 2;
                }
                else if (classLevels.getDruid() >= 9 && classLevels.getDruid() <= 20) {
                    return 3;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (classLevels.getFighter() == 19 || classLevels.getFighter() == 20) {
                    return 1;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() == 7) {
                    return 1;
                }
                else if (classLevels.getSorcerer() == 8) {
                    return 2;
                }
                else if (classLevels.getSorcerer() >= 9 && classLevels.getSorcerer() <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (classLevels.getPaladin() == 13 || classLevels.getPaladin() == 14) {
                    return 1;
                }
                else if (classLevels.getPaladin() == 15 || classLevels.getPaladin() == 16) {
                    return 2;
                }
                else if (classLevels.getPaladin() >= 17 && classLevels.getPaladin() >= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() == 7 || classLevels.getWarlock() == 8) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() == 7) {
                    return 1;
                }
                else if (classLevels.getWizard() == 8) {
                    return 2;
                }
                else if (classLevels.getWizard() >= 9 && classLevels.getWizard() <= 20) {
                    return 3;
                }
                break;
        }

        return 0;
    }

    public int get5thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                if (classLevels.getBard() == 9) {
                    return 1;
                }
                else if (classLevels.getBard() >= 10 && classLevels.getBard() <= 17) {
                    return 2;
                }
                else if (classLevels.getBard() >= 18 && classLevels.getBard() <= 20) {
                    return 3;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() == 9) {
                    return 1;
                }
                else if (classLevels.getCleric() >= 10 && classLevels.getCleric() <= 17) {
                    return 2;
                }
                else if (classLevels.getCleric() >= 18 && classLevels.getCleric() <= 20) {
                    return 3;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() == 9) {
                    return 1;
                }
                else if (classLevels.getDruid() >= 10 && classLevels.getDruid() <= 17) {
                    return 2;
                }
                else if (classLevels.getDruid() >= 18 && classLevels.getDruid() <= 20) {
                    return 3;
                }
                break;
            case PALADIN:
                if (classLevels.getPaladin() == 17 || classLevels.getPaladin() == 18) {
                    return 1;
                }
                else if (classLevels.getPaladin() == 19 || classLevels.getPaladin() == 20) {
                    return 2;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() == 9) {
                    return 1;
                }
                else if (classLevels.getSorcerer() >= 10 && classLevels.getSorcerer() <= 17) {
                    return 2;
                }
                else if (classLevels.getSorcerer() >= 18 && classLevels.getSorcerer() <= 20) {
                    return 3;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() == 9 || classLevels.getWarlock() == 10) {
                    return 2;
                }
                else if (classLevels.getWarlock() >= 11 && classLevels.getWarlock() <= 16) {
                    return 3;
                }
                else if (classLevels.getWarlock() >= 17 && classLevels.getWarlock() <= 20) {
                    return 4;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() == 9) {
                    return 1;
                }
                else if (classLevels.getWizard() >= 10 && classLevels.getWizard() <= 17) {
                    return 2;
                }
                else if (classLevels.getWizard() >= 18 && classLevels.getWizard() <= 20) {
                    return 3;
                }
                break;
        }

        return 0;
    }

    public int get6thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                if (classLevels.getBard() >= 11 && classLevels.getBard() <= 18) {
                    return 1;
                }
                else if (classLevels.getBard() >= 19 && classLevels.getBard() <= 20) {
                    return 2;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() >= 11 && classLevels.getCleric() <= 18) {
                    return 1;
                }
                else if (classLevels.getCleric() >= 19 && classLevels.getCleric() <= 20) {
                    return 2;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() >= 11 && classLevels.getDruid() <= 18) {
                    return 1;
                }
                else if (classLevels.getDruid() >= 19 && classLevels.getDruid() <= 20) {
                    return 2;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() >= 11 && classLevels.getSorcerer() <= 18) {
                    return 1;
                }
                else if (classLevels.getSorcerer() >= 19 && classLevels.getSorcerer() <= 20) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() >= 11 && classLevels.getWizard() <= 18) {
                    return 1;
                }
                else if (classLevels.getWizard() >= 19 && classLevels.getWizard() <= 20) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get7thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                if (classLevels.getBard() >= 13 && classLevels.getBard() <= 19) {
                    return 1;
                }
                else if (classLevels.getBard() == 20) {
                    return 2;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() >= 13 && classLevels.getCleric() <= 19) {
                    return 1;
                }
                else if (classLevels.getCleric() == 20) {
                    return 2;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() >= 13 && classLevels.getDruid() <= 19) {
                    return 1;
                }
                else if (classLevels.getDruid() == 20) {
                    return 2;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() >= 13 && classLevels.getSorcerer() <= 19) {
                    return 1;
                }
                else if (classLevels.getSorcerer() == 20) {
                    return 2;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() >= 13 && classLevels.getWizard() <= 19) {
                    return 1;
                }
                else if (classLevels.getWizard() == 20) {
                    return 2;
                }
                break;
        }

        return 0;
    }

    public int get8thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                if (classLevels.getBard() >= 15 && classLevels.getBard() <= 20) {
                    return 1;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() >= 15 && classLevels.getCleric() <= 20) {
                    return 1;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() >= 15 && classLevels.getDruid() <= 20) {
                    return 1;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() >= 15 && classLevels.getSorcerer() <= 20) {
                    return 1;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() >= 15 && classLevels.getWizard() <= 20) {
                    return 1;
                }
                break;
        }

        return 0;
    }

    public int get9thLevelAmount(ClassLevels classLevels) {
        switch (this) {
            case BARD:
                if (classLevels.getBard() >= 17 && classLevels.getBard() <= 20) {
                    return 1;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() >= 17 && classLevels.getCleric() <= 20) {
                    return 1;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() >= 17 && classLevels.getDruid() <= 20) {
                    return 1;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() >= 17 && classLevels.getSorcerer() <= 20) {
                    return 1;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() >= 17 && classLevels.getWizard() <= 20) {
                    return 1;
                }
                break;
        }

        return 0;
    }

    public int getCantripsAmount(ClassLevels classLevels) {
        switch (this) {
            case ARCANE_TRICKSTER:
                if (classLevels.getRogue() >= 3 && classLevels.getRogue() <= 9) {
                    return 3;
                }
                else if (classLevels.getRogue() >= 10 && classLevels.getRogue() <= 20) {
                    return 4;
                }
                break;
            case BARD:
                if (classLevels.getBard() >= 1 && classLevels.getBard() <= 3) {
                    return 2;
                }
                else if (classLevels.getBard() >= 4 && classLevels.getBard() <= 9) {
                    return 3;
                }
                else if (classLevels.getBard() >= 10 && classLevels.getBard() <= 20) {
                    return 4;
                }
                break;
            case CLERIC:
                if (classLevels.getCleric() >= 1 && classLevels.getCleric() <= 3) {
                    return 3;
                }
                else if (classLevels.getCleric() >= 4 && classLevels.getCleric() <= 9) {
                    return 4;
                }
                else if (classLevels.getCleric() >= 10 && classLevels.getCleric() <= 20) {
                    return 5;
                }
                break;
            case DRUID:
                if (classLevels.getDruid() >= 1 && classLevels.getDruid() <= 3) {
                    return 2;
                }
                else if (classLevels.getDruid() >= 4 && classLevels.getDruid() <= 9) {
                    return 3;
                }
                else if (classLevels.getDruid() >= 10 && classLevels.getDruid() <= 20) {
                    return 4;
                }
                break;
            case ELDRITCH_KNIGHT:
                if (classLevels.getFighter() >= 3 && classLevels.getFighter() <= 9) {
                    return 2;
                }
                else if (classLevels.getFighter() >= 10 && classLevels.getFighter() <= 20) {
                    return 3;
                }
                break;
            case RANGER:
                if (classLevels.getRanger() == 2) {
                    return 2;
                }
                else if (classLevels.getRanger() == 3 || classLevels.getRanger() == 4) {
                    return 3;
                }
                else if (classLevels.getRanger() == 5 || classLevels.getRanger() == 6) {
                    return 4;
                }
                else if (classLevels.getRanger() == 7 || classLevels.getRanger() == 8) {
                    return 5;
                }
                else if (classLevels.getRanger() == 9 || classLevels.getRanger() == 10) {
                    return 6;
                }
                else if (classLevels.getRanger() == 11 || classLevels.getRanger() == 12) {
                    return 7;
                }
                else if (classLevels.getRanger() == 13 || classLevels.getRanger() == 14) {
                    return 8;
                }
                else if (classLevels.getRanger() == 15 || classLevels.getRanger() == 16) {
                    return 9;
                }
                else if (classLevels.getRanger() == 17 || classLevels.getRanger() == 18) {
                    return 10;
                }
                else if (classLevels.getRanger() == 19 || classLevels.getRanger() == 20) {
                    return 11;
                }
                break;
            case SORCERER:
                if (classLevels.getSorcerer() >= 1 && classLevels.getSorcerer() <= 3) {
                    return 3;
                }
                else if (classLevels.getSorcerer() >= 4 && classLevels.getSorcerer() <= 9) {
                    return 4;
                }
                else if (classLevels.getSorcerer() >= 10 && classLevels.getSorcerer() <= 20) {
                    return 5;
                }
                break;
            case WARLOCK:
                if (classLevels.getWarlock() >= 1 && classLevels.getWarlock() <= 3) {
                    return 2;
                }
                else if (classLevels.getWarlock() >= 4 && classLevels.getWarlock() <= 9) {
                    return 3;
                }
                else if (classLevels.getWarlock() >= 10 && classLevels.getWarlock() <= 20) {
                    return 4;
                }
                break;
            case WIZARD:
                if (classLevels.getWizard() >= 1 && classLevels.getWizard() <= 3) {
                    return 3;
                }
                else if (classLevels.getWizard() >= 4 && classLevels.getWizard() <= 9) {
                    return 4;
                }
                else if (classLevels.getWizard() >= 10 && classLevels.getWizard() <= 20) {
                    return 5;
                }
                break;
        }

        return 0;
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
}
