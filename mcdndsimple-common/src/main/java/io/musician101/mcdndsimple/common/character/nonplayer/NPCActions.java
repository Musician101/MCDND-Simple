package io.musician101.mcdndsimple.common.character.nonplayer;

import java.util.ArrayList;
import java.util.List;

public class NPCActions {

    private String multiAttack = "None";
    private List<NPCAction> actions = new ArrayList<>();

    public List<NPCAction> getActions() {
        return actions;
    }

    public String getMultiAttack() {
        return multiAttack;
    }

    public void setActions(List<NPCAction> actions) {
        this.actions = actions;
    }

    public void setMultiAttack(String multiAttack) {
        this.multiAttack = multiAttack;
    }
}
