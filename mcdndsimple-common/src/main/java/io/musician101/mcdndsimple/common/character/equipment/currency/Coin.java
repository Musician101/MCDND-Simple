package io.musician101.mcdndsimple.common.character.equipment.currency;

public class Coin
{
    private final String name;
    private int amount = 0;
    private final String shortName;

    public Coin(String name, String shortName)
    {
        this.name = name;
        this.shortName = shortName;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getName()
    {
        return name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public double getWeight()
    {
        return 0.02 * getAmount();
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
