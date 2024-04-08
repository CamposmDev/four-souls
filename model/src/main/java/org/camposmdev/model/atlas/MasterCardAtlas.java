package org.camposmdev.model.atlas;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.bonus_soul.BonusSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;

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
        character.forEach((key, value) -> obj1.put(key, value.toJSON()));
        root.put(CardType.CHARACTER.key(), obj1);
        var obj2 = new JsonObject();
        eternal.forEach((key, value) -> obj2.put(key, value.toJSON()));
        root.put(CardType.ETERNAL.key(), obj2);
        var obj3 = new JsonObject();
        root.put(CardType.TREASURE.key(), obj3);
        var obj4 = new JsonObject();
        root.put(CardType.MONSTER.key(), obj4);
        var obj5 = new JsonObject();
        loot.forEach((key,value) -> obj5.put(key, value.toJSON()));
        root.put(CardType.LOOT.key(), obj5);
        var obj6 = new JsonObject();
        soul.forEach((key, value) -> obj6.put(key, value.toJSON()));
        root.put(CardType.BSOUL.key(), obj6);
        var obj7 = new JsonObject();
        root.put(CardType.ROOM.key(), obj7);
        return root;
    }

    @Override
    public String toString() {
        return toJSON().encodePrettily();
    }
}
