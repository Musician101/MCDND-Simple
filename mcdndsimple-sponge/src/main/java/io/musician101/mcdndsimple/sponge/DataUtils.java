package io.musician101.mcdndsimple.sponge;

import com.google.common.collect.Table;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static Optional<DataContainer> getDataContainer(DataContainer dataContainer, Key<Value<DataContainer>> key)
    {
        return dataContainer.getObject(key.getQuery(), DataContainer.class);
    }

    public static Optional<List<DataContainer>> getDataContainerList(DataContainer dataContainer, Key<ListValue<DataContainer>> key)
    {
        return dataContainer.getObjectList(key.getQuery(), DataContainer.class);
    }
}
