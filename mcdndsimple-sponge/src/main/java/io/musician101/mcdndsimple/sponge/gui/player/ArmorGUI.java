package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ArmorGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final Armor armor;

    public ArmorGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index).getName(), 18);
        this.armor = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index);
        updateIsWorn();
        updateIsUnarmored();
        setButton(2, SpongeIconBuilder.of(ItemTypes.NAME_TAG, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                armor.setName(s);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.IRON_CHESTPLATE, Text.of(MenuText.baseAC(armor))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                armor.setBaseArmorClass(i);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.IRON_INGOT, Text.of(armor.getArmorType().getName())), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ArmorTypeGUI(mcdndPlayer, index, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.magicBonus(armor))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                armor.setMagicBonus(i);
                new ArmorGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(6, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.totalAC(armor))));
        updateHasStealthPenalty();
        updateHasSpeedPenalty();
        setButton(17, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ArmorListGUI(mcdndPlayer, p)));
    }

    private void updateHasSpeedPenalty() {
        setButton(8, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.hasSpeedPenalty(armor))).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            armor.setSpeedPenalty(!armor.hasSpeedPenalty());
            updateHasSpeedPenalty();
        }));
    }

    private void updateHasStealthPenalty() {
        setButton(7, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(armor.hasStealthPenalty() ? TextColors.GREEN : TextColors.RED, MenuText.hasStealthPenalty(armor))).potionEffect(PotionEffectTypes.INVISIBILITY).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            armor.setStealthPenalty(!armor.hasStealthPenalty());
            updateHasStealthPenalty();
        }));
    }

    private void updateIsUnarmored() {
        setButton(1, SpongeIconBuilder.builder(ItemTypes.CHAINMAIL_CHESTPLATE).name(Text.of(armor.isUnarmored() ? TextColors.GREEN : TextColors.RED, MenuText.isUnarmored(armor))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            armor.setIsUnarmored(!armor.isUnarmored());
            updateIsUnarmored();
        }));
    }

    private void updateIsWorn() {
        setButton(0, SpongeIconBuilder.builder(ItemTypes.CHAINMAIL_CHESTPLATE).name(Text.of(armor.isWorn() ? TextColors.GREEN : TextColors.RED, MenuText.isWorn(armor))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            armor.setIsWorn(!armor.isWorn());
            updateIsWorn();
        }));
    }
}
