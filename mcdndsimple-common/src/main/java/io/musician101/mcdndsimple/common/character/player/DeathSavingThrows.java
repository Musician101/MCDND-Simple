package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.Dice;

public class DeathSavingThrows {

    private final Dice d20 = new Dice(20);
    private int failCount = 0;
    private int successCount = 0;

    public int getFailCount() {
        return failCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void reset() {
        failCount = 0;
        successCount = 0;
    }

    public int roll() {
        int roll = Dice.total(d20.roll());
        if (roll == 1) {
            failCount = +2;
        }
        else if (roll >= 10 && roll < 20) {
            successCount++;
        }
        else if (roll > 1 && roll < 10) {
            failCount++;
        }

        return roll;
    }
}
