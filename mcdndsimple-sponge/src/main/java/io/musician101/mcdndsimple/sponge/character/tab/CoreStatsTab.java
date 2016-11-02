package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.CoreStats;
import io.musician101.mcdndsimple.sponge.character.Experience;
import io.musician101.mcdndsimple.sponge.character.HitDice;
import io.musician101.mcdndsimple.sponge.character.HitPoints;
import io.musician101.mcdndsimple.sponge.character.bonus.Bonuses;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class CoreStatsTab implements DataSerializable
{
    private boolean inspiration = false;
    private Bonuses bonuses = new Bonuses();
    private CoreStats coreStats = new CoreStats();
    private Experience experience = new Experience();
    private HitDice hitDice = new HitDice();
    private HitPoints hitPoints = new HitPoints();
    private int initiativeBonus = 0;
    private int speed = 30;

    public Bonuses getBonuses()
    {
        return bonuses;
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.BONUSES, bonuses.toContainer())
                .set(MCDNDSimpleKeys.CORE_STATS, coreStats.toContainer())
                .set(MCDNDSimpleKeys.EXPERIENCE, experience.toContainer())
                .set(MCDNDSimpleKeys.HIT_DICE, hitDice.toContainer())
                .set(MCDNDSimpleKeys.HIT_POINTS, hitPoints.toContainer())
                .set(MCDNDSimpleKeys.INITIATIVE_BONUS, initiativeBonus)
                .set(MCDNDSimpleKeys.SPEED, speed);
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

    public void setHitPoints(HitPoints hitPoints)
    {
        this.hitPoints = hitPoints;
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

    public static CoreStatsTab fromDataContainer(DataContainer dataContainer)
    {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.BONUSES).ifPresent(data -> coreStatsTab.setBonuses(Bonuses.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CORE_STATS).ifPresent(data -> coreStatsTab.setCoreStats(CoreStats.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.EXPERIENCE).ifPresent(data -> coreStatsTab.setExperience(Experience.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.HIT_DICE).ifPresent(data -> coreStatsTab.setHitDice(HitDice.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.HIT_POINTS).ifPresent(data -> coreStatsTab.setHitPoints(HitPoints.fromDataContainer(data)));
        dataContainer.getInt(MCDNDSimpleKeys.INITIATIVE_BONUS.getQuery()).ifPresent(coreStatsTab::setInitiativeBonus);
        dataContainer.getInt(MCDNDSimpleKeys.SPEED.getQuery()).ifPresent(coreStatsTab::setSpeed);
        return coreStatsTab;
    }
}
