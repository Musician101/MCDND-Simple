package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class InitiativeGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final AbilityScore dex;
    private final Initiative initiative;

    public InitiativeGUI(Player player, AbilityScore dex, BioAndInfo bioAndInfo, Initiative initiative, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.INITIATIVE, 9, prevGUI);
        this.dex = dex;
        this.bioAndInfo = bioAndInfo;
        this.initiative = initiative;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.GLOWSTONE_DUST, Text.of(MenuText.BONUS)), player -> {
            new IntegerInputAnvilGUI(player, (p, i) -> {
                initiative.setBonus(i);
                player.closeInventory();
                open();
            });
        });
        set(1, createItem(ItemTypes.GLOWSTONE, Text.of(MenuText.total(initiative, dex))));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_INITIATIVE)), player -> {
            player.closeInventory();
            int baseResult = new Dice(20).roll().get(0).getValue();
            String resultMsg = Integer.toString(baseResult + initiative.getInitiative(dex));
            if (baseResult == 1) {
                resultMsg = TextColors.RED + resultMsg;
            }
            else if (baseResult == 20) {
                resultMsg = TextColors.GREEN + resultMsg;
            }

            String message = StringUtils.join(Messages.initiative(bioAndInfo, player.getName(), resultMsg), "\n");
            SpongeMCDNDSimple.instance().getLogger().info(message);
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(Text.of(message)));
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
