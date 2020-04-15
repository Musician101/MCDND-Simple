package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class OutputOptionsGUI extends SpongeMCDNDSimpleGUI {

    public OutputOptionsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.OUTPUT_OPTIONS, 9);
        setButton(0, SpongeIconBuilder.of(ItemTypes.IRON_INGOT, Text.of(MenuText.SAVING_THROWS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SavingThrowOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.GOLD_INGOT, Text.of(MenuText.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new WeaponsSpellMiscOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, Text.of(MenuText.CORE_SKILLS_OUTPUT_SKILLS_OPTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreSkillsOutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassActionGUI(mcdndPlayer, index, p)));
    }
}
