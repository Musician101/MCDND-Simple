package io.musician101.mcdndsimple.common.character.player.spell;

import java.util.ArrayList;
import java.util.List;

public class SpellSave {

    private int customDC = 0;
    private List<String> onSuccessfulSave = new ArrayList<>();
    private StatBonus savingStat = StatBonus.NONE;
    private SpellcasterClass spellcasterClass = null;

    public int getCustomDC() {
        return customDC;
    }

    public void setCustomDC(int customDC) {
        this.customDC = customDC;
    }

    public List<String> getOnSuccessfulSave() {
        return onSuccessfulSave;
    }

    public void setOnSuccessfulSave(List<String> onSuccessfulSave) {
        this.onSuccessfulSave = onSuccessfulSave;
    }

    public StatBonus getSavingStat() {
        return savingStat;
    }

    public void setSavingStat(StatBonus savingStat) {
        this.savingStat = savingStat;
    }

    public SpellcasterClass getSaveDCType() {
        return spellcasterClass;
    }

    public void setSaveDCType(SpellcasterClass spellcasterClass) {
        this.spellcasterClass = spellcasterClass;
    }
}
