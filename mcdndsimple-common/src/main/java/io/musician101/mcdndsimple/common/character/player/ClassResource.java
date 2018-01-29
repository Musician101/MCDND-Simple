package io.musician101.mcdndsimple.common.character.player;

public class ClassResource extends Rechargeable {

    private int currentCharges = 0;
    private int maxCharges = 0;
    private String name = "";

    public int getMaxUses() {
        return maxCharges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsesLeft() {
        return currentCharges;
    }

    public void setCurrentCharges(int currentCharges) {
        this.currentCharges = currentCharges;
    }

    public void setMaxCharges(int maxCharges) {
        this.maxCharges = maxCharges;
    }
}
