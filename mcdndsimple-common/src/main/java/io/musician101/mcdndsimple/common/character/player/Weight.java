package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import java.util.List;
import javax.annotation.Nonnull;

public class Weight {

    private double other = 0.0;

    public double getCarryingMax(@Nonnull CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 15D;
    }

    public double getCoin(@Nonnull Wealth wealth) {
        return wealth.getCopper().getWeight() + wealth.getElectrum().getWeight() + wealth.getGold().getWeight() + wealth.getPlatinum().getWeight() + wealth.getSilver().getWeight();
    }

    public double getEncumbered(@Nonnull CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 5D;
    }

    public double getHeavilyEncumbered(@Nonnull CoreStats coreStats) {
        return coreStats.getStrength().getScore() * 10D;
    }

    public double getInventory(@Nonnull List<MCDNDItem> items) {
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

    public double getPushDragLift(@Nonnull CoreStats coreStats) {
        return getCarryingMax(coreStats) * 2;
    }

    public double getWeight(@Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth) {
        return getInventory(items) + getCoin(wealth) + other;
    }

    public boolean isEncumbered(@Nonnull CoreStats coreStats, @Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth) {
        return getWeight(items, wealth) <= getEncumbered(coreStats);
    }

    public boolean isHeavilyEncumbered(@Nonnull CoreStats coreStats, @Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth) {
        return getWeight(items, wealth) <= getHeavilyEncumbered(coreStats);
    }
}
