package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class NonPlayerCoreStatsGUI extends SpongeMCDNDSimpleGUI {

    public NonPlayerCoreStatsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.CORE_STATS, 9);
        CoreStats<NonPlayerAbilityScore> coreStats = nonPlayer.getNonPlayerSheet().getCoreStats();
        NonPlayerAbilityScore str = coreStats.getStrength();
        NonPlayerAbilityScore dex = coreStats.getDexterity();
        NonPlayerAbilityScore con = coreStats.getConstitution();
        NonPlayerAbilityScore intel = coreStats.getIntelligence();
        NonPlayerAbilityScore wis = coreStats.getWisdom();
        NonPlayerAbilityScore cha = coreStats.getCharisma();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(Text.of(str.getName())).description(stringArrayToTextList(MenuText.scoreLore(str))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.STR, p)));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.BOW).name(Text.of(dex.getName())).description(stringArrayToTextList(MenuText.scoreLore(dex))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.DEX, p)));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(Text.of(con.getName())).description(stringArrayToTextList(MenuText.scoreLore(con))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.CON, p)));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(Text.of(intel.getName())).description(stringArrayToTextList(MenuText.scoreLore(intel))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.INTEL, p)));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(wis.getName())).description(stringArrayToTextList(MenuText.scoreLore(wis))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.WIS, p)));
        setButton(5, SpongeIconBuilder.builder(ItemTypes.SKULL).name(Text.of(cha.getName())).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).description(stringArrayToTextList(MenuText.scoreLore(cha))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.CHA, p)));
    }
}
