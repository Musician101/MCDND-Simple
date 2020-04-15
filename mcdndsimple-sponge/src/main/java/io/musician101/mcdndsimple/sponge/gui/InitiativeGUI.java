package io.musician101.mcdndsimple.sponge.gui;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.nonplayer.NonPlayerGUI;
import io.musician101.mcdndsimple.sponge.gui.player.CoreStatsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class InitiativeGUI extends SpongeMCDNDSimpleGUI {

    public static final InitiativeBridger NPC = new InitiativeBridger() {

        @Nonnull
        @Override
        public AbilityScore getDexterity(@Nonnull AbstractPlayer abstractPlayer) {
            return ((NonPlayer) abstractPlayer).getNonPlayerSheet().getCoreStats().getDexterity();
        }

        @Nonnull
        @Override
        public Initiative getInitiative(@Nonnull AbstractPlayer abstractPlayer) {
            return ((NonPlayer) abstractPlayer).getInitiative();
        }

        @Override
        public void handleBackButton(@Nonnull AbstractPlayer abstractPlayer, @Nonnull Player player) {
            new NonPlayerGUI(((NonPlayer) abstractPlayer), player);
        }
    };
    public static final InitiativeBridger PLAYER = new InitiativeBridger() {

        @Nonnull
        @Override
        public AbilityScore getDexterity(@Nonnull AbstractPlayer abstractPlayer) {
            return ((MCDNDPlayer) abstractPlayer).getCharacterSheet().getCoreStatsTab().getCoreStats().getDexterity();
        }

        @Nonnull
        @Override
        public Initiative getInitiative(@Nonnull AbstractPlayer abstractPlayer) {
            return ((MCDNDPlayer) abstractPlayer).getCharacterSheet().getCoreStatsTab().getInitiative();
        }

        @Override
        public void handleBackButton(@Nonnull AbstractPlayer abstractPlayer, @Nonnull Player player) {
            new CoreStatsTabGUI((MCDNDPlayer) abstractPlayer, player);
        }
    };

    public InitiativeGUI(@Nonnull AbstractPlayer abstractPlayer, @Nonnull InitiativeBridger bridger, @Nonnull Player player) {
        super(player, MenuText.INITIATIVE, 9);
        Initiative initiative = bridger.getInitiative(abstractPlayer);
        AbilityScore dex = bridger.getDexterity(abstractPlayer);
        setButton(0, SpongeIconBuilder.of(ItemTypes.GLOWSTONE_DUST, Text.of(MenuText.BONUS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                initiative.setBonus(i);
                new InitiativeGUI(abstractPlayer, bridger, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.GLOWSTONE, Text.of(MenuText.total(initiative, dex))));
        setButton(2, SpongeIconBuilder.of(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_INITIATIVE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int mod = dex.getMod() + initiative.getBonus();
            int roll = Dice.total(dice.roll());
            List<Object> objects = new ArrayList<>();
            objects.add(p.getName() + " (as " + abstractPlayer.getName() + ") has rolled for initiative.\nThe result are: ");
            if (roll == 20) {
                objects.add(TextColors.GREEN);
            }
            else if (roll == 1) {
                objects.add(TextColors.RED);
            }

            objects.add(roll + mod);
            broadcastMessage(Text.of(objects));
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> bridger.handleBackButton(abstractPlayer, p)));
    }

    private interface InitiativeBridger {

        @Nonnull
        AbilityScore getDexterity(@Nonnull AbstractPlayer abstractPlayer);

        @Nonnull
        Initiative getInitiative(@Nonnull AbstractPlayer abstractPlayer);

        void handleBackButton(@Nonnull AbstractPlayer abstractPlayer, @Nonnull Player player);
    }
}
