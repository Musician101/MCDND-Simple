package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class OutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final OutputOptions outputOptions;

    public OutputOptionsGUI(Player player, OutputOptions outputOptions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.OUTPUT_OPTIONS, 9, prevGUI);
        this.outputOptions = outputOptions;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.IRON_INGOT, Text.of(MenuText.SAVING_THROWS)), player -> new SavingThrowOutputOptionsGUI(player, outputOptions.getSavingThrowOutputOptions(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.GOLD_INGOT, Text.of(MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS)), player -> new WeaponsSpellMiscOutputOptionsGUI(player, outputOptions.getWeaponsSpellMiscOutputOptions(), this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.NETHER_STAR, Text.of(MenuText.CORE_SKILLS_OUTPUT_SKILLS)), player -> new CoreSkillsOutputOptionsGUI(player, outputOptions.getCoreSkillsOutputOptions(), this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
