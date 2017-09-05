package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.spell.SpellDamage;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellDamageGUI extends MCDNDSimpleChestGUI {

    private final SpellDamage spellDamage;

    public SpellDamageGUI(Player player, SpellDamage spellDamage, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.DAMAGE, prevGUI);
        this.spellDamage = spellDamage;
    }

    @Override
    protected void build() {
        boolean canCrit = spellDamage.canCrit();
        ItemStack canCritStack = createItem(Material.DIAMOND_SWORD, MenuText.CAN_CRIT);
        if (canCrit) {
            canCritStack = addGlow(canCritStack);
        }

        set(0, canCritStack, player -> {
            spellDamage.setCanCrit(!canCrit);
            open();
        });
        set(1, createItem(Material.REDSTONE_LAMP_OFF, MenuText.DAMAGE_DICE), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellDamage.setDice(dice);
            delayedOpen();
        }));
        set(2, createItem(Material.STICK, MenuText.OTHER_BONUS), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellDamage.setBonus(i);
            delayedOpen();
        }));
        set(3, createItem(Material.ENCHANTED_BOOK, MenuText.DAMAGE_TYPE), player -> new StringInputAnvilGUI(player, (p, s) -> {
            spellDamage.setDamageType(s);
            delayedOpen();
        }));
        setBackButton(8, Material.BARRIER);
    }
}
