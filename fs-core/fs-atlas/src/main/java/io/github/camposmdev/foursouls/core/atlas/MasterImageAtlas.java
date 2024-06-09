package io.github.camposmdev.foursouls.core.atlas;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.util.assets.CardAsset;

import java.io.IOException;
import java.util.*;

@Deprecated
public record MasterImageAtlas(
        String character,
        Map<String, String> eternal,
        Map<String, String> treasure,
        Map<String, String> monster,
        Map<String, String> loot,
        Map<String, String> money,
        String bsoul,
        String room,
        String outside
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

    public ImageAtlas loadOutsideJSON() {
        return loadImageAtlas(outside());
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
                ImageAtlas table = loadPeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadAeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadPaideternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadOeternalJSON();
                if (table.contains(key)) return table.source2(key);
                table = loadSeternalJSON();
                if (table.contains(key)) return table.source2(key);
            case AETERNAL:
                return loadAeternalJSON().source2(key);
            case PETERNAL:
                return loadPeternalJSON().source2(key);
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
            case OUTSIDE:
                return loadOutsideJSON().source2(key);
            default: return null;
        }
    }
    
    public CardAsset getInfo(CardType type, String key) {
        CardAsset info;
        switch (type) {
            case CHARACTER -> info = loadCharacterJSON().get(key);
            case PETERNAL -> info = loadPeternalJSON().get(key);
            case AETERNAL -> info = loadAeternalJSON().get(key);
            case PAIDETERNAL -> info = loadPaideternalJSON().get(key);
            case OETERNAL -> info = loadOeternalJSON().get(key);
            case SETERNAL -> info = loadSeternalJSON().get(key);
            case PTREASURE -> info = loadPtreasureJSON().get(key);
            case ATREASURE -> info = loadAtreasureJSON().get(key);
            case PAIDTREASURE -> info = loadPaidtreasureJSON().get(key);
            case OTREASURE -> info = loadOtreasureJSON().get(key);
            case STREASURE -> info = loadStreasureJSON().get(key);
            case BMONSTER -> info = loadBmonsterJSON().get(key);
            case CMONSTER -> info = loadCmonsterJSON().get(key);
            case HMONSTER -> info = loadHmonsterJSON().get(key);
            case CHAMONSTER -> info = loadChamonsterJSON().get(key);
            case GEVENT -> info = loadGeventJSON().get(key);
            case BEVENT -> info = loadBeventJSON().get(key);
            case CURSE -> info = loadCurseJSON().get(key);
            case BOSS -> info = loadMonsterBossJSON().get(key);
            case EPIC -> info = loadMonsterEpicJSON().get(key);
            case CARDS -> info = loadLootCardsJSON().get(key);
            case TRINKETS -> info = loadLootTrinketsJSON().get(key);
            case PILLS -> info = loadLootPillsJSON().get(key);
            case RUNES -> info = loadLootRunesJSON().get(key);
            case BOMBS -> info = loadLootBombsJSON().get(key);
            case BUTTER -> info = loadLootButterJSON().get(key);
            case BATTERIES -> info = loadLootBatteriesJSON().get(key);
            case KEYS -> info = loadLootKeysJSON().get(key);
            case DICE -> info = loadLootDiceJSON().get(key);
            case SHEART -> info = loadLootSheartJSON().get(key);
            case BHEART -> info = loadLootBheartJSON().get(key);
            case SACK -> info = loadLootSackJSON().get(key);
            case LSOUL -> info = loadLootLsoulJSON().get(key);
            case WILDCARD -> info = loadLootWildcardJSON().get(key);
            case MONEY1C -> info = loadMoney1cJSON().get(key);
            case MONEY2C -> info = loadMoney2cJSON().get(key);
            case MONEY3C -> info = loadMoney3cJSON().get(key);
            case MONEY4C -> info = loadMoney4cJSON().get(key);
            case MONEY5C -> info = loadMoney5cJSON().get(key);
            case MONEY10C -> info = loadMoney10cJSON().get(key);
            case BSOUL -> info = loadBsoulJSON().get(key);
            case ROOM -> info = loadRoomJSON().get(key);
            case OUTSIDE -> info = loadOutsideJSON().get(key);
            default -> throw new IllegalArgumentException("Invalid key: " + key);
        }
        return info;
    }
}
