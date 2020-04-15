package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class StatBonusGUI extends SpongeMCDNDSimpleGUI {

    public final static StatBonusBridger SPELL = new StatBonusBridger(MenuText.GAINED_FROM) {

        @Override
        public void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
            new SpellGUI(mcdndPlayer, level, index, player);
        }

        @Override
        public void set(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).setAttackStat(statBonus);
        }

        @Override
        public boolean test(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            return statBonus == mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getAttackStat();
        }
    };
    public final static StatBonusBridger SPELL_HEALING = new StatBonusBridger(MenuText.STAT_BONUS) {

        @Override
        public void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
            new SpellSaveGUI(mcdndPlayer, level, index, player);
        }

        @Override
        public void set(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellHealing().setStatBonus(statBonus);
        }

        @Override
        public boolean test(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            return statBonus == mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellHealing().getStatBonus();
        }
    };
    public final static StatBonusBridger SPELL_SAVE = new StatBonusBridger(MenuText.SAVING_STAT) {

        @Override
        public void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
            new SpellSaveGUI(mcdndPlayer, level, index, player);
        }

        @Override
        public void set(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave().setSavingStat(statBonus);
        }

        @Override
        public boolean test(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            return statBonus == mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave().getSavingStat();
        }
    };
    @Nonnull
    private final StatBonusBridger bridger;
    private final int index;
    private final int level;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public StatBonusGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull StatBonusBridger bridger, @Nonnull Player player) {
        super(player, bridger.title, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.level = level;
        this.index = index;
        this.bridger = bridger;
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> bridger.back(mcdndPlayer, level, index, p)));
    }

    @Nonnull
    protected ItemStack statBonusIcon(@Nonnull StatBonus statBonus) {
        SpongeIconBuilder builder;
        switch (statBonus) {
            case CHA:
                builder = SpongeIconBuilder.builder(ItemTypes.SKULL).type(Keys.SKULL_TYPE, SkullTypes.PLAYER);
                break;
            case CON:
                builder = SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpongeIconBuilder.builder(ItemTypes.BOW);
                break;
            case INT:
                builder = SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK);
                break;
            case STR:
                builder = SpongeIconBuilder.builder(ItemTypes.IRON_SWORD);
                break;
            case WIS:
                builder = SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK);
                break;
            default:
                builder = SpongeIconBuilder.builder(ItemTypes.ENDER_CHEST);
                break;
        }

        return builder.name(Text.of(bridger.test(statBonus, mcdndPlayer, level, index) ? TextColors.GREEN : TextColors.RED, statBonus.getName())).build();
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                StatBonus statBonus = StatBonus.values()[x + (page - 1) * 45];
                ItemStack itemStack = statBonusIcon(statBonus);
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> bridger.set(statBonus, mcdndPlayer, level, index)));
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

        int maxPage = Double.valueOf(Math.ceil(SpellcasterClass.values().length / 45D)).intValue();
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

    private static abstract class StatBonusBridger {

        @Nonnull
        private final String title;

        public StatBonusBridger(@Nonnull String title) {
            this.title = title;
        }

        public abstract void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player);

        public abstract void set(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index);

        public abstract boolean test(@Nonnull StatBonus statBonus, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index);

    }
}
