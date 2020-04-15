package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
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

public class StatBonusGUI extends SpigotMCDNDSimpleGUI {

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
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> bridger.back(mcdndPlayer, level, index, p)));
    }

    @Nonnull
    protected ItemStack statBonusIcon(@Nonnull StatBonus statBonus) {
        SpigotIconBuilder builder;
        switch (statBonus) {
            case CHA:
                builder = SpigotIconBuilder.builder(Material.PLAYER_HEAD);
                break;
            case CON:
                builder = SpigotIconBuilder.builder(Material.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpigotIconBuilder.builder(Material.BOW);
                break;
            case INT:
                builder = SpigotIconBuilder.builder(Material.WRITABLE_BOOK);
                break;
            case STR:
                builder = SpigotIconBuilder.builder(Material.IRON_SWORD);
                break;
            case WIS:
                builder = SpigotIconBuilder.builder(Material.ENCHANTED_BOOK);
                break;
            default:
                builder = SpigotIconBuilder.builder(Material.ENDER_CHEST);
                break;
        }

        return builder.name((bridger.test(statBonus, mcdndPlayer, level, index) ? ChatColor.GREEN : ChatColor.RED) + statBonus.getName()).build();
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                StatBonus statBonus = StatBonus.values()[x + (page - 1) * 45];
                ItemStack itemStack = statBonusIcon(statBonus);
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> bridger.set(statBonus, mcdndPlayer, level, index)));
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
