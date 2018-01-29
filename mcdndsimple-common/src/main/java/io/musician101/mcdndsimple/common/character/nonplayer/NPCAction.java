package io.musician101.mcdndsimple.common.character.nonplayer;

import java.util.ArrayList;
import java.util.List;

public class NPCAction {

    private boolean multiAttack = false;
    private List<String> description = new ArrayList<>();
    private List<String> effect = new ArrayList<>();
    private NPCActionType actionType = NPCActionType.NORMAL;
    private String name = "";

    public NPCActionType getActionType() {
        return actionType;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getEffect() {
        return effect;
    }

    public String getName() {
        return name;
    }

    public boolean isMultiAttack() {
        return multiAttack;
    }

    public void setActionType(NPCActionType actionType) {
        this.actionType = actionType;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setEffect(List<String> effect) {
        this.effect = effect;
    }

    public void setMultiAttack(boolean multiAttack) {
        this.multiAttack = multiAttack;
    }

    public void setName(String name) {
        this.name = name;
    }
}
