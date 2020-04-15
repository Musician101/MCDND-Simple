package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ExperienceGUI extends SpongeMCDNDSimpleGUI {

    public ExperienceGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.LEVEL_AND_XP, 9);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        setButton(0, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.overallLevel(experience, classLevels))));
        setButton(1, SpongeIconBuilder.of(ItemTypes.ANVIL, Text.of(MenuText.proficiencyBonus(experience, classLevels))));
        setButton(2, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.currentXP(experience))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                experience.setExp(i);
                new ExperienceGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.xpForNextLevel(experience, classLevels))));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
