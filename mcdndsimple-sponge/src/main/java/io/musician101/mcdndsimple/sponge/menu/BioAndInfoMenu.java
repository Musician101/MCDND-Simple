package io.musician101.mcdndsimple.sponge.menu;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.sponge.menu.AbstractSpongeMenu;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.event.item.inventory.InteractItemEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.translation.FixedTranslation;

import java.util.Optional;
import java.util.UUID;

//TODO need DM version to allow for extra settings/control
public class BioAndInfoMenu extends AbstractSpongeMenu
{
    public BioAndInfoMenu()
    {
        super(SpongeMCDNDSimple.getPluginContainer(),
                CustomInventory.builder().size(2).name(new FixedTranslation("Bio & Info")).build(),
                event ->
                {
                    ItemStack itemStack = event.getItem();
                    Optional<Text> nameOptional = itemStack.get(Keys.DISPLAY_NAME);
                    if (nameOptional.isPresent())
                    {
                        UUID uuid = event.getPlayer().getUniqueId();
                        String name = ((LiteralText) nameOptional.get()).getContent();
                        if ("Name".equals(name))
                        {
                            event.setWillDestroy(true);
                            //TODO open ChangeNameMenu
                        }
                        else if ("Bio & Info".equals(name))
                        {
                            event.setWillDestroy(true);
                            //TODO open book GUI
                        }
                        else if ("Back".equals(name))
                        {
                            event.setWillDestroy(true);
                            //TODO reopen CharacterSheetMenu with new data
                        }
                    }
                });
    }
}
