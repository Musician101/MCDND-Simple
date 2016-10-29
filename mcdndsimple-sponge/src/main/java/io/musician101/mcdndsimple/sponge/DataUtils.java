package io.musician101.mcdndsimple.sponge;

import com.google.common.collect.Table;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import java.util.ArrayList;
import java.util.List;

public class DataUtils
{
    private DataUtils()
    {

    }

    public static <E extends DataSerializable> List<DataContainer> serialize(List<E> list)
    {
        List<DataContainer> containers = new ArrayList<>();
        list.forEach(object -> containers.add(object.toContainer()));
        return containers;
    }

    public static DataContainer serialize(Table<Integer, String, Integer> table)
    {
        DataContainer dataContainer = new MemoryDataContainer();
        table.rowMap().entrySet().forEach(entry -> dataContainer.set(DataQuery.of(Integer.toString(entry.getKey())), entry.getValue()));
        return dataContainer;
    }
}
