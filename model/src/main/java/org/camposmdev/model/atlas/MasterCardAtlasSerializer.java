package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class MasterCardAtlasSerializer extends JsonSerializer<MasterCardAtlas> {
    @Override
    public void serialize(MasterCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("bsoul", value.bsoul);
        gen.writeObjectField("character", value.character);
        gen.writeObjectField("eternal", value.eternal);
        gen.writeObjectField("extra", value.extra);
        gen.writeObjectField("loot", value.loot);
        gen.writeObjectField("monster", value.monster);
        gen.writeObjectField("room", value.room);
        gen.writeObjectField("treasure", value.treasure);
        gen.writeEndObject();
    }
}
