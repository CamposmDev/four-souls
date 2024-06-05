// Define type aliases for deeply nested structures
export type EternalDeck = {
    aeternal: {};
    paideternal: {};
    peternal: {};
    seternal: {};
};

export type TreasureDeck = {
    atreasure: {};
    otreasure: {};
    paidtreasure: {};
    ptreasure: {};
    streasure: {};
};

export type MonsterDeck = {
    bmonster: {};
    cmonster: {};
    hmonster: {};
    chamonster: {};
    gevent: {};
    bevent: {};
    curse: {};
    boss: {};
    epic: {};
};

export type LootDeck = {
    batteries: {};
    bheart: {};
    bomb: {};
    butter: {};
    cards: {};
    dice: {};
    keys: {};
    lsoul: {};
    pill: {};
    rune: {};
    sack: {};
    sheart: {};
    trinket: {};
    wild: {};
    money: MoneyDeck;
};

export type MoneyDeck = {
    money1c: {};
    money2c: {};
    money3c: {};
    money4c: {};
    money5c: {};
    money10c: {};
};

// Define the DeckType using the type aliases
export type DeckType = {
    bsoul: {};
    character: {};
    eternal: EternalDeck;
    treasure: TreasureDeck;
    monster: MonsterDeck;
    loot: LootDeck;
    room: {};
    outside: {};
};

export type BaseCard = {
    id: string,
    cardType: string
}