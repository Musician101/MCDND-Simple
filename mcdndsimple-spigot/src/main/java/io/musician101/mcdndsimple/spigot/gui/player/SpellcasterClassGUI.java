package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SpellcasterClassGUI extends SpigotMCDNDSimpleGUI {

    public final static SpellcasterClassBridger SPELL = new SpellcasterClassBridger(MenuText.GAINED_FROM) {

        @Override
        public void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
            new SpellGUI(mcdndPlayer, level, index, player);
        }

        @Override
        public void set(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).setGainedFrom(spellcasterClass);
        }

        @Override
        public boolean test(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            return spellcasterClass == mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getGainedFrom();
        }
    };
    public final static SpellcasterClassBridger SPELL_SAVE = new SpellcasterClassBridger(MenuText.SAVE_DC) {

        @Override
        public void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
            new SpellSaveGUI(mcdndPlayer, level, index, player);
        }

        @Override
        public void set(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave().setSaveDCType(spellcasterClass);
        }

        @Override
        public boolean test(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index) {
            return spellcasterClass == mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave().getSaveDCType();
        }
    };
    @Nonnull
    private final SpellcasterClassBridger bridger;
    private final int index;
    private final int level;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public SpellcasterClassGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull SpellcasterClassBridger bridger, @Nonnull Player player) {
        super(player, bridger.title, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.level = level;
        this.index = index;
        this.bridger = bridger;
        updateSlots();
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> bridger.back(mcdndPlayer, level, index, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                SpellcasterClass spellcasterClass = SpellcasterClass.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name((bridger.test(spellcasterClass, mcdndPlayer, level, index) ? ChatColor.GREEN : ChatColor.RED) + spellcasterClass.getName()).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> bridger.set(spellcasterClass, mcdndPlayer, level, index)));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(SpellcasterClass.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page++;
                updateSlots();
            }));
        }
    }

    private static abstract class SpellcasterClassBridger {

        @Nonnull
        private final String title;

        public SpellcasterClassBridger(@Nonnull String title) {
            this.title = title;
        }

        public abstract void back(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player);

        public abstract void set(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index);

        public abstract boolean test(@Nonnull SpellcasterClass spellcasterClass, @Nonnull MCDNDPlayer mcdndPlayer, int level, int index);
    }
}
