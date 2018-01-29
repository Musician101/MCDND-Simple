package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import java.util.List;

public class Weight {

    private double other = 0.0;

    public double getCarryingMax(CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 15D;
    }

    public double getCoin(Wealth wealth) {
        return wealth.getCopper().getWeight() + wealth.getElectrum().getWeight() + wealth.getGold().getWeight() + wealth.getPlatinum().getWeight() + wealth.getSilver().getWeight();
    }

    public double getEncumbered(CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 5D;
    }

    public double getHeavilyEncumbered(CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 10D;
    }

    public double getInventory(List<MCDNDItem> items) {
        double inventoryWeight = 0.0;
        for (MCDNDItem item : items) {
            inventoryWeight = +item.getWeight();
        }

        return inventoryWeight;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    //TODO need to add FindBugs to common module
    public double getPushDragLift(CoreStats coreStats) {
        return getCarryingMax(coreStats) * 2;
    }

    public double getWeight(List<MCDNDItem> items, Wealth wealth) {
        return getInventory(items) + getCoin(wealth) + other;
    }

    public boolean isEncumbered(CoreStats coreStats, List<MCDNDItem> items, Wealth wealth) {
        return getWeight(items, wealth) <= getEncumbered(coreStats);
    }

    public boolean isHeavilyEncumbered(CoreStats coreStats, List<MCDNDItem> items, Wealth wealth) {
        return getWeight(items, wealth) <= getHeavilyEncumbered(coreStats);
    }
}
