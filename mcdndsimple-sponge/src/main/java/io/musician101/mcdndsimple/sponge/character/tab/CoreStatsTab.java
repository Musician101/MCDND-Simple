package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.CoreStats;
import io.musician101.mcdndsimple.sponge.character.Experience;
import io.musician101.mcdndsimple.sponge.character.HitDice;
import io.musician101.mcdndsimple.sponge.character.HitPoints;
import io.musician101.mcdndsimple.sponge.character.bonus.Bonuses;

public class CoreStatsTab
{
    private boolean inspiration = false;
    private Bonuses bonuses = new Bonuses();
    private CoreStats coreStats = new CoreStats();
    private Experience experience = new Experience();
    private HitDice hitDice = new HitDice();
    private HitPoints hitpoints = new HitPoints();
    private int initiativeBonus = 0;
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

    public HitPoints getHitpoints()
    {
        return hitpoints;
    }

    public int getInitiativeBonus()
    {
        return initiativeBonus;
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

    public void setHitpoints(HitPoints hitpoints)
    {
        this.hitpoints = hitpoints;
    }

    public void setInitiativeBonus(int initiativeBonus)
    {
        this.initiativeBonus = initiativeBonus;
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
