package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class RangedWeaponGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final RangedWeapon weapon;

    public RangedWeaponGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons().get(index).getName(), 18);
        this.weapon = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons().get(index);
        updateIsProficient();
        setButton(1, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                weapon.setName(s);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.ATTACK_STAT), ImmutableMap.of(ClickType.LEFT, p -> new AttackStatGUI(mcdndPlayer, index, p)));
        setButton(3, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.magicBonus(weapon)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                weapon.setMagicBonus(i);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
        CoreStats<PlayerAbilityScore> coreStats = coreStatsTab.getCoreStats();
        Experience experience = coreStatsTab.getExperience();
        setButton(4, SpigotIconBuilder.of(Material.STONE_SWORD, MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))));
        setButton(5, SpigotIconBuilder.of(Material.GOLDEN_SWORD, MenuText.damageDice(weapon.getDamage())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                weapon.setDamage(dice);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(6, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.damageBonus(weapon, coreStats)));
        setButton(7, SpigotIconBuilder.of(Material.DIAMOND, MenuText.damageType(weapon.getDamageType())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                weapon.setDamageType(s);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(8, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.critDamage(weapon)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                weapon.setCritDamage(dice);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(9, SpigotIconBuilder.of(Material.GUNPOWDER, MenuText.critOn(weapon)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                weapon.setCritMin(i);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(10, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.ROLL_ATTACK), ImmutableMap.of(ClickType.LEFT, p -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            RangedBonus rangedBonus = coreStatsTab.getBonuses().getRanged();
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(rangedBonus.getAttack().roll());
            String newLine = "\n";
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + mcdndPlayer.getBioAndInfo().getName() + ") ranged with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (firstAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(firstAttackRoll + bonus).append(" | ");
            if (secondAttackRoll >= weapon.getCritMin()) {
                sb.append(ChatColor.GREEN);
            }
            else if (secondAttackRoll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(secondAttackRoll + bonus).append(" vs AC").append(newLine).append("Damage: ").append(Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(rangedBonus.getDamage().roll()))).append(" ").append(weapon.getDamageType());
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                sb.append("Crit: ").append(Dice.total(critDice.roll())).append(newLine).append("Crit (adv roll): ").append(critDice.roll());
            }

            Bukkit.broadcastMessage(sb.toString());
            p.closeInventory();
        }));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new RangedWeaponsGUI(mcdndPlayer, p)));
    }

    private void updateIsProficient() {
        setButton(0, SpigotIconBuilder.builder(weapon.isProficient() ? Material.REDSTONE_TORCH : Material.TORCH).name(MenuText.isProficient(weapon)).addGlow(weapon.isProficient()).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            weapon.setIsProficient(!weapon.isProficient());
            updateIsProficient();
        }));
    }
}
