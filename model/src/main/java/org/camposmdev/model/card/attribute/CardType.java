package org.camposmdev.model.card.attribute;

public enum CardType{
    CHARACTER("character"),
    ETERNAL("eternal"),
    PETERNAL("peternal"),
    AETERNAL("aeternal"),
    PAIDETERNAL("paideternal"),
    OETERNAL("oeternal"),
    SETERNAL("seternal"),
    TREASURE("treasure"),
    PTREASURE("ptreasure"),
    ATREASURE("atreasure"),
    PAIDTREASURE("paidtreasure"),
    OTREASURE("otreasure"),
    STREASURE("streasure"),
    MONSTER("monster"),
    BMONSTER("bmonster"),
    CMONSTER("cmonster"),
    HMONSTER("hmonster"),
    CHAMONSTER("chamonster"),
    GEVENT("gevent"),
    BEVENT("bevent"),
    CURSE("curse"),
    BOSS("boss"),
    EPIC("epic"),
    LOOT("loot"),
    CARDS("cards"),
    TRINKETS("trinkets"),
    PILLS("pills"),
    RUNES("runes"),
    BOMBS("bombs"),
    BUTTER("butter"),
    BATTERIES("batteries"),
    KEYS("keys"),
    DICE("dice"),
    SHEART("sheart"),
    BHEART("bheart"),
    SACK("sack"),
    LSOUL("lsoul"),
    WILDCARD("wildcard"),
    MONEY("money"),
    MONEY1C("1c"),
    MONEY2C("2c"),
    MONEY3C("3c"),
    MONEY4C("4c"),
    MONEY5C("5c"),
    MONEY10C("10c"),
    BSOUL("bsoul"),
    ROOM("room"),
    EXTRA("extra");

    private final String key;

    CardType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    public static CardType parse(String name) {
        for (var type : CardType.values())
            if (type.key.equals(name))
                return type;
        throw new IllegalArgumentException("Invalid CardType: " + name);
    }
}
