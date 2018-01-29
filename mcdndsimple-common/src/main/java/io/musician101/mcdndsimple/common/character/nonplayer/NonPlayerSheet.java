package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.AbstractPlayer;

public class NonPlayerSheet extends AbstractPlayer {

    private NPCActions actions = new NPCActions();
    private NPCSheet npcSheet = new NPCSheet();
    private NPCSkills npcSkills = new NPCSkills();
    private TraitsBackground traitsBackground = new TraitsBackground();

    public NPCActions getActions() {
        return actions;
    }

    public NPCSheet getNPCSheet() {
        return npcSheet;
    }

    public NPCSkills getSkills() {
        return npcSkills;
    }

    public TraitsBackground getTraitsBackground() {
        return traitsBackground;
    }

    public void setActions(NPCActions actions) {
        this.actions = actions;
    }

    public void setNPCSheet(NPCSheet NPCSheet) {
        this.npcSheet = NPCSheet;
    }

    public void setSkills(NPCSkills npcSkills) {
        this.npcSkills = npcSkills;
    }

    public void setTraitsBackground(TraitsBackground traitsBackground) {
        this.traitsBackground = traitsBackground;
    }
}
