package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class HealingGUI extends MCDNDSimpleChestGUI {

    private final SpellHealing spellHealing;

    public HealingGUI(Player player, SpellHealing spellHealing, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.HEALING, 9, prevGUI);
        this.spellHealing = spellHealing;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.healAmount(spellHealing.getHealAmount()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            spellHealing.setHealAmount(dice);
            delayedOpen();
        }));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE, Text.of(MenuText.STAT_BONUS)), player -> new StatBonusGUI<>(player, spellHealing, SpellHealing::setStatBonus, (sh, statBonus) -> sh.getStatBonus() == statBonus, this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
