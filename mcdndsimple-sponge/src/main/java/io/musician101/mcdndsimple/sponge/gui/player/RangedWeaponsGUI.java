package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class RangedWeaponsGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;
    public RangedWeaponsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.RANGED_WEAPONS, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.NEW_RANGED_WEAPON)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                RangedWeapon rangedWeapon = new RangedWeapon();
                rangedWeapon.setName(s);
                List<RangedWeapon> rangedWeapons = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons();
                new RangedWeaponGUI(mcdndPlayer, rangedWeapons.size() - 1, player);
            }
        }));
        setButton(50, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new WeaponsTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<RangedWeapon> rangedWeapons = mcdndPlayer.getCharacterSheet().getWeaponsTab().getRangedWeapons();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                RangedWeapon rangedWeapon = rangedWeapons.get(index);
                CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
                CoreStatsTab coreStats = characterSheet.getCoreStatsTab();
                ItemStack itemStack = ItemRepresentation.rangedWeapon(rangedWeapon, characterSheet.getClassTab().getClassLevels(), coreStats.getCoreStats(), coreStats.getExperience());
                List<Text> lore = new ArrayList<>();
                lore.add(Text.of(TextColors.GREEN, "LEFT-CLICK: ", TextColors.RESET, "to select."));
                lore.add(Text.of(TextColors.RED, "RIGHT-CLICK: ", TextColors.RESET, "to delete."));
                itemStack.get(Keys.ITEM_LORE).ifPresent(lore::addAll);
                setButton(x, SpongeIconBuilder.builder(itemStack).description(lore).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RangedWeaponGUI(mcdndPlayer, index, p), ClickInventoryEvent.Secondary.class, p -> {
                    rangedWeapons.remove(index);
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

        int maxPage = Double.valueOf(Math.ceil(rangedWeapons.size() / 45D)).intValue();
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
