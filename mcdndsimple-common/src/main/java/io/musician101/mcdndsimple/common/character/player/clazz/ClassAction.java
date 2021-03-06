package io.musician101.mcdndsimple.common.character.player.clazz;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

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

    public static class Serializer extends BaseSerializer<ClassAction> {

        @Override
        public ClassAction deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ClassAction classAction = new ClassAction();
            classAction.setGainedFrom(deserialize(jsonObject, context, Keys.GAINED_FROM));;
            classAction.setMaxUses(deserialize(jsonObject, context, Keys.MAX_USES));;
            classAction.setUsed(deserialize(jsonObject, context, Keys.USED));;
            classAction.setOutput(deserialize(jsonObject, context, Keys.OUTPUT));;
            classAction.setOutputOptions(deserialize(jsonObject, context, Keys.OUTPUT_OPTIONS));;
            classAction.setName(deserialize(jsonObject, context, Keys.NAME));;
            return classAction;
        }

        @Override
        public JsonElement serialize(ClassAction src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.GAINED_FROM, src.getGainedFrom());
            serialize(jsonObject, context, Keys.MAX_USES, src.getMaxUses());
            serialize(jsonObject, context, Keys.USED, src.getUsed());
            serialize(jsonObject, context, Keys.OUTPUT, src.getOutput());
            serialize(jsonObject, context, Keys.OUTPUT_OPTIONS, src.getOutputOptions());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            return jsonObject;
        }
    }
}
