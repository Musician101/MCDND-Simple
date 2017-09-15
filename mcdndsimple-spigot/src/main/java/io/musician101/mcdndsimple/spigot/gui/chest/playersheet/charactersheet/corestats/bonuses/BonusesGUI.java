package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.bonus.Bonuses;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class BonusesGUI extends MCDNDSimpleChestGUI {

    private final Bonuses bonuses;

    public BonusesGUI(Player player, Bonuses bonuses, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.BONUSES_PENALTIES, prevGUI);
        this.bonuses = bonuses;
    }

    @Override
    protected void build() {
        //Melee (attack, damage)
        //Ranged (attack, damage)
        //Spellcasting (attack, damage, save dc)
        //saves
        //skill checks
        set(0, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.MELEE_BONUSES), player -> new MeleeBonusGUI(player, bonuses.getMelee(), this));
        set(1, ClickType.LEFT, createItem(Material.BOW, MenuText.RANGED_BONUSES), player -> new RangedBonusGUI(player, bonuses.getRanged(), this));
        set(2, ClickType.LEFT, createItem(Material.BOW, MenuText.SPELLCASTING_BONUSES), player -> new SpellcastingBonusGUI(player, bonuses.getSpellcasting(), this));
        set(3, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.SAVING_THROW_BONUSES), PotionType.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            bonuses.setSaves(dice);
            delayedOpen();
        }));
        set(4, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.ABILITY_SKILL_CHECK_ROLLS), PotionType.LUCK), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            bonuses.setAbilitiesAndSkills(dice);
            delayedOpen();
        }));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
