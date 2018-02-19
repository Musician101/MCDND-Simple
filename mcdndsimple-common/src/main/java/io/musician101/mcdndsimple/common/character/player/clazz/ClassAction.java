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
import javax.annotation.Nonnull;

@TypeOf(ClassAction.Serializer.class)
public class ClassAction extends Rechargeable {

    @Nonnull
    private GainedFrom gainedFrom = GainedFrom.OTHER;
    private int maxUses = 0;
    @Nonnull
    private String name = "";
    @Nonnull
    private List<String> output = new ArrayList<>();
    @Nonnull
    private OutputOptions outputOptions = new OutputOptions();
    private int used = 0;

    @Nonnull
    public GainedFrom getGainedFrom() {
        return gainedFrom;
    }

    public void setGainedFrom(@Nonnull GainedFrom gainedFrom) {
        this.gainedFrom = gainedFrom;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public List<String> getOutput() {
        return output;
    }

    public void setOutput(@Nonnull List<String> output) {
        this.output = output;
    }

    @Nonnull
    public OutputOptions getOutputOptions() {
        return outputOptions;
    }

    private void setOutputOptions(@Nonnull OutputOptions outputOptions) {
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
