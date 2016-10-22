package io.musician101.mcdndsimple.sponge.character;

public class MCDNDItem
{
    private boolean carried = true;
    private double weight = 0.0;
    private String description = "";
    private String name = "";

    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public double getWeight()
    {
        return weight;
    }

    public boolean isCarried()
    {
        return carried;
    }

    public void setCarried(boolean carried)
    {
        this.carried = carried;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}
