package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.classactions;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class SavingThrowOutputOptionsGUI extends MCDNDSimpleChestGUI {

    private final SavingThrowOutputOptions savingThrowOutputOptions;

    public SavingThrowOutputOptionsGUI(Player player, SavingThrowOutputOptions savingThrowOutputOptions, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SAVING_THROWS, 9, prevGUI);
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    @Override
    protected void build() {
        boolean str = savingThrowOutputOptions.isStrengthEnabled();
        set(0, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.IRON_SWORD, Text.of(MenuText.STRENGTH)), () -> str), player -> {
            savingThrowOutputOptions.setStrengthEnabled(!str);
            open();
        });

        boolean dex = savingThrowOutputOptions.isDexterityEnabled();
        set(1, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.BOW, Text.of(MenuText.DEXTERITY)), () -> dex), player -> {
            savingThrowOutputOptions.setDexterityEnabled(!dex);
            open();
        });

        boolean con = savingThrowOutputOptions.isConstitutionEnabled();
        set(2, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.POTION, Text.of(MenuText.CONSTITUTION)), () -> con), player -> {
            savingThrowOutputOptions.setConstitutionEnabled(!con);
            open();
        });

        boolean intel = savingThrowOutputOptions.isIntelligenceEnabled();
        set(3, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.INTELLIGENCE)), () -> intel), player -> {
            savingThrowOutputOptions.setIntelligenceEnabled(!intel);
            open();
        });

        boolean wis = savingThrowOutputOptions.isWisdomEnabled();
        set(4, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.INTELLIGENCE)), () -> wis), player -> {
            savingThrowOutputOptions.setWisdomEnabled(!wis);
            open();
        });

        boolean cha = savingThrowOutputOptions.isCharismaEnabled();
        set(5, ClickInventoryEvent.class, addGlowIfConditionsMet(setType(createItem(ItemTypes.SKULL, Text.of(MenuText.CHARISMA)), Keys.SKULL_TYPE, SkullTypes.PLAYER), () -> cha), player -> {
            savingThrowOutputOptions.setCharismaEnabled(!savingThrowOutputOptions.isCharismaEnabled());
            open();
        });

        boolean death = savingThrowOutputOptions.isDeathEnabled();
        set(6, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.SKULL, Text.of(MenuText.DEATH)), () -> death), player -> {
            savingThrowOutputOptions.setDeathEnabled(!savingThrowOutputOptions.isDeathEnabled());
            open();
        });

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
