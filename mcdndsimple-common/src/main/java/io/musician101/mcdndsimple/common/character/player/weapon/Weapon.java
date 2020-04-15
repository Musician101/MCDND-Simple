package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
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

    Weapon(@Nonnull WeaponAttackStat defaultAttackStat) {
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

    static class Serializer<W extends Weapon> extends BaseSerializer<W> {

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
            weapon.setAttackStat(deserialize(jsonObject, context, Keys.WEAPON_ATTACK_STAT));;
            weapon.setCritDamage(deserialize(jsonObject, context, Keys.CRIT_DAMAGE));;
            weapon.setCritMin(deserialize(jsonObject, context, Keys.CRIT_MINIMUM));
            weapon.setDamage(deserialize(jsonObject, context, Keys.DAMAGE));;
            weapon.setDamageType(deserialize(jsonObject, context, Keys.DAMAGE_TYPE));
            weapon.setIsProficient(deserialize(jsonObject, context, Keys.IS_PROFICIENT));
            weapon.setMagicBonus(deserialize(jsonObject, context, Keys.MAGIC_BONUS));
            weapon.setName(deserialize(jsonObject, context, Keys.NAME));
            return weapon;
        }

        @Override
        public JsonElement serialize(W src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.WEAPON_ATTACK_STAT, src.getAttackStat());
            serialize(jsonObject, context, Keys.CRIT_DAMAGE, src.getCritDamage());
            serialize(jsonObject, context, Keys.CRIT_MINIMUM, src.getCritMin());
            serialize(jsonObject, context, Keys.DAMAGE, src.getDamage());
            serialize(jsonObject, context, Keys.DAMAGE_TYPE, src.getDamageType());
            serialize(jsonObject, context, Keys.IS_PROFICIENT, src.isProficient());
            serialize(jsonObject, context, Keys.MAGIC_BONUS, src.getMagicBonus());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            return jsonObject;
        }
    }
}
