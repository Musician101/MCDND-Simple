package io.musician101.mcdndsimple.common.character;

public class ClassAction
{
    private int max = 0;
    private int used = 0;
    private Recharge recharge = Recharge.NONE;
    private String gainedFrom = "---";
    private String name = "";

    public String getGainedFrom()
    {
        return gainedFrom;
    }

    public int getMax()
    {
        return max;
    }

    public String getName()
    {
        return name;
    }

    public Recharge getRecharge()
    {
        return recharge;
    }

    public int getUsesLeft()
    {
        return used;
    }

    public void setGainedFrom(String gainedFrom)
    {
        this.gainedFrom = gainedFrom;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRecharge(Recharge recharge)
    {
        this.recharge = recharge;
    }

    public void setUsesLeft(int used)
    {
        this.used = used;
    }
}
