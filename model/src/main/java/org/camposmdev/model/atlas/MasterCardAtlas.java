package org.camposmdev.model.atlas;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.*;

import java.util.Map;
public record MasterCardAtlas(
        Map<String, CharacterCard> character,
        Map<String, EternalCard> eternal,
        Map<String, LootCard> loot,
        Map<String, BonusSoulCard> soul
) {
    public void addCharacter(CharacterCard card) {
        character.put(card.id(), card);
    }

    public void addEternal(EternalCard card) {
        eternal.put(card.id(), card);
    }

    public void addLoot(LootCard card) {
        loot.put(card.id(), card);
    }

    public void addSoul(BonusSoulCard card) {
        soul.put(card.id(), card);
    }

    public JsonObject toJSON() {
        var root = new JsonObject();
        var obj1 = new JsonObject();
        character.forEach((key, value) ->
                obj1.put(key, value.toJSON()));
        root.put(CardType.CHARACTER.name(), obj1);
        var obj2 = new JsonObject();
        eternal.forEach((key, value) -> {
            if (obj2.containsKey(value.type().name()))
                obj2.getJsonObject(value.type().name())
                        .put(key, value.toJSON());
            else
                obj2.put(value.type().name(),
                        JsonObject.of(key, value.toJSON()));
        });
        root.put(CardType.ETERNAL.name(), obj2);
        var obj3 = new JsonObject();
        root.put(CardType.TREASURE.name(), obj3);
        var obj4 = new JsonObject();
        root.put(CardType.MONSTER.name(), obj4);
        var obj5 = new JsonObject();
        loot.forEach((key,value) -> {
            if (obj3.containsKey(value.type().name()))
                obj3.getJsonObject(value.type().name()).put(key, value.toJSON());
            else
                obj3.put(value.type().name(),
                    JsonObject.of(key, value.toJSON()));
        });
        root.put(CardType.LOOT.name(), obj5);
        var obj6 = new JsonObject();
        soul.forEach((key, value) -> {
            obj6.put(key, value);
        });
        root.put(CardType.BSOUL.name(), obj6);
        var obj7 = new JsonObject();
        root.put(CardType.ROOM.name(), obj7);
        return root;
    }

    @Override
    public String toString() {
        return toJSON().encodePrettily();
    }
}
