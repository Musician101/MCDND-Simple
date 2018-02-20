package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

public class ArmorGUI extends MCDNDSimpleChestGUI {

    private final Armor armor;

    public ArmorGUI(Player player, Armor armor, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, armor.getName(), 18, prevGUI);
        this.armor = armor;
    }

    @Override
    protected void build() {
        boolean isWorn = armor.isUnarmored();
        ItemStack worn = createItem(ItemTypes.LEATHER_CHESTPLATE, Text.of(MenuText.isWorn(armor)));
        if (isWorn) {
            worn = addGlow(worn);
        }

        set(0, ClickInventoryEvent.class, worn, player -> {
            armor.setIsWorn(!isWorn);
            open();
        });

        boolean isUnarmored = armor.isUnarmored();
        ItemStack unarmored = createItem(ItemTypes.LEATHER_CHESTPLATE, Text.of(MenuText.isUnarmored(armor)));
        if (isUnarmored) {
            unarmored = addGlow(unarmored);
        }

        set(1, ClickInventoryEvent.class, unarmored, player -> {
            armor.setIsUnarmored(!isUnarmored);
            open();
        });
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.RENAME)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            armor.setName(s);
            open();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.CHAINMAIL_CHESTPLATE, Text.of(MenuText.baseAC(armor))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            armor.setBaseArmorClass(i);
            open();
        }));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.IRON_INGOT, Text.of(armor.getArmorType().getName())), player -> new ArmorTypeGUI(player, armor, this));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.magicBonus(armor.getMagicBonus()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            armor.setMagicBonus(i);
            open();
        }));
        set(6, createItem(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.totalAC(armor))));
        set(7, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.hasStealthPenalty(armor))), PotionEffectTypes.INVISIBILITY), player -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            open();
        });
        set(8, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.hasSpeedPenalty(armor))), PotionEffectTypes.SPEED), player -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            open();
        });
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
