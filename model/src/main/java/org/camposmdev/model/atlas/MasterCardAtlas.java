package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.bsoul.BonusSoulCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.*;
import org.camposmdev.model.card.extra.OutsideCard;
import org.camposmdev.model.card.loot.*;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class MasterCardAtlas implements CardAtlas<BaseCard> {
    protected Map<String, BonusSoulCard> bsoul;
    protected Map<String, CharacterCard> character;
    protected EternalCardAtlas eternal;
    protected TreasureCardAtlas treasure;
    protected MonsterCardAtlas monster;
    protected LootCardAtlas loot;
    protected Map<String, RoomCard> room;
    protected Map<String, OutsideCard> outside;

    public MasterCardAtlas() {
        this.bsoul = new HashMap<>();
        this.character = new HashMap<>();
        this.eternal = new EternalCardAtlas();
        this.treasure = new TreasureCardAtlas();
        this.monster = new MonsterCardAtlas();
        this.loot = new LootCardAtlas();
        this.room = new HashMap<>();
        this.outside = new HashMap<>();
    }

    @Override
    public void add(BaseCard card) {
        switch (card.getCardType()) {
            case BSOUL -> bsoul.put(card.getId(), (BonusSoulCard) card);
            case CHARACTER -> character.put(card.getId(), (CharacterCard) card);
            case AETERNAL, OETERNAL, PAIDETERNAL, PETERNAL, SETERNAL -> eternal.add((EternalCard) card);
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE -> treasure.add((TreasureCard) card);
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC -> monster.add((BaseMonsterCard) card);
            case TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD, MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> loot.add((LootCard) card);
            case ROOM -> room.put(card.getId(), (RoomCard) card);
            case OUTSIDE -> outside.put(card.getId(), (OutsideCard) card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String key) {
        return switch (cardType) {
            case CHARACTER -> character.containsKey(key);
            case PETERNAL, AETERNAL, SETERNAL, OETERNAL, PAIDETERNAL ->
                eternal.contains(cardType, key);
            case PTREASURE, ATREASURE, PAIDTREASURE, OTREASURE, STREASURE ->
                treasure.contains(cardType, key);
            case BMONSTER, CMONSTER, HMONSTER, CHAMONSTER, GEVENT, BEVENT, CURSE, BOSS, EPIC ->
                monster.contains(cardType, key);
            case TRINKETS, PILLS, RUNES, BOMBS, BUTTER, BATTERIES, KEYS, DICE, SHEART, BHEART, SACK, LSOUL, WILDCARD, MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C ->
                loot.contains(cardType, key);
            case BSOUL ->
                bsoul.containsKey(key);
            case ROOM ->
                room.containsKey(key);
            case OUTSIDE ->
                outside.containsKey(key);
            default -> false;
        };
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addSerializer(EternalCardAtlas.class, new EternalCardAtlasSerializer());
        module.addSerializer(LootCardAtlas.class, new LootCardAtlasSerializer());
        module.addSerializer(MasterCardAtlas.class, new MasterCardAtlasSerializer());
        module.addSerializer(MonsterCardAtlas.class, new MonsterCardAtlasSerializer());
        module.addSerializer(TreasureCardAtlas.class, new TreasureCardAtlasSerializer());
        module.addSerializer(MoneyCardAtlas.class, new MoneyCardAtlasSerializer());
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static MasterCardAtlas deserialize(byte[] buffer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(MasterCardAtlas.class, new MasterCardAtlasDeserializer());
        module.addDeserializer(EternalCardAtlas.class, new EternalCardAtlasDeserializer());
        module.addDeserializer(LootCardAtlas.class, new LootCardAtlasDeserializer());
        module.addDeserializer(MonsterCardAtlas.class, new MonsterCardAtlasDeserializer());
        module.addDeserializer(TreasureCardAtlas.class, new TreasureCardAtlasDeserializer());
        module.addDeserializer(MoneyCardAtlas.class, new MoneyCardAtlasDeserializer());
        mapper.registerModule(module);
        return mapper.readValue(buffer, MasterCardAtlas.class);
    }
}
