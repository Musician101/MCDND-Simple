package io.musician101.mcdndsimple.common.character.player.clazz;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@TypeOf(ClassAction.Serializer.class)
public class ClassAction extends Rechargeable {

    private GainedFrom gainedFrom = GainedFrom.OTHER;
    private int maxUses = 0;
    private String name = "";
    private List<String> output = new ArrayList<>();
    private OutputOptions outputOptions = new OutputOptions();
    private int used = 0;

    public GainedFrom getGainedFrom() {
        return gainedFrom;
    }

    public void setGainedFrom(GainedFrom gainedFrom) {
        this.gainedFrom = gainedFrom;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public OutputOptions getOutputOptions() {
        return outputOptions;
    }

    public void setOutputOptions(OutputOptions outputOptions) {
        this.outputOptions = outputOptions;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public static class Serializer implements JsonDeserializer<ClassAction>, JsonSerializer<ClassAction> {

        @Override
        public ClassAction deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ClassAction classAction = new ClassAction();
            JsonKeyProcessor.<JsonPrimitive, GainedFrom>getJsonKey(Keys.GAINED_FROM).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(classAction::setGainedFrom));
            Keys.MAX_USES.deserializeFromParent(jsonObject, context).ifPresent(classAction::setMaxUses);
            Keys.USED.deserializeFromParent(jsonObject, context).ifPresent(classAction::setUsed);
            Keys.OUTPUT.deserializeFromParent(jsonObject, context).ifPresent(classAction::setOutput);
            JsonKeyProcessor.<JsonObject, OutputOptions>getJsonKey(Keys.OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(classAction::setOutputOptions));
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(classAction::setName);
            return classAction;
        }

        @Override
        public JsonElement serialize(ClassAction src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, GainedFrom>getJsonKey(Keys.GAINED_FROM).ifPresent(jsonKey -> jsonKey.serialize(src.getGainedFrom(), jsonObject, context));
            Keys.MAX_USES.serialize(src.getMaxUses(), jsonObject, context);
            Keys.USED.serialize(src.getUsed(), jsonObject, context);
            Keys.OUTPUT.serialize(src.getOutput(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, OutputOptions>getJsonKey(Keys.OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getOutputOptions(), jsonObject, context));
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            return jsonObject;
        }
    }
}
