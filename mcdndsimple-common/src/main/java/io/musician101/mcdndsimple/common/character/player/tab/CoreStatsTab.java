package io.musician101.mcdndsimple.common.character.player.tab;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;

public class CoreStatsTab {

    private Bonuses bonuses = new Bonuses();
    private CoreStats coreStats = new CoreStats();
    private Experience experience = new Experience();
    private HitDice hitDice = new HitDice();
    private HitPoints hitPoints = new HitPoints();
    private Initiative initiative = new Initiative();
    private boolean inspiration = false;
    private int speed = 30;

    public Bonuses getBonuses() {
        return bonuses;
    }

    public void setBonuses(Bonuses bonuses) {
        this.bonuses = bonuses;
    }

    public CoreStats getCoreStats() {
        return coreStats;
    }

    public void setCoreStats(CoreStats coreStats) {
        this.coreStats = coreStats;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public HitDice getHitDice() {
        return hitDice;
    }

    public void setHitDice(HitDice hitDice) {
        this.hitDice = hitDice;
    }

    public HitPoints getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(HitPoints hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean hasInspiration() {
        return inspiration;
    }

    public void setHasInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }
}
