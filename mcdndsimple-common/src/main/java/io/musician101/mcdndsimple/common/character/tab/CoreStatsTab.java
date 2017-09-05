package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.bonus.Bonuses;

public class CoreStatsTab
{
    private boolean inspiration = false;
    private Bonuses bonuses = new Bonuses();
    private CoreStats coreStats = new CoreStats();
    private Experience experience = new Experience();
    private HitDice hitDice = new HitDice();
    private HitPoints hitPoints = new HitPoints();
    private Initiative initiative = new Initiative();
    private int speed = 30;

    public Bonuses getBonuses()
    {
        return bonuses;
    }

    public CoreStats getCoreStats()
    {
        return coreStats;
    }

    public Experience getExperience()
    {
        return experience;
    }

    public HitDice getHitDice()
    {
        return hitDice;
    }

    public HitPoints getHitPoints()
    {
        return hitPoints;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public int getSpeed()
    {
        return speed;
    }

    public boolean isInspiration()
    {
        return inspiration;
    }

    public void setBonuses(Bonuses bonuses)
    {
        this.bonuses = bonuses;
    }

    public void setCoreStats(CoreStats coreStats)
    {
        this.coreStats = coreStats;
    }

    public void setExperience(Experience experience)
    {
        this.experience = experience;
    }

    public void setHitDice(HitDice hitDice)
    {
        this.hitDice = hitDice;
    }

    public void setHitPoints(HitPoints hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public void setInspiration(boolean inspiration)
    {
        this.inspiration = inspiration;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}
