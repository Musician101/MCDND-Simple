package io.musician101.mcdndsimple.sponge.gui.player;

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
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class RangedWeaponGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final RangedWeapon weapon;

    public RangedWeaponGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons().get(index).getName(), 18);
        this.weapon = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons().get(index);
        updateIsProficient();
        setButton(1, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                weapon.setName(s);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, Text.of(MenuText.ATTACK_STAT)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new AttackStatGUI(mcdndPlayer, index, p)));
        setButton(3, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.magicBonus(weapon))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
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
        setButton(4, SpongeIconBuilder.of(ItemTypes.STONE_SWORD, Text.of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)))));
        setButton(5, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.damageDice(weapon.getDamage()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                weapon.setDamage(dice);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(6, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.damageBonus(weapon, coreStats))));
        setButton(7, SpongeIconBuilder.of(ItemTypes.DIAMOND, Text.of(MenuText.damageType(weapon.getDamageType()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                weapon.setDamageType(s);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.critDamage(weapon))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(Text.of(TextColors.RED + Messages.malformedDiceInput(s)));
                    return;
                }

                weapon.setCritDamage(dice);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(9, SpongeIconBuilder.of(ItemTypes.GUNPOWDER, Text.of(MenuText.critOn(weapon))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                weapon.setCritMin(i);
                new RangedWeaponGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(10, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_ATTACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            Dice d20 = new Dice(20);
            int firstAttackRoll = Dice.total(d20.roll());
            int secondAttackRoll = Dice.total(d20.roll());
            RangedBonus rangedBonus = coreStatsTab.getBonuses().getRanged();
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(rangedBonus.getAttack().roll());
            String newLine = "\n";
            List<Object> objects = new ArrayList<>();
            objects.add(p.getName() + " (as " + mcdndPlayer.getBioAndInfo().getName() + ") ranged attacked with " + weapon.getName() + "." + newLine + "Attack: ");
            if (firstAttackRoll >= weapon.getCritMin()) {
                objects.add(TextColors.GREEN);
            }
            else if (firstAttackRoll == 1) {
                objects.add(TextColors.RED);
            }

            objects.add((firstAttackRoll + bonus) + " | ");
            if (secondAttackRoll >= weapon.getCritMin()) {
                objects.add(TextColors.GREEN);
            }
            else if (secondAttackRoll == 1) {
                objects.add(TextColors.RED);
            }

            objects.add((secondAttackRoll + bonus)+ " vs AC" + newLine + "Damage: " + Dice.total(weapon.getDamage().roll(), weapon.getDamageBonus(coreStats) + Dice.total(rangedBonus.getDamage().roll())) + " " + weapon.getDamageType());
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                objects.add("Crit: " + Dice.total(critDice.roll()) + newLine + "Crit (adv roll): " + critDice.roll());
            }

            broadcastMessage(Text.of(objects));
            p.closeInventory();
        }));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RangedWeaponsGUI(mcdndPlayer, p)));
    }

    private void updateIsProficient() {
        setButton(0, SpongeIconBuilder.builder(ItemTypes.WOOL).type(Keys.DYE_COLOR, weapon.isProficient() ? DyeColors.GREEN : DyeColors.RED).name(Text.of(MenuText.isProficient(weapon))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            weapon.setIsProficient(!weapon.isProficient());
            updateIsProficient();
        }));
    }
}
