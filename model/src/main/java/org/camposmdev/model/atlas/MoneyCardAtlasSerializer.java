package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MoneyCardAtlasSerializer extends JsonSerializer<MoneyCardAtlas> {
    @Override
    public void serialize(MoneyCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("money1c", value.money1c);
        gen.writeObjectField("money2c", value.money2c);
        gen.writeObjectField("money3c", value.money3c);
        gen.writeObjectField("money4c", value.money4c);
        gen.writeObjectField("money5c", value.money5c);
        gen.writeObjectField("money10c", value.money10c);
        gen.writeEndObject();
    }
}
