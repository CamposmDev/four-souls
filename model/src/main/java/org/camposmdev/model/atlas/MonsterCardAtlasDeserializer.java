package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.monster.BadEventCard;
import org.camposmdev.model.card.monster.CurseCard;
import org.camposmdev.model.card.monster.GoodEventCard;
import org.camposmdev.model.card.monster.MonsterCard;

import java.io.IOException;

public class MonsterCardAtlasDeserializer extends CardAtlasDeserializer<MonsterCardAtlas> {
    @Override
    public MonsterCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        var mapper = new ObjectMapper();
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var bmonster = node.get("bmonster");
        var cmonster = node.get("cmonster");
        var hmonster = node.get("hmonster");
        var chamonster = node.get("chamonster");
        var gevent = node.get("gevent");
        var bevent = node.get("bevent");
        var curse = node.get("curse");
        var boss = node.get("boss");
        var epic = node.get("epic");
        var obj = new MonsterCardAtlas();
        if (!isEmptyMap(bmonster))
            obj.bmonster = deserializeMap(bmonster, mapper, MonsterCard.class);
        if (!isEmptyMap(cmonster))
            obj.cmonster = deserializeMap(cmonster, mapper, MonsterCard.class);
        if (!isEmptyMap(hmonster))
            obj.hmonster = deserializeMap(hmonster, mapper, MonsterCard.class);
        if (!isEmptyMap(chamonster))
            obj.chamonster = deserializeMap(chamonster, mapper, MonsterCard.class);
        if (!isEmptyMap(gevent))
            obj.gevent = deserializeMap(gevent, mapper, GoodEventCard.class);
        if (!isEmptyMap(bevent))
            obj.bevent = deserializeMap(bevent, mapper, BadEventCard.class);
        if (!isEmptyMap(curse))
            obj.curse = deserializeMap(curse, mapper, CurseCard.class);
        if (!isEmptyMap(boss))
            obj.boss = deserializeMap(boss, mapper, MonsterCard.class);
        if (!isEmptyMap(epic))
            obj.epic = deserializeMap(epic, mapper, MonsterCard.class);
        return obj;
    }
}
