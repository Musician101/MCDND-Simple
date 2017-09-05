package io.musician101.mcdndsimple.common.character.weapon;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;

public abstract class AbstractWeapon
{
    private boolean isProficient = true;
    private Dice critDamageDice = new Dice(0);
    private Dice damageDice = new Dice(0);
    private int critMin = 20;
    private int magicBonus = 0;
    private WeaponAttackStat attackStat;
    private String damageType = "";
    private String name = "";

    public WeaponAttackStat getAttackStat()
    {
        return attackStat;
    }

    public Dice getCritDamageDice()
    {
        return critDamageDice;
    }

    public int getCritMin()
    {
        return critMin;
    }

    public int getDamageBonus(CoreStats coreStats)
    {
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

    public Dice getDamageDice()
    {
        return damageDice;
    }

    public String getDamageType() {
        return damageType;
    }

    public int getMagicBonus()
    {
        return magicBonus;
    }

    public String getName()
    {
        return name;
    }

    public int getToHit(ClassLevels classLevels, CoreStats coreStats, Experience experience)
    {
        int toHit = experience.getProficiencyBonus(classLevels);
        if (attackStat.equals(WeaponAttackStat.FINESSE)) {
            toHit =+ Math.max(coreStats.getStrength().getMod(), coreStats.getDexterity().getMod());
        }
        else if (attackStat.equals(WeaponAttackStat.CHA)) {
            toHit =+ coreStats.getCharisma().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.CON)) {
            toHit =+ coreStats.getConstitution().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.DEX)) {
            toHit =+ coreStats.getDexterity().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.INT)) {
            toHit =+ coreStats.getIntelligence().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.STR)) {
            toHit =+ coreStats.getStrength().getMod();
        }
        else if (attackStat.equals(WeaponAttackStat.WIS)) {
            toHit =+ coreStats.getWisdom().getMod();
        }

        return toHit;
    }

    public boolean isProficient()
    {
        return isProficient;
    }

    public void setAttackStat(WeaponAttackStat attackStat)
    {
        this.attackStat = attackStat;
    }

    public void setCritDamageDice(Dice critDamageDice)
    {
        this.critDamageDice = critDamageDice;
    }

    public void setCritMin(int critMin)
    {
        this.critMin = critMin;
    }

    public void setDamageDice(Dice damageDice)
    {
        this.damageDice = damageDice;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void setMagicBonus(int magicBonus)
    {
        this.magicBonus = magicBonus;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setIsProficient(boolean proficient)
    {
        isProficient = proficient;
    }
}
