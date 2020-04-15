package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
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

public class PlayerCoreStatsGUI extends SpongeMCDNDSimpleGUI {

    public PlayerCoreStatsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CORE_STATS, 9);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStats<PlayerAbilityScore> coreStats = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getCoreStats();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        PlayerAbilityScore str = coreStats.getStrength();
        PlayerAbilityScore dex = coreStats.getDexterity();
        PlayerAbilityScore con = coreStats.getConstitution();
        PlayerAbilityScore intel = coreStats.getIntelligence();
        PlayerAbilityScore wis = coreStats.getWisdom();
        PlayerAbilityScore cha = coreStats.getCharisma();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(Text.of(str.getName())).description(stringArrayToTextList(MenuText.scoreLore(str, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.STR, p)));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.BOW).name(Text.of(dex.getName())).description(stringArrayToTextList(MenuText.scoreLore(dex, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.DEX, p)));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(Text.of(con.getName())).description(stringArrayToTextList(MenuText.scoreLore(con, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.CON, p)));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(Text.of(intel.getName())).description(stringArrayToTextList(MenuText.scoreLore(intel, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.INTEL, p)));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(wis.getName())).description(stringArrayToTextList(MenuText.scoreLore(wis, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.WIS, p)));
        setButton(5, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(cha.getName())).description(stringArrayToTextList(MenuText.scoreLore(cha, classLevels, experience))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.CHA, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
