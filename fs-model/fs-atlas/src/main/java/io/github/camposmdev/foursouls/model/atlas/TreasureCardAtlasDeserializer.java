package io.github.camposmdev.foursouls.model.atlas;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.treasure.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TreasureCardAtlasDeserializer extends JsonDeserializer<TreasureCardAtlas> {
    @Override
    public TreasureCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var mapper = new ObjectMapper();
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var atreasure = node.get("atreasure");
        var otreasure = node.get("otreasure");
        var paidtreasure = node.get("paidtreasure");
        var ptreasure = node.get("ptreasure");
        var streasure = node.get("streasure");
        var obj = new TreasureCardAtlas();
        if (atreasure.fields().hasNext()) {
            Iterator<Map.Entry<String, JsonNode>> fields = atreasure.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                var card = mapper.treeToValue(entry.getValue(), ActiveTreasureCard.class);
                obj.atreasure.put(entry.getKey(), card);
            }
        }
        if (otreasure.fields().hasNext()) {
            Iterator<Map.Entry<String, JsonNode>> fields = otreasure.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                var card = mapper.treeToValue(entry.getValue(), OneUseTreasureCard.class);
                obj.otreasure.put(entry.getKey(), card);
            }
        }
        if (paidtreasure.fields().hasNext()) {
            Iterator<Map.Entry<String, JsonNode>> fields = paidtreasure.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                var card = mapper.treeToValue(entry.getValue(), PaidTreasureCard.class);
                obj.paidtreasure.put(entry.getKey(), card);
            }
        }
        if (ptreasure.fields().hasNext()) {
            Iterator<Map.Entry<String, JsonNode>> fields = ptreasure.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                var card = mapper.treeToValue(entry.getValue(), PassiveTreasureCard.class);
                obj.ptreasure.put(entry.getKey(), card);
            }
        }
        if (streasure.fields().hasNext()) {
            Iterator<Map.Entry<String, JsonNode>> fields = streasure.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                var card = mapper.treeToValue(entry.getValue(), SoulTreasureCard.class);
                obj.streasure.put(entry.getKey(), card);
            }
        }

        return obj;
    }
}
