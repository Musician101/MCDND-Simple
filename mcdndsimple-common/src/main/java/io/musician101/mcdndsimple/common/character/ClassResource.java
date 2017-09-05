package io.musician101.mcdndsimple.common.character;

public class ClassResource extends Rechargeable
{
    private int currentCharges = 0;
    private int maxCharges = 0;
    private String name = "";

    public int getUsesLeft()
    {
        return currentCharges;
    }

    public int getMaxUses()
    {
        return maxCharges;
    }

    public String getName()
    {
        return name;
    }

    public void setCurrentCharges(int currentCharges)
    {
        this.currentCharges = currentCharges;
    }

    public void setMaxCharges(int maxCharges)
    {
        this.maxCharges = maxCharges;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
