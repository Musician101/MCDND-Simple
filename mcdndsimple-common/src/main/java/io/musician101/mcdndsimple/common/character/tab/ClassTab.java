package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.ClassResource;
import java.util.ArrayList;
import java.util.List;

public class ClassTab {

    private List<ClassAction> classActions = new ArrayList<>();
    private List<String> classFeatureNotes = new ArrayList<>();
    private ClassLevels classLevels = new ClassLevels();
    private List<ClassResource> classResources = new ArrayList<>();
    private List<String> otherNotes = new ArrayList<>();

    public void addClassAction(ClassAction classAction) {
        classActions.add(classAction);
    }

    public void addClassResource(ClassResource classResource) {
        classResources.add(classResource);
    }

    public List<ClassAction> getClassActions() {
        return classActions;
    }

    public void setClassActions(List<ClassAction> classActions) {
        this.classActions = classActions;
    }

    public List<String> getClassFeatureNotes() {
        return classFeatureNotes;
    }

    public void setClassFeatureNotes(List<String> classFeatureNotes) {
        this.classFeatureNotes = classFeatureNotes;
    }

    public ClassLevels getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(ClassLevels classLevels) {
        this.classLevels = classLevels;
    }

    public List<ClassResource> getClassResources() {
        return classResources;
    }

    public void setClassResources(List<ClassResource> classResources) {
        this.classResources = classResources;
    }

    public List<String> getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(List<String> otherNotes) {
        this.otherNotes = otherNotes;
    }

    public void removeClassAction(ClassAction classAction) {
        classActions.remove(classAction);
    }

    public void removeClassResource(ClassResource classResource) {
        classResources.remove(classResource);
    }
}
