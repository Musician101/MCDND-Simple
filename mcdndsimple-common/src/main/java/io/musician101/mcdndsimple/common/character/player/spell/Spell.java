package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

public class Spell {

    @Nonnull
    private List<String> atHigherLevels = new ArrayList<>();
    @Nonnull
    private StatBonus attackStat = StatBonus.NONE;
    //Most spells simply state Action or Bonus Action which have actual numerical values
    //but to keep things simple, we'll just leave it as a string.
    @Nonnull
    private String castTime = "";
    @Nonnull
    private List<String> components = new ArrayList<>();
    @Nonnull
    private List<String> description = new ArrayList<>();
    @Nonnull
    private String duration = "";
    @Nonnull
    private List<String> effects = new ArrayList<>();
    @Nonnull
    private SpellcasterClass gainedFrom = SpellcasterClass.OTHER;
    private boolean isRitual = false;
    private int level = 0;
    @Nonnull
    private MacroOptions macroOptions = new MacroOptions();
    @Nonnull
    private String name = "";
    private boolean needsConcentration = false;
    @Nonnull
    private Prepared prepared = Prepared.NO;
    @Nonnull
    private String range = "";
    @Nonnull
    private SpellDamage spellDamage = new SpellDamage();
    @Nonnull
    private SpellHealing spellHealing = new SpellHealing();
    @Nonnull
    private SpellSave spellSave = new SpellSave();
    @Nonnull
    private SpellType spellType = SpellType.OTHER;
    @Nonnull
    private String targetArea = "";

    @Nonnull
    public List<String> getAtHigherLevels() {
        return atHigherLevels;
    }

    public void setAtHigherLevels(@Nonnull List<String> atHigherLevels) {
        this.atHigherLevels = atHigherLevels;
    }

    @Nonnull
    public StatBonus getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(@Nonnull StatBonus attackStat) {
        this.attackStat = attackStat;
    }

    @Nonnull
    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(@Nonnull String castTime) {
        this.castTime = castTime;
    }

    @Nonnull
    public List<String> getComponents() {
        return components;
    }

    public void setComponents(@Nonnull List<String> components) {
        this.components = components;
    }

    @Nonnull
    public List<String> getDescription() {
        return description;
    }

    public void setDescription(@Nonnull List<String> description) {
        this.description = description;
    }

    @Nonnull
    public String getDuration() {
        return duration;
    }

    public void setDuration(@Nonnull String duration) {
        this.duration = duration;
    }

    @Nonnull
    public List<String> getEffects() {
        return effects;
    }

    public void setEffects(@Nonnull List<String> effects) {
        this.effects = effects;
    }

    @Nonnull
    public SpellcasterClass getGainedFrom() {
        return gainedFrom;
    }

    public void setGainedFrom(@Nonnull SpellcasterClass gainedFrom) {
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

    @Nonnull
    public MacroOptions getMacroOptions() {
        return macroOptions;
    }

    private void setMacroOptions(@Nonnull MacroOptions macroOptions) {
        this.macroOptions = macroOptions;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public Prepared getPrepared() {
        return prepared;
    }

    public void setPrepared(@Nonnull Prepared prepared) {
        this.prepared = prepared;
    }

    @Nonnull
    public String getRange() {
        return range;
    }

    public void setRange(@Nonnull String range) {
        this.range = range;
    }

    @Nonnull
    public SpellDamage getSpellDamage() {
        return spellDamage;
    }

    private void setSpellDamage(@Nonnull SpellDamage spellDamage) {
        this.spellDamage = spellDamage;
    }

    @Nonnull
    public SpellHealing getSpellHealing() {
        return spellHealing;
    }

    private void setSpellHealing(@Nonnull SpellHealing spellHealing) {
        this.spellHealing = spellHealing;
    }

    @Nonnull
    public SpellSave getSpellSave() {
        return spellSave;
    }

    private void setSpellSave(@Nonnull SpellSave spellSave) {
        this.spellSave = spellSave;
    }

    @Nonnull
    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(@Nonnull SpellType spellType) {
        this.spellType = spellType;
    }

    @Nonnull
    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(@Nonnull String targetArea) {
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

    public static class Serializer extends BaseSerializer<Spell> {

        @Override
        public Spell deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Spell spell = new Spell();
            spell.setIsRitual(deserialize(jsonObject, context, Keys.IS_RITUAL));
            spell.setNeedsConcentration(deserialize(jsonObject, context, Keys.NEEDS_CONCENTRATION));
            spell.setLevel(deserialize(jsonObject, context, Keys.LEVEL));
            spell.setAtHigherLevels(deserialize(jsonObject, context, Keys.AT_HIGHER_LEVELS));
            spell.setComponents(deserialize(jsonObject, context, Keys.COMPONENTS));
            spell.setDescription(deserialize(jsonObject, context, Keys.DESCRIPTION));
            spell.setEffects(deserialize(jsonObject, context, Keys.EFFECTS));
            spell.setCastTime(deserialize(jsonObject, context, Keys.CAST_TIME));
            spell.setDuration(deserialize(jsonObject, context, Keys.DURATION));
            spell.setName(deserialize(jsonObject, context, Keys.NAME));
            spell.setRange(deserialize(jsonObject, context, Keys.RANGE));
            spell.setTargetArea(deserialize(jsonObject, context, Keys.TARGET_AREA));
            handleAnnotatedKeys(jsonObject, context, spell);
            return spell;
        }

        private void handleAnnotatedKeys(JsonObject jsonObject, JsonDeserializationContext context, Spell spell) throws JsonParseException {
            spell.setMacroOptions(deserialize(jsonObject, context, Keys.MACRO_OPTIONS));;
            spell.setPrepared(deserialize(jsonObject, context, Keys.PREPARED));;
            spell.setGainedFrom(deserialize(jsonObject, context, Keys.GAINED_FROM_SPELL));;
            spell.setSpellDamage(deserialize(jsonObject, context, Keys.SPELL_DAMAGE));;
            spell.setSpellHealing(deserialize(jsonObject, context, Keys.SPELL_HEALING));;
            spell.setSpellSave(deserialize(jsonObject, context, Keys.SPELL_SAVE));;
            spell.setSpellType(deserialize(jsonObject, context, Keys.SPELL_TYPE));;
            spell.setAttackStat(deserialize(jsonObject, context, Keys.ATTACK_STAT));;
        }

        @Override
        public JsonElement serialize(Spell src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.IS_RITUAL, src.isRitual());
            serialize(jsonObject, context, Keys.NEEDS_CONCENTRATION, src.needsConcentration());
            serialize(jsonObject, context, Keys.LEVEL, src.getLevel());
            serialize(jsonObject, context, Keys.AT_HIGHER_LEVELS, src.getAtHigherLevels());
            serialize(jsonObject, context, Keys.COMPONENTS, src.getComponents());
            serialize(jsonObject, context, Keys.DESCRIPTION, src.getDescription());
            serialize(jsonObject, context, Keys.EFFECTS, src.getEffects());
            serialize(jsonObject, context, Keys.MACRO_OPTIONS, src.getMacroOptions());
            serialize(jsonObject, context, Keys.PREPARED, src.getPrepared());
            serialize(jsonObject, context, Keys.GAINED_FROM_SPELL, src.getGainedFrom());
            serialize(jsonObject, context, Keys.SPELL_DAMAGE, src.getSpellDamage());
            serialize(jsonObject, context, Keys.SPELL_HEALING, src.getSpellHealing());
            serialize(jsonObject, context, Keys.SPELL_SAVE, src.getSpellSave());
            serialize(jsonObject, context, Keys.SPELL_TYPE, src.getSpellType());
            serialize(jsonObject, context, Keys.ATTACK_STAT, src.getAttackStat());
            serialize(jsonObject, context, Keys.CAST_TIME, src.getCastTime());
            serialize(jsonObject, context, Keys.DURATION, src.getDuration());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            serialize(jsonObject, context, Keys.RANGE, src.getRange());
            serialize(jsonObject, context, Keys.TARGET_AREA, src.getTargetArea());
            return jsonObject;
        }
    }
}
