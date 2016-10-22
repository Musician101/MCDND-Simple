package io.musician101.mcdndsimple.sponge.character.spell;

public class SpellSave
{
    private SaveDCType saveDCType = null;
    private String onSuccessfulSave = "";
    private String savingStat = "";

    public String getOnSuccessfulSave()
    {
        return onSuccessfulSave;
    }

    public SaveDCType getSaveDCType()
    {
        return saveDCType;
    }

    public String getSavingStat()
    {
        return savingStat;
    }

    public void setOnSuccessfulSave(String onSuccessfulSave)
    {
        this.onSuccessfulSave = onSuccessfulSave;
    }

    public void setSaveDCType(SaveDCType saveDCType)
    {
        this.saveDCType = saveDCType;
    }

    public void setSavingStat(String savingStat)
    {
        this.savingStat = savingStat;
    }
}
