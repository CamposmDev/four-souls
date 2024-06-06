package io.github.camposmdev.foursouls.core.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TreasureCardAtlasSerializer extends JsonSerializer<TreasureCardAtlas> {
    @Override
    public void serialize(TreasureCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("atreasure", value.atreasure);
        gen.writeObjectField("otreasure", value.otreasure);
        gen.writeObjectField("paidtreasure", value.paidtreasure);
        gen.writeObjectField("ptreasure", value.ptreasure);
        gen.writeObjectField("streasure", value.streasure);
        gen.writeEndObject();
    }
}
