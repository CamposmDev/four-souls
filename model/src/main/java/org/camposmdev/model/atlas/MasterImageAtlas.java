package org.camposmdev.model.atlas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;

import java.io.IOException;
import java.util.*;

public record MasterImageAtlas(
        String character,
        Map<String, String> eternal,
        Map<String, String> treasure,
        Map<String, String> monster,
        Map<String, String> loot,
        Map<String, String> money,
        String bsoul,
        String room
) {
    public ImageAtlas loadCharacterJSON() {
        return loadImageAtlas(character);
    }

    public ImageAtlas loadPeternalJSON() {
        return loadImageAtlas(peternal());
    }

    public ImageAtlas loadAeternalJSON() {
        return loadImageAtlas(aeternal());
    }

    public ImageAtlas loadPaideternalJSON() {
        return loadImageAtlas(paideternal());
    }

    public ImageAtlas loadOeternalJSON() {
        return loadImageAtlas(oeternal());
    }

    public ImageAtlas loadSeternalJSON() {
        return loadImageAtlas(seternal());
    }

    public ImageAtlas loadPtreasureJSON() {
        return loadImageAtlas(ptreasure());
    }

    public ImageAtlas loadAtreasureJSON() {
        return loadImageAtlas(atreasure());
    }

    public ImageAtlas loadPaidtreasureJSON() {
        return loadImageAtlas(paidtreasure());
    }

    public ImageAtlas loadOtreasureJSON() {
        return loadImageAtlas(otreasure());
    }

    public ImageAtlas loadStreasureJSON() {
        return loadImageAtlas(streasure());
    }

    public ImageAtlas loadBmonsterJSON() {
        return loadImageAtlas(bmonster());
    }

    public ImageAtlas loadCmonsterJSON() {
        return loadImageAtlas(cmonster());
    }

    public ImageAtlas loadHmonsterJSON() {
        return loadImageAtlas(hmonster());
    }

    public ImageAtlas loadChamonsterJSON() {
        return loadImageAtlas(chamonster());
    }

    public ImageAtlas loadGeventJSON() {
        return loadImageAtlas(gevent());
    }

    public ImageAtlas loadBeventJSON() {
        return loadImageAtlas(bevent());
    }

    public ImageAtlas loadCurseJSON() {
        return loadImageAtlas(curse());
    }

    public ImageAtlas loadMonsterBossJSON() {
        return loadImageAtlas(monster_boss());
    }

    public ImageAtlas loadMonsterEpicJSON() {
        return loadImageAtlas(monster_epic());
    }

    public ImageAtlas loadLootCardsJSON() {
        return loadImageAtlas(loot_cards());
    }

    public ImageAtlas loadLootTrinketsJSON() {
        return loadImageAtlas(loot_trinkets());
    }

    public ImageAtlas loadLootPillsJSON() {
        return loadImageAtlas(loot_pills());
    }

    public ImageAtlas loadLootRunesJSON() {
        return loadImageAtlas(loot_runes());
    }

    public ImageAtlas loadLootBombsJSON() {
        return loadImageAtlas(loot_bombs());
    }

    public ImageAtlas loadLootButterJSON() {
        return loadImageAtlas(loot_butter());
    }

    public ImageAtlas loadLootBatteriesJSON() {
        return loadImageAtlas(loot_batteries());
    }

    public ImageAtlas loadLootKeysJSON() {
        return loadImageAtlas(loot_keys());
    }

    public ImageAtlas loadLootDiceJSON() {
        return loadImageAtlas(loot_dice());
    }

    public ImageAtlas loadLootSheartJSON() {
        return loadImageAtlas(loot_sheart());
    }

    public ImageAtlas loadLootBheartJSON() {
        return loadImageAtlas(loot_bheart());
    }

    public ImageAtlas loadLootSackJSON() {
        return loadImageAtlas(loot_sack());
    }

    public ImageAtlas loadLootLsoulJSON() {
        return loadImageAtlas(loot_lsoul());
    }

    public ImageAtlas loadLootWildcardJSON() {
        return loadImageAtlas(loot_wildcard());
    }

    public ImageAtlas loadMoney1cJSON() {
        return loadImageAtlas(money_1c());
    }

    public ImageAtlas loadMoney2cJSON() {
        return loadImageAtlas(money_2c());
    }

    public ImageAtlas loadMoney3cJSON() {
        return loadImageAtlas(money_3c());
    }

    public ImageAtlas loadMoney4cJSON() {
        return loadImageAtlas(money_4c());
    }

    public ImageAtlas loadMoney5cJSON() {
        return loadImageAtlas(money_5c());
    }

    public ImageAtlas loadMoney10cJSON() {
        return loadImageAtlas(money_10c());
    }

    public ImageAtlas loadBsoulJSON() {
        return loadImageAtlas(bsoul);
    }

    public ImageAtlas loadRoomJSON() {
        return loadImageAtlas(room);
    }

    public String peternal() {
        return eternal.get("peternal");
    }

    public String aeternal() {
        return eternal.get("aeternal");
    }

    public String paideternal() {
        return eternal.get("paideternal");
    }

    public String oeternal() {
        return eternal.get("oeternal");
    }

    public String seternal() {
        return eternal.get("seternal");
    }

    public String ptreasure() {
        return treasure.get("ptreasure");
    }

    public String atreasure() {
        return treasure.get("atreasure");
    }

    public String paidtreasure() {
        return treasure.get("paidtreasure");
    }

    public String otreasure() {
        return treasure.get("otreasure");
    }

    public String streasure() {
        return treasure.get("streasure");
    }

    public String bmonster() {
        return monster.get("bmonster");
    }

    public String cmonster() {
        return monster.get("cmonster");
    }

    public String hmonster() {
        return monster.get("hmonster");
    }

    public String chamonster() {
        return monster.get("chamonster");
    }

    public String gevent() {
        return monster.get("gevent");
    }

    public String bevent() {
        return monster.get("bevent");
    }

    public String curse() {
        return monster.get("curse");
    }

    public String monster_boss() {
        return monster.get("boss");
    }

    public String monster_epic() {
        return monster.get("epic");
    }

    public String loot_cards() {
        return loot.get("cards");
    }

    public String loot_trinkets() {
        return loot.get("trinkets");
    }

    public String loot_pills() {
        return loot.get("pills");
    }

    public String loot_runes() {
        return loot.get("runes");
    }

    public String loot_bombs() {
        return loot.get("bombs");
    }

    public String loot_butter() {
        return loot.get("butter");
    }

    public String loot_batteries() {
        return loot.get("batteries");
    }

    public String loot_keys() {
        return loot.get("keys");
    }

    public String loot_dice() {
        return loot.get("dice");
    }

    public String loot_sheart() {
        return loot.get("sheart");
    }

    public String loot_bheart() {
        return loot.get("bheart");
    }

    public String loot_sack() {
        return loot.get("sack");
    }

    public String loot_lsoul() {
        return loot.get("lsoul");
    }

    public String loot_wildcard() {
        return loot.get("wildcard");
    }

    public String money_1c() {
        return money.get("1c");
    }

    public String money_2c() {
        return money.get("2c");
    }

    public String money_3c() {
        return money.get("3c");
    }

    public String money_4c() {
        return money.get("4c");
    }

    public String money_5c() {
        return money.get("5c");
    }

    public String money_10c() {
        return money.get("10c");
    }

    private ImageAtlas loadImageAtlas(String src) {
        if (!src.startsWith("assets/"))
            src = "assets/" + src;
        var input = MasterImageAtlas.class.getClassLoader().getResourceAsStream(src);
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(input, ImageAtlas.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Set<String> allEternalKeys() {
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(loadAeternalJSON().images().keySet());
        keySet.addAll(loadOeternalJSON().images().keySet());
        keySet.addAll(loadPaideternalJSON().images().keySet());
        keySet.addAll(loadPeternalJSON().images().keySet());
        keySet.addAll(loadSeternalJSON().images().keySet());
        return keySet;
    }

    public String source2(CardType type, String key) {
        switch (type) {
            case CHARACTER:
                return loadCharacterJSON().source2(key);
            case ETERNAL:
                var table = loadPeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadAeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadPaideternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadOeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadSeternalJSON();
                if (table.contains(key)) return table.source2(key);
            case PETERNAL:
                return loadPeternalJSON().source2(key);
            case AETERNAL:
                return loadAeternalJSON().source2(key);
            case PAIDETERNAL:
                return loadPaideternalJSON().source2(key);
            case OETERNAL:
                return loadOeternalJSON().source2(key);
            case SETERNAL:
                return loadSeternalJSON().source2(key);
            case PTREASURE:
                return loadPtreasureJSON().source2(key);
            case ATREASURE:
                return loadAtreasureJSON().source2(key);
            case PAIDTREASURE:
                return loadPaidtreasureJSON().source2(key);
            case OTREASURE:
                return loadOtreasureJSON().source2(key);
            case STREASURE:
                return loadStreasureJSON().source2(key);
            case BMONSTER:
                return loadBmonsterJSON().source2(key);
            case CMONSTER:
                return loadCmonsterJSON().source2(key);
            case HMONSTER:
                return loadHmonsterJSON().source2(key);
            case CHAMONSTER:
                return loadChamonsterJSON().source2(key);
            case GEVENT:
                return loadGeventJSON().source2(key);
            case BEVENT:
                return loadBeventJSON().source2(key);
            case CURSE:
                return loadCurseJSON().source2(key);
            case BOSS:
                return loadMonsterBossJSON().source2(key);
            case EPIC:
                return loadMonsterEpicJSON().source2(key);
            case CARDS:
                return loadLootCardsJSON().source2(key);
            case TRINKETS:
                return loadLootTrinketsJSON().source2(key);
            case PILLS:
                return loadLootPillsJSON().source2(key);
            case RUNES:
                return loadLootRunesJSON().source2(key);
            case BOMBS:
                return loadLootBombsJSON().source2(key);
            case BUTTER:
                return loadLootButterJSON().source2(key);
            case BATTERIES:
                return loadLootBatteriesJSON().source2(key);
            case KEYS:
                return loadLootKeysJSON().source2(key);
            case DICE:
                return loadLootDiceJSON().source2(key);
            case SHEART:
                return loadLootPillsJSON().source2(key);
            case BHEART:
                return loadLootPillsJSON().source2(key);
            case SACK:
                return loadLootPillsJSON().source2(key);
            case LSOUL:
                return loadLootLsoulJSON().source2(key);
            case WILDCARD:
                return loadLootWildcardJSON().source2(key);
            case MONEY1C:
                return loadMoney1cJSON().source2(key);
            case MONEY2C:
                return loadMoney2cJSON().source2(key);
            case MONEY3C:
                return loadMoney3cJSON().source2(key);
            case MONEY4C:
                return loadMoney4cJSON().source2(key);
            case MONEY5C:
                return loadMoney5cJSON().source2(key);
            case MONEY10C:
                return loadMoney10cJSON().source2(key);
            case BSOUL:
                return loadBsoulJSON().source2(key);
            case ROOM:
                return loadRoomJSON().source2(key);
        }
        return null;
    }
    
    public ImageInfo getInfo(CardType type, String key) {
        ImageInfo info;
        switch (type.key()) {
            case "character" -> info = loadCharacterJSON().get(key);
            case "peternal" -> info = loadPeternalJSON().get(key);
            case "aeternal" -> info = loadAeternalJSON().get(key);
            case "paideternal" -> info = loadPaideternalJSON().get(key);
            case "oeternal" -> info = loadOeternalJSON().get(key);
            case "seternal" -> info = loadSeternalJSON().get(key);
            case "ptreasure" -> info = loadPtreasureJSON().get(key);
            case "atreasure" -> info = loadAtreasureJSON().get(key);
            case "paidtreasure" -> info = loadPaidtreasureJSON().get(key);
            case "otreasure" -> info = loadOtreasureJSON().get(key);
            case "streasure" -> info = loadStreasureJSON().get(key);
            case "bmonster" -> info = loadBmonsterJSON().get(key);
            case "cmonster" -> info = loadCmonsterJSON().get(key);
            case "hmonster" -> info = loadHmonsterJSON().get(key);
            case "chamonster" -> info = loadChamonsterJSON().get(key);
            case "gevent" -> info = loadGeventJSON().get(key);
            case "bevent" -> info = loadBeventJSON().get(key);
            case "curse" -> info = loadCurseJSON().get(key);
            case "boss" -> info = loadMonsterBossJSON().get(key);
            case "epic" -> info = loadMonsterEpicJSON().get(key);
            case "cards" -> info = loadLootCardsJSON().get(key);
            case "trinkets" -> info = loadLootTrinketsJSON().get(key);
            case "pills" -> info = loadLootPillsJSON().get(key);
            case "runes" -> info = loadLootRunesJSON().get(key);
            case "bombs" -> info = loadLootBombsJSON().get(key);
            case "butter" -> info = loadLootButterJSON().get(key);
            case "batteries" -> info = loadLootBatteriesJSON().get(key);
            case "keys" -> info = loadLootKeysJSON().get(key);
            case "dice" -> info = loadLootDiceJSON().get(key);
            case "sheart" -> info = loadLootSheartJSON().get(key);
            case "bheart" -> info = loadLootBheartJSON().get(key);
            case "sack" -> info = loadLootSackJSON().get(key);
            case "lsoul" -> info = loadLootLsoulJSON().get(key);
            case "wildcard" -> info = loadLootWildcardJSON().get(key);
            case "1c" -> info = loadMoney1cJSON().get(key);
            case "2c" -> info = loadMoney2cJSON().get(key);
            case "3c" -> info = loadMoney3cJSON().get(key);
            case "4c" -> info = loadMoney4cJSON().get(key);
            case "5c" -> info = loadMoney5cJSON().get(key);
            case "10c" -> info = loadMoney10cJSON().get(key);
            case "bsoul" -> info = loadBsoulJSON().get(key);
            case "room" -> info = loadRoomJSON().get(key);
            default -> throw new IllegalArgumentException("Invalid key: " + key);
        }
        return info;
    }
}
