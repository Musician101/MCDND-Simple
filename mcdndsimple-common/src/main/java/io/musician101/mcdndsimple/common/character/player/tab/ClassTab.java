package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@JsonKey(key = Keys.CLASS_TAB, typeAdapter = ClassTab.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<ClassTab>, JsonSerializer<ClassTab> {

        @Override
        public ClassTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ClassTab classTab = new ClassTab();
            JsonKeyProcessor.<JsonObject, ClassLevels>getJsonKey(Keys.CLASS_LEVELS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(classTab::setClassLevels));
            Keys.CLASS_RESOURCES.deserializeFromParent(jsonObject, context).ifPresent(classTab::setClassResources);
            Keys.CLASS_ACTIONS.deserializeFromParent(jsonObject, context).ifPresent(classTab::setClassActions);
            Keys.CLASS_FEATURE_NOTES.deserializeFromParent(jsonObject, context).ifPresent(classTab::setClassFeatureNotes);
            Keys.OTHER_NOTES.deserializeFromParent(jsonObject, context).ifPresent(classTab::setOtherNotes);
            return classTab;
        }

        @Override
        public JsonElement serialize(ClassTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, ClassLevels>getJsonKey(Keys.CLASS_LEVELS).ifPresent(jsonKey -> jsonKey.serialize(src.getClassLevels(), jsonObject, context));
            Keys.CLASS_RESOURCES.serialize(src.getClassResources(), jsonObject, context);
            Keys.CLASS_ACTIONS.serialize(src.getClassActions(), jsonObject, context);
            Keys.CLASS_FEATURE_NOTES.serialize(src.getClassFeatureNotes(), jsonObject, context);
            Keys.OTHER_NOTES.serialize(src.getOtherNotes(), jsonObject, context);
            return jsonObject;
        }
    }
}
