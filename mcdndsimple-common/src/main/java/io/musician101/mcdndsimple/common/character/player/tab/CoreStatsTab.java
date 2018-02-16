package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

@JsonKey(key = Keys.CORE_STATS_TAB, typeAdapter = CoreStatsTab.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<CoreStatsTab>, JsonSerializer<CoreStatsTab> {

        @Override
        public CoreStatsTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStatsTab coreStatsTab = new CoreStatsTab();
            JsonKeyProcessor.<JsonObject, Bonuses>getJsonKey(Keys.BONUSES).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setBonuses));
            Keys.INSPIRATION.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setHasInspiration);
            JsonKeyProcessor.<JsonObject, CoreStats>getJsonKey(Keys.CORE_STATS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setCoreStats));
            JsonKeyProcessor.<JsonObject, DeathSavingThrows>getJsonKey(Keys.DEATH_SAVING_THROWS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setDeathSavingThrows));
            Keys.EXPERIENCE.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab.getExperience()::setExp);
            JsonKeyProcessor.<JsonObject, HitDice>getJsonKey(Keys.HIT_DICE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setHitDice));
            JsonKeyProcessor.<JsonObject, HitPoints>getJsonKey(Keys.HIT_POINTS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setHitPoints));
            Keys.INITIATIVE_BONUS.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab.getInitiative()::setBonus);
            Keys.SPEED.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setSpeed);
            return coreStatsTab;
        }

        @Override
        public JsonElement serialize(CoreStatsTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, Bonuses>getJsonKey(Keys.BONUSES).ifPresent(jsonKey -> jsonKey.serialize(src.getBonuses(), jsonObject, context));
            Keys.INSPIRATION.serialize(src.hasInspiration(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, CoreStats>getJsonKey(Keys.CORE_STATS).ifPresent(jsonKey -> jsonKey.serialize(src.getCoreStats(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, DeathSavingThrows>getJsonKey(Keys.DEATH_SAVING_THROWS).ifPresent(jsonKey -> jsonKey.serialize(src.getDeathSavingThrows(), jsonObject, context));
            Keys.EXPERIENCE.serialize(src.getExperience().getXP(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, HitDice>getJsonKey(Keys.HIT_DICE).ifPresent(jsonKey -> jsonKey.serialize(src.getHitDice(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, HitPoints>getJsonKey(Keys.HIT_POINTS).ifPresent(jsonKey -> jsonKey.serialize(src.getHitPoints(), jsonObject, context));
            Keys.INITIATIVE_BONUS.serialize(src.getInitiative().getBonus(), jsonObject, context);
            Keys.SPEED.serialize(src.getSpeed(), jsonObject, context);
            return jsonObject;
        }
    }
}
