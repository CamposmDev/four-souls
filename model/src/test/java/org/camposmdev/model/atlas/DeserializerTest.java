package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camposmdev.model.card.attribute.CardSet;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CounterType;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.eternal.ActiveEternalCard;
import org.camposmdev.model.card.eternal.SoulEternalCard;
import org.camposmdev.model.card.loot.MoneyCard;
import org.camposmdev.model.card.monster.GoodEventCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeserializerTest {
    private static ObjectMapper mapper;
    private static String bsoul_json;
    private static String gevent_json;
    private static String eternal_card_atlas_json;
    private static String master_card_atlas_json;

    @BeforeAll
    static void init() throws JsonProcessingException {
        mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addSerializer(EternalCardAtlas.class, new EternalCardAtlasSerializer());
        module.addSerializer(LootCardAtlas.class, new LootCardAtlasSerializer());
        module.addSerializer(MasterCardAtlas.class, new MasterCardAtlasSerializer());
        module.addSerializer(MonsterCardAtlas.class, new MonsterCardAtlasSerializer());
        module.addSerializer(TreasureCardAtlas.class, new TreasureCardAtlasSerializer());
        module.addSerializer(MoneyCardAtlas.class, new MoneyCardAtlasSerializer());
        module.addDeserializer(EternalCardAtlas.class, new EternalCardAtlasDeserializer());
        module.addDeserializer(LootCardAtlas.class, new LootCardAtlasDeserializer());
        module.addDeserializer(MasterCardAtlas.class, new MasterCardAtlasDeserializer());
        module.addDeserializer(MonsterCardAtlas.class, new MonsterCardAtlasDeserializer());
        module.addDeserializer(TreasureCardAtlas.class, new TreasureCardAtlasDeserializer());
        module.addDeserializer(MoneyCardAtlas.class, new MoneyCardAtlasDeserializer());
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        /* serialize BonusSoulCard */
        var bsoulCard = new BonusSoulCard();
        bsoulCard.setId("b-card");
        bsoulCard.setImage(null);
        bsoulCard.setCardSet(CardSet.BASE_V1);
        bsoulCard.setEnvy(true).setCounter(true).setCounterType(CounterType.PLAYER_DIED);
        bsoul_json = mapper.writeValueAsString(bsoulCard);
        /* serialize GoodEventCard */
        var geventCard = new GoodEventCard();
        geventCard.setAmbush((byte) 2);
        gevent_json = mapper.writeValueAsString(geventCard);
        /* serialize EternalCardAtlas */
        var eternalCardAtlas = new EternalCardAtlas();
        var c1 = new SoulEternalCard();
        c1.setId("hello");
        eternalCardAtlas.add(c1);
        eternal_card_atlas_json = mapper.writeValueAsString(eternalCardAtlas);
        /* serialize MasterCardAtlas */
        var master = new MasterCardAtlas();
        var c2 = new SoulEternalCard();
        c2.setId("world");
        c2.setCardSet(CardSet.BASE_V1);
        master.add(c2);
        var m1 = new MoneyCard();
        m1.setId("m1-card");
        m1.setCardType(CardType.MONEY10C);
        m1.setValue((byte)10);
        master.add(m1);
        master_card_atlas_json = mapper.writeValueAsString(master);
    }

    @Test
    void testBonusSoulCard() throws JsonProcessingException {
        var card = mapper.readValue(bsoul_json, BonusSoulCard.class);
        assertEquals("b-card", card.getId());
        assertNull(card.getImage());
        assertEquals(CardSet.BASE_V1, card.getCardSet());
        assertTrue(card.isEnvy());
        assertEquals(CounterType.PLAYER_DIED, card.getCounterType());
    }

    @Test
    void testActiveEternalCard() throws JsonProcessingException {
        var card = new ActiveEternalCard();
        card.setId("aeternal-card");
        card.setImage(new ImageInfo("originURL", "url1", "url2", "path/to/dir"));
        var expected = mapper.writeValueAsString(card);
        var actual = mapper.writeValueAsString(mapper.readValue(expected, ActiveEternalCard.class));
        assertEquals(expected, actual);
    }

    @Test
    void testGoodEventCard() throws JsonProcessingException {
        var card = mapper.readValue(gevent_json, GoodEventCard.class);
        var actual = mapper.writeValueAsString(card);
        var expected = gevent_json;
        assertEquals(expected, actual);
    }

    @Test
    void testEternalCardAtlas() throws JsonProcessingException {
        var atlas = mapper.readValue(eternal_card_atlas_json, EternalCardAtlas.class);
        var actual = mapper.writeValueAsString(atlas);
        var expected = eternal_card_atlas_json;
        assertEquals(expected, actual);
    }

    @Test
    void testMasterCardAtlas() throws JsonProcessingException {
        var atlas = mapper.readValue(master_card_atlas_json, MasterCardAtlas.class);
        var actual = mapper.writeValueAsString(atlas);
        var expected = master_card_atlas_json;
        assertEquals(expected, actual);
    }
}
