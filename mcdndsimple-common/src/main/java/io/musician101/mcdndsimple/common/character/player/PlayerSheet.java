package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.AbstractPlayer;

public class PlayerSheet extends AbstractPlayer {

    private BioAndInfo bioAndInfo = new BioAndInfo();
    private CharacterSheet characterSheet = new CharacterSheet();

    public BioAndInfo getBioAndInfo() {
        return bioAndInfo;
    }

    public void setBioAndInfo(BioAndInfo bioAndInfo) {
        this.bioAndInfo = bioAndInfo;
    }

    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    public void setCharacterSheet(CharacterSheet characterSheet) {
        this.characterSheet = characterSheet;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        bioAndInfo.setName(name);
    }
}
