package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class HitDiceGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final AbilityScore con;
    private final HitDice hitDice;

    public HitDiceGUI(Player player, AbilityScore con, BioAndInfo bioAndInfo, HitDice hitDice, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.HIT_DICE, 18, prevGUI);
        this.con = con;
        this.bioAndInfo = bioAndInfo;
        this.hitDice = hitDice;
    }

    @Override
    protected void build() {
        ItemType redstoneLamp = ItemTypes.REDSTONE_LAMP;
        String[] hitDiceStrings = MenuText.hitDice(hitDice);
        set(0, ClickInventoryEvent.class, createItem(redstoneLamp, Text.of(hitDiceStrings[0]), Text.of(MenuText.D6_DESC)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(6, i);
            open();
        }));
        set(1, ClickInventoryEvent.class, createItem(redstoneLamp, Text.of(hitDiceStrings[1]), convertToText(MenuText.D8_DESC)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(8, i);
            open();
        }));
        set(2, ClickInventoryEvent.class, createItem(redstoneLamp, Text.of(hitDiceStrings[2]), Text.of(MenuText.D10_DESC)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(10, i);
            open();
        }));
        set(3, ClickInventoryEvent.class, createItem(redstoneLamp, Text.of(hitDiceStrings[3]), Text.of(MenuText.D12_DESC)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(12, i);
            open();
        }));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.rollHitDie(6))), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 6)));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.rollHitDie(8))), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 8)));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.rollHitDie(10))), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 10)));
        set(12, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.rollHitDie(12))), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 12)));
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private void rollHitDie(int amount, int sides) {
        player.closeInventory();
        Dice dice = new Dice(amount, sides);
        String message = StringUtils.join(convertToText(Messages.rolledHitDice(bioAndInfo, player.getName(), Dice.total(dice.roll(), con.getMod()), dice)), "\n");
        SpongeMusicianLibrary.instance().getLogger().info(message);
        Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(Text.of(message)));
    }
}
