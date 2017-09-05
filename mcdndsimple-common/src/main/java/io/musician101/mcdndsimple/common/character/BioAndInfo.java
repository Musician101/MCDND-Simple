package io.musician101.mcdndsimple.common.character;

import java.util.ArrayList;
import java.util.List;

//TODO see if it's possible to have a picture of some sort
public class BioAndInfo {

    private List<String> bio = new ArrayList<>();
    private String name = "Not Named";

    public List<String> getBio() {
        return bio;
    }

    public void setBio(List<String> bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
