package io.musician101.mcdndsimple.common.character.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;

public class Experience {

    private int exp = 0;

    public int getOverallLevel(ClassLevels classLevels) {
        return classLevels.getBarbarian() + classLevels.getBard() + classLevels.getCleric() + classLevels.getDruid() + classLevels.getFighter() + classLevels.getMonk() + classLevels.getPaladin() + classLevels.getRanger() + classLevels.getRogue() + classLevels.getSorcerer() + classLevels.getWarlock() + classLevels.getWizard();
    }

    public int getProficiencyBonus(ClassLevels classLevels) {
        return ImmutableMap.<Integer, Integer>builder().put(1, 2).put(2, 2).put(3, 2).put(4, 2).put(5, 3).put(6, 3).put(7, 3).put(8, 3).put(9, 4).put(10, 4).put(11, 4).put(12, 4).put(13, 5).put(14, 5).put(15, 5).put(16, 5).put(17, 6).put(18, 6).put(19, 6).put(20, 6).build().get(getOverallLevel(classLevels));
    }

    public int getXP() {
        return exp;
    }

    public int getXPForNextLevel(ClassLevels classLevels) {
        return ImmutableMap.<Integer, Integer>builder().put(1, 0).put(2, 300).put(3, 900).put(4, 2700).put(5, 6500).put(6, 14000).put(7, 23000).put(8, 34000).put(9, 48000).put(10, 64000).put(11, 85000).put(12, 100000).put(13, 120000).put(14, 140000).put(15, 165000).put(16, 195000).put(17, 225000).put(18, 265000).put(19, 305000).put(20, 355000).build().get(getOverallLevel(classLevels));
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
