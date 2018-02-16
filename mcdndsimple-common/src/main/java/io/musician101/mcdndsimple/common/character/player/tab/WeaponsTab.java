package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.WEAPONS_TAB, typeAdapter = WeaponsTab.Serializer.class)
public class WeaponsTab {

    @Nonnull
    private List<MeleeWeapon> meleeWeapons = new ArrayList<>();
    @Nonnull
    private List<RangedWeapon> rangedWeapons = new ArrayList<>();

    public void addMeleeWeapon(@Nonnull MeleeWeapon meleeWeapon) {
        meleeWeapons.add(meleeWeapon);
    }

    public void addRangedWeapon(@Nonnull RangedWeapon rangedWeapon) {
        rangedWeapons.add(rangedWeapon);
    }

    @Nonnull
    public List<MeleeWeapon> getMeleeWeapons() {
        return meleeWeapons;
    }

    public void setMeleeWeapons(@Nonnull List<MeleeWeapon> meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    @Nonnull
    public List<RangedWeapon> getRangedWeapons() {
        return rangedWeapons;
    }

    public void setRangedWeapons(@Nonnull List<RangedWeapon> rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public void removeMeleeWeapon(@Nonnull MeleeWeapon meleeWeapon) {
        meleeWeapons.remove(meleeWeapon);
    }

    public void removeRangedWeapon(@Nonnull RangedWeapon rangedWeapon) {
        rangedWeapons.remove(rangedWeapon);
    }

    public static class Serializer implements JsonDeserializer<WeaponsTab>, JsonSerializer<WeaponsTab> {

        @Override
        public WeaponsTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            WeaponsTab weaponsTab = new WeaponsTab();
            Keys.MELEE_WEAPONS.deserializeFromParent(jsonObject, context).ifPresent(weaponsTab::setMeleeWeapons);
            Keys.RANGED_WEAPONS.deserializeFromParent(jsonObject, context).ifPresent(weaponsTab::setRangedWeapons);
            return weaponsTab;
        }

        @Override
        public JsonElement serialize(WeaponsTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.MELEE_WEAPONS.serialize(src.getMeleeWeapons(), jsonObject, context);
            Keys.RANGED_WEAPONS.serialize(src.getRangedWeapons(), jsonObject, context);
            return jsonObject;
        }
    }
}
