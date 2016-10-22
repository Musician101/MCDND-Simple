package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.ClassAction;
import io.musician101.mcdndsimple.sponge.character.ClassLevels;
import io.musician101.mcdndsimple.sponge.character.ClassResource;

import java.util.ArrayList;
import java.util.List;

public class ClassTab
{
    private ClassLevels classLevels = new ClassLevels();
    private List<ClassAction> classActions = new ArrayList<>();
    private List<ClassResource> classResources = new ArrayList<>();
    private List<String> classFeatureNotes = new ArrayList<>();
    private List<String> otherNotes = new ArrayList<>();

    public List<ClassAction> getClassActions()
    {
        return classActions;
    }

    public List<String> getClassFeatureNotes()
    {
        return classFeatureNotes;
    }

    public ClassLevels getClassLevels()
    {
        return classLevels;
    }

    public List<ClassResource> getClassResources()
    {
        return classResources;
    }

    public List<String> getOtherNotes()
    {
        return otherNotes;
    }

    public void setClassActions(List<ClassAction> classActions)
    {
        this.classActions = classActions;
    }

    public void setClassFeatureNotes(List<String> classFeatureNotes)
    {
        this.classFeatureNotes = classFeatureNotes;
    }

    public void setClassLevels(ClassLevels classLevels)
    {
        this.classLevels = classLevels;
    }

    public void setClassResources(List<ClassResource> classResources)
    {
        this.classResources = classResources;
    }

    public void setOtherNotes(List<String> otherNotes)
    {
        this.otherNotes = otherNotes;
    }
}
