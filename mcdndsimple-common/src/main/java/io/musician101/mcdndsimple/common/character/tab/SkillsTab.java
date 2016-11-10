package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.skill.Skill;

public class SkillsTab
{
    private final Skill acrobatics = new Skill("Acrobatics (Dex)");
    private final Skill animalHandling = new Skill("Animal Handling (Wis)");
    private final Skill arcana = new Skill("Arcana (Int)");
    private final Skill athletics = new Skill("Athletics (Str)");
    private final Skill deception = new Skill("Deception (Cha)");
    private final Skill history = new Skill("history (Int)");
    private final Skill insight = new Skill("Insight (Wis)");
    private final Skill intimidation = new Skill("Intimidation (Cha)");
    private final Skill investigation = new Skill("Investigation (Int)");
    private final Skill medicine = new Skill("Medicine (Wis)");
    private final Skill nature = new Skill("Nature (Int)");
    private final Skill perception = new Skill("Perception (Wis)");
    private final Skill performance = new Skill("Performance (Cha)");
    private final Skill persuasion = new Skill("Persuasion (Cha)");
    private final Skill religion = new Skill("Religion (Int)");
    private final Skill sleightOfHand = new Skill("Sleight of Hand (Dex)");
    private final Skill stealth = new Skill("Stealth (Dex)");
    private final Skill survival = new Skill("Survival (Wis)");
    private final Skill unskilledCHA = new Skill("Unskilled CHA");
    private final Skill unskilledCON = new Skill("Unskilled CON");
    private final Skill unskilledDEX = new Skill("Unskilled DEX");
    private final Skill unskilledINT = new Skill("Unskilled INT");
    private final Skill unskilledSTR = new Skill("Unskilled STR");
    private final Skill unskilledWIS = new Skill("Unskilled WIS");

    public Skill getAcrobatics()
    {
        return acrobatics;
    }

    public Skill getAnimalHandling()
    {
        return animalHandling;
    }

    public Skill getArcana()
    {
        return arcana;
    }

    public Skill getAthletics()
    {
        return athletics;
    }

    public Skill getDeception()
    {
        return deception;
    }

    public Skill getHistory()
    {
        return history;
    }

    public Skill getInsight()
    {
        return insight;
    }

    public Skill getIntimidation()
    {
        return intimidation;
    }

    public Skill getInvestigation()
    {
        return investigation;
    }

    public Skill getMedicine()
    {
        return medicine;
    }

    public Skill getNature()
    {
        return nature;
    }

    public Skill getPerception()
    {
        return perception;
    }

    public Skill getPerformance()
    {
        return performance;
    }

    public Skill getPersuasion()
    {
        return persuasion;
    }

    public Skill getReligion()
    {
        return religion;
    }

    public Skill getSleightOfHand()
    {
        return sleightOfHand;
    }

    public Skill getStealth()
    {
        return stealth;
    }

    public Skill getSurvival()
    {
        return survival;
    }

    public Skill getUnskilledCHA()
    {
        return unskilledCHA;
    }

    public Skill getUnskilledCON()
    {
        return unskilledCON;
    }

    public Skill getUnskilledDEX()
    {
        return unskilledDEX;
    }

    public Skill getUnskilledINT()
    {
        return unskilledINT;
    }

    public Skill getUnskilledSTR()
    {
        return unskilledSTR;
    }

    public Skill getUnskilledWIS()
    {
        return unskilledWIS;
    }

    public void updateSkills(CoreStats coreStats)
    {
        AbilityScore cha = coreStats.getCharisma();
        AbilityScore con = coreStats.getConstitution();
        AbilityScore dex = coreStats.getDexterity();
        //tfw int is a Java native
        AbilityScore intel = coreStats.getIntelligence();
        AbilityScore str = coreStats.getStrength();
        AbilityScore wis = coreStats.getWisdom();
        acrobatics.update(dex);
        animalHandling.update(wis);
        arcana.update(intel);
        athletics.update(str);
        deception.update(cha);
        history.update(intel);
        insight.update(wis);
        investigation.update(intel);
        intimidation.update(cha);
        medicine.update(wis);
        nature.update(intel);
        perception.update(wis);
        performance.update(cha);
        persuasion.update(cha);
        religion.update(intel);
        sleightOfHand.update(dex);
        stealth.update(dex);
        survival.update(wis);
        unskilledCHA.update(cha);
        unskilledCON.update(con);
        unskilledDEX.update(dex);
        unskilledINT.update(intel);
        unskilledSTR.update(str);
        unskilledWIS.update(wis);
    }
}
