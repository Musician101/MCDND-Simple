package io.musician101.mcdndsimple.common.character;

public class AbilityScore
{
    private boolean proficient = false;
    private int score = 0;
    private final String shortName;
    private final String name;

    public AbilityScore(String name, String shortName)
    {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public int getMod()
    {
        return (score - 10) / 2;
    }

    public String getShortName()
    {
        return shortName;
    }

    public boolean isProficient()
    {
        return proficient;
    }

    public void setProficient(boolean proficient)
    {
        this.proficient = proficient;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getSaveMod(ClassLevels classLevels, Experience experience) {
        int mod = getMod();
        if (proficient) {
            return mod + experience.getProficiencyBonus(classLevels);
        }

        return mod;
    }
}
