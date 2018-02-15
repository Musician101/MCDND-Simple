package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class SpellDamageGUI extends MCDNDSimpleChestGUI {

    private final SpellDamage spellDamage;

    public SpellDamageGUI(Player player, SpellDamage spellDamage, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.DAMAGE, 9, prevGUI);
        this.spellDamage = spellDamage;
    }

    @Override
    protected void build() {
        boolean canCrit = spellDamage.canCrit();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.CAN_CRIT)), () -> canCrit), player -> {
            spellDamage.setCanCrit(!canCrit);
            open();
        });
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.DAMAGE_DICE)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellDamage.setDamage(dice);
            delayedOpen();
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.STICK, Text.of(MenuText.OTHER_BONUS)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellDamage.setBonus(i);
            delayedOpen();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.DAMAGE_TYPE)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spellDamage.setDamageType(s);
            delayedOpen();
        }));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
