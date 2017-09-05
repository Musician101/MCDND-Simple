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

    public boolean isEncumbered(CoreStats coreStats)
    {
        return getWeight() <= getEncumbered(coreStats);
    }

    public boolean isHeavilyEncumbered(CoreStats coreStats)
    {
        return getWeight() <= getHeavilyEncumbered(coreStats);
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

    public double getEncumbered(CoreStats coreStats)
    {
        return coreStats.getStrength().getScore() * 5D;
    }

    public double getHeavilyEncumbered(CoreStats coreStats)
    {
        return coreStats.getStrength().getScore() * 10D;
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

    public void setCarryingMax(CoreStats coreStats)
    {
        this.carryingMax = coreStats.getStrength().getScore() * 15D;
        this.pushDragLift = this.carryingMax * 2;
    }

    public void setCoinWeight(Wealth wealth)
    {
        this.coin = wealth.getCopper().getWeight() + wealth.getElectrum().getWeight() + wealth.getGold().getWeight()
                + wealth.getPlatinum().getWeight() + wealth.getSilver().getWeight();
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
