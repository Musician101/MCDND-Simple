package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.weapon.Weapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class AttackStatGUI extends SpongeMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public AttackStatGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ATTACK_STAT, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.index = index;
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new MeleeWeaponGUI(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        Weapon weapon = mcdndPlayer.getCharacterSheet().getWeaponsTab().getMeleeWeapons().get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                WeaponAttackStat attackStat = WeaponAttackStat.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.builder(ItemRepresentation.weaponAttackStat(attackStat)).name(Text.of(attackStat == weapon.getAttackStat() ? TextColors.GREEN : TextColors.RED, attackStat.getName())).build();
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                    weapon.setAttackStat(attackStat);
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

        int maxPage = Double.valueOf(Math.ceil(WeaponAttackStat.values().length / 45D)).intValue();
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
