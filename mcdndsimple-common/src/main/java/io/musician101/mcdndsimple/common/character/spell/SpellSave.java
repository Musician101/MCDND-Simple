package io.musician101.mcdndsimple.common.character.spell;

import java.util.ArrayList;
import java.util.List;

public class SpellSave {

    private int customDC = 0;
    private List<String> onSuccessfulSave = new ArrayList<>();
    private String savingStat = "";
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

    public String getSavingStat() {
        return savingStat;
    }

    public void setSavingStat(String savingStat) {
        this.savingStat = savingStat;
    }

    public SpellcasterClass getSpellcasterClass() {
        return spellcasterClass;
    }

    public void setSaveDCType(SpellcasterClass spellcasterClass) {
        this.spellcasterClass = spellcasterClass;
    }
}
