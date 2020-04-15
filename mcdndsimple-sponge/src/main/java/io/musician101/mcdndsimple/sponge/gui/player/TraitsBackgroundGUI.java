package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.gui.nonplayer.NonPlayerGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class TraitsBackgroundGUI extends SpongeMCDNDSimpleGUI {

    public TraitsBackgroundGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.TRAITS_BACKGROUND, 9);
        TraitsBackground traitsBackground = nonPlayer.getTraitsBackground();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.MILK_BUCKET).name(Text.of(MenuText.CONDITION_IMMUNITY)).description(Text.of(traitsBackground.getConditionImmunity())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setConditionImmunity(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.DIAMOND_CHESTPLATE).name(Text.of(MenuText.DAMAGE_IMMUNITY)).description(Text.of(traitsBackground.getDamageImmunity())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageImmunity(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.SHIELD).name(Text.of(MenuText.DAMAGE_RESISTANCE)).description(Text.of(traitsBackground.getDamageResistance())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageResistance(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(Text.of(MenuText.DAMAGE_VULNERABILITY)).description(Text.of(traitsBackground.getDamageVulnerability())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageVulnerability(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.SHIELD).name(Text.of(MenuText.TRAITS)).description(Text.of(traitsBackground.getDamageResistance())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.TRAITS, traitsBackground.getTraits()), (ply, pages) -> {
            traitsBackground.setTraits(textListToStringList(pages));
            new TraitsBackgroundGUI(nonPlayer, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerGUI(nonPlayer, p)));
    }
}
