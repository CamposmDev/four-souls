package org.camposmdev.model.atlas;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camposmdev.model.card.loot.*;

import java.io.IOException;

public class LootCardAtlasDeserializer extends CardAtlasDeserializer<LootCardAtlas> {
    @Override
    public LootCardAtlas deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var mapper = new ObjectMapper();
        var module = new SimpleModule("CardAtlasDataBind", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(MoneyCardAtlas.class, new MoneyCardAtlasDeserializer());
        mapper.registerModule(module);
        var codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        var batteries = node.get("batteries");
        var bheart = node.get("bheart");
        var bomb = node.get("bomb");
        var butter = node.get("butter");
        var cards = node.get("cards");
        var dice = node.get("dice");
        var key = node.get("key");
        var lsoul = node.get("lsoul");
        var pill = node.get("pill");
        var rune = node.get("rune");
        var sack = node.get("sack");
        var sheart = node.get("sheart");
        var trinket = node.get("trinket");
        var wild = node.get("wild");
        var money = node.get("money");
        var obj = new LootCardAtlas();
        if (!isEmptyMap(batteries))
            obj.batteries = deserializeMap(batteries, mapper, BatteryCard.class);
        if (!isEmptyMap(bheart))
            obj.bheart = deserializeMap(bheart, mapper, BlackHeartCard.class);
        if (!isEmptyMap(bomb))
            obj.bomb = deserializeMap(bomb, mapper, BombCard.class);
        if (!isEmptyMap(butter))
            obj.butter = deserializeMap(butter, mapper, ButterBeanCard.class);
        if (!isEmptyMap(cards))
            obj.cards = deserializeMap(cards, mapper, WildCard.class);
        if (!isEmptyMap(dice))
            obj.dice = deserializeMap(dice, mapper, DiceShardCard.class);
        if (!isEmptyMap(key))
            obj.key = deserializeMap(key, mapper, KeyCard.class);
        if (!isEmptyMap(lsoul))
            obj.lsoul = deserializeMap(lsoul, mapper, LostSoulCard.class);
        if (!isEmptyMap(pill))
            obj.pill = deserializeMap(pill, mapper, PillCard.class);
        if (!isEmptyMap(rune))
            obj.rune = deserializeMap(rune, mapper, RuneCard.class);
        if (!isEmptyMap(sack))
            obj.sack = deserializeMap(sack, mapper, SackCard.class);
        if (!isEmptyMap(sheart))
            obj.sheart = deserializeMap(sheart, mapper, SoulHeartCard.class);
        if (!isEmptyMap(trinket))
            obj.trinket = deserializeMap(trinket, mapper, TrinketCard.class);
        if (!isEmptyMap(wild))
            obj.wild = deserializeMap(wild, mapper, WildCard.class);
        if (!isEmptyMap(money))
            obj.money = mapper.treeToValue(money, MoneyCardAtlas.class);
        return obj;
    }
}
