package io.musician101.mcdndsimple.sponge.character;

public class ClassResource
{
    private int currentCharges = 0;
    private int maxCharges = 0;
    private Recharge recharge = Recharge.NONE;
    private String name = "";

    public int getCurrentCharges()
    {
        return currentCharges;
    }

    public int getMaxCharges()
    {
        return maxCharges;
    }

    public String getName()
    {
        return name;
    }

    public Recharge getRecharge()
    {
        return recharge;
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

    public void setRecharge(Recharge recharge)
    {
        this.recharge = recharge;
    }
}
