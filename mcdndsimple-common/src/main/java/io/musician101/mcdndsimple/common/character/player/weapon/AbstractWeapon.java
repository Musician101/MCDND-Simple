package io.musician101.mcdndsimple.common.character.player.weapon;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;

public abstract class AbstractWeapon {

    private WeaponAttackStat attackStat;
    private Dice critDamageDice = new Dice(0);
    private int critMin = 20;
    private Dice damageDice = new Dice(0);
    private String damageType = "";
    private boolean isProficient = true;
    private int magicBonus = 0;
    private String name = "";

    public WeaponAttackStat getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(WeaponAttackStat attackStat) {
        this.attackStat = attackStat;
    }

    public Dice getCritDamageDice() {
        return critDamageDice;
    }

    public void setCritDamageDice(Dice critDamageDice) {
        this.critDamageDice = critDamageDice;
    }

    public int getCritMin() {
        return critMin;
    }

    public void setCritMin(int critMin) {
        this.critMin = critMin;
    }

    public int getDamageBonus(CoreStats coreStats) {
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

    public Dice getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(Dice damageDice) {
        this.damageDice = damageDice;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
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

    public int getToHit(ClassLevels classLevels, CoreStats coreStats, Experience experience) {
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
}
