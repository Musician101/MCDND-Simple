package io.musician101.mcdndsimple.common.character.spell;

import java.util.ArrayList;
import java.util.List;

public class SpellSave
{
    private int customDC = 0;
    private SpellcasterClass spellcasterClass = null;
    private List<String> onSuccessfulSave = new ArrayList<>();
    private String savingStat = "";

    public int getCustomDC() {
        return customDC;
    }

    public List<String> getOnSuccessfulSave()
    {
        return onSuccessfulSave;
    }

    public SpellcasterClass getSpellcasterClass()
    {
        return spellcasterClass;
    }

    public String getSavingStat()
    {
        return savingStat;
    }

    public void setCustomDC(int customDC) {
        this.customDC = customDC;
    }

    public void setOnSuccessfulSave(List<String> onSuccessfulSave)
    {
        this.onSuccessfulSave = onSuccessfulSave;
    }

    public void setSaveDCType(SpellcasterClass spellcasterClass)
    {
        this.spellcasterClass = spellcasterClass;
    }

    public void setSavingStat(String savingStat)
    {
        this.savingStat = savingStat;
    }
}
