package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;


public class MeleeBonusGUI extends MCDNDSimpleChestGUI {

    private final MeleeBonus meleeBonus;

    public MeleeBonusGUI(Player player, MeleeBonus meleeBonus, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.MELEE_BONUSES, 9, prevGUI);
        this.meleeBonus = meleeBonus;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.ATTACK_ROLLS)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            meleeBonus.setAttack(dice);
            delayedOpen();
        }));

        set(1, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.DAMAGE_ROLLS)), PotionEffectTypes.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            meleeBonus.setDamage(dice);
            delayedOpen();
        }));

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
