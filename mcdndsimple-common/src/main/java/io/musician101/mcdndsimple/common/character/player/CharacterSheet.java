package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.CHARACTER_SHEET, typeAdapter = CharacterSheet.Serializer.class)
public class CharacterSheet {

    @Nonnull
    private ArmorTab armorTab = new ArmorTab();
    @Nonnull
    private BackgroundTab backgroundTab = new BackgroundTab();
    @Nonnull
    private ClassTab classTab = new ClassTab();
    @Nonnull
    private CoreStatsTab coreStatsTab = new CoreStatsTab();
    @Nonnull
    private InventoryTab inventoryTab = new InventoryTab();
    @Nonnull
    private SkillsTab skillsTab = new SkillsTab();
    @Nonnull
    private SpellbookTab spellbookTab = new SpellbookTab();
    @Nonnull
    private WeaponsTab weaponsTab = new WeaponsTab();

    @Nonnull
    public ArmorTab getArmorTab() {
        return armorTab;
    }

    private void setArmorTab(@Nonnull ArmorTab armorTab) {
        this.armorTab = armorTab;
    }

    @Nonnull
    public BackgroundTab getBackgroundTab() {
        return backgroundTab;
    }

    private void setBackgroundTab(@Nonnull BackgroundTab backgroundTab) {
        this.backgroundTab = backgroundTab;
    }

    @Nonnull
    public ClassTab getClassTab() {
        return classTab;
    }

    private void setClassTab(@Nonnull ClassTab classTab) {
        this.classTab = classTab;
    }

    @Nonnull
    public CoreStatsTab getCoreStatsTab() {
        return coreStatsTab;
    }

    private void setCoreStatsTab(@Nonnull CoreStatsTab coreStatsTab) {
        this.coreStatsTab = coreStatsTab;
    }

    @Nonnull
    public InventoryTab getInventoryTab() {
        return inventoryTab;
    }

    private void setInventoryTab(@Nonnull InventoryTab inventoryTab) {
        this.inventoryTab = inventoryTab;
    }

    @Nonnull
    public SkillsTab getSkillsTab() {
        return skillsTab;
    }

    private void setSkillsTab(@Nonnull SkillsTab skillsTab) {
        this.skillsTab = skillsTab;
    }

    @Nonnull
    public SpellbookTab getSpellbookTab() {
        return spellbookTab;
    }

    private void setSpellbookTab(@Nonnull SpellbookTab spellbookTab) {
        this.spellbookTab = spellbookTab;
    }

    @Nonnull
    public WeaponsTab getWeaponsTab() {
        return weaponsTab;
    }

    private void setWeaponsTab(@Nonnull WeaponsTab weaponsTab) {
        this.weaponsTab = weaponsTab;
    }

    public static class Serializer implements JsonDeserializer<CharacterSheet>, JsonSerializer<CharacterSheet> {

        @Override
        public CharacterSheet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CharacterSheet characterSheet = new CharacterSheet();
            JsonKeyProcessor.<JsonObject, ArmorTab>getJsonKey(Keys.ARMOR_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setArmorTab));
            JsonKeyProcessor.<JsonObject, BackgroundTab>getJsonKey(Keys.BACKGROUND_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setBackgroundTab));
            JsonKeyProcessor.<JsonObject, ClassTab>getJsonKey(Keys.CLASS_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setClassTab));
            JsonKeyProcessor.<JsonObject, CoreStatsTab>getJsonKey(Keys.CORE_STATS_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setCoreStatsTab));
            JsonKeyProcessor.<JsonObject, InventoryTab>getJsonKey(Keys.INVENTORY_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setInventoryTab));
            JsonKeyProcessor.<JsonObject, SkillsTab>getJsonKey(Keys.SKILLS_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setSkillsTab));
            JsonKeyProcessor.<JsonObject, SpellbookTab>getJsonKey(Keys.SPELLBOOK_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setSpellbookTab));
            JsonKeyProcessor.<JsonObject, WeaponsTab>getJsonKey(Keys.WEAPONS_TAB).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(characterSheet::setWeaponsTab));
            return characterSheet;
        }

        @Override
        public JsonElement serialize(CharacterSheet src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, ArmorTab>getJsonKey(Keys.ARMOR_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getArmorTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, BackgroundTab>getJsonKey(Keys.BACKGROUND_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getBackgroundTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, ClassTab>getJsonKey(Keys.CLASS_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getClassTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, CoreStatsTab>getJsonKey(Keys.CORE_STATS_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getCoreStatsTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, InventoryTab>getJsonKey(Keys.INVENTORY_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getInventoryTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SkillsTab>getJsonKey(Keys.SKILLS_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getSkillsTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellbookTab>getJsonKey(Keys.SPELLBOOK_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellbookTab(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, WeaponsTab>getJsonKey(Keys.WEAPONS_TAB).ifPresent(jsonKey -> jsonKey.serialize(src.getWeaponsTab(), jsonObject, context));
            return jsonObject;
        }
    }
}
