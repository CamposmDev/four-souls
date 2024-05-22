const DEFAULT_MASTER_CARD_ATLAS = {
  "bsoul": {},
  "character": {},
  "eternal": {
    "aeternal": {},
    "paideternal": {},
    "peternal": {},
    "seternal": {}
  },
  "treasure": {
    "atreasure": {},
    "otreasure": {},
    "paidtreasure": {},
    "ptreasure": {},
    "streasure": {}
  },
  "monster": {
    "bmonster": {},
    "cmonster": {},
    "hmonster": {},
    "chamonster": {},
    "gevent": {},
    "bevent": {},
    "curse": {},
    "boss": {},
    "epic": {}
  },
  "loot": {
    "batteries": {},
    "bheart": {},
    "bomb": {},
    "butter": {},
    "cards": {},
    "dice": {},
    "key": {},
    "lsoul": {},
    "pill": {},
    "rune": {},
    "sack": {},
    "sheart": {},
    "trinket": {},
    "wild": {},
    "money": {
      "money1c": {},
      "money2c": {},
      "money3c": {},
      "money4c": {},
      "money5c": {},
      "money10c": {},
    }
  },
  "room": {},
  "outside": {},
}

const FILE = './cards.json';

const CardType = {
  CHARACTER: "CHARACTER",
  ETERNAL: "ETERNAL",
  PETERNAL: "PETERNAL",
  AETERNAL: "AETERNAL",
  PAIDETERNAL: "PAIDETERNAL",
  OETERNAL: "OETERNAL",
  SETERNAL: "SETERNAL",
  TREASURE: "TREASURE",
  PTREASURE: "PTREASURE",
  ATREASURE: "ATREASURE",
  PAIDTREASURE: "PAIDTREASURE",
  OTREASURE: "OTREASURE",
  STREASURE: "STREASURE",
  MONSTER: "MONSTER",
  BMONSTER: "BMONSTER",
  CMONSTER: "CMONSTER",
  HMONSTER: "HMONSTER",
  CHAMONSTER: "CHAMONSTER",
  GEVENT: "GEVENT",
  BEVENT: "BEVENT",
  CURSE: "CURSE",
  BOSS: "BOSS",
  EPIC: "EPIC",
  LOOT: "LOOT",
  CARDS: "CARDS",
  TRINKETS: "TRINKETS",
  PILLS: "PILLS",
  RUNES: "RUNES",
  BOMBS: "BOMBS",
  BUTTER: "BUTTER",
  BATTERIES: "BATTERIES",
  KEYS: "KEYS",
  DICE: "DICE",
  SHEART: "SHEART",
  BHEART: "BHEART",
  SACK: "SACK",
  LSOUL: "LSOUL",
  WILDCARD: "WILDCARD",
  MONEY: "MONEY",
  MONEY1C: "MONEY1C",
  MONEY2C: "MONEY2C",
  MONEY3C: "MONEY3C",
  MONEY4C: "MONEY4C",
  MONEY5C: "MONEY5C",
  MONEY10C: "MONEY10C",
  BSOUL: "BSOUL",
  ROOM: "ROOM",
  OUTSIDE: "OUTSIDE"
};


module.exports = {
  DEFAULT_MASTER_CARD_ATLAS,
  FILE,
  CardType
}