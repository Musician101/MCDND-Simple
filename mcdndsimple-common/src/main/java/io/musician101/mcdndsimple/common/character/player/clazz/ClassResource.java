package io.musician101.mcdndsimple.common.character.player.clazz;

import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import javax.annotation.Nonnull;

public class ClassResource extends Rechargeable {

    private int currentCharges = 0;
    private int maxCharges = 0;
    @Nonnull
    private String name = "";

    public int getMaxUses() {
        return maxCharges;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
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
