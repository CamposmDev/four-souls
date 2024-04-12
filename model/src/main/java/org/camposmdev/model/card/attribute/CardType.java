package org.camposmdev.model.card.attribute;

public enum CardType{
    CHARACTER("character", "Character"),
    ETERNAL("eternal", "Eternal Treasure"),
    PETERNAL("peternal", "Passive Eternal Treasure"),
    AETERNAL("aeternal", "Active Eternal Treasure"),
    PAIDETERNAL("paideternal", "Paid Eternal Treasure"),
    OETERNAL("oeternal", "One-Use Eternal Treasure"),
    SETERNAL("seternal", "Soul Eternal Treasure"),
    TREASURE("treasure", "Treasure"),
    PTREASURE("ptreasure", "Passive Treasure"),
    ATREASURE("atreasure", "Active Treasure"),
    PAIDTREASURE("paidtreasure", "Paid Treasure"),
    OTREASURE("otreasure", "One-Use Treasure"),
    STREASURE("streasure", "Soul Treasure"),
    MONSTER("monster", "Monster"),
    BMONSTER("bmonster", "Basic Monster"),
    CMONSTER("cmonster", "Cursed Monster"),
    HMONSTER("hmonster", "Holy Monster"),
    CHAMONSTER("chamonster", "Charmed Monster"),
    GEVENT("gevent", "Good Event"),
    BEVENT("bevent", "Bad Event"),
    CURSE("curse", "Curse"),
    BOSS("boss", "Boss"),
    EPIC("epic", "Epic"),
    LOOT("loot", "Loot"),
    CARDS("cards", "Cards"),
    TRINKETS("trinkets", "Trinkets"),
    PILLS("pills", "Pills"),
    RUNES("runes", "Runes"),
    BOMBS("bombs", "Bombs"),
    BUTTER("butter", "Butter Beans"),
    BATTERIES("batteries", "Batteries"),
    KEYS("keys", "Keys"),
    DICE("dice", "Dice Shards"),
    SHEART("sheart", "Soul Hearts"),
    BHEART("bheart", "Black Hearts"),
    SACK("sack", "Sacks"),
    LSOUL("lsoul", "Lost Soul"),
    WILDCARD("wildcard", "Wildcard"),
    MONEY("money", "Money"),
    MONEY1C("1c", "1 Cents"),
    MONEY2C("2c", "2 Cents"),
    MONEY3C("3c", "3 Cents"),
    MONEY4C("4c", "4 Cents"),
    MONEY5C("5c", "Nickel"),
    MONEY10C("10c", "Dime"),
    BSOUL("bsoul", "Bonus Soul"),
    ROOM("room", "Room"),
    OUTSIDE("outside", "Outside");

    private final String key;
    private final String pretty;

    CardType(String key, String pretty) {
        this.key = key;
        this.pretty = pretty;
    }

    public String key() {
        return key;
    }

    public String pretty() { return pretty; }

    public static CardType parse(String name) {
        for (var type : CardType.values())
            if (type.key.equals(name))
                return type;
        for (var type : CardType.values()) {
            if (type.pretty.equals(name))
                return type;
        }
        throw new IllegalArgumentException("Invalid CardType: " + name);
    }
}
