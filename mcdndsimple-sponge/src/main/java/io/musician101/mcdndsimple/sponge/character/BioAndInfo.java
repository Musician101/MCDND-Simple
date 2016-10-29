package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

//TODO see if it's possible to have a picture of some sort
public class BioAndInfo implements DataSerializable
{
    private List<String> bio = new ArrayList<>();
    private String name;

    public BioAndInfo(String name)
    {
        this.name = name;
    }

    public List<String> getBio()
    {
        return bio;
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
                .set(MCDNDSimpleKeys.NAME, name)
                .set(MCDNDSimpleKeys.BIO, bio);
    }

    public String getName()
    {
        return name;
    }

    public void setBio(List<String> bio)
    {
        this.bio = bio;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
