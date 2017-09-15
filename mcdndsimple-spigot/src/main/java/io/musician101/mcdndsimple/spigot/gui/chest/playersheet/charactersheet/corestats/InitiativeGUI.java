package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.tab.Initiative;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class InitiativeGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final AbilityScore dex;
    private final Initiative initiative;

    public InitiativeGUI(Player player, AbilityScore dex, BioAndInfo bioAndInfo, Initiative initiative, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.INITIATIVE, prevGUI);
        this.dex = dex;
        this.bioAndInfo = bioAndInfo;
        this.initiative = initiative;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.GLOWSTONE_DUST, MenuText.BONUS), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                initiative.setBonus(i);
                player.closeInventory();
                open();
            });
        });
        set(1, createItem(Material.GLOWSTONE, MenuText.totalInitiative(initiative, dex)));
        set(2, ClickType.LEFT, createItem(Material.REDSTONE_LAMP_OFF, MenuText.ROLL_INITIATIVE), player -> {
            player.closeInventory();
            int baseResult = new Dice(20).roll().get(0).getValue();
            String resultMsg = Integer.toString(baseResult + initiative.getInitiative(dex));
            if (baseResult == 1) {
                resultMsg = ChatColor.RED + resultMsg;
            }
            else if (baseResult == 20) {
                resultMsg = ChatColor.GREEN + resultMsg;
            }

            Stream.of(Messages.initiative(bioAndInfo, player.getName(), resultMsg)).forEach(Bukkit::broadcastMessage);
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
