package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EternalCardAtlasSerializer extends JsonSerializer<EternalCardAtlas> {
    @Override
    public void serialize(EternalCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        if (value.aeternal.isEmpty()) {
            gen.writeFieldName("aeternal");
            gen.writeStartObject();
            gen.writeEndObject();
        } else
            gen.writeObjectField("aeternal", value.aeternal);
        if (value.paideternal.isEmpty()) {
            gen.writeFieldName("paideternal");
            gen.writeStartObject();
            gen.writeEndObject();
        } else
            gen.writeObjectField("paideternal", value.paideternal);
        if (value.peternal.isEmpty()) {
            gen.writeFieldName("peternal");
            gen.writeStartObject();
            gen.writeEndObject();
        } else
            gen.writeObjectField("peternal", value.peternal);
        if (value.seternal.isEmpty()) {
            gen.writeFieldName("seternal");
            gen.writeStartObject();
            gen.writeEndObject();
        } else
            gen.writeObjectField("seternal", value.seternal);
        gen.writeEndObject();
    }
}
