package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class DeathSavingThrowsGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final DeathSavingThrows deathSavingThrows;

    public DeathSavingThrowsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.DEATH_SAVING_THROWS, 9);
        CoreStatsTab coreStatsTab = mcdndPlayer.getCharacterSheet().getCoreStatsTab();
        this.deathSavingThrows = coreStatsTab.getDeathSavingThrows();
        BioAndInfo bioAndInfo = mcdndPlayer.getBioAndInfo();
        HitPoints hitPoints = coreStatsTab.getHitPoints();
        updateSuccessCount();
        updateFailCount();
        setButton(2, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(MenuText.failCount(deathSavingThrows.getFailCount()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            int result = Dice.total(new Dice(20).roll());
            int total = result + Dice.total(coreStatsTab.getBonuses().getSaves().roll());
            String newLine = "\n";
            List<Object> objects = new ArrayList<>();
            objects.add(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a Death Saving Throw." + newLine + "The results are ");
            if (result == 20) {
                objects.add(TextColors.GREEN);
            }
            else if (result == 1) {
                objects.add(TextColors.RED);
            }

            objects.add(total);
            objects.add(TextColors.RESET);
            objects.add("." + newLine);
            if (total > 10) {
                if (result == 1) {
                    deathSavingThrows.addFailCount();
                }

                deathSavingThrows.addFailCount();
                objects.add(bioAndInfo.getName() + " now has ");
                objects.add(TextColors.RED);
                objects.add(deathSavingThrows.getFailCount());
                objects.add(TextColors.RESET);
                objects.add(" failed Death Saving Throws!");
            }
            else {
                deathSavingThrows.addSuccessCount();
                objects.add(bioAndInfo.getName());
                objects.add(" now has ");
                objects.add(TextColors.GREEN);
                objects.add(deathSavingThrows.getSuccessCount());
                objects.add(TextColors.RESET);
                objects.add(" successful Death Saving Throws!");
                if (result == 20) {
                    objects.add(TextColors.GOLD);
                    objects.add("They have also gained 1HP.");
                    hitPoints.setCurrent(1);
                }
            }

            broadcastMessage(Text.of(objects));
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }

    private void updateFailCount() {
        setButton(1, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(MenuText.failCount(deathSavingThrows.getFailCount()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            deathSavingThrows.addFailCount();
            updateFailCount();
        }, ClickInventoryEvent.Secondary.class, p -> {
            deathSavingThrows.removeFailCount();
            updateFailCount();
        }));
    }

    private void updateSuccessCount() {
        setButton(0, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(MenuText.successCount(deathSavingThrows.getSuccessCount()))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            deathSavingThrows.addSuccessCount();
            updateSuccessCount();
        }, ClickInventoryEvent.Secondary.class, p -> {
            deathSavingThrows.removeSuccessCount();
            updateSuccessCount();
        }));
    }
}
