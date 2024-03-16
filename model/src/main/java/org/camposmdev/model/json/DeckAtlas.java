package org.camposmdev.model.json;

import com.almasb.fxgl.dsl.FXGL;

import java.util.Map;

public record DeckAtlas(
        String character,
        Map<String, String> eternal,
        Map<String, String> treasure,
        Map<String, String> monster,
        Map<String, String> loot,
        Map<String, String> money,
        String bsoul,
        String room
) {
    public ImageDataAtlas loadCharacterJSON() {
        return loadImageAtlas(character);
    }

    public ImageDataAtlas loadPeternalJSON() {
        return loadImageAtlas(peternal());
    }

    public ImageDataAtlas loadAeternalJSON() {
        return loadImageAtlas(aeternal());
    }

    public ImageDataAtlas loadPaideternalJSON() {
        return loadImageAtlas(paideternal());
    }

    public ImageDataAtlas loadOeternalJSON() {
        return loadImageAtlas(oeternal());
    }

    public ImageDataAtlas loadSeternalJSON() {
        return loadImageAtlas(seternal());
    }

    public ImageDataAtlas loadPtreasureJSON() {
        return loadImageAtlas(ptreasure());
    }

    public ImageDataAtlas loadAtreasureJSON() {
        return loadImageAtlas(atreasure());
    }

    public ImageDataAtlas loadPaidtreasureJSON() {
        return loadImageAtlas(paidtreasure());
    }

    public ImageDataAtlas loadOtreasureJSON() {
        return loadImageAtlas(otreasure());
    }

    public ImageDataAtlas loadStreasureJSON() {
        return loadImageAtlas(streasure());
    }

    public ImageDataAtlas loadBmonsterJSON() {
        return loadImageAtlas(bmonster());
    }

    public ImageDataAtlas loadCmonsterJSON() {
        return loadImageAtlas(cmonster());
    }

    public ImageDataAtlas loadHmonsterJSON() {
        return loadImageAtlas(hmonster());
    }

    public ImageDataAtlas loadChamonsterJSON() {
        return loadImageAtlas(chamonster());
    }

    public ImageDataAtlas loadGeventJSON() {
        return loadImageAtlas(gevent());
    }

    public ImageDataAtlas loadBeventJSON() {
        return loadImageAtlas(bevent());
    }

    public ImageDataAtlas loadCurseJSON() {
        return loadImageAtlas(curse());
    }

    public ImageDataAtlas loadMonsterBossJSON() {
        return loadImageAtlas(monster_boss());
    }

    public ImageDataAtlas loadMonsterEpicJSON() {
        return loadImageAtlas(monster_epic());
    }

    public ImageDataAtlas loadLootCardsJSON() {
        return loadImageAtlas(loot_cards());
    }

    public ImageDataAtlas loadLootTrinketsJSON() {
        return loadImageAtlas(loot_trinkets());
    }

    public ImageDataAtlas loadLootPillsJSON() {
        return loadImageAtlas(loot_pills());
    }

    public ImageDataAtlas loadLootRunesJSON() {
        return loadImageAtlas(loot_runes());
    }

    public ImageDataAtlas loadLootBombsJSON() {
        return loadImageAtlas(loot_bombs());
    }

    public ImageDataAtlas loadLootButterJSON() {
        return loadImageAtlas(loot_butter());
    }

    public ImageDataAtlas loadLootBatteriesJSON() {
        return loadImageAtlas(loot_batteries());
    }

    public ImageDataAtlas loadLootKeysJSON() {
        return loadImageAtlas(loot_keys());
    }

    public ImageDataAtlas loadLootDiceJSON() {
        return loadImageAtlas(loot_dice());
    }

    public ImageDataAtlas loadLootSheartJSON() {
        return loadImageAtlas(loot_sheart());
    }

    public ImageDataAtlas loadLootBheartJSON() {
        return loadImageAtlas(loot_bheart());
    }

    public ImageDataAtlas loadLootSackJSON() {
        return loadImageAtlas(loot_sack());
    }

    public ImageDataAtlas loadLootLsoulJSON() {
        return loadImageAtlas(loot_lsoul());
    }

    public ImageDataAtlas loadLootWildcardJSON() {
        return loadImageAtlas(loot_wildcard());
    }

    public ImageDataAtlas loadMoney1cJSON() {
        return loadImageAtlas(money_1c());
    }

    public ImageDataAtlas loadMoney2cJSON() {
        return loadImageAtlas(money_2c());
    }

    public ImageDataAtlas loadMoney3cJSON() {
        return loadImageAtlas(money_3c());
    }

    public ImageDataAtlas loadMoney4cJSON() {
        return loadImageAtlas(money_4c());
    }

    public ImageDataAtlas loadMoney5cJSON() {
        return loadImageAtlas(money_5c());
    }

    public ImageDataAtlas loadMoney10cJSON() {
        return loadImageAtlas(money_10c());
    }

    public ImageDataAtlas loadBsoulJSON() {
        return loadImageAtlas(bsoul);
    }

    public ImageDataAtlas loadRoomJSON() {
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

    public ImageDataAtlas loadImageAtlas(String src) {
        var result = FXGL.getAssetLoader().loadJSON(src, ImageDataAtlas.class);
        assert result.isPresent();
        return result.get();
    }
}
