package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.gui.player.PlayerSkillGUI.PlayerSkillBridger;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

public class SkillsTabGUI extends SpongeMCDNDSimpleGUI {

    public SkillsTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SKILLS, 27);
        setSlot(mcdndPlayer, 0, SpongeIconBuilder.of(ItemTypes.LEATHER_BOOTS, Text.of(MenuText.ACROBATICS)), PlayerSkillGUI.ACROBATICS);
        setSlot(mcdndPlayer, 1, SpongeIconBuilder.of(ItemTypes.SADDLE, Text.of(MenuText.ANIMAL_HANDLING)), PlayerSkillGUI.ANIMAL_HANDLING);
        setSlot(mcdndPlayer, 2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.ARCANA)), PlayerSkillGUI.ARCANA);
        setSlot(mcdndPlayer, 3, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.SPEED).name(Text.of(MenuText.ATHLETICS)).build(), PlayerSkillGUI.ATHLETICS);
        setSlot(mcdndPlayer, 4, SpongeIconBuilder.of(ItemTypes.EMERALD, Text.of(MenuText.DECEPTION)), PlayerSkillGUI.DECEPTION);
        setSlot(mcdndPlayer, 5, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.HISTORY)), PlayerSkillGUI.HISTORY);
        setSlot(mcdndPlayer, 6, SpongeIconBuilder.of(ItemTypes.ICE, Text.of(MenuText.INSIGHT)), PlayerSkillGUI.INSIGHT);
        setSlot(mcdndPlayer, 7, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.INTIMIDATION)), PlayerSkillGUI.INTIMIDATION);
        setSlot(mcdndPlayer, 8, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.INVESTIGATION)), PlayerSkillGUI.INVESTIGATION);
        setSlot(mcdndPlayer, 9, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.INSTANT_HEALTH).name(Text.of(MenuText.MEDICINE)).build(), PlayerSkillGUI.MEDICINE);
        setSlot(mcdndPlayer, 10, SpongeIconBuilder.of(ItemTypes.VINE, Text.of(MenuText.NATURE)), PlayerSkillGUI.NATURE);
        setSlot(mcdndPlayer, 11, SpongeIconBuilder.of(ItemTypes.CARROT, Text.of(MenuText.PERCEPTION)), PlayerSkillGUI.PERCEPTION);
        setSlot(mcdndPlayer, 12, SpongeIconBuilder.of(ItemTypes.NOTEBLOCK, Text.of(MenuText.PERFORMANCE)), PlayerSkillGUI.PERFORMANCE);
        setSlot(mcdndPlayer, 13, SpongeIconBuilder.of(ItemTypes.ENDER_EYE, Text.of(MenuText.PERSUASION)), PlayerSkillGUI.PERSUASION);
        setSlot(mcdndPlayer, 14, SpongeIconBuilder.of(ItemTypes.NETHER_STAR, Text.of(MenuText.RELIGION)), PlayerSkillGUI.RELIGION);
        setSlot(mcdndPlayer, 15, SpongeIconBuilder.of(ItemTypes.TRIPWIRE_HOOK, Text.of(MenuText.SLEIGHT_OF_HAND)), PlayerSkillGUI.SLEIGHTOFHAND);
        setSlot(mcdndPlayer, 16, SpongeIconBuilder.builder(ItemTypes.POTION).potionEffect(PotionEffectTypes.NIGHT_VISION).name(Text.of(MenuText.STEALTH)).build(), PlayerSkillGUI.STEALTH);
        setSlot(mcdndPlayer, 17, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(MenuText.SURVIVAL)), PlayerSkillGUI.SURVIVAL);
        setSlot(mcdndPlayer, 18, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, Text.of(MenuText.STRENGTH)), PlayerSkillGUI.UNSKILLED_STR);
        setSlot(mcdndPlayer, 19, SpongeIconBuilder.of(ItemTypes.BOW, Text.of(MenuText.DEXTERITY)), PlayerSkillGUI.UNSKILLED_DEX);
        setSlot(mcdndPlayer, 20, SpongeIconBuilder.of(ItemTypes.GOLDEN_APPLE, Text.of(MenuText.CONSTITUTION)), PlayerSkillGUI.UNSKILLED_CON);
        setSlot(mcdndPlayer, 21, SpongeIconBuilder.of(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.INTELLIGENCE)), PlayerSkillGUI.UNSKILLED_INT);
        setSlot(mcdndPlayer, 22, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.WISDOM)), PlayerSkillGUI.UNSKILLED_WIS);
        setSlot(mcdndPlayer, 23, SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER).name(Text.of(MenuText.CHARISMA)).build(), PlayerSkillGUI.UNSKILLED_CHA);
        setButton(26, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, @Nonnull ItemStack itemStack, @Nonnull PlayerSkillBridger bridger) {
        setButton(slot, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerSkillGUI(mcdndPlayer, bridger, p)));
    }
}
