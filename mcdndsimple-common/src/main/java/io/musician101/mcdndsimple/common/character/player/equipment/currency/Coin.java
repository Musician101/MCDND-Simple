package io.musician101.mcdndsimple.common.character.player.equipment.currency;

import javax.annotation.Nonnull;

public class Coin {

    @Nonnull
    private final String name;
    @Nonnull
    private final String shortName;
    private int amount = 0;

    public Coin(@Nonnull String name, @Nonnull String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getShortName() {
        return shortName;
    }

    public double getWeight() {
        return 0.02 * getAmount();
    }
}
