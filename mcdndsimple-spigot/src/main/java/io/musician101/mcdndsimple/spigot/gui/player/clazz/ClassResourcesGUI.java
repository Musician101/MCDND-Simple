package io.musician101.mcdndsimple.spigot.gui.player.clazz;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ClassResourcesGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public ClassResourcesGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS_RESOURCES, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.EMERALD, MenuText.NEW_CLASS_RESOURCE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                ClassResource classResource = new ClassResource();
                classResource.setName(s);
                List<ClassResource> classResources = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources();
                classResources.add(classResource);
                new ClassResourceGUI(mcdndPlayer, classResources.size() - 1, player);
            }
        }));
        setButton(50, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new ClassTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<ClassResource> classResources = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                ClassResource classResource = classResources.get(index);
                ItemStack itemStack = SpigotIconBuilder.of(Material.NETHER_STAR, classResource.getName());
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new ClassResourceGUI(mcdndPlayer, index, p), ClickType.RIGHT, p -> {
                    classResources.remove(index);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException ignored) {
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

        int maxPage = Double.valueOf(Math.ceil(classResources.size() / 45D)).intValue();
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
