package io.musician101.mcdndsimple.common.character.player.bonus;

import io.musician101.mcdndsimple.common.Dice;

public class MeleeBonus {

    private Dice attack = new Dice(0);
    private Dice damage = new Dice(0);

    public Dice getAttack() {
        return attack;
    }

    public void setAttack(Dice attack) {
        this.attack = attack;
    }

    public Dice getDamage() {
        return damage;
    }

    public void setDamage(Dice damage) {
        this.damage = damage;
    }
}
