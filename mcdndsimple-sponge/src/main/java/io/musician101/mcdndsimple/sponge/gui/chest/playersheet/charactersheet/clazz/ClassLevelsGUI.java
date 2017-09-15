package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class ClassLevelsGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;

    public ClassLevelsGUI(Player player, ClassLevels classLevels, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CLASS_LEVELS, 18, prevGUI);
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.WOODEN_SWORD, Text.of(MenuText.BARBARIAN)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setBarbarian(i);
            delayedOpen();
        }));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.NOTEBLOCK, Text.of(MenuText.BARD)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setBard(i);
            delayedOpen();
        }));
        set(2, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.CLERIC)), PotionEffectTypes.INSTANT_HEALTH), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setCleric(i);
            delayedOpen();
        }));
        set(3, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.DRUID)), PotionEffectTypes.LUCK), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setDruid(i);
            delayedOpen();
        }));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.IRON_SWORD, Text.of(MenuText.FIGHTER)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setFighter(i);
            delayedOpen();
        }));
        set(5, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.MONK)), PotionEffectTypes.SPEED), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setMonk(i);
            delayedOpen();
        }));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.PALADIN)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setPaladin(i);
            delayedOpen();
        }));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(MenuText.RANGER)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setRanger(i);
            delayedOpen();
        }));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.ROGUE)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setRogue(i);
            delayedOpen();
        }));
        set(12, ClickInventoryEvent.class, createItem(ItemTypes.MAGMA_CREAM, Text.of(MenuText.SORCERER)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setSorcerer(i);
            delayedOpen();
        }));
        set(13, ClickInventoryEvent.class, createItem(ItemTypes.END_CRYSTAL, Text.of(MenuText.WARLOCK)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setWarlock(i);
            delayedOpen();
        }));
        set(14, ClickInventoryEvent.class, createItem(ItemTypes.TOTEM_OF_UNDYING, Text.of(MenuText.WIZARD)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            classLevels.setWizard(i);
            delayedOpen();
        }));
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
