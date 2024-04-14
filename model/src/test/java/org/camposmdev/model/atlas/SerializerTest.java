package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.model.card.monster.GoodEventCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class SerializerTest {
    private static ObjectMapper mapper;
    @BeforeAll
    static void init() {
        mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addSerializer(EternalCardAtlas.class, new EternalCardAtlasSerializer());
        module.addSerializer(LootCardAtlas.class, new LootCardAtlasSerializer());
        module.addSerializer(MasterCardAtlas.class, new MasterCardAtlasSerializer());
        module.addSerializer(MonsterCardAtlas.class, new MonsterCardAtlasSerializer());
        module.addSerializer(TreasureCardAtlas.class, new TreasureCardAtlasSerializer());
        module.addSerializer(MoneyCardAtlas.class, new MoneyCardAtlasSerializer());
        mapper.registerModule(module);
    }

    @Test
    void testBonusSoulCard() throws JsonProcessingException {
        var card = new BonusSoulCard();
        card.setId("b-card");
        card.setImage(null);
        card.setCardSet(CardSet.BASE_V1);
        card.setCounterType(CounterType.UNDEFINED);
        String expected = "{\"id\":\"b-card\",\"image\":null,\"cardType\":\"BSOUL\",\"cardSet\":\"BASE_V1\",\"loot\":0,\"money\":0,\"guppyItems\":0,\"counter\":false,\"counterLimit\":0,\"counterType\":\"UNDEFINED\",\"envy\":false,\"sloth\":false,\"strawberry\":false}";
        String actual = mapper.writeValueAsString(card);
        assertEquals(expected, actual);
        JsonNode jsonNode = mapper.readTree(actual);
        Field[] fields = BonusSoulCard.class.getFields();
        for (Field field : fields) {
            var name = field.getName();
            assertTrue(jsonNode.has(name));
        }
    }

    @Test
    void testActiveEternalCard() throws JsonProcessingException {
        var card = new ActiveEternalCard();
        card.setId("aeternal-card");
        card.setCardSet(CardSet.BASE_V1);
        card.setImage(null);
        String actual = mapper.writeValueAsString(card);
        JsonNode jsonNode = mapper.readTree(actual);
        Field[] fields = ActiveEternalCard.class.getFields();
        for (Field field : fields) {
            var name = field.getName();
            assertTrue(jsonNode.has(name));
        }
    }

    @Test
    void testGoodEventCard() throws JsonProcessingException {
        var card = new GoodEventCard();
        String actual = mapper.writeValueAsString(card);
        JsonNode jsonNode = mapper.readTree(actual);
        Field[] fields = GoodEventCard.class.getFields();
        for (Field field : fields) {
            var name = field.getName();
            assertTrue(jsonNode.has(name));
        }
    }

    @Test
    void testReward() throws JsonProcessingException {
        Reward reward = new Reward(0,0,0,0,true, false, false, false);
        String actual = mapper.writeValueAsString(reward);
        JsonNode jsonNode = mapper.readTree(actual);
        Field[] fields = Reward.class.getFields();
        for (Field field : fields) {
            var name = field.getName();
            assertTrue(jsonNode.has(name));
        }
        assertEquals(reward.rechargeItem(), jsonNode.get("rechargeItem").asBoolean());
    }

    @Test
    void testRollListener() throws JsonProcessingException {
        var listener = new RollListener(RollType.ANY, (byte) 0, null, (byte) 0, (byte) 0, (byte) 0,
                (byte)0, (byte)0, EntityTarget.UNDEFINED, false, (byte)0, (byte)0, false,
                DeckType.ANY, (byte)0, (byte)0, false, (byte)0);
        String actual = mapper.writeValueAsString(listener);
        JsonNode jsonNode = mapper.readTree(actual);
        Field[] fields = RollListener.class.getFields();
        for (Field field : fields) {
            var name = field.getName();
            assertTrue(jsonNode.has(name));
        }
        assertEquals(listener.type().name(), jsonNode.get("type").asText());
        assertEquals(listener.damageTo().name(), jsonNode.get("damageTo").asText());
        assertEquals(listener.peekDeck().name(), jsonNode.get("peekDeck").asText());
    }
}
