package io.musician101.mcdndsimple.sponge.character;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;

public enum SpellcasterClass implements DataSerializable
{
    ARCANE_TRICKSTER("Arcane Trickster"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    ELDRITCH_KNIGHT("Eldritch Knight"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final Table<Integer, String, Integer> table = Tables.newCustomTable(new HashMap<>(), HashMap::new);
    private final String name;
    public static final String CANTRIPS = "cantrips";
    public static final String SPELLS = "spells";
    public static final String FIRST = "1st";
    public static final String SECOND = "2nd";
    public static final String THIRD = "3rd";
    public static final String FOURTH = "4th";
    public static final String FIFTH = "5th";
    public static final String SIXTH = "6th";
    public static final String SEVENTH = "7th";
    public static final String EIGHTH = "8th";
    public static final String NINTH = "9th";

    SpellcasterClass(String name)
    {
        this.name = name;
        setTable();
    }

    private void setTable()
    {
        switch (name)
        {
            case "Arcane Trickster":
                arcaneTrickster();
                break;
            case "Bard"://NOSONAR
                bard();
                break;
            case "Cleric":
                cleric();
                break;
            case "Druid":
                druid();
                break;
            case "Eldritch Knight":
                eldritchKnight();
                break;
            case "Paladin":
                paladin();
                break;
            case "Sorcerer":
                sorcerer();
                break;
            case "Warlock":
                warlock();
                break;
            case "Wizard":
                wizard();
                break;
            default:
        }
    }

    private void arcaneTrickster()
    {
        multiAdd(1, 2, CANTRIPS, 0);
        multiAdd(3, 9, CANTRIPS, 3);
        multiAdd(10, 20, CANTRIPS, 4);
        multiAdd(1, 2, SPELLS, 0);
        table.put(3, SPELLS, 3);
        multiAdd(4, 6, SPELLS, 4);
        table.put(7, SPELLS, 5);
        multiAdd(8, 9, SPELLS, 6);
        table.put(10, SPELLS, 7);
        multiAdd(11, 12, SPELLS, 8);
        table.put(13, SPELLS, 9);
        multiAdd(14, 15, SPELLS, 10);
        multiAdd(16, 18, SPELLS, 11);
        table.put(19, SPELLS, 12);
        table.put(20, SPELLS, 13);
        multiAdd(1, 2, FIRST, 0);
        table.put(3, FIRST, 2);
        multiAdd(4, 6, FIRST, 3);
        multiAdd(7, 20, FIRST, 4);
        multiAdd(1, 6, SECOND, 0);
        multiAdd(7, 9, SECOND, 2);
        multiAdd(10, 20, SECOND, 3);
        multiAdd(1, 12, THIRD, 0);
        multiAdd(13, 15, THIRD, 2);
        multiAdd(16, 20, THIRD, 3);
        multiAdd(1, 18, FOURTH, 0);
        multiAdd(19, 20, FOURTH, 1);
        multiAdd(1, 20, FIFTH, 0);
        multiAdd(1, 20, SIXTH, 0);
        multiAdd(1, 20, SEVENTH, 0);
        multiAdd(1, 20, EIGHTH, 0);
        multiAdd(1, 20, NINTH, 0);
    }

    private void bard()
    {
        multiAdd(1, 3, CANTRIPS, 3);
        multiAdd(4, 9, CANTRIPS, 4);
        multiAdd(10, 20, CANTRIPS, 5);
        table.put(1, SPELLS, 4);
        table.put(2, SPELLS, 5);
        table.put(4, SPELLS, 6);
        table.put(4, SPELLS, 7);
        table.put(5, SPELLS, 8);
        table.put(6, SPELLS, 9);
        table.put(7, SPELLS, 10);
        table.put(8, SPELLS, 11);
        table.put(9, SPELLS, 12);
        table.put(10, SPELLS, 14);
        multiAdd(11, 12, SPELLS, 15);
        table.put(13, SPELLS, 16);
        table.put(14, SPELLS, 18);
        multiAdd(15, 16, SPELLS, 19);
        table.put(17, SPELLS, 20);
        multiAdd(18, 20, SPELLS, 11);
        table.put(1, FIRST, 2);
        table.put(2, FIRST, 3);
        multiAdd(3, 20, FIRST, 4);
        multiAdd(1, 2, SECOND, 0);
        table.put(3, SECOND, 2);
        multiAdd(4, 20, SECOND, 3);
        multiAdd(1, 4, THIRD, 0);
        table.put(5, THIRD, 2);
        multiAdd(6, 20, THIRD, 3);
        multiAdd(1, 6, FOURTH, 0);
        table.put(7, FOURTH, 1);
        table.put(8, FOURTH, 2);
        multiAdd(9, 20, FOURTH, 3);
        multiAdd(1, 8, FIFTH, 0);
        multiAdd(9, 17, FIFTH, 2);
        multiAdd(18, 20, FIFTH, 3);
        multiAdd(1, 10, SIXTH, 0);
        multiAdd(11, 18, SIXTH, 1);
        multiAdd(19, 20, SIXTH, 2);
        multiAdd(1, 12, SEVENTH, 0);
        multiAdd(14, 19, SEVENTH, 1);
        table.put(20, SEVENTH, 2);
        multiAdd(1, 14, EIGHTH, 0);
        multiAdd(15, 20, EIGHTH, 1);
        multiAdd(1, 16, NINTH, 0);
        multiAdd(17, 20, NINTH, 1);
    }

    private void cleric()
    {
        multiAdd(1, 3, CANTRIPS, 3);
        multiAdd(4, 9, CANTRIPS, 4);
        multiAdd(10, 20, CANTRIPS, 5);
        table.put(1, FIRST, 2);
        table.put(2, FIRST, 3);
        multiAdd(3, 20, FIRST, 4);
        multiAdd(1, 2, SECOND, 0);
        table.put(3, SECOND, 2);
        multiAdd(4, 20, SECOND, 3);
        multiAdd(1, 4, THIRD, 0);
        table.put(5, THIRD, 2);
        multiAdd(6, 20, THIRD, 3);
        multiAdd(1, 6, FOURTH, 0);
        table.put(7, FOURTH, 1);
        table.put(8, FOURTH, 2);
        multiAdd(9, 20, FOURTH, 3);
        multiAdd(1, 8, FIFTH, 0);
        multiAdd(9, 17, FIFTH, 2);
        multiAdd(18, 20, FIFTH, 3);
        multiAdd(1, 10, SIXTH, 0);
        multiAdd(11, 18, SIXTH, 1);
        multiAdd(19, 20, SIXTH, 2);
        multiAdd(1, 12, SEVENTH, 0);
        multiAdd(14, 19, SEVENTH, 1);
        table.put(20, SEVENTH, 2);
        multiAdd(1, 14, EIGHTH, 0);
        multiAdd(15, 20, EIGHTH, 1);
        multiAdd(1, 16, NINTH, 0);
        multiAdd(17, 20, NINTH, 1);
    }

    private void druid()
    {
        multiAdd(1, 3, CANTRIPS, 3);
        multiAdd(4, 9, CANTRIPS, 4);
        multiAdd(10, 20, CANTRIPS, 5);
        table.put(1, FIRST, 2);
        table.put(2, FIRST, 3);
        multiAdd(3, 20, FIRST, 4);
        multiAdd(1, 2, SECOND, 0);
        table.put(3, SECOND, 2);
        multiAdd(4, 20, SECOND, 3);
        multiAdd(1, 4, THIRD, 0);
        table.put(5, THIRD, 2);
        multiAdd(6, 20, THIRD, 3);
        multiAdd(1, 6, FOURTH, 0);
        table.put(7, FOURTH, 1);
        table.put(8, FOURTH, 2);
        multiAdd(9, 20, FOURTH, 3);
        multiAdd(1, 8, FIFTH, 0);
        table.put(7, FIFTH, 1);
        multiAdd(8, 17, FIFTH, 2);
        multiAdd(18, 20, FIFTH, 3);
        multiAdd(1, 10, SIXTH, 0);
        multiAdd(11, 18, SIXTH, 1);
        multiAdd(19, 20, SIXTH, 2);
        multiAdd(1, 12, SEVENTH, 0);
        multiAdd(14, 19, SEVENTH, 1);
        table.put(20, SEVENTH, 2);
        multiAdd(1, 14, EIGHTH, 0);
        multiAdd(15, 20, EIGHTH, 1);
        multiAdd(1, 16, NINTH, 0);
        multiAdd(17, 20, NINTH, 1);
    }

    private void eldritchKnight()
    {
        multiAdd(1, 2, CANTRIPS, 0);
        multiAdd(3, 9, CANTRIPS, 2);
        multiAdd(10, 20, CANTRIPS, 3);
        multiAdd(1, 2, SPELLS, 0);
        table.put(3, SPELLS, 2);
        multiAdd(4, 6, SPELLS, 4);
        table.put(7, SPELLS, 5);
        multiAdd(8, 9, SPELLS, 6);
        table.put(10, SPELLS, 7);
        multiAdd(11, 12, SPELLS, 8);
        table.put(13, SPELLS, 9);
        multiAdd(14, 15, SPELLS, 10);
        multiAdd(16, 18, SPELLS, 11);
        table.put(19, SPELLS, 12);
        table.put(20, SPELLS, 13);
        multiAdd(1, 2, FIRST, 0);
        table.put(3, FIRST, 2);
        multiAdd(4, 6, FIRST, 3);
        multiAdd(7, 20, FIRST, 4);
        multiAdd(1, 6, SECOND, 0);
        multiAdd(7, 9, SECOND, 2);
        multiAdd(10, 20, SECOND, 3);
        multiAdd(1, 12, THIRD, 0);
        multiAdd(12, 15, THIRD, 2);
        multiAdd(16, 20, THIRD, 3);
        multiAdd(1, 18, FOURTH, 0);
        multiAdd(19, 20, FOURTH, 1);
        multiAdd(1, 20, FIFTH, 0);
        multiAdd(1, 20, SIXTH, 0);
        multiAdd(1, 20, SEVENTH, 0);
        multiAdd(1, 20, EIGHTH, 0);
        multiAdd(1, 20, NINTH, 0);
    }

    private void paladin()
    {
        table.put(1, FIRST, 0);
        table.put(2, FIRST, 2);
        multiAdd(3, 4, FIRST, 3);
        multiAdd(5, 20, FIRST, 4);
        multiAdd(1, 4, SECOND, 0);
        multiAdd(5, 6, SECOND, 2);
        multiAdd(7, 20, SECOND, 3);
        multiAdd(1, 8, THIRD, 0);
        multiAdd(9, 10, THIRD, 2);
        multiAdd(11, 20, THIRD, 3);
        multiAdd(1, 12, FOURTH, 0);
        multiAdd(13, 14, FOURTH, 1);
        multiAdd(15, 16, FOURTH, 2);
        multiAdd(17, 20, FOURTH, 3);
        multiAdd(1, 16, FIFTH, 0);
        multiAdd(17, 18, FIFTH, 1);
        multiAdd(19, 20, FIFTH, 2);
        multiAdd(1, 20, SIXTH, 0);
        multiAdd(1, 20, SEVENTH, 0);
        multiAdd(1, 20, EIGHTH, 0);
        multiAdd(1, 20, NINTH, 0);
    }

    private void sorcerer()
    {
        multiAdd(1, 3, CANTRIPS, 4);
        multiAdd(4, 9, CANTRIPS, 5);
        multiAdd(10, 20, CANTRIPS, 6);
        table.put(1, SPELLS, 2);
        table.put(2, SPELLS, 3);
        table.put(4, SPELLS, 4);
        table.put(4, SPELLS, 5);
        table.put(5, SPELLS, 6);
        table.put(6, SPELLS, 7);
        table.put(7, SPELLS, 8);
        table.put(8, SPELLS, 9);
        table.put(9, SPELLS, 10);
        table.put(10, SPELLS, 11);
        multiAdd(11, 12, SPELLS, 12);
        multiAdd(13, 14, SPELLS, 13);
        multiAdd(15, 16, SPELLS, 14);
        multiAdd(17, 20, SPELLS, 15);
        table.put(1, FIRST, 2);
        table.put(2, FIRST, 3);
        multiAdd(3, 20, FIRST, 4);
        multiAdd(1, 2, SECOND, 0);
        table.put(3, SECOND, 2);
        multiAdd(4, 20, SECOND, 3);
        multiAdd(1, 4, THIRD, 0);
        table.put(5, THIRD, 2);
        multiAdd(6, 20, THIRD, 3);
        multiAdd(1, 6, FOURTH, 0);
        table.put(7, FOURTH, 1);
        table.put(8, FOURTH, 2);
        multiAdd(9, 20, FOURTH, 3);
        multiAdd(1, 8, FIFTH, 0);
        multiAdd(9, 17, FIFTH, 2);
        multiAdd(18, 20, FIFTH, 3);
        multiAdd(1, 10, SIXTH, 0);
        multiAdd(11, 18, SIXTH, 1);
        multiAdd(19, 20, SIXTH, 2);
        multiAdd(1, 12, SEVENTH, 0);
        multiAdd(14, 19, SEVENTH, 1);
        table.put(20, SEVENTH, 2);
        multiAdd(1, 14, EIGHTH, 0);
        multiAdd(15, 20, EIGHTH, 1);
        multiAdd(1, 16, NINTH, 0);
        multiAdd(17, 20, NINTH, 1);
    }

    private void warlock()
    {
        multiAdd(1, 3, CANTRIPS, 2);
        multiAdd(4, 9, CANTRIPS, 3);
        multiAdd(10, 20, CANTRIPS, 4);
        table.put(1, SPELLS, 2);
        multiAdd(1, 3, SPELLS, 2);
        multiAdd(4, 9, SPELLS, 3);
        multiAdd(10, 20, SPELLS, 4);
        multiAdd(0, 1, FIRST, 0);
        multiAdd(0, 1, SECOND, 0);
        multiAdd(0, 1, THIRD, 0);
        multiAdd(0, 1, FOURTH, 0);
        multiAdd(0, 1, FIFTH, 0);
        multiAdd(0, 1, SIXTH, 0);
        multiAdd(0, 1, SEVENTH, 0);
        multiAdd(0, 1, EIGHTH, 0);
        multiAdd(0, 1, NINTH, 0);
    }

    private void wizard()
    {
        multiAdd(1, 3, CANTRIPS, 3);
        multiAdd(4, 9, CANTRIPS, 4);
        multiAdd(10, 20, CANTRIPS, 5);
        multiAdd(1, 20, SPELLS, 0);
        table.put(1, FIRST, 2);
        table.put(2, FIRST, 3);
        multiAdd(3, 20, FIRST, 4);
        multiAdd(1, 2, SECOND, 0);
        table.put(3, SECOND, 2);
        multiAdd(4, 20, SECOND, 3);
        multiAdd(1, 4, THIRD, 0);
        table.put(5, THIRD, 2);
        multiAdd(6, 20, THIRD, 3);
        multiAdd(1, 6, FOURTH, 0);
        table.put(7, FOURTH, 1);
        table.put(8, FOURTH, 2);
        multiAdd(9, 20, FOURTH, 3);
        multiAdd(1, 8, FIFTH, 0);
        table.put(9, FIFTH, 1);
        multiAdd(10, 17, FIFTH, 2);
        multiAdd(18, 20, FIFTH, 3);
        multiAdd(1, 10, SIXTH, 0);
        multiAdd(11, 18, SIXTH, 1);
        multiAdd(19, 20, SIXTH, 2);
        multiAdd(1, 13, SEVENTH, 0);
        multiAdd(14, 19, SEVENTH, 1);
        table.put(20, SEVENTH, 1);
        multiAdd(1, 14, EIGHTH, 0);
        multiAdd(15, 20, EIGHTH, 1);
        multiAdd(1, 16, NINTH, 0);
        multiAdd(17, 20, NINTH, 1);
    }

    private void multiAdd(int startingLevel, int maxLevel, String column, int amount)
    {
        for (int i = startingLevel; i < maxLevel + 1; i++)
            table.put(i, column, amount);
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.SPELLCASTER_TABLE, DataUtils.serialize(table))
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public int getTableEntry(int level, String column)//NOSONAR
    {
        //Special check for Warlock
        if ("Warlock".equals(name))
        {
            if (level == 1 && column.equals(FIRST))
                return 1;
            else if ((level == 2 && column.equals(FIRST)) || (Arrays.asList(3, 4).contains(level) && column.equals(SECOND)) || (Arrays.asList(5, 6).contains(level) && column.equals(THIRD)) || (Arrays.asList(7, 8).contains(level) && column.equals(FOURTH)) || (Arrays.asList(9, 10).contains(level) && column.equals(FIFTH)))//NOSONAR
                return 2;
            else if (level >= 11 && level <= 16 && column.equals(FIFTH))
                return 3;
            else if (level >= 17 && level <= 20 && column.equals(FIFTH))
                return 4;
        }

        return table.get(level, column);
    }

    public int getCantripsAmount(int level)
    {
        return table.get(level, CANTRIPS);
    }

    public int getSpellsAmount(int level)
    {
        return table.get(level, SPELLS);
    }

    public int get1stLevelAmount(int level)
    {
        return table.get(level, FIRST);
    }

    public int get2ndLevelAmount(int level)
    {
        return table.get(level, SECOND);
    }

    public int get3rdLevelAmount(int level)
    {
        return table.get(level, THIRD);
    }

    public int get4thLevelAmount(int level)
    {
        return table.get(level, FOURTH);
    }

    public int get5thLevelAmount(int level)
    {
        return table.get(level, FIFTH);
    }

    public int get6thLevelAmount(int level)
    {
        return table.get(level, SIXTH);
    }

    public int get7thLevelAmount(int level)
    {
        return table.get(level, SEVENTH);
    }

    public int get8thLevelAmount(int level)
    {
        return table.get(level, EIGHTH);
    }

    public int get9thLevelAmount(int level)
    {
        return table.get(level, NINTH);
    }

    public String getName()
    {
        return name;
    }
}
