package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.model.card.eternal.PaidEternalCard;
import org.camposmdev.model.card.eternal.PassiveEternalCard;
import org.camposmdev.model.card.eternal.SoulEternalCard;

import java.io.IOException;

public class EternalCardAtlasDeserializer extends CardAtlasDeserializer<EternalCardAtlas> {
    @Override
    public EternalCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        var mapper = new ObjectMapper();
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var aeternal = node.get("aeternal");
        var paideternal = node.get("paideternal");
        var peternal = node.get("peternal");
        var seternal = node.get("seternal");
        var obj = new EternalCardAtlas();
        if (!isEmptyMap(aeternal))
            obj.aeternal = deserializeMap(aeternal, mapper, ActiveEternalCard.class);
        if (!isEmptyMap(paideternal))
            obj.paideternal = deserializeMap(paideternal, mapper, PaidEternalCard.class);
        if (!isEmptyMap(peternal))
            obj.peternal = deserializeMap(peternal, mapper, PassiveEternalCard.class);
        if (!isEmptyMap(seternal))
            obj.seternal = deserializeMap(seternal, mapper, SoulEternalCard.class);
        return obj;
    }
}
