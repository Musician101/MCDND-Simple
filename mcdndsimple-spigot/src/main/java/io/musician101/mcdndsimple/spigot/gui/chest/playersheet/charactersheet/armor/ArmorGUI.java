package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class ArmorGUI extends MCDNDSimpleChestGUI {

    private final Armor armor;

    public ArmorGUI(Player player, Armor armor, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, armor.getName(), prevGUI);
        this.armor = armor;
    }

    @Override
    protected void build() {
        boolean isWorn = armor.isUnarmored();
        ItemStack worn = createItem(Material.LEATHER_CHESTPLATE, MenuText.isWorn(armor));
        if (isWorn) {
            worn = addGlow(worn);
        }

        set(0, worn, player -> {
            armor.setIsWorn(!isWorn);
            player.closeInventory();
            open();
        });

        boolean isUnarmored = armor.isUnarmored();
        ItemStack unarmored = createItem(Material.LEATHER_CHESTPLATE, MenuText.isUnarmored(armor));
        if (isUnarmored) {
            unarmored = addGlow(unarmored);
        }

        set(1, unarmored, player -> {
            armor.setIsUnarmored(!isUnarmored);
            player.closeInventory();
            open();
        });
        set(2, createItem(Material.PAPER, MenuText.RENAME), player -> new StringInputAnvilGUI(player, (p, s) -> {
            armor.setName(s);
            player.closeInventory();
            open();
        }));
        set(3, createItem(Material.CHAINMAIL_CHESTPLATE, MenuText.baseAC(armor)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            armor.setBaseArmorClass(i);
            player.closeInventory();
            open();
        }));
        set(4, createItem(Material.IRON_INGOT, armor.getArmorType().getName()), player -> new ArmorTypeGUI(player, armor, this));
        set(5, createItem(Material.ENCHANTMENT_TABLE, MenuText.magicBonus(armor.getMagicBonus())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            armor.setMagicBonus(i);
            player.closeInventory();
            open();
        }));
        set(6, createItem(Material.DIAMOND_CHESTPLATE, MenuText.totalAC(armor)));
        set(7, setPotionEffect(createItem(Material.POTION, MenuText.hasStealthPenalty(armor)), PotionType.INVISIBILITY), player -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            player.closeInventory();
            open();
        });
        set(8, setPotionEffect(createItem(Material.POTION, MenuText.hasSpeedPenalty(armor)), PotionType.SPEED), player -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            player.closeInventory();
            open();
        });
        setBackButton(17, Material.BARRIER);
    }
}
