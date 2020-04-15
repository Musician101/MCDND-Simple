package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SpellsGUI extends SpigotMCDNDSimpleGUI {

    private final int level;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public SpellsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, @Nonnull Player player) {
        super(player, MenuText.spellLevel(level), 54);
        this.mcdndPlayer = mcdndPlayer;
        this.level = level;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_MELEE_WEAPON), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Spell spell = new Spell();
                spell.setName(s);
                List<Spell> spells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
                spells.add(spell);
                new SpellGUI(mcdndPlayer, level, (int) spells.stream().filter(sp -> sp.getLevel() == level).count() - 1, player);
            }
        }));
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellBookGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<Spell> allSpells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
        List<Spell> spells = allSpells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList());
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                Spell spell = spells.get(index);
                ItemStack itemStack = ItemRepresentation.spell(spell);
                List<String> lore = Stream.concat(Stream.of(ChatColor.GREEN + "LEFT-CLICK" + ChatColor.RESET + " to edit.", ChatColor.RED + "RIGHT-CLICK" + ChatColor.RESET + " to delete."), itemStack.getItemMeta().getLore().stream()).collect(Collectors.toList());
                setButton(x, SpigotIconBuilder.builder(itemStack).description(lore).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpellGUI(mcdndPlayer, level, index, p), ClickType.RIGHT, p -> {
                    spells.remove(index);
                    allSpells.removeIf(s -> s.getLevel() == level);
                    allSpells.addAll(spells);
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
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(spells.size() / 45D)).intValue();
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
}
