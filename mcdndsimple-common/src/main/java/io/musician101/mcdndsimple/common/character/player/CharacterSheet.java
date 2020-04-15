package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

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

    public static class Serializer extends BaseSerializer<CharacterSheet> {

        @Override
        public CharacterSheet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CharacterSheet characterSheet = new CharacterSheet();
            characterSheet.setArmorTab(deserialize(jsonObject, context, Keys.ARMOR_TAB));;
            characterSheet.setBackgroundTab(deserialize(jsonObject, context, Keys.BACKGROUND_TAB));;
            characterSheet.setClassTab(deserialize(jsonObject, context, Keys.CLASS_TAB));;
            characterSheet.setCoreStatsTab(deserialize(jsonObject, context, Keys.CORE_STATS_TAB));;
            characterSheet.setInventoryTab(deserialize(jsonObject, context, Keys.INVENTORY_TAB));;
            characterSheet.setSkillsTab(deserialize(jsonObject, context, Keys.SKILLS_TAB));;
            characterSheet.setSpellbookTab(deserialize(jsonObject, context, Keys.SPELLBOOK_TAB));;
            characterSheet.setWeaponsTab(deserialize(jsonObject, context, Keys.WEAPONS_TAB));;
            return characterSheet;
        }

        @Override
        public JsonElement serialize(CharacterSheet src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ARMOR_TAB, src.getArmorTab());
            serialize(jsonObject, context, Keys.BACKGROUND_TAB, src.getBackgroundTab());
            serialize(jsonObject, context, Keys.CLASS_TAB, src.getClassTab());
            serialize(jsonObject, context, Keys.CORE_STATS_TAB, src.getCoreStatsTab());
            serialize(jsonObject, context, Keys.INVENTORY_TAB, src.getInventoryTab());
            serialize(jsonObject, context, Keys.SKILLS_TAB, src.getSkillsTab());
            serialize(jsonObject, context, Keys.SPELLBOOK_TAB, src.getSpellbookTab());
            serialize(jsonObject, context, Keys.WEAPONS_TAB, src.getWeaponsTab());
            return jsonObject;
        }
    }
}
