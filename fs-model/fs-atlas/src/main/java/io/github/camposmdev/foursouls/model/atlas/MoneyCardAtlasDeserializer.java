package io.github.camposmdev.foursouls.model.atlas;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.loot.MoneyCard;

import java.io.IOException;

public class MoneyCardAtlasDeserializer extends CardAtlasDeserializer<MoneyCardAtlas> {
    @Override
    public MoneyCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var mapper = new ObjectMapper();
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var money1c = node.get("money1c");
        var money2c = node.get("money2c");
        var money3c = node.get("money3c");
        var money4c = node.get("money4c");
        var money5c = node.get("money5c");
        var money10c = node.get("money10c");
        var obj = new MoneyCardAtlas();
        if (!isEmptyMap(money1c))
            obj.money1c = deserializeMap(money1c, mapper, MoneyCard.class);
        if (!isEmptyMap(money2c))
            obj.money2c = deserializeMap(money2c, mapper, MoneyCard.class);
        if (!isEmptyMap(money3c))
            obj.money3c = deserializeMap(money3c, mapper, MoneyCard.class);
        if (!isEmptyMap(money4c))
            obj.money4c = deserializeMap(money4c, mapper, MoneyCard.class);
        if (!isEmptyMap(money5c))
            obj.money5c = deserializeMap(money5c, mapper, MoneyCard.class);
        if (!isEmptyMap(money10c))
            obj.money10c = deserializeMap(money10c, mapper, MoneyCard.class);
        return obj;
    }
}
