package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public abstract class Weapon {

    @Nonnull
    private WeaponAttackStat attackStat;
    @Nonnull
    private Dice critDamage = new Dice(0);
    private int critMin = 20;
    @Nonnull
    private Dice damage = new Dice(0);
    @Nonnull
    private String damageType = "";
    private boolean isProficient = true;
    private int magicBonus = 0;
    @Nonnull
    private String name = "";

    protected Weapon(@Nonnull WeaponAttackStat defaultAttackStat) {
        this.attackStat = defaultAttackStat;
    }

    @Nonnull
    public WeaponAttackStat getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(@Nonnull WeaponAttackStat attackStat) {
        this.attackStat = attackStat;
    }

    @Nonnull
    public Dice getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(@Nonnull Dice critDamage) {
        this.critDamage = critDamage;
    }

    public int getCritMin() {
        return critMin;
    }

    public void setCritMin(int critMin) {
        this.critMin = critMin;
    }

    @Nonnull
    public Dice getDamage() {
        return damage;
    }

    public void setDamage(@Nonnull Dice damage) {
        this.damage = damage;
    }

    public int getDamageBonus(@Nonnull CoreStats coreStats) {
        if (attackStat.equals(WeaponAttackStat.FINESSE)) {
            return Math.max(coreStats.getStrength().getMod(), coreStats.getDexterity().getMod());
        }
        else if (attackStat.equals(WeaponAttackStat.CHA)) {
            return coreStats.getCharisma().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.CON)) {
            return coreStats.getConstitution().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.DEX)) {
            return coreStats.getDexterity().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.INT)) {
            return coreStats.getIntelligence().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.STR)) {
            return coreStats.getStrength().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.WIS)) {
            return coreStats.getWisdom().getMod();
        }

        return 0;
    }

    @Nonnull
    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(@Nonnull String damageType) {
        this.damageType = damageType;
    }

    public int getMagicBonus() {
        return magicBonus;
    }

    public void setMagicBonus(int magicBonus) {
        this.magicBonus = magicBonus;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public int getToHit(@Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience) {
        int toHit = experience.getProficiencyBonus(classLevels);
        if (attackStat.equals(WeaponAttackStat.FINESSE)) {
            toHit = +Math.max(coreStats.getStrength().getMod(), coreStats.getDexterity().getMod());
        }
        else if (attackStat.equals(WeaponAttackStat.CHA)) {
            toHit = +coreStats.getCharisma().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.CON)) {
            toHit = +coreStats.getConstitution().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.DEX)) {
            toHit = +coreStats.getDexterity().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.INT)) {
            toHit = +coreStats.getIntelligence().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.STR)) {
            toHit = +coreStats.getStrength().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.WIS)) {
            toHit = +coreStats.getWisdom().getMod();
        }

        return toHit;
    }

    public boolean isProficient() {
        return isProficient;
    }

    public void setIsProficient(boolean proficient) {
        isProficient = proficient;
    }

    static class Serializer<W extends Weapon> implements JsonDeserializer<W>, JsonSerializer<W> {

        @SuppressWarnings("unchecked")
        @Override
        public W deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            W weapon;
            try {
                weapon = ((Class<W>) type).newInstance();
            }
            catch (InstantiationException | IllegalAccessException e) {
                throw new JsonParseException(e);
            }

            JsonObject jsonObject = json.getAsJsonObject();
            JsonKeyProcessor.<JsonPrimitive, WeaponAttackStat>getJsonKey(Keys.ATTACK_STAT).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(weapon::setAttackStat));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.CRIT_DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(weapon::setCritDamage));
            Keys.CRIT_MINIMUM.deserializeFromParent(jsonObject, context).ifPresent(weapon::setCritMin);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(weapon::setDamage));
            Keys.DAMAGE_TYPE.deserializeFromParent(jsonObject, context).ifPresent(weapon::setDamageType);
            Keys.IS_PROFICIENT.deserializeFromParent(jsonObject, context).ifPresent(weapon::setIsProficient);
            Keys.MAGIC_BONUS.deserializeFromParent(jsonObject, context).ifPresent(weapon::setMagicBonus);
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(weapon::setName);
            return weapon;
        }

        @Override
        public JsonElement serialize(W src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonPrimitive, WeaponAttackStat>getJsonKey(Keys.ATTACK_STAT).ifPresent(jsonKey -> jsonKey.serialize(src.getAttackStat(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.CRIT_DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getCritDamage(), jsonObject, context));
            Keys.CRIT_MINIMUM.serialize(src.getCritMin(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getDamage(), jsonObject, context));
            Keys.DAMAGE_TYPE.serialize(src.getDamageType(), jsonObject, context);
            Keys.IS_PROFICIENT.serialize(src.isProficient(), jsonObject, context);
            Keys.MAGIC_BONUS.serialize(src.getMagicBonus(), jsonObject, context);
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            return jsonObject;
        }
    }
}
