package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.ClassAction;
import io.musician101.mcdndsimple.sponge.character.ClassLevels;
import io.musician101.mcdndsimple.sponge.character.ClassResource;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ClassTab implements DataSerializable
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
                .set(MCDNDSimpleKeys.CLASS_LEVELS, classLevels.toContainer())
                .set(MCDNDSimpleKeys.CLASS_ACTIONS, DataUtils.serialize(classActions))
                .set(MCDNDSimpleKeys.CLASS_RESOURCES, DataUtils.serialize(classResources))
                .set(MCDNDSimpleKeys.CLASS_FEATURE_NOTES, classFeatureNotes)
                .set(MCDNDSimpleKeys.OTHER_NOTES, otherNotes);
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
