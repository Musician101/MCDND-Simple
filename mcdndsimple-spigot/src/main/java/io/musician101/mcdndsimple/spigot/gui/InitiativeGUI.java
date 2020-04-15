package io.musician101.mcdndsimple.spigot.gui;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.nonplayer.NonPlayerGUI;
import io.musician101.mcdndsimple.spigot.gui.player.corestats.CoreStatsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class InitiativeGUI extends SpigotMCDNDSimpleGUI {

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
        setButton(0, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.BONUS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                initiative.setBonus(i);
                new InitiativeGUI(abstractPlayer, bridger, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.GLOWSTONE, MenuText.total(initiative, dex)));
        setButton(2, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.ROLL_INITIATIVE), ImmutableMap.of(ClickType.LEFT, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int mod = dex.getMod() + initiative.getBonus();
            int roll = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + abstractPlayer.getName() + ") has rolled for initiative.\nThe result are: ");
            if (roll == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (roll == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(roll + mod);
            Bukkit.broadcastMessage(sb.toString());
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> bridger.handleBackButton(abstractPlayer, p)));
    }

    private interface InitiativeBridger {

        @Nonnull
        AbilityScore getDexterity(@Nonnull AbstractPlayer abstractPlayer);

        @Nonnull
        Initiative getInitiative(@Nonnull AbstractPlayer abstractPlayer);

        void handleBackButton(@Nonnull AbstractPlayer abstractPlayer, @Nonnull Player player);
    }
}
