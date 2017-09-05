package io.musician101.mcdndsimple.common.character.equipment.currency;

public class Wealth {

    private Coin copper = new Coin("Copper", "CP");
    private Coin electrum = new Coin("Electrum", "EP");
    private Coin gold = new Coin("Gold", "GP");
    private Coin platinum = new Coin("Platinum", "PP");
    private Coin silver = new Coin("Silver", "SP");

    public Coin getCopper() {
        return copper;
    }

    public void setCopper(Coin copper) {
        this.copper = copper;
    }

    public Coin getElectrum() {
        return electrum;
    }

    public void setElectrum(Coin electrum) {
        this.electrum = electrum;
    }

    public Coin getGold() {
        return gold;
    }

    public void setGold(Coin gold) {
        this.gold = gold;
    }

    public Coin getPlatinum() {
        return platinum;
    }

    public void setPlatinum(Coin platinum) {
        this.platinum = platinum;
    }

    public Coin getSilver() {
        return silver;
    }

    public void setSilver(Coin silver) {
        this.silver = silver;
    }
}
