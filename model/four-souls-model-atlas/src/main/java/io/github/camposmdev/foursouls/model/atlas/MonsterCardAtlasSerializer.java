package io.github.camposmdev.foursouls.model.atlas;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MonsterCardAtlasSerializer extends JsonSerializer<MonsterCardAtlas> {
    @Override
    public void serialize(MonsterCardAtlas value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("bmonster", value.bmonster);
        gen.writeObjectField("cmonster", value.cmonster);
        gen.writeObjectField("hmonster", value.hmonster);
        gen.writeObjectField("chamonster", value.chamonster);
        gen.writeObjectField("gevent", value.gevent);
        gen.writeObjectField("bevent", value.bevent);
        gen.writeObjectField("curse", value.curse);
        gen.writeObjectField("boss", value.boss);
        gen.writeObjectField("epic", value.epic);
        gen.writeEndObject();
    }
}
