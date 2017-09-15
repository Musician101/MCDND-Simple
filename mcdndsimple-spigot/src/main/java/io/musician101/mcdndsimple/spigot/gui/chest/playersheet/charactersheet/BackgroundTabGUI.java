package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.tab.BackgroundTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.potion.PotionType;

public class BackgroundTabGUI extends MCDNDSimpleChestGUI {

    private BackgroundTab backgroundTab;

    public BackgroundTabGUI(Player player, BackgroundTab backgroundTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 27, MenuText.BACKGROUND, prevGUI);
        this.backgroundTab = backgroundTab;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.ARROW, MenuText.gender(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setGender(s);
            delayedOpen();
        }));
        set(1, ClickType.LEFT, createItem(Material.RAW_FISH, MenuText.age(backgroundTab)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            backgroundTab.setAge(i);
            delayedOpen();
        }));
        set(2, ClickType.LEFT, createItem(Material.ARMOR_STAND, MenuText.height(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setHeight(s);
            delayedOpen();
        }));
        set(3, ClickType.LEFT, createItem(Material.IRON_BLOCK, MenuText.weight(backgroundTab)), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            backgroundTab.setWeight(d);
            delayedOpen();
        }));
        set(4, ClickType.LEFT, createItem(Material.SPIDER_EYE, MenuText.eyes(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setEyes(s);
            delayedOpen();
        }));
        set(5, ClickType.LEFT, createItem(Material.RABBIT_HIDE, MenuText.hair(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setHair(s);
            delayedOpen();
        }));
        set(6, ClickType.LEFT, createItem(Material.LEATHER, MenuText.size(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setSize(s);
            delayedOpen();
        }));
        set(7, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.vision(backgroundTab)), PotionType.NIGHT_VISION), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setVision(s);
            delayedOpen();
        }));
        set(8, ClickType.LEFT, createItem(Material.BOOK, MenuText.KNOWN_LANGUAGES), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getLanguages());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setLanguages(pages);
                open();
            });
        });
        set(9, ClickType.LEFT, createItem(Material.TOTEM, MenuText.alignment(backgroundTab)), player -> new StringInputAnvilGUI(player, (p, s) -> {
            backgroundTab.setAlignment(s);
            open();
        }));
        set(10, ClickType.LEFT, createItem(Material.BOOK, MenuText.BACKGROUND), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getBackground());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setBackground(pages);
                open();
            });
        });
        set(11, ClickType.LEFT, createItem(Material.BOOK, MenuText.RACIAL_TRAITS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getRacialTraits());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setRacialTraits(pages);
                open();
            });
        });
        set(12, ClickType.LEFT, createItem(Material.BOOK, MenuText.PERSONALITY_TRAITS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getPersonalityTraits());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setPersonalityTraits(pages);
                open();
            });
        });
        set(13, ClickType.LEFT, createItem(Material.BOOK, MenuText.IDEALS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getIdeals());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setIdeals(pages);
                open();
            });
        });
        set(14, ClickType.LEFT, createItem(Material.BOOK, MenuText.BONDS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getBonds());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setBonds(pages);
                open();
            });
        });
        set(15, ClickType.LEFT, createItem(Material.BOOK, MenuText.FLAWS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getFlaws());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setFlaws(pages);
                open();
            });
        });
        set(16, ClickType.LEFT, createItem(Material.BOOK, MenuText.ARMOR_PROFICIENCIES), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getArmorProficiencies());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setArmorProficiencies(pages);
                open();
            });
        });
        set(16, ClickType.LEFT, createItem(Material.BOOK, MenuText.WEAPON_PROFICIENCIES), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getWeaponProficiencies());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setWeaponProficiencies(pages);
                open();
            });
        });
        set(18, ClickType.LEFT, createItem(Material.BOOK, MenuText.TOOL_PROFICIENCIES), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getToolProficiencies());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setToolProficiencies(pages);
                open();
            });
        });
        set(19, ClickType.LEFT, createItem(Material.BOOK, MenuText.OTHER_NOTES), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(backgroundTab.getOtherNotes());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                backgroundTab.setOtherNotes(pages);
                open();
            });
        });
        setBackButton(26, ClickType.LEFT, Material.BARRIER);
    }
}
