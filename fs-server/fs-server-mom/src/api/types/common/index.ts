// Define type aliases for deeply nested structures
export type EternalDeck = {
    aeternal: object;
    paideternal: object;
    peternal: object;
    seternal: object;
};

export type TreasureDeck = {
    atreasure: object;
    otreasure: object;
    paidtreasure: object;
    ptreasure: object;
    streasure: object;
};

export type MonsterDeck = {
    bmonster: object;
    cmonster: object;
    hmonster: object;
    chamonster: object;
    gevent: object;
    bevent: object;
    curse: object;
    boss: object;
    epic: object;
};

export type LootDeck = {
    batteries: object;
    bheart: object;
    bombs: object;
    butter: object;
    cards: object;
    dice: object;
    keys: object;
    lsoul: object;
    pills: object;
    runes: object;
    sack: object;
    sheart: object;
    trinkets: object;
    wildcard: object;
    money: MoneyDeck;
};

export type MoneyDeck = {
    money1c: object;
    money2c: object;
    money3c: object;
    money4c: object;
    money5c: object;
    money10c: object;
};

// Define the DeckType using the type aliases
export type DeckType = {
    bsoul: object;
    character: object;
    eternal: EternalDeck;
    treasure: TreasureDeck;
    monster: MonsterDeck;
    loot: LootDeck;
    room: object;
    outside: object;
};

export type BaseCard = {
    id: string,
    cardType: string
}