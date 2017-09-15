package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.SpellcasterClassGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;


public class SpellSaveGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final SpellSave spellSave;

    public SpellSaveGUI(Player player, CoreStats coreStats, SpellSave spellSave, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SAVE, 9, prevGUI);
        this.spellSave = spellSave;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.SHIELD, Text.of(MenuText.SAVING_STAT)), player -> new SpellSavingStatGUI(player, coreStats, spellSave, this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SAVE_DC)), player -> new SpellcasterClassGUI<>(player, spellSave, SpellSave::setSaveDCType, spellcasterClass -> spellcasterClass.equals(spellSave.getSpellcasterClass()), this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.customDC(spellSave.getCustomDC()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellSave.setCustomDC(i);
            delayedOpen();
        }));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.ON_SUCCESSFUL_SAVE)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, spellSave.getOnSuccessfulSave().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                spellSave.setOnSuccessfulSave(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
