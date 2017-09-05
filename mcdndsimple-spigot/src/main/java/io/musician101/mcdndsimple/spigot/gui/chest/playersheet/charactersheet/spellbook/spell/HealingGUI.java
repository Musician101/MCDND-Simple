package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.spell.SpellHealing;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HealingGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final SpellHealing spellHealing;

    public HealingGUI(Player player, CoreStats coreStats, SpellHealing spellHealing, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.HEALING, prevGUI);
        this.coreStats = coreStats;
        this.spellHealing = spellHealing;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.REDSTONE_LAMP_OFF, MenuText.healAmount(spellHealing.getHealAmount())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellHealing.setHealAmount(dice);
            delayedOpen();
        }));
        set(1, createItem(Material.REDSTONE, MenuText.STAT_BONUS), player -> new StatBonusGUI<>(player, coreStats, spellHealing, SpellHealing::setStatBonus, this));
        setBackButton(8, Material.BARRIER);
    }
}