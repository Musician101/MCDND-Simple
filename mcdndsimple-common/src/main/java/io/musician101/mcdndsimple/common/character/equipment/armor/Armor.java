package io.musician101.mcdndsimple.common.character.equipment.armor;

public class Armor {

    private ArmorType armorType = ArmorType.NONE;
    private int baseArmorClass = 0;
    private int magicBonus = 0;
    private String name = "";
    private boolean speedPenalty = false;
    private boolean stealthPenalty = false;
    private boolean unarmored = false;
    private boolean worn = true;

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public int getBaseArmorClass() {
        return baseArmorClass;
    }

    public void setBaseArmorClass(int baseArmorClass) {
        this.baseArmorClass = baseArmorClass;
    }

    public int getMagicBonus() {
        return magicBonus;
    }

    public void setMagicBonus(int magicBonus) {
        this.magicBonus = magicBonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalArmorClass() {
        return baseArmorClass + magicBonus;
    }

    public boolean hasSpeedPenalty() {
        return speedPenalty;
    }

    public boolean hasStealthPenalty() {
        return stealthPenalty;
    }

    public boolean isUnarmored() {
        return unarmored;
    }

    public boolean isWorn() {
        return worn;
    }

    public void setIsUnarmored(boolean unarmored) {
        this.unarmored = unarmored;
    }

    public void setIsWorn(boolean worn) {
        this.worn = worn;
    }

    public void setSpeedPenalty(boolean speedPenalty) {
        this.speedPenalty = speedPenalty;
    }

    public void setStealthPenalty(boolean stealthPenalty) {
        this.stealthPenalty = stealthPenalty;
    }
}
