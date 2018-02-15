package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.BACKGROUND_TAB, typeAdapter = BackgroundTab.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<BackgroundTab>, JsonSerializer<BackgroundTab> {

        @Override
        public BackgroundTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            BackgroundTab backgroundTab = new BackgroundTab();
            Keys.WEIGHT_DOUBLE.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setWeight);
            Keys.AGE.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setWeight);
            Keys.ARMOR_PROFICIENCIES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setArmorProficiencies);
            Keys.BACKGROUND.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setBackground);
            Keys.BONDS.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setBonds);
            Keys.FLAWS.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setFlaws);
            Keys.IDEALS.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setIdeals);
            Keys.LANGUAGES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setLanguages);
            Keys.OTHER_NOTES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setOtherNotes);
            Keys.PERSONALITY_TRAITS.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setPersonalityTraits);
            Keys.RACIAL_TRAITS.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setRacialTraits);
            Keys.TOOL_PROFICIENCIES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setToolProficiencies);
            Keys.WEAPON_PROFICIENCIES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setWeaponProficiencies);
            Keys.ALIGNMENT.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setAlignment);
            Keys.EYES.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setEyes);
            Keys.GENDER.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setGender);
            Keys.HAIR.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setHair);
            Keys.HEIGHT.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setHeight);
            Keys.SIZE.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setSize);
            Keys.VISION.deserializeFromParent(jsonObject, context).ifPresent(backgroundTab::setVision);
            return backgroundTab;
        }

        @Override
        public JsonElement serialize(BackgroundTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.WEIGHT_DOUBLE.serialize(src.getWeight(), jsonObject, context);
            Keys.AGE.serialize(src.getAge(), jsonObject, context);
            Keys.ARMOR_PROFICIENCIES.serialize(src.getArmorProficiencies(), jsonObject, context);
            Keys.BACKGROUND.serialize(src.getBackground(), jsonObject, context);
            Keys.BONDS.serialize(src.getBonds(), jsonObject, context);
            Keys.FLAWS.serialize(src.getFlaws(), jsonObject, context);
            Keys.IDEALS.serialize(src.getIdeals(), jsonObject, context);
            Keys.LANGUAGES.serialize(src.getLanguages(), jsonObject, context);
            Keys.OTHER_NOTES.serialize(src.getOtherNotes(), jsonObject, context);
            Keys.PERSONALITY_TRAITS.serialize(src.getPersonalityTraits(), jsonObject, context);
            Keys.RACIAL_TRAITS.serialize(src.getRacialTraits(), jsonObject, context);
            Keys.TOOL_PROFICIENCIES.serialize(src.getToolProficiencies(), jsonObject, context);
            Keys.WEAPON_PROFICIENCIES.serialize(src.getWeaponProficiencies(), jsonObject, context);
            Keys.ALIGNMENT.serialize(src.getAlignment(), jsonObject, context);
            Keys.EYES.serialize(src.getEyes(), jsonObject, context);
            Keys.GENDER.serialize(src.getGender(), jsonObject, context);
            Keys.HAIR.serialize(src.getHair(), jsonObject, context);
            Keys.HEIGHT.serialize(src.getHeight(), jsonObject, context);
            Keys.SIZE.serialize(src.getSize(), jsonObject, context);
            Keys.VISION.serialize(src.getVision(), jsonObject, context);
            return jsonObject;
        }
    }
}
