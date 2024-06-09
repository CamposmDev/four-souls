import { DeckType } from "types/common";


// Initialize DeckType with inferred values
const DeckType: DeckType = {
    bsoul: {},
    character: {},
    eternal: {
        aeternal: {},
        paideternal: {},
        peternal: {},
        seternal: {}
    },
    treasure: {
        atreasure: {},
        otreasure: {},
        paidtreasure: {},
        ptreasure: {},
        streasure: {}
    },
    monster: {
        bmonster: {},
        cmonster: {},
        hmonster: {},
        chamonster: {},
        gevent: {},
        bevent: {},
        curse: {},
        boss: {},
        epic: {}
    },
    loot: {
        batteries: {},
        bheart: {},
        bombs: {},
        butter: {},
        cards: {},
        dice: {},
        keys: {},
        lsoul: {},
        pills: {},
        runes: {},
        sack: {},
        sheart: {},
        trinkets: {},
        wildcard: {},
        money: {
            money1c: {},
            money2c: {},
            money3c: {},
            money4c: {},
            money5c: {},
            money10c: {}
        }
    },
    room: {},
    outside: {}
};

enum CardType {
    BSOUL = "BSOUL",
    CHARACTER = "CHARACTER",
    ETERNAL = "ETERNAL",
    PETERNAL = "PETERNAL",
    AETERNAL = "AETERNAL",
    PAIDETERNAL = "PAIDETERNAL",
    OETERNAL = "OETERNAL",
    SETERNAL = "SETERNAL",
    TREASURE = "TREASURE",
    PTREASURE = "PTREASURE",
    ATREASURE = "ATREASURE",
    PAIDTREASURE = "PAIDTREASURE",
    OTREASURE = "OTREASURE",
    STREASURE = "STREASURE",
    MONSTER = "MONSTER",
    BMONSTER = "BMONSTER",
    CMONSTER = "CMONSTER",
    HMONSTER = "HMONSTER",
    CHAMONSTER = "CHAMONSTER",
    GEVENT = "GEVENT",
    BEVENT = "BEVENT",
    CURSE = "CURSE",
    BOSS = "BOSS",
    EPIC = "EPIC",
    LOOT = "LOOT",
    CARDS = "CARDS",
    TRINKETS = "TRINKETS",
    PILLS = "PILLS",
    RUNES = "RUNES",
    BOMBS = "BOMBS",
    BUTTER = "BUTTER",
    BATTERIES = "BATTERIES",
    KEYS = "KEYS",
    DICE = "DICE",
    SHEART = "SHEART",
    BHEART = "BHEART",
    SACK = "SACK",
    LSOUL = "LSOUL",
    WILDCARD = "WILDCARD",
    MONEY = "MONEY",
    MONEY1C = "MONEY1C",
    MONEY2C = "MONEY2C",
    MONEY3C = "MONEY3C",
    MONEY4C = "MONEY4C",
    MONEY5C = "MONEY5C",
    MONEY10C = "MONEY10C",
    ROOM = "ROOM",
    OUTSIDE = "OUTSIDE"
};


export { DeckType, CardType };
