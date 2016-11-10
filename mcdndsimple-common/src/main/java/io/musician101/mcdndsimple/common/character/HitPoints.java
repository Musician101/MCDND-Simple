package io.musician101.mcdndsimple.common.character;

public class HitPoints
{
    private int current = 0;
    private int max = 0;
    private int temp = 0;

    public int getCurrent()
    {
        return current;
    }

    public int getMax()
    {
        return max;
    }

    public int getTemp()
    {
        return temp;
    }

    public void setCurrent(int current)
    {
        this.current = current;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public void setTemp(int temp)
    {
        this.temp = temp;
    }
}
