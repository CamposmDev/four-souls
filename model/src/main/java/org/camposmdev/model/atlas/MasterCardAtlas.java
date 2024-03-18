package org.camposmdev.model.atlas;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.CardType;
import org.camposmdev.model.card.CharacterCard;
import org.camposmdev.model.card.EternalCard;

import java.util.Map;
import java.util.TreeMap;

public record MasterCardAtlas(
        Map<String, CharacterCard> character,
        Map<String, EternalCard> eternal
) {
    public void addCharacter(CharacterCard card) {
        character.put(card.id(), card);
    }

    public void addEternal(EternalCard card) {
        eternal.put(card.id(), card);
    }

    public JsonObject toJSON() {
        var root = new JsonObject();
        var obj1 = new JsonObject();
        character.forEach((key, value) ->
                obj1.put(key, value.toJSON()));
        var obj2 = new JsonObject();
        eternal.forEach((key, value) -> {
            if (obj2.containsKey(value.type().name()))
                obj2.getJsonObject(value.type().name())
                        .put(key, value.toJSON());
            else
                obj2.put(value.type().name(),
                        JsonObject.of(key, value.toJSON()));
        });
        root.put(CardType.CHARACTER.name(), obj1);
        root.put(CardType.ETERNAL.name(), obj2);
//        root.put(CardType.TREASURE.name(), obj3);
//        root.put(CardType.MONSTER.name(), obj4);
//        root.put(CardType.LOOT.name(), obj5);
//        root.put(CardType.BSOUL.name(), obj6);
//        root.put(CardType.ROOM.name(), obj7);
        return root;
    }

    @Override
    public String toString() {
        return toJSON().encodePrettily();
    }
}
