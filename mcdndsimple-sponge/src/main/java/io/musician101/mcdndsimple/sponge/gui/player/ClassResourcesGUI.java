package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

public class ClassResourcesGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public ClassResourcesGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS_RESOURCES, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpongeIconBuilder.of(ItemTypes.EMERALD, Text.of(MenuText.NEW_CLASS_RESOURCE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                ClassResource classResource = new ClassResource();
                classResource.setName(s);
                List<ClassResource> classResources = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources();
                classResources.add(classResource);
                new ClassResourceGUI(mcdndPlayer, classResources.size() - 1, player);
            }
        }));
        setButton(50, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<ClassResource> classResources = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                ClassResource classResource = classResources.get(index);
                ItemStack itemStack = SpongeIconBuilder.of(ItemTypes.NETHER_STAR, Text.of(classResource.getName()));
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassResourceGUI(mcdndPlayer, index, p), ClickInventoryEvent.Secondary.class, p -> {
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
            setButton(45, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.PREVIOUS_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(classResources.size() / 45D)).intValue();
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
}
