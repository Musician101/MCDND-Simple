package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class AbilityScoreGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final Experience experience;
    private final AbilityScore score;

    public AbilityScoreGUI(Player player, AbilityScore score, BioAndInfo bioAndInfo, ClassLevels classLevels, Experience experience, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, score.getName(), 9, prevGUI);
        this.score = score;
        this.bioAndInfo = bioAndInfo;
        this.experience = experience;
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.score(score))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            score.setScore(i);
            open();
        }));
        set(1, createItem(ItemTypes.GUNPOWDER, Text.of(MenuText.mod(score))));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(MenuText.proficient(score))), player -> {
            score.setIsProficient(!score.isProficient());
            open();
        });
        set(3, createItem(ItemTypes.GLOWSTONE_DUST, Text.of(MenuText.saveMod(score, classLevels, experience))));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.REDSTONE_LAMP, Text.of(MenuText.ROLL_SAVE)), player -> {
            Dice dice = new Dice(20);
            int saveMod = score.getSaveMod(classLevels, experience);
            String message = StringUtils.join(Stream.of(Messages.savingThrow(score, bioAndInfo, player.getName(), Dice.total(dice.roll(), saveMod), Dice.total(dice.roll(), saveMod))).collect(Collectors.toList()), "\n");
            SpongeMCDNDSimple.instance().getLogger().info(message);
            Sponge.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(Text.of(message)));
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
