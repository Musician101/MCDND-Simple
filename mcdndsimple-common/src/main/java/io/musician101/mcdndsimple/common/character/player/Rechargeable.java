package io.musician101.mcdndsimple.common.character.player;

public class Rechargeable {

    private Recharge recharge = Recharge.NONE;

    public Recharge getRecharge() {
        return recharge;
    }

    public void setRecharge(Recharge recharge) {
        this.recharge = recharge;
    }
}