package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MasterCardAtlasSerializer extends JsonSerializer<MasterCardAtlas> {
    @Override
    public void serialize(MasterCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("bsoul", value.bsoul);
        gen.writeObjectField("character", value.character);
        gen.writeObjectField("eternal", value.eternal);
        gen.writeObjectField("outside", value.outside);
        gen.writeObjectField("loot", value.loot);
        gen.writeObjectField("monster", value.monster);
        gen.writeObjectField("room", value.room);
        gen.writeObjectField("treasure", value.treasure);
        gen.writeEndObject();
    }
}
