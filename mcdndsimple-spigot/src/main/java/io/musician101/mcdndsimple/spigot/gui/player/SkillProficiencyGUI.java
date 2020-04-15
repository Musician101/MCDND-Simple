package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.PlayerSkillGUI.PlayerSkillBridger;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SkillProficiencyGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final PlayerSkillBridger bridger;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public SkillProficiencyGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull PlayerSkillBridger bridger, @Nonnull Player player) {
        super(player, MenuText.SKILL_PROFICIENCY, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.bridger = bridger;
        updateSlots();
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new PlayerSkillGUI(mcdndPlayer, bridger, p)));
    }

    private void updateSlots() {
        PlayerSkill skill = bridger.getSkill(mcdndPlayer);
        IntStream.of(0, 45).forEach(x -> {
            try {
                SkillProficiency skillProficiency = SkillProficiency.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.of(Material.BOOK, (skillProficiency == skill.getSkillProficiency() ? ChatColor.GREEN : ChatColor.RED) + skillProficiency.getName());
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    skill.setSkillProficiency(skillProficiency);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(SkillProficiency.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
