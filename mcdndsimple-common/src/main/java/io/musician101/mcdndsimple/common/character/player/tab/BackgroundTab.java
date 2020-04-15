package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;


public class BackgroundTab {

    private int age = 0;
    @Nonnull
    private String alignment = "";
    @Nonnull
    private List<String> armorProficiencies = new ArrayList<>();
    @Nonnull
    private List<String> background = new ArrayList<>();
    @Nonnull
    private List<String> bonds = new ArrayList<>();
    @Nonnull
    private String eyes = "";
    @Nonnull
    private List<String> flaws = new ArrayList<>();
    @Nonnull
    private String gender = "";
    @Nonnull
    private String hair = "";
    @Nonnull
    private String height = "";
    @Nonnull
    private List<String> ideals = new ArrayList<>();
    @Nonnull
    private List<String> languages = new ArrayList<>();
    @Nonnull
    private List<String> otherNotes = new ArrayList<>();
    @Nonnull
    private List<String> personalityTraits = new ArrayList<>();
    @Nonnull
    private List<String> racialTraits = new ArrayList<>();
    @Nonnull
    private String size = "";
    @Nonnull
    private List<String> toolProficiencies = new ArrayList<>();
    @Nonnull
    private String vision = "";
    @Nonnull
    private List<String> weaponProficiencies = new ArrayList<>();
    private double weight = 0.0;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Nonnull
    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(@Nonnull String alignment) {
        this.alignment = alignment;
    }

    @Nonnull
    public List<String> getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(@Nonnull List<String> armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    @Nonnull
    public List<String> getBackground() {
        return background;
    }

    public void setBackground(@Nonnull List<String> background) {
        this.background = background;
    }

    @Nonnull
    public List<String> getBonds() {
        return bonds;
    }

    public void setBonds(@Nonnull List<String> bonds) {
        this.bonds = bonds;
    }

    @Nonnull
    public String getEyes() {
        return eyes;
    }

    public void setEyes(@Nonnull String eyes) {
        this.eyes = eyes;
    }

    @Nonnull
    public List<String> getFlaws() {
        return flaws;
    }

    public void setFlaws(@Nonnull List<String> flaws) {
        this.flaws = flaws;
    }

    @Nonnull
    public String getGender() {
        return gender;
    }

    public void setGender(@Nonnull String gender) {
        this.gender = gender;
    }

    @Nonnull
    public String getHair() {
        return hair;
    }

    public void setHair(@Nonnull String hair) {
        this.hair = hair;
    }

    @Nonnull
    public String getHeight() {
        return height;
    }

    public void setHeight(@Nonnull String height) {
        this.height = height;
    }

    @Nonnull
    public List<String> getIdeals() {
        return ideals;
    }

    public void setIdeals(@Nonnull List<String> ideals) {
        this.ideals = ideals;
    }

    @Nonnull
    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(@Nonnull List<String> languages) {
        this.languages = languages;
    }

    @Nonnull
    public List<String> getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(@Nonnull List<String> otherNotes) {
        this.otherNotes = otherNotes;
    }

    @Nonnull
    public List<String> getPersonalityTraits() {
        return personalityTraits;
    }

    public void setPersonalityTraits(@Nonnull List<String> personalityTraits) {
        this.personalityTraits = personalityTraits;
    }

    @Nonnull
    public List<String> getRacialTraits() {
        return racialTraits;
    }

    public void setRacialTraits(@Nonnull List<String> racialTraits) {
        this.racialTraits = racialTraits;
    }

    @Nonnull
    public String getSize() {
        return size;
    }

    public void setSize(@Nonnull String size) {
        this.size = size;
    }

    @Nonnull
    public List<String> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(@Nonnull List<String> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    @Nonnull
    public String getVision() {
        return vision;
    }

    public void setVision(@Nonnull String vision) {
        this.vision = vision;
    }

    @Nonnull
    public List<String> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(@Nonnull List<String> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static class Serializer extends BaseSerializer<BackgroundTab> {

        @Override
        public BackgroundTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            BackgroundTab backgroundTab = new BackgroundTab();
            backgroundTab.setWeight(deserialize(jsonObject, context, Keys.WEIGHT_DOUBLE));
            backgroundTab.setWeight(deserialize(jsonObject, context, Keys.AGE));
            backgroundTab.setArmorProficiencies(deserialize(jsonObject, context, Keys.ARMOR_PROFICIENCIES));
            backgroundTab.setBackground(deserialize(jsonObject, context, Keys.BACKGROUND));
            backgroundTab.setBonds(deserialize(jsonObject, context, Keys.BONDS));
            backgroundTab.setFlaws(deserialize(jsonObject, context, Keys.FLAWS));
            backgroundTab.setIdeals(deserialize(jsonObject, context, Keys.IDEALS));
            backgroundTab.setLanguages(deserialize(jsonObject, context, Keys.LANGUAGES));
            backgroundTab.setOtherNotes(deserialize(jsonObject, context, Keys.OTHER_NOTES));
            backgroundTab.setPersonalityTraits(deserialize(jsonObject, context, Keys.PERSONALITY_TRAITS));
            backgroundTab.setRacialTraits(deserialize(jsonObject, context, Keys.RACIAL_TRAITS));
            backgroundTab.setToolProficiencies(deserialize(jsonObject, context, Keys.TOOL_PROFICIENCIES));
            backgroundTab.setWeaponProficiencies(deserialize(jsonObject, context, Keys.WEAPON_PROFICIENCIES));
            backgroundTab.setAlignment(deserialize(jsonObject, context, Keys.ALIGNMENT));
            backgroundTab.setEyes(deserialize(jsonObject, context, Keys.EYES));
            backgroundTab.setGender(deserialize(jsonObject, context, Keys.GENDER));
            backgroundTab.setHair(deserialize(jsonObject, context, Keys.HAIR));
            backgroundTab.setHeight(deserialize(jsonObject, context, Keys.HEIGHT));
            backgroundTab.setSize(deserialize(jsonObject, context, Keys.SIZE));
            backgroundTab.setVision(deserialize(jsonObject, context, Keys.VISION));
            return backgroundTab;
        }

        @Override
        public JsonElement serialize(BackgroundTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.WEIGHT_DOUBLE, src.getWeight());
            serialize(jsonObject, context, Keys.AGE, src.getAge());
            serialize(jsonObject, context, Keys.ARMOR_PROFICIENCIES, src.getArmorProficiencies());
            serialize(jsonObject, context, Keys.BACKGROUND, src.getBackground());
            serialize(jsonObject, context, Keys.BONDS, src.getBonds());
            serialize(jsonObject, context, Keys.FLAWS, src.getFlaws());
            serialize(jsonObject, context, Keys.IDEALS, src.getIdeals());
            serialize(jsonObject, context, Keys.LANGUAGES, src.getLanguages());
            serialize(jsonObject, context, Keys.OTHER_NOTES, src.getOtherNotes());
            serialize(jsonObject, context, Keys.PERSONALITY_TRAITS, src.getPersonalityTraits());
            serialize(jsonObject, context, Keys.RACIAL_TRAITS, src.getRacialTraits());
            serialize(jsonObject, context, Keys.TOOL_PROFICIENCIES, src.getToolProficiencies());
            serialize(jsonObject, context, Keys.WEAPON_PROFICIENCIES, src.getWeaponProficiencies());
            serialize(jsonObject, context, Keys.ALIGNMENT, src.getAlignment());
            serialize(jsonObject, context, Keys.EYES, src.getEyes());
            serialize(jsonObject, context, Keys.GENDER, src.getGender());
            serialize(jsonObject, context, Keys.HAIR, src.getHair());
            serialize(jsonObject, context, Keys.HEIGHT, src.getHeight());
            serialize(jsonObject, context, Keys.SIZE, src.getSize());
            serialize(jsonObject, context, Keys.VISION, src.getVision());
            return jsonObject;
        }
    }
}
