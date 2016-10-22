package io.musician101.mcdndsimple.sponge.menu;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.sponge.menu.AbstractSpongeMenu;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory.Builder;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.type.OrderedInventory;
import org.spongepowered.api.text.LiteralText;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class ChangeNameMenu extends AbstractSpongeMenu
{
    public ChangeNameMenu()
    {
        super(SpongeMCDNDSimple.getPluginContainer(),
                (OrderedInventory) Sponge.getRegistry().createBuilder(Builder.class).of(InventoryArchetypes.ANVIL).build(SpongeMCDNDSimple.instance()),
                event ->
                {
                    Optional<Text> nameOptional = event.getItem().get(Keys.DISPLAY_NAME);
                    if (nameOptional.isPresent())
                    {
                        String name = ((LiteralText) nameOptional.get()).getContent();
                        event.setWillDestroy(true);
                        if ("Rename Me".equals(name))
                        {
                            //TODO go back to BioAndInfoMenu
                        }
                        else
                        {
                            //TODO set the new name and then go back to BioAndInfoMenu
                        }
                    }
                });

        //TODO redo AbstractSpongeMenu so that it uses Inventory and not OrderedInventory
        setOption(0, ItemStack.of(ItemTypes.PAPER, 1), "Rename Me");
    }
}
