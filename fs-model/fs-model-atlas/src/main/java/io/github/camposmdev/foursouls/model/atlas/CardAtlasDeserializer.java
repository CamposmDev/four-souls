package io.github.camposmdev.foursouls.model.atlas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class CardAtlasDeserializer<T> extends JsonDeserializer<T> {
    public <A> Map<String, A> deserializeMap(JsonNode node, ObjectMapper mapper, Class<A> clazz) throws JsonProcessingException {
        Map<String, A> map = new HashMap<>();
        if (!node.isObject()) {
            throw new IllegalArgumentException("Invalid JSON node type. Expected an object");
        }
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            JsonNode valueNode = entry.getValue();
            if (valueNode != null && !valueNode.isNull() && !valueNode.isEmpty()) {
                A value = mapper.treeToValue(valueNode, clazz);
                map.put(key, value);
            }
        }
        return map;
    }

    public boolean isEmptyMap(@NotNull JsonNode node) {
        if (node.isObject()) {
            return !node.fields().hasNext();
        }
        return false;
    }
}
