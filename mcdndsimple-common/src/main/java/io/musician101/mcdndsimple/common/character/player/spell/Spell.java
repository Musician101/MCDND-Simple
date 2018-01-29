package io.musician101.mcdndsimple.common.character.player.spell;

import java.util.ArrayList;
import java.util.List;

public class Spell {

    private List<String> atHigherLevels = new ArrayList<>();
    private StatBonus attackStat = StatBonus.NONE;
    //Most spells simply state Action or Bonus Action which have actual numerical values
    //but to keep things simple, we'll just leave it as a string.
    private String castTime = "";
    private String components = "";
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

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
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
}
