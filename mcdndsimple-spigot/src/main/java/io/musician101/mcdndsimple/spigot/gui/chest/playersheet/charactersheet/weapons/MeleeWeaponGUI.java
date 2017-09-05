package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MeleeWeaponGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final MeleeBonus meleeBonus;
    private final MeleeWeapon weapon;

    public MeleeWeaponGUI(Player player, MeleeWeapon weapon, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, MeleeBonus meleeBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 18, weapon.getName(), prevGUI);
        this.weapon = weapon;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.meleeBonus = meleeBonus;
    }

    @Override
    protected void build() {
        boolean isProficient = weapon.isProficient();
        set(0, createItem(isProficient ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_ON, MenuText.isProficient(weapon.isProficient())), player -> {
            weapon.setIsProficient(!isProficient);
            open();
        });
        set(1, createItem(Material.PAPER, MenuText.CHANGE_NAME), player -> new StringInputAnvilGUI(player, (p, s) -> {
            weapon.setName(s);
            delayedOpen(() -> new MeleeWeaponGUI(player, weapon, bioAndInfo, classLevels, coreStats, experience, meleeBonus, prevGUI));
        }));
        set(2, createItem(Material.IRON_SWORD, MenuText.ATTACK_STAT), player -> new AttackStatGUI(player, weapon, coreStats, this));
        set(3, createItem(Material.ENCHANTED_BOOK, MenuText.magicBonus(weapon.getMagicBonus())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            weapon.setMagicBonus(i);
            delayedOpen();
        }));
        set(4, createItem(Material.STONE_SWORD, MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))));
        set(5, createItem(Material.GOLD_SWORD, MenuText.damageDice(weapon.getDamageDice())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setDamageDice(dice);
            delayedOpen();
        }));
        boolean plusStat = weapon.isPlusStat();
        set(6, addGlowIfConditionsMet(createItem(Material.GLOWSTONE, MenuText.PLUS_STAT), () -> plusStat), player -> {
            weapon.setPlusStat(!plusStat);
            open();
        });
        set(7, createItem(Material.ENCHANTED_BOOK, MenuText.damageBonus(weapon.getDamageBonus(coreStats))));
        set(8, createItem(Material.DIAMOND, MenuText.damageType(weapon.getDamageType())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            weapon.setDamageType(s);
            delayedOpen();
        }));
        set(9, createItem(Material.DIAMOND_SWORD, MenuText.critDamage(weapon.getCritDamageDice())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            weapon.setCritDamageDice(dice);
            delayedOpen();
        }));
        set(10, createItem(Material.SULPHUR, MenuText.critOn(weapon.getCritMin())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            weapon.setCritMin(i);
            delayedOpen();
        }));
        set(11, createItem(Material.REDSTONE_LAMP_ON, MenuText.ROLL_ATTACK), player -> {
            String newLine = "\n";
            Dice d20 = new Dice(20);
            int firstAttackRoll = d20.roll().get(0).getValue();
            int secondAttackRoll = d20.roll().get(0).getValue();
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(meleeBonus.getAttack().roll());
            TextComponent firstRoll = new TextComponent("" + (firstAttackRoll + bonus));
            if (firstAttackRoll >= weapon.getCritMin()) {
                firstRoll.setColor(ChatColor.GREEN);
            }
            else if (firstAttackRoll == 1) {
                firstRoll.setColor(ChatColor.RED);
            }

            TextComponent secondRoll = new TextComponent(" | " + (secondAttackRoll + bonus));
            if (secondAttackRoll >= weapon.getCritMin()) {
                secondRoll.setColor(ChatColor.GREEN);
            }
            else if (secondAttackRoll == 1) {
                secondRoll.setColor(ChatColor.RED);
            }

            TextComponent attackText = new TextComponent("Attack: ");
            attackText.addExtra(firstRoll);
            attackText.addExtra(secondRoll);
            attackText.addExtra(" vs AC" + newLine);

            int damage = Dice.total(weapon.getDamageDice().roll()) + weapon.getDamageBonus(coreStats) + Dice.total(meleeBonus.getDamage().roll());
            TextComponent damageText = new TextComponent("Damage: " + damage + " " + weapon.getDamageType());
            TextComponent message = new TextComponent(player.getName() + " (as " + bioAndInfo.getName() + ") meleed with " + weapon.getName() + "." + newLine);
            message.addExtra(attackText);
            message.addExtra(damageText);
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamageDice();
                message.addExtra(new TextComponent("Crit: " + Dice.total(critDice.roll()) + newLine + "Crit (adv roll): " + Dice.total(critDice.roll())));
            }

            player.spigot().sendMessage(message);
        });
        setBackButton(17, Material.BARRIER);
    }
}
