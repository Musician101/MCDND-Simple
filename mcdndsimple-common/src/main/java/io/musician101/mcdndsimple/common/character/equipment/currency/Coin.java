package io.musician101.mcdndsimple.common.character.equipment.currency;

public class Coin {

    private final String name;
    private final String shortName;
    private int amount = 0;

    public Coin(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public double getWeight() {
        return 0.02 * getAmount();
    }
}
