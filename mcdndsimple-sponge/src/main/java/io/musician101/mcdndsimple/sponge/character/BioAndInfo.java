package io.musician101.mcdndsimple.sponge.character;

import java.util.ArrayList;
import java.util.List;

//TODO see if it's possible to have a picture of some sort
//TODO needs dataserializable
public class BioAndInfo
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
