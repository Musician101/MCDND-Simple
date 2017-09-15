package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class ExperienceGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;
    private final Experience experience;

    public ExperienceGUI(Player player, ClassLevels classLevels, Experience experience, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.LEVEL_AND_XP, 9, prevGUI);
        this.classLevels = classLevels;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, createItem(ItemTypes.BOOK, Text.of(MenuText.overallLevel(experience, classLevels))));
        set(1, createItem(ItemTypes.ANVIL, Text.of(MenuText.proficiencyBonus(experience, classLevels))));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.currentXP(experience))), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                experience.setExp(i);
                open();
            });
        });
        set(3, createItem(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.xpForNextLevel(experience, classLevels))));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
