package io.musician101.mcdndsimple.sponge.menu;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.sponge.menu.AbstractSpongeMenu;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.custom.CustomInventory;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.translation.FixedTranslation;

import java.util.Optional;
import java.util.UUID;

public class CharacterSheetMenu extends AbstractSpongeMenu
{
    //TODO need constructor that accepts data as input
    public CharacterSheetMenu()
    {
        super(SpongeMCDNDSimple.getPluginContainer(),
                CustomInventory.builder().size(2).name(new FixedTranslation("New Character")).build(),
                event ->
                {
                    ItemStack itemStack = event.getItem();
                    Optional<Text> nameOptional = itemStack.get(Keys.DISPLAY_NAME);
                    if (nameOptional.isPresent())
                    {
                        UUID uuid = event.getPlayer().getUniqueId();
                        String name = ((LiteralText) nameOptional.get()).getContent();
                        if ("Bio & Info".equals(name))
                        {
                            event.setWillDestroy(true);
                            new BioAndInfoMenu().open(uuid);
                        }
                        else if ("Character Sheet".equals(name))
                        {
                            event.setWillDestroy(true);
                            //TODO open character sheet GUI
                        }
                        else if ("Exit".equals(name))
                        {
                            event.setWillDestroy(true);
                            event.setWillClose(true);
                        }
                    }
                });
    }
}
