package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.skills;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.skill.Skill;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SkillGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final Skill skill;
    private final Dice skillBonus;

    public SkillGUI(Player player, BioAndInfo bioAndInfo, Dice skillBonus, Skill skill, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, skill.getName(), prevGUI);
        this.skill = skill;
        this.bioAndInfo = bioAndInfo;
        this.skillBonus = skillBonus;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.PROFICIENT, "- " + skill.getSkillProficiency().getName()), player -> new SkillProficiencyGUI(player, skill, this));
        set(1, ClickType.LEFT, setDurability(createItem(Material.INK_SACK, MenuText.bonus(skill)), 4), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            skill.setBonus(i);
            delayedOpen();
        }));
        set(2, createItem(Material.BOOKSHELF, MenuText.total(skill)));
        set(3, ClickType.LEFT, createItem(Material.REDSTONE_LAMP_ON, MenuText.ROLL), player -> {
            Dice dice = new Dice(20);
            int bonus = Dice.total(skillBonus.roll());
            int first = Dice.total(dice.roll(), bonus);
            int second = Dice.total(dice.roll(), bonus);
            TextComponent firstText = new TextComponent("" + first);
            if (first == 20) {
                firstText.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            }
            else if (first == 1) {
                firstText.setColor(net.md_5.bungee.api.ChatColor.RED);
            }

            TextComponent secondText = new TextComponent("" + second);
            if (second == 20) {
                secondText.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            }
            else if (second == 1) {
                secondText.setColor(net.md_5.bungee.api.ChatColor.RED);
            }

            TextComponent message = new TextComponent(player.getName() + " (as " + bioAndInfo.getName() + ") has rolled a " + skill.getName() + " check.\n");
            message.addExtra("The results are: ");
            message.addExtra(firstText);
            message.addExtra(" | ");
            message.addExtra(secondText);
            if (bonus > 0) {
                message.addExtra("\nA bonus roll of " + bonus + " was added to the total.");
            }

            Bukkit.spigot().broadcast(message);
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
