package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class SkillProficiencyGUI extends MCDNDSimpleChestGUI {

    private final PlayerSkill skill;

    public SkillProficiencyGUI(Player player, PlayerSkill skill, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.PROFICIENT, 9, prevGUI);
        this.skill = skill;
    }

    @Override
    protected void build() {
        SkillProficiency[] skillProficiencies = SkillProficiency.values();
        for (int x = 0; x < skillProficiencies.length; x++) {
            SkillProficiency skillProficiency = skillProficiencies[x];
            set(x, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(skillProficiency.getName())), player -> {
                skill.setSkillProficiency(skillProficiency);
                closeGUI();
            });
        }
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
