package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;

import java.util.List;

public class Weight
{
    private double inventory = 0.0;
    private double coin = 0.0;
    private double other = 0.0;
    private double carryingMax = 150;
    private double pushDragLift = 300;
    private double encumbered = 50;
    private double heavilyEncumbered = 100;

    public boolean isEncumbered()
    {
        return getWeight() <= encumbered;
    }

    public boolean isHeavilyEncumbered()
    {
        return getWeight() <= heavilyEncumbered;
    }

    public double getWeight()
    {
        return inventory + coin;
    }

    public double getCarryingMax()
    {
        return carryingMax;
    }

    public double getCoin()
    {
        return coin;
    }

    public double getEncumbered()
    {
        return encumbered;
    }

    public double getHeavilyEncumbered()
    {
        return heavilyEncumbered;
    }

    public double getInventory()
    {
        return inventory;
    }

    public double getOther()
    {
        return other;
    }

    public double getPushDragLift()
    {
        return pushDragLift;
    }

    public void setCarryingMax(int strengthScore)
    {
        this.carryingMax = strengthScore * 15D;
        this.pushDragLift = this.carryingMax * 2;
    }

    public void setCoinWeight(Wealth wealth)
    {
        this.coin = wealth.getCopper().getWeight() + wealth.getElectrum().getWeight() + wealth.getGold().getWeight()
                + wealth.getPlatinum().getWeight() + wealth.getSilver().getWeight();
    }

    public void setEncumbered(int strengthScore)
    {
        this.encumbered = strengthScore * 5D;
    }

    public void setHeavilyEncumbered(int strengthScore)
    {
        this.heavilyEncumbered = strengthScore * 10D;
    }

    public void setInventoryWeight(List<MCDNDItem> items)
    {
        double inventoryWeight = 0.0;
        for (MCDNDItem item : items)
            inventoryWeight =+ item.getWeight();

        this.inventory = inventoryWeight;
    }

    public void setOther(double other)
    {
        this.other = other;
    }
}