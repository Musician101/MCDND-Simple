package io.musician101.mcdndsimple.common.character.outputoption;

public class OutputOptions {

    private SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
    private WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();
    private CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();

    public CoreSkillsOutputOptions getCoreSkillsOutputOptions() {
        return coreSkillsOutputOptions;
    }

    public SavingThrowOutputOptions getSavingThrowOutputOptions() {
        return savingThrowOutputOptions;
    }

    public WeaponsSpellMiscOutputOptions getWeaponsSpellMiscOutputOptions() {
        return weaponsSpellMiscOutputOptions;
    }

    public void setCoreSkillsOutputOptions(CoreSkillsOutputOptions coreSkillsOutputOptions) {
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    public void setSavingThrowOutputOptions(SavingThrowOutputOptions savingThrowOutputOptions) {
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    public void setWeaponsSpellMiscOutputOptions(WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions) {
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }
}
