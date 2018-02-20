package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Text.Builder;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

public class SkillGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final PlayerSkill skill;
    private final Dice skillBonus;

    public SkillGUI(Player player, BioAndInfo bioAndInfo, Dice skillBonus, PlayerSkill skill, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, skill.getName(), 9, prevGUI);
        this.skill = skill;
        this.bioAndInfo = bioAndInfo;
        this.skillBonus = skillBonus;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.PROFICIENT), Text.of("- " + skill.getSkillProficiency().getName())), player -> new SkillProficiencyGUI(player, skill, this));
        set(1, ClickInventoryEvent.class, setDurability(createItem(ItemTypes.DYE, Text.of(MenuText.bonus(skill))), 4), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            skill.setBonus(i);
            delayedOpen();
        }));
        set(2, createItem(ItemTypes.BOOKSHELF, Text.of(MenuText.total(skill))));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL)), player -> {
            Builder message = Text.of(player.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + skill.getName() + " check.\n").toBuilder();
            Dice dice = new Dice(20);
            int bonus = Dice.total(skillBonus.roll());
            int first = Dice.total(dice.roll(), bonus);
            int second = Dice.total(dice.roll(), bonus);
            Text.Builder firstText = Text.builder("" + first);
            if (first == 20) {
                firstText.color(TextColors.GREEN);
            }
            else if (first == 1) {
                firstText.color(TextColors.RED);
            }

            Text.Builder secondText = Text.builder("" + second);
            if (second == 20) {
                secondText.color(TextColors.GREEN);
            }
            else if (second == 1) {
                secondText.color(TextColors.RED);
            }

            message.append(Text.NEW_LINE, Text.of("The results are: ", firstText.build(), Text.of(" | "), secondText.build()));
            if (bonus > 0) {
                message.append(Text.NEW_LINE, Text.of("A bonus roll of " + bonus + " was added to the total."));
            }

            Text finalMessage = message.build();
            SpongeMCDNDSimple.instance().getLogger().info(TextSerializers.PLAIN.serialize(finalMessage));
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(finalMessage));
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
