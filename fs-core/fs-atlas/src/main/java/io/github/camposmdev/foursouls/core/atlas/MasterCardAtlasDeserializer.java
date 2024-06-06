package io.github.camposmdev.foursouls.core.atlas;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.camposmdev.foursouls.core.card.bsoul.BonusSoulCard;
import io.github.camposmdev.foursouls.core.card.character.CharacterCard;
import io.github.camposmdev.foursouls.core.card.outside.OutsideCard;
import io.github.camposmdev.foursouls.core.card.room.RoomCard;

import java.io.IOException;

public class MasterCardAtlasDeserializer extends CardAtlasDeserializer<MasterCardAtlas> {

    @Override
    public MasterCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(EternalCardAtlas.class, new EternalCardAtlasDeserializer());
        module.addDeserializer(LootCardAtlas.class, new LootCardAtlasDeserializer());
        module.addDeserializer(MonsterCardAtlas.class, new MonsterCardAtlasDeserializer());
        module.addDeserializer(TreasureCardAtlas.class, new TreasureCardAtlasDeserializer());
        module.addDeserializer(MoneyCardAtlas.class, new MoneyCardAtlasDeserializer());
        mapper.registerModule(module);
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var obj = new MasterCardAtlas();
        var bsoul = node.get("bsoul");
        var character = node.get("character");
        var eternal = node.get("eternal");
        var outside = node.get("outside");
        var loot = node.get("loot");
        var monster = node.get("monster");
        var room = node.get("room");
        var treasure = node.get("treasure");
        if (!isEmptyMap(bsoul))
            obj.bsoul = deserializeMap(bsoul, mapper, BonusSoulCard.class);
        if (!isEmptyMap(character))
            obj.character = deserializeMap(character, mapper, CharacterCard.class);
        if (!eternal.isEmpty())
            obj.eternal = mapper.treeToValue(eternal, EternalCardAtlas.class);
        if (!isEmptyMap(outside))
            obj.outside = deserializeMap(outside, mapper, OutsideCard.class);
        if (!loot.isEmpty())
            obj.loot = mapper.treeToValue(loot, LootCardAtlas.class);
        if (!isEmptyMap(monster))
            obj.monster = mapper.treeToValue(monster, MonsterCardAtlas.class);
        if (!isEmptyMap(room))
            obj.room = deserializeMap(room, mapper, RoomCard.class);
        if (!isEmptyMap(treasure))
            obj.treasure = mapper.treeToValue(treasure, TreasureCardAtlas.class);
        return obj;
    }
}
