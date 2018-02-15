package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@TypeOf(Spell.Serializer.class)
public class Spell {

    private List<String> atHigherLevels = new ArrayList<>();
    private StatBonus attackStat = StatBonus.NONE;
    //Most spells simply state Action or Bonus Action which have actual numerical values
    //but to keep things simple, we'll just leave it as a string.
    private String castTime = "";
    private List<String> components = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private String duration = "";
    private List<String> effects = new ArrayList<>();
    private SpellcasterClass gainedFrom = SpellcasterClass.OTHER;
    private boolean isRitual = false;
    private int level = 0;
    private MacroOptions macroOptions = new MacroOptions();
    private String name = "";
    private boolean needsConcentration = false;
    private Prepared prepared = Prepared.NO;
    private String range = "";
    private SpellDamage spellDamage = new SpellDamage();
    private SpellHealing spellHealing = new SpellHealing();
    private SpellSave spellSave = new SpellSave();
    private SpellType spellType = SpellType.OTHER;
    private String targetArea = "";

    public List<String> getAtHigherLevels() {
        return atHigherLevels;
    }

    public void setAtHigherLevels(List<String> atHigherLevels) {
        this.atHigherLevels = atHigherLevels;
    }

    public StatBonus getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(StatBonus attackStat) {
        this.attackStat = attackStat;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getEffects() {
        return effects;
    }

    public void setEffects(List<String> effects) {
        this.effects = effects;
    }

    public SpellcasterClass getGainedFrom() {
        return gainedFrom;
    }

    public void setGainedFrom(SpellcasterClass gainedFrom) {
        this.gainedFrom = gainedFrom;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if (this.level == 0) {
            prepared = Prepared.ALWAYS;
        }
    }

    public MacroOptions getMacroOptions() {
        return macroOptions;
    }

    public void setMacroOptions(MacroOptions macroOptions) {
        this.macroOptions = macroOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prepared getPrepared() {
        return prepared;
    }

    public void setPrepared(Prepared prepared) {
        this.prepared = prepared;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public SpellDamage getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(SpellDamage spellDamage) {
        this.spellDamage = spellDamage;
    }

    public SpellHealing getSpellHealing() {
        return spellHealing;
    }

    public void setSpellHealing(SpellHealing spellHealing) {
        this.spellHealing = spellHealing;
    }

    public SpellSave getSpellSave() {
        return spellSave;
    }

    public void setSpellSave(SpellSave spellSave) {
        this.spellSave = spellSave;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public boolean isRitual() {
        return isRitual;
    }

    public boolean needsConcentration() {
        return needsConcentration;
    }

    public void setIsRitual(boolean ritual) {
        isRitual = ritual;
    }

    public void setNeedsConcentration(boolean needsConcentration) {
        this.needsConcentration = needsConcentration;
    }

    public static class Serializer implements JsonDeserializer<Spell>, JsonSerializer<Spell> {

        @Override
        public Spell deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Spell spell = new Spell();
            Keys.IS_RITUAL.deserializeFromParent(jsonObject, context).ifPresent(spell::setIsRitual);
            Keys.NEEDS_CONCENTRATION.deserializeFromParent(jsonObject, context).ifPresent(spell::setNeedsConcentration);
            Keys.LEVEL.deserializeFromParent(jsonObject, context).ifPresent(spell::setLevel);
            Keys.AT_HIGHER_LEVELS.deserializeFromParent(jsonObject, context).ifPresent(spell::setAtHigherLevels);
            Keys.COMPONENTS.deserializeFromParent(jsonObject, context).ifPresent(spell::setComponents);
            Keys.DESCRIPTION.deserializeFromParent(jsonObject, context).ifPresent(spell::setDescription);
            Keys.EFFECTS.deserializeFromParent(jsonObject, context).ifPresent(spell::setEffects);
            Keys.CAST_TIME.deserializeFromParent(jsonObject, context).ifPresent(spell::setCastTime);
            Keys.DURATION.deserializeFromParent(jsonObject, context).ifPresent(spell::setDuration);
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(spell::setName);
            Keys.RANGE.deserializeFromParent(jsonObject, context).ifPresent(spell::setRange);
            Keys.TARGET_AREA.deserializeFromParent(jsonObject, context).ifPresent(spell::setTargetArea);
            handleAnnotatedKeys(jsonObject, context, spell);
            return spell;
        }

        private void handleAnnotatedKeys(JsonObject jsonObject, JsonDeserializationContext context, Spell spell) throws JsonParseException {
            JsonKeyProcessor.<JsonObject, MacroOptions>getJsonKey(Keys.MACRO_OPTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setMacroOptions));
            JsonKeyProcessor.<JsonPrimitive, Prepared>getJsonKey(Keys.PREPARED).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setPrepared));
            JsonKeyProcessor.<JsonPrimitive, SpellcasterClass>getJsonKey(Keys.GAINED_FROM).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setGainedFrom));
            JsonKeyProcessor.<JsonObject, SpellDamage>getJsonKey(Keys.SPELL_DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setSpellDamage));
            JsonKeyProcessor.<JsonObject, SpellHealing>getJsonKey(Keys.SPELL_HEALING).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setSpellHealing));
            JsonKeyProcessor.<JsonObject, SpellSave>getJsonKey(Keys.SPELL_SAVE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setSpellSave));
            JsonKeyProcessor.<JsonPrimitive, SpellType>getJsonKey(Keys.SPELL_TYPE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setSpellType));
            JsonKeyProcessor.<JsonPrimitive, StatBonus>getJsonKey(Keys.ATTACK_STAT).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spell::setAttackStat));
        }

        @Override
        public JsonElement serialize(Spell src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.IS_RITUAL.serialize(src.isRitual(), jsonObject, context);
            Keys.NEEDS_CONCENTRATION.serialize(src.needsConcentration(), jsonObject, context);
            Keys.LEVEL.serialize(src.getLevel(), jsonObject, context);
            Keys.AT_HIGHER_LEVELS.serialize(src.getAtHigherLevels(), jsonObject, context);
            Keys.COMPONENTS.serialize(src.getComponents(), jsonObject, context);
            Keys.DESCRIPTION.serialize(src.getDescription(), jsonObject, context);
            Keys.EFFECTS.serialize(src.getEffects(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, MacroOptions>getJsonKey(Keys.MACRO_OPTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getMacroOptions(), jsonObject, context));
            JsonKeyProcessor.<JsonPrimitive, Prepared>getJsonKey(Keys.PREPARED).ifPresent(jsonKey -> jsonKey.serialize(src.getPrepared(), jsonObject, context));
            JsonKeyProcessor.<JsonPrimitive, SpellcasterClass>getJsonKey(Keys.GAINED_FROM).ifPresent(jsonKey -> jsonKey.serialize(src.getGainedFrom(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellDamage>getJsonKey(Keys.SPELL_DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellDamage(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellHealing>getJsonKey(Keys.SPELL_HEALING).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellHealing(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellSave>getJsonKey(Keys.SPELL_SAVE).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellSave(), jsonObject, context));
            JsonKeyProcessor.<JsonPrimitive, SpellType>getJsonKey(Keys.SPELL_TYPE).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellType(), jsonObject, context));
            JsonKeyProcessor.<JsonPrimitive, StatBonus>getJsonKey(Keys.ATTACK_STAT).ifPresent(jsonKey -> jsonKey.serialize(src.getAttackStat(), jsonObject, context));
            Keys.CAST_TIME.serialize(src.getCastTime(), jsonObject, context);
            Keys.DURATION.serialize(src.getDuration(), jsonObject, context);
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            Keys.RANGE.serialize(src.getRange(), jsonObject, context);
            Keys.TARGET_AREA.serialize(src.getTargetArea(), jsonObject, context);
            return jsonObject;
        }
    }
}
