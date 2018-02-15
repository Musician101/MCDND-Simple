package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.ranged;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.AttackStatGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

public class RangedWeaponGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final RangedBonus rangedBonus;
    private final RangedWeapon weapon;

    public RangedWeaponGUI(Player p, RangedWeapon weapon, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, RangedBonus rangedBonus, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(p, weapon.getName(), 18, prevGUI);
        this.weapon = weapon;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.rangedBonus = rangedBonus;
    }

    @Override
    protected void build() {
        boolean isProficient = weapon.isProficient();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.isProficient(weapon.isProficient()))), () -> isProficient), player -> {
            weapon.setIsProficient(!isProficient);
            open();
        });
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.CHANGE_NAME)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            weapon.setName(s);
            delayedOpen(() -> new RangedWeaponGUI(player, weapon, bioAndInfo, classLevels, coreStats, experience, rangedBonus, prevGUI));
        }));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.IRON_SWORD, Text.of(MenuText.ATTACK_STAT)), player -> new AttackStatGUI<>(player, weapon, prevGUI));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.magicBonus(weapon.getMagicBonus()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            weapon.setMagicBonus(i);
            delayedOpen();
        }));
        set(4, createItem(ItemTypes.STONE_SWORD, Text.of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)))));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.damageDice(weapon.getDamage()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setDamage(dice);
            delayedOpen();
        }));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.ARROW, Text.of(MenuText.ammo(weapon.getAmmo()))), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
               weapon.setAmmo(i);
               delayedOpen();
            });
        });
        set(7, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.damageBonus(weapon.getDamageBonus(coreStats)))));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND, Text.of(MenuText.damageType(weapon.getDamageType()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            weapon.setDamageType(s);
            delayedOpen();
        }));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.critDamage(weapon.getCritDamage()))), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(Text.of(TextColors.RED, Messages.malformedDiceInput(s)));
                return;
            }

            weapon.setCritDamage(dice);
            delayedOpen();
        }));
        set(10, ClickInventoryEvent.class, createItem(ItemTypes.GUNPOWDER, Text.of(MenuText.critOn(weapon.getCritMin()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            weapon.setCritMin(i);
            delayedOpen();
        }));
        set(11, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_ATTACK)), player -> {
            String newLine = "\n";
            Text.Builder message = Text.builder(player.getName() + " (as " + bioAndInfo.getName() + ") meleed with " + weapon.getName() + ".");
            Dice d20 = new Dice(20);
            int firstAttackRoll = d20.roll().get(0).getValue();
            int secondAttackRoll = d20.roll().get(0).getValue();
            int bonus = weapon.getToHit(classLevels, coreStats, experience) + Dice.total(rangedBonus.getAttack().roll());
            Text.Builder firstRoll = Text.of(firstAttackRoll + bonus).toBuilder();
            if (firstAttackRoll >= weapon.getCritMin()) {
                firstRoll.color(TextColors.GREEN);
            }
            else if (firstAttackRoll == 1) {
                firstRoll.color(TextColors.RED);
            }

            Text.Builder secondRoll = Text.builder(" | " + (secondAttackRoll + bonus));
            if (secondAttackRoll >= weapon.getCritMin()) {
                secondRoll.color(TextColors.GREEN);
            }
            else if (secondAttackRoll == 1) {
                secondRoll.color(TextColors.RED);
            }

            Text.Builder attackText = Text.builder("Attack: ");
            attackText.append(firstRoll.build());
            attackText.append(secondRoll.build());
            attackText.append(Text.of(" vs AC" + newLine));
            message.append(attackText.build());

            int damage = Dice.total(weapon.getDamage().roll()) + weapon.getDamageBonus(coreStats) + Dice.total(rangedBonus.getDamage().roll());
            message.append(Text.of("Damage: " + damage + " " + weapon.getDamageType()));
            if (firstAttackRoll >= weapon.getCritMin()) {
                Dice critDice = weapon.getCritDamage();
                message.append(Text.of("Crit: " + Dice.total(critDice.roll()) + newLine + "Crit (adv roll): " + Dice.total(critDice.roll())));
            }

            Text finalMessage = message.build();
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(finalMessage));
            SpongeMCDNDSimple.instance().getLogger().info(TextSerializers.PLAIN.serialize(finalMessage));
        });
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
