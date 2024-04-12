package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LootCardAtlasSerializer extends JsonSerializer<LootCardAtlas> {
    @Override
    public void serialize(LootCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("batteries", value.batteries);
        gen.writeObjectField("bheart", value.bheart);
        gen.writeObjectField("bomb", value.bomb);
        gen.writeObjectField("butter", value.butter);
        gen.writeObjectField("cards", value.cards);
        gen.writeObjectField("dice", value.dice);
        gen.writeObjectField("key", value.key);
        gen.writeObjectField("lsoul", value.lsoul);
        gen.writeObjectField("pill", value.pill);
        gen.writeObjectField("rune", value.rune);
        gen.writeObjectField("sack", value.sack);
        gen.writeObjectField("sheart", value.sheart);
        gen.writeObjectField("trinket", value.trinket);
        gen.writeObjectField("wild", value.wild);
        gen.writeObjectField("money", value.money);
        gen.writeEndObject();
    }
}
