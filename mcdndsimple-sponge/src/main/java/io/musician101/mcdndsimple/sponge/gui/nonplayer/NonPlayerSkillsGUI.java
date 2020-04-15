package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class NonPlayerSkillsGUI extends SpongeMCDNDSimpleGUI {

    public NonPlayerSkillsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.SKILLS, 27);
        setButton(0, SpongeIconBuilder.of(ItemTypes.LEATHER_BOOTS, Text.of(MenuText.ACROBATICS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ACROBATICS, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.SADDLE, Text.of(MenuText.ANIMAL_HANDLING)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ANIMAL_HANDLING, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.ARCANA)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ARCANA, p)));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.SPEED).name(Text.of(MenuText.ATHLETICS)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.ATHLETICS, p)));
        setButton(4, SpongeIconBuilder.of(ItemTypes.EMERALD, Text.of(MenuText.DECEPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.DECEPTION, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.HISTORY)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.HISTORY, p)));
        setButton(6, SpongeIconBuilder.of(ItemTypes.ICE, Text.of(MenuText.INSIGHT)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INSIGHT, p)));
        setButton(7, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.INTIMIDATION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INTIMIDATION, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.INVESTIGATION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.INVESTIGATION, p)));
        setButton(9, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(Text.of(MenuText.MEDICINE)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.MEDICINE, p)));
        setButton(10, SpongeIconBuilder.of(ItemTypes.VINE, Text.of(MenuText.NATURE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.NATURE, p)));
        setButton(11, SpongeIconBuilder.of(ItemTypes.CARROT, Text.of(MenuText.PERCEPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERCEPTION, p)));
        setButton(12, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, Text.of(MenuText.PERFORMANCE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERFORMANCE, p)));
        setButton(13, SpongeIconBuilder.of(ItemTypes.ENDER_EYE, Text.of(MenuText.PERSUASION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.PERSUASION, p)));
        setButton(14, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, Text.of(MenuText.RELIGION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.RELIGION, p)));
        setButton(15, SpongeIconBuilder.of(ItemTypes.TRIPWIRE_HOOK, Text.of(MenuText.SLEIGHT_OF_HAND)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.SLEIGHTOFHAND, p)));
        setButton(16, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.NIGHT_VISION).name(Text.of(MenuText.STEALTH)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.STEALTH, p)));
        setButton(17, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(MenuText.SURVIVAL)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.SURVIVAL, p)));
        setButton(18, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, Text.of(MenuText.STRENGTH)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_STR, p)));
        setButton(19, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.DEXTERITY)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_DEX, p)));
        setButton(20, SpongeIconBuilder.of(ItemTypes.GOLDEN_APPLE, Text.of(MenuText.CONSTITUTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_CON, p)));
        setButton(21, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.INTELLIGENCE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_INT, p)));
        setButton(22, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.WISDOM)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_WIS, p)));
        setButton(23, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(MenuText.CHARISMA)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerSkillGUI(nonPlayer, NonPlayerSkillGUI.UNSKILLED_CHA, p)));
    }
}
