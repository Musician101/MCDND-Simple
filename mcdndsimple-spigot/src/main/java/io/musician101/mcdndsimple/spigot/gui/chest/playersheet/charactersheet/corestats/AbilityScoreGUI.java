package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class AbilityScoreGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final Experience experience;
    private final AbilityScore score;

    public AbilityScoreGUI(Player player, AbilityScore score, BioAndInfo bioAndInfo, ClassLevels classLevels, Experience experience, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, score.getName(), prevGUI);
        this.score = score;
        this.bioAndInfo = bioAndInfo;
        this.experience = experience;
        this.classLevels = classLevels;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.PAPER, MenuText.score(score)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            score.setScore(i);
            open();
        }));
        set(1, createItem(Material.SULPHUR, MenuText.mod(score)));
        set(2, ClickType.LEFT, createItem(Material.PAPER, MenuText.proficient(score)), player -> {
            score.setProficient(!score.isProficient());
            open();
        });
        set(3, createItem(Material.GLOWSTONE_DUST, MenuText.saveMod(score, classLevels, experience)));
        set(4, ClickType.LEFT, createItem(Material.REDSTONE_LAMP_OFF, MenuText.ROLL_SAVE), player -> {
            Dice dice = new Dice(20);
            int saveMod = score.getSaveMod(classLevels, experience);
            //TODO incomplete
            Bukkit.spigot().broadcast();
            Stream.of(Messages.savingThrow(score, bioAndInfo, player.getName(), Dice.total(dice.roll(), saveMod), Dice.total(dice.roll(), saveMod))).forEach(Bukkit::broadcastMessage);
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
