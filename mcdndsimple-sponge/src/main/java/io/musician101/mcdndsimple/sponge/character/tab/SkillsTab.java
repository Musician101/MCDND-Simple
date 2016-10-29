package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;
import io.musician101.mcdndsimple.sponge.character.CoreStats;
import io.musician101.mcdndsimple.sponge.character.skill.Skill;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class SkillsTab implements DataSerializable
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

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.ACROBATICS, acrobatics.toContainer())
                .set(MCDNDSimpleKeys.ANIMAL_HANDLING, animalHandling.toContainer())
                .set(MCDNDSimpleKeys.ARCANA, arcana.toContainer())
                .set(MCDNDSimpleKeys.ATHLETICS, athletics.toContainer())
                .set(MCDNDSimpleKeys.DECEPTION, deception.toContainer())
                .set(MCDNDSimpleKeys.HISTORY, history.toContainer())
                .set(MCDNDSimpleKeys.INSIGHT, insight.toContainer())
                .set(MCDNDSimpleKeys.INTIMIDATION, intimidation.toContainer())
                .set(MCDNDSimpleKeys.INVESTIGATION, investigation.toContainer())
                .set(MCDNDSimpleKeys.MEDICINE, medicine.toContainer())
                .set(MCDNDSimpleKeys.NATURE, nature.toContainer())
                .set(MCDNDSimpleKeys.PERFORMANCE, performance.toContainer())
                .set(MCDNDSimpleKeys.PERCEPTION, perception.toContainer())
                .set(MCDNDSimpleKeys.PERSUASION, persuasion.toContainer())
                .set(MCDNDSimpleKeys.RELIGION, religion.toContainer())
                .set(MCDNDSimpleKeys.SLEIGHT_OF_HAND, sleightOfHand.toContainer())
                .set(MCDNDSimpleKeys.STEALTH, stealth.toContainer())
                .set(MCDNDSimpleKeys.SURVIVAL, survival.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_CHA, unskilledCHA.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_CON, unskilledCON.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_DEX, unskilledDEX.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_INT, unskilledINT.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_STR, unskilledSTR.toContainer())
                .set(MCDNDSimpleKeys.UNSKILLED_WIS, unskilledWIS.toContainer());
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
