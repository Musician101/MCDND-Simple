package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.skill.Skill;
import io.musician101.mcdndsimple.common.character.skill.SkillProficiency;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SkillProficiencyGUI extends MCDNDSimpleChestGUI {

    private final Skill skill;

    public SkillProficiencyGUI(Player player, Skill skill, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.PROFICIENT, prevGUI);
        this.skill = skill;
    }

    @Override
    protected void build() {
        SkillProficiency[] skillProficiencies = SkillProficiency.values();
        for (int x = 0; x < skillProficiencies.length; x++) {
            SkillProficiency skillProficiency = skillProficiencies[x];
            set(x, ClickType.LEFT, createItem(Material.BOOK, skillProficiency.getName()), player -> {
                skill.setSkillProficiency(skillProficiency);
                open();
            });
        }
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
