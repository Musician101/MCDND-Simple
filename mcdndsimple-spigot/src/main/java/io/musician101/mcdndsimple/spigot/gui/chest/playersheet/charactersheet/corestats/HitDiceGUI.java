package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HitDiceGUI extends MCDNDSimpleChestGUI {

    private final AbilityScore con;
    private final BioAndInfo bioAndInfo;
    private final HitDice hitDice;

    public HitDiceGUI(Player player, AbilityScore con, BioAndInfo bioAndInfo, HitDice hitDice, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, MenuText.HIT_DICE, prevGUI);
        this.con = con;
        this.bioAndInfo = bioAndInfo;
        this.hitDice = hitDice;
    }

    @Override
    protected void build() {
        Material redstoneLamp = Material.REDSTONE_LAMP_OFF;
        String[] hitDiceStrings = MenuText.hitDice(hitDice);
        set(0, createItem(redstoneLamp, hitDiceStrings[0], MenuText.D6_DESC), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(6, i);
            player.closeInventory();
            open();
        }));
        set(1, createItem(redstoneLamp, hitDiceStrings[1], MenuText.D8_DESC), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(8, i);
            player.closeInventory();
            open();
        }));
        set(2, createItem(redstoneLamp, hitDiceStrings[2], MenuText.D10_DESC), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(10, i);
            player.closeInventory();
            open();
        }));
        set(3, createItem(redstoneLamp, hitDiceStrings[3], MenuText.D12_DESC), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            hitDice.updateHitDie(12, i);
            player.closeInventory();
            open();
        }));
        set(9, createItem(Material.REDSTONE_LAMP_ON, MenuText.rollHitDie(6)), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 6)));
        set(10, createItem(Material.REDSTONE_LAMP_ON, MenuText.rollHitDie(8)), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 8)));
        set(11, createItem(Material.REDSTONE_LAMP_ON, MenuText.rollHitDie(10)), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 10)));
        set(12, createItem(Material.REDSTONE_LAMP_ON, MenuText.rollHitDie(12)), player -> new IntegerInputAnvilGUI(player, (p, i) -> rollHitDie(i, 12)));
        setBackButton(17, Material.BARRIER);
    }

    private void rollHitDie(int amount, int sides) {
        player.closeInventory();
        Dice dice = new Dice(amount, sides);
        player.sendMessage(Messages.rolledHitDice(bioAndInfo, player.getName(), Dice.total(dice.roll(), con.getMod()), dice));
    }
}
