package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;

public class AbilityScore {

    private final String name;
    private final String shortName;
    private boolean proficient = false;
    private int score = 0;

    public AbilityScore(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getMod() {
        return (score - 10) / 2;
    }

    public String getName() {
        return name;
    }

    public int getSaveMod(ClassLevels classLevels, Experience experience) {
        int mod = getMod();
        if (proficient) {
            return mod + experience.getProficiencyBonus(classLevels);
        }

        return mod;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setIsProficient(boolean proficient) {
        this.proficient = proficient;
    }
}
