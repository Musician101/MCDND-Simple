package io.musician101.mcdndsimple.common.character.player.tab;

import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;

public class SkillsTab {

    private final PlayerSkill acrobatics = new PlayerSkill("Acrobatics (Dex)");
    private final PlayerSkill animalHandling = new PlayerSkill("Animal Handling (Wis)");
    private final PlayerSkill arcana = new PlayerSkill("Arcana (Int)");
    private final PlayerSkill athletics = new PlayerSkill("Athletics (Str)");
    private final PlayerSkill deception = new PlayerSkill("Deception (Cha)");
    private final PlayerSkill history = new PlayerSkill("history (Int)");
    private final PlayerSkill insight = new PlayerSkill("Insight (Wis)");
    private final PlayerSkill intimidation = new PlayerSkill("Intimidation (Cha)");
    private final PlayerSkill investigation = new PlayerSkill("Investigation (Int)");
    private final PlayerSkill medicine = new PlayerSkill("Medicine (Wis)");
    private final PlayerSkill nature = new PlayerSkill("Nature (Int)");
    private final PlayerSkill perception = new PlayerSkill("Perception (Wis)");
    private final PlayerSkill performance = new PlayerSkill("Performance (Cha)");
    private final PlayerSkill persuasion = new PlayerSkill("Persuasion (Cha)");
    private final PlayerSkill religion = new PlayerSkill("Religion (Int)");
    private final PlayerSkill sleightOfHand = new PlayerSkill("Sleight of Hand (Dex)");
    private final PlayerSkill stealth = new PlayerSkill("Stealth (Dex)");
    private final PlayerSkill survival = new PlayerSkill("Survival (Wis)");
    private final PlayerSkill unskilledCHA = new PlayerSkill("Unskilled CHA");
    private final PlayerSkill unskilledCON = new PlayerSkill("Unskilled CON");
    private final PlayerSkill unskilledDEX = new PlayerSkill("Unskilled DEX");
    private final PlayerSkill unskilledINT = new PlayerSkill("Unskilled INT");
    private final PlayerSkill unskilledSTR = new PlayerSkill("Unskilled STR");
    private final PlayerSkill unskilledWIS = new PlayerSkill("Unskilled WIS");

    public PlayerSkill getAcrobatics() {
        return acrobatics;
    }

    public PlayerSkill getAnimalHandling() {
        return animalHandling;
    }

    public PlayerSkill getArcana() {
        return arcana;
    }

    public PlayerSkill getAthletics() {
        return athletics;
    }

    public PlayerSkill getDeception() {
        return deception;
    }

    public PlayerSkill getHistory() {
        return history;
    }

    public PlayerSkill getInsight() {
        return insight;
    }

    public PlayerSkill getIntimidation() {
        return intimidation;
    }

    public PlayerSkill getInvestigation() {
        return investigation;
    }

    public PlayerSkill getMedicine() {
        return medicine;
    }

    public PlayerSkill getNature() {
        return nature;
    }

    public PlayerSkill getPerception() {
        return perception;
    }

    public PlayerSkill getPerformance() {
        return performance;
    }

    public PlayerSkill getPersuasion() {
        return persuasion;
    }

    public PlayerSkill getReligion() {
        return religion;
    }

    public PlayerSkill getSleightOfHand() {
        return sleightOfHand;
    }

    public PlayerSkill getStealth() {
        return stealth;
    }

    public PlayerSkill getSurvival() {
        return survival;
    }

    public PlayerSkill getUnskilledCHA() {
        return unskilledCHA;
    }

    public PlayerSkill getUnskilledCON() {
        return unskilledCON;
    }

    public PlayerSkill getUnskilledDEX() {
        return unskilledDEX;
    }

    public PlayerSkill getUnskilledINT() {
        return unskilledINT;
    }

    public PlayerSkill getUnskilledSTR() {
        return unskilledSTR;
    }

    public PlayerSkill getUnskilledWIS() {
        return unskilledWIS;
    }

    public void updateSkills(CoreStats coreStats) {
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
