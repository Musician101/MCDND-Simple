package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
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

public class BonusesGUI extends MCDNDSimpleChestGUI {

    private final Bonuses bonuses;

    public BonusesGUI(Player player, Bonuses bonuses, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.BONUSES_PENALTIES, 9, prevGUI);
        this.bonuses = bonuses;
    }

    @Override
    protected void build() {
        //Melee (attack, damage)
        //Ranged (attack, damage)
        //Spellcasting (attack, damage, save dc)
        //saves
        //skill checks
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.MELEE_BONUSES)), player -> new MeleeBonusGUI(player, bonuses.getMelee(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(MenuText.RANGED_BONUSES)), player -> new RangedBonusGUI(player, bonuses.getRanged(), this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.BOW, Text.of(MenuText.SPELLCASTING_BONUSES)), player -> new SpellcastingBonusGUI(player, bonuses.getSpellcasting(), this));
        set(3, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.SAVING_THROW_BONUSES)), PotionEffectTypes.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            bonuses.setSaves(dice);
            delayedOpen();
        }));
        set(4, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.ABILITY_SKILL_CHECK_ROLLS)), PotionEffectTypes.LUCK), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            bonuses.setAbilitiesAndSkills(dice);
            delayedOpen();
        }));
        setBackButton(9, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
