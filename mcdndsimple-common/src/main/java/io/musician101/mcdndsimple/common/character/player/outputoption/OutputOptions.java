package io.musician101.mcdndsimple.common.character.player.outputoption;

public class OutputOptions {

    private CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();
    private SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
    private WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();

    public CoreSkillsOutputOptions getCoreSkillsOutputOptions() {
        return coreSkillsOutputOptions;
    }

    public void setCoreSkillsOutputOptions(CoreSkillsOutputOptions coreSkillsOutputOptions) {
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    public SavingThrowOutputOptions getSavingThrowOutputOptions() {
        return savingThrowOutputOptions;
    }

    public void setSavingThrowOutputOptions(SavingThrowOutputOptions savingThrowOutputOptions) {
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    public WeaponsSpellMiscOutputOptions getWeaponsSpellMiscOutputOptions() {
        return weaponsSpellMiscOutputOptions;
    }

    public void setWeaponsSpellMiscOutputOptions(WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions) {
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }
}
