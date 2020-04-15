package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.gui.player.PlayerSkillGUI.PlayerSkillBridger;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SkillProficiencyGUI extends SpongeMCDNDSimpleGUI {

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
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerSkillGUI(mcdndPlayer, bridger, p)));
    }

    private void updateSlots() {
        PlayerSkill skill = bridger.getSkill(mcdndPlayer);
        IntStream.of(0, 45).forEach(x -> {
            try {
                SkillProficiency skillProficiency = SkillProficiency.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(skillProficiency == skill.getSkillProficiency() ? TextColors.GREEN : TextColors.RED, skillProficiency.getName()));
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
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
            setButton(45, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.PREVIOUS_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(SkillProficiency.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.NEXT_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
