package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.outputoption.OutputOptions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class OutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final OutputOptions outputOptions;

    public OutputOptionsGUI(Player player, OutputOptions outputOptions, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.OUTPUT_OPTIONS, prevGUI);
        this.outputOptions = outputOptions;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.IRON_INGOT, MenuText.SAVING_THROWS), player -> new SavingThrowOutputOptionsGUI(player, outputOptions.getSavingThrowOutputOptions(), this));
        set(1, createItem(Material.GOLD_INGOT, MenuText.WEAPONS_SPELL_MISC), player -> new WeaponsSpellMiscOutputOptionsGUI(player, outputOptions.getWeaponsSpellMiscOutputOptions(), this));
        set(2, createItem(Material.NETHER_STAR, MenuText.CORE_SKILLS), player -> new CoreSkillsOutputOptionsGUI(player, outputOptions.getCoreSkillsOutputOptions(), this));
        setBackButton(8, Material.BARRIER);
    }
}
