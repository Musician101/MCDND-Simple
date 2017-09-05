package io.musician101.mcdndsimple.common.character;

import java.util.ArrayList;
import java.util.List;

public class MCDNDItem
{
    private boolean carried = true;
    private double weight = 0.0;
    private int quantity = 1;
    private List<String> description = new ArrayList<>();
    private String name = "";

    public List<String> getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight()
    {
        return weight;
    }

    public boolean isCarried()
    {
        return carried;
    }

    public void setIsCarried(boolean carried)
    {
        this.carried = carried;
    }

    public void setDescription(List<String> description)
    {
        this.description = description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}
