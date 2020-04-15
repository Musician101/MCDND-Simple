package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;


public class CoreStatsTab {

    @Nonnull
    private final Experience experience = new Experience();
    @Nonnull
    private final Initiative initiative = new Initiative();
    @Nonnull
    private Bonuses bonuses = new Bonuses();
    @Nonnull
    private CoreStats<PlayerAbilityScore> coreStats = new CoreStats<>(PlayerAbilityScore.class);
    @Nonnull
    private DeathSavingThrows deathSavingThrows = new DeathSavingThrows();
    @Nonnull
    private HitDice hitDice = new HitDice();
    @Nonnull
    private HitPoints hitPoints = new HitPoints();
    private boolean inspiration = false;
    private int speed = 30;

    @Nonnull
    public Bonuses getBonuses() {
        return bonuses;
    }

    private void setBonuses(@Nonnull Bonuses bonuses) {
        this.bonuses = bonuses;
    }

    @Nonnull
    public CoreStats<PlayerAbilityScore> getCoreStats() {
        return coreStats;
    }

    private void setCoreStats(@Nonnull CoreStats<PlayerAbilityScore> coreStats) {
        this.coreStats = coreStats;
    }

    @Nonnull
    public DeathSavingThrows getDeathSavingThrows() {
        return deathSavingThrows;
    }

    private void setDeathSavingThrows(@Nonnull DeathSavingThrows deathSavingThrows) {
        this.deathSavingThrows = deathSavingThrows;
    }

    @Nonnull
    public Experience getExperience() {
        return experience;
    }

    @Nonnull
    public HitDice getHitDice() {
        return hitDice;
    }

    private void setHitDice(@Nonnull HitDice hitDice) {
        this.hitDice = hitDice;
    }

    @Nonnull
    public HitPoints getHitPoints() {
        return hitPoints;
    }

    private void setHitPoints(@Nonnull HitPoints hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Nonnull
    public Initiative getInitiative() {
        return initiative;
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

    public static class Serializer extends BaseSerializer<CoreStatsTab> {

        @Override
        public CoreStatsTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStatsTab coreStatsTab = new CoreStatsTab();
            coreStatsTab.setBonuses(deserialize(jsonObject, context, Keys.BONUSES));;
            coreStatsTab.setHasInspiration(deserialize(jsonObject, context, Keys.INSPIRATION));
            coreStatsTab.setCoreStats(deserialize(jsonObject, context, Keys.PLAYER_CORE_STATS));
            coreStatsTab.setDeathSavingThrows(deserialize(jsonObject, context, Keys.DEATH_SAVING_THROWS));;
            coreStatsTab.getExperience().setExp(deserialize(jsonObject, context,Keys.EXPERIENCE));
            coreStatsTab.setHitDice(deserialize(jsonObject, context, Keys.HIT_DICE));;
            coreStatsTab.setHitPoints(deserialize(jsonObject, context, Keys.HIT_POINTS));;
            coreStatsTab.getInitiative().setBonus(deserialize(jsonObject, context, Keys.INITIATIVE_BONUS));
            coreStatsTab.setSpeed(deserialize(jsonObject, context, Keys.SPEED));
            return coreStatsTab;
        }

        @Override
        public JsonElement serialize(CoreStatsTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.BONUSES, src.getBonuses());
            serialize(jsonObject, context, Keys.INSPIRATION, src.hasInspiration());
            serialize(jsonObject, context, Keys.PLAYER_CORE_STATS, src.getCoreStats());
            serialize(jsonObject, context, Keys.DEATH_SAVING_THROWS, src.getDeathSavingThrows());
            serialize(jsonObject, context, Keys.EXPERIENCE, src.getExperience().getXP());
            serialize(jsonObject, context, Keys.HIT_DICE, src.getHitDice());
            serialize(jsonObject, context, Keys.HIT_POINTS, src.getHitPoints());
            serialize(jsonObject, context, Keys.INITIATIVE_BONUS, src.getInitiative().getBonus());
            serialize(jsonObject, context, Keys.SPEED, src.getSpeed());
            return jsonObject;
        }
    }
}
