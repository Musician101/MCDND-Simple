package io.musician101.mcdndsimple.spigot.gui.player.clazz;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ClassResourceGUI extends SpigotMCDNDSimpleGUI {

    public ClassResourceGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getClassTab().getClassResources().get(index).getName(), 9);
        ClassResource classResource = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources().get(index);
        setButton(0, SpigotIconBuilder.of(Material.NAME_TAG, classResource.getName()), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                classResource.setName(s);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.RED_BED, MenuText.recharge(classResource.getRecharge())), ImmutableMap.of(ClickType.LEFT, p -> new RechargeGUI<>(mcdndPlayer, RechargeGUI.CLASS_RESOURCES, index, p)));
        setButton(2, SpigotIconBuilder.of(Material.REPEATER, MenuText.current(classResource)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                classResource.setCurrentCharges(i);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.COMPARATOR, MenuText.maxUses(classResource.getMaxUses())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                classResource.setMaxCharges(i);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new ClassResourcesGUI(mcdndPlayer, p)));
    }
}
