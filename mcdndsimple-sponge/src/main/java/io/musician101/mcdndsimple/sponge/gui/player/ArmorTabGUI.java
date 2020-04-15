package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class ArmorTabGUI extends SpongeMCDNDSimpleGUI {

    public ArmorTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.ARMOR, 54);
        ArmorTab armorTab = mcdndPlayer.getCharacterSheet().getArmorTab();
        setButton(0, SpongeIconBuilder.of(ItemTypes.CHAINMAIL_CHESTPLATE, Text.of(MenuText.armoredAC(armorTab))));
        setButton(1, SpongeIconBuilder.of(ItemTypes.LEATHER_CHESTPLATE, Text.of(MenuText.unarmoredAC(armorTab))));
        setButton(2, SpongeIconBuilder.of(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.ARMOR)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ArmorListGUI(mcdndPlayer, p)));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.IRON_CHESTPLATE).name(Text.of(MenuText.UNARMORED_BONUS)).description(Text.of(MenuText.current(armorTab))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new UnarmoredBonusGUI(mcdndPlayer, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
