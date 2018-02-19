package io.musician101.mcdndsimple.common.character.player;

import javax.annotation.Nonnull;

public abstract class AbilityScore {

    @Nonnull
    private final String name;
    @Nonnull
    private final String shortName;
    private int score = 0;

    protected AbilityScore(@Nonnull String name, @Nonnull String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getMod() {
        return (score - 10) / 2;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Nonnull
    public String getShortName() {
        return shortName;
    }
}
