package io.musician101.mcdndsimple.common.character.player;

import javax.annotation.Nonnull;

public abstract class Rechargeable {

    @Nonnull
    private Recharge recharge = Recharge.NONE;

    @Nonnull
    public Recharge getRecharge() {
        return recharge;
    }

    public void setRecharge(@Nonnull Recharge recharge) {
        this.recharge = recharge;
    }
}
