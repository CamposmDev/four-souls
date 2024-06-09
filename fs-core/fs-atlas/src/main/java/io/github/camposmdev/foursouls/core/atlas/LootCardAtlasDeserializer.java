package io.github.camposmdev.foursouls.core.atlas;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.camposmdev.foursouls.core.card.loot.*;

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
        var bombs = node.get("bombs");
        var butter = node.get("butter");
        var cards = node.get("cards");
        var dice = node.get("dice");
        var keys = node.get("keys");
        var lsoul = node.get("lsoul");
        var pills = node.get("pills");
        var runes = node.get("runes");
        var sack = node.get("sack");
        var sheart = node.get("sheart");
        var trinkets = node.get("trinkets");
        var wildcard = node.get("wildcard");
        var money = node.get("money");
        var obj = new LootCardAtlas();
        if (!isEmptyMap(batteries))
            obj.batteries = deserializeMap(batteries, mapper, BatteryCard.class);
        if (!isEmptyMap(bheart))
            obj.bheart = deserializeMap(bheart, mapper, BlackHeartCard.class);
        if (!isEmptyMap(bombs))
            obj.bombs = deserializeMap(bombs, mapper, BombCard.class);
        if (!isEmptyMap(butter))
            obj.butter = deserializeMap(butter, mapper, ButterBeanCard.class);
        if (!isEmptyMap(cards))
            obj.cards = deserializeMap(cards, mapper, TarotCard.class);
        if (!isEmptyMap(dice))
            obj.dice = deserializeMap(dice, mapper, DiceShardCard.class);
        if (!isEmptyMap(keys))
            obj.keys = deserializeMap(keys, mapper, KeyCard.class);
        if (!isEmptyMap(lsoul))
            obj.lsoul = deserializeMap(lsoul, mapper, LostSoulCard.class);
        if (!isEmptyMap(pills))
            obj.pills = deserializeMap(pills, mapper, PillCard.class);
        if (!isEmptyMap(runes))
            obj.runes = deserializeMap(runes, mapper, RuneCard.class);
        if (!isEmptyMap(sack))
            obj.sack = deserializeMap(sack, mapper, SackCard.class);
        if (!isEmptyMap(sheart))
            obj.sheart = deserializeMap(sheart, mapper, SoulHeartCard.class);
        if (!isEmptyMap(trinkets))
            obj.trinket = deserializeMap(trinkets, mapper, TrinketCard.class);
        if (!isEmptyMap(wildcard))
            obj.wildcard = deserializeMap(wildcard, mapper, WildCard.class);
        if (!isEmptyMap(money))
            obj.money = mapper.treeToValue(money, MoneyCardAtlas.class);
        return obj;
    }
}
