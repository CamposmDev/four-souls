package org.camposmdev.model.atlas;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.bonus_soul.BonusSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.*;
import org.camposmdev.model.card.extra.ExtraCard;
import org.camposmdev.model.card.loot.*;
import org.camposmdev.model.card.monster.AbstractMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.HashMap;
import java.util.Map;
public class MasterCardAtlas {
    private final Map<String, BonusSoulCard> bsoul;
    private final Map<String, CharacterCard> character;
    private final EternalCardAtlas eternal;
    private final Map<String, ExtraCard> extra;
    private final LootCardAtlas loot;
    private final MonsterCardAtlas monster;
    private final Map<String, RoomCard> room;
    private final TreasureCardAtlas treasure;

    public MasterCardAtlas() {
        this.bsoul = new HashMap<>();
        this.character = new HashMap<>();
        this.eternal = new EternalCardAtlas();
        this.extra = new HashMap<>();
        this.loot = new LootCardAtlas();
        this.monster = new MonsterCardAtlas();
        this.room = new HashMap<>();
        this.treasure = new TreasureCardAtlas();
    }

    public void add(BonusSoulCard card) {
        bsoul.put(card.id(), card);
    }
    public void add(CharacterCard card) {
        character.put(card.id(), card);
    }
    public void add(EternalCard card) {
        eternal.add(card);
    }
    public void add(ExtraCard card) {
        extra.put(card.id(), card);
    }
    public void add(LootCard card) {
        loot.add(card);
    }
    public void add(AbstractMonsterCard card) {
        monster.add(card);
    }
    public void add(RoomCard card) {
        room.put(card.id(), card);
    }
    public void add(TreasureCard card) {
        treasure.add(card);
    }

    public JsonObject toJSON() {
        var root = new JsonObject();
//        var obj1 = new JsonObject();
//        character.forEach((key, value) -> obj1.put(key, value.toJSON()));
//        root.put(CardType.CHARACTER.key(), obj1);
//        var obj2 = new JsonObject();
////        eternal.forEach((key, value) -> obj2.put(key, value.toJSON()));
//        root.put(CardType.ETERNAL.key(), obj2);
//        var obj3 = new JsonObject();
//        root.put(CardType.TREASURE.key(), obj3);
//        var obj4 = new JsonObject();
//        root.put(CardType.MONSTER.key(), obj4);
//        var obj5 = new JsonObject();
//        loot.forEach((key,value) -> obj5.put(key, value.toJSON()));
//        root.put(CardType.LOOT.key(), obj5);
//        var obj6 = new JsonObject();
//        bsoul.forEach((key, value) -> obj6.put(key, value.toJSON()));
//        root.put(CardType.BSOUL.key(), obj6);
//        var obj7 = new JsonObject();
//        root.put(CardType.ROOM.key(), obj7);
        return root;
    }

    @Override
    public String toString() {
        return toJSON().encodePrettily();
    }
}
