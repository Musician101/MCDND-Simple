package io.musician101.mcdndsimple.common.character.nonplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TraitsBackground {

    private List<String> traits = new ArrayList<>(Collections.singletonList("None"));
    private String conditionImmunity = "None";
    private String damageImmunity = "None";
    private String damageResistance = "None";
    private String damageVulnerability = "None";

    public String getConditionImmunity() {
        return conditionImmunity;
    }

    public String getDamageImmunity() {
        return damageImmunity;
    }

    public String getDamageResistance() {
        return damageResistance;
    }

    public String getDamageVulnerability() {
        return damageVulnerability;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setConditionImmunity(String conditionImmunity) {
        this.conditionImmunity = conditionImmunity;
    }

    public void setDamageImmunity(String damageImmunity) {
        this.damageImmunity = damageImmunity;
    }

    public void setDamageResistance(String damageResistance) {
        this.damageResistance = damageResistance;
    }

    public void setDamageVulnerability(String damageVulnerability) {
        this.damageVulnerability = damageVulnerability;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }
}
