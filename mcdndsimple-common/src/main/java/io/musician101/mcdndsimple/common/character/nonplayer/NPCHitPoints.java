package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.HitPoints;

public class NPCHitPoints extends HitPoints {

    private Dice hitDice = new Dice(0);

    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }
}
