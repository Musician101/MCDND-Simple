package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class ClassLevelsGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;

    public ClassLevelsGUI(Player player, ClassLevels classLevels, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.CLASS_LEVELS, prevGUI);
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.WOOD_SWORD, MenuText.BARBARIAN), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setBarbarian(i);
            open();
        }));
        set(1, ClickType.LEFT, createItem(Material.NOTE_BLOCK, MenuText.BARD), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setBard(i);
            open();
        }));
        set(2, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.CLERIC), PotionType.INSTANT_HEAL), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setCleric(i);
            open();
        }));
        set(3, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.DRUID), PotionType.LUCK), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setDruid(i);
            open();
        }));
        set(4, ClickType.LEFT, createItem(Material.IRON_SWORD, MenuText.FIGHTER), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setFighter(i);
            open();
        }));
        set(5, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.MONK), PotionType.SPEED), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setMonk(i);
            open();
        }));
        set(9, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.PALADIN), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setPaladin(i);
            open();
        }));
        set(10, ClickType.LEFT, createItem(Material.BOW, MenuText.RANGER), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setRanger(i);
            open();
        }));
        set(11, ClickType.LEFT, createItem(Material.GOLD_SWORD, MenuText.ROGUE), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setRogue(i);
            open();
        }));
        set(12, ClickType.LEFT, createItem(Material.MAGMA_CREAM, MenuText.SORCERER), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setRogue(i);
            open();
        }));
        set(13, ClickType.LEFT, createItem(Material.END_CRYSTAL, MenuText.WARLOCK), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setWarlock(i);
            open();
        }));
        set(14, ClickType.LEFT, createItem(Material.TOTEM, MenuText.WIZARD), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setWizard(i);
            open();
        }));
        setBackButton(17, ClickType.LEFT, Material.BARRIER);
    }
}
