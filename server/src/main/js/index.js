const express = require('express');
const fs = require('fs')
const { DEFAULT_MASTER_CARD_ATLAS, FILE, CardType } = require('./util/constants')
const card = require('./express/middleware/card')

const model = { cards: DEFAULT_MASTER_CARD_ATLAS }

/* Try to read {FILE} */
fs.readFile(FILE, (err, data) => {
    if (err) {
        console.error(`Failed to read ${FILE} (${err.code})`);
        fs.writeFile(FILE, JSON.stringify(model.cards), (err) => {
            if (err) console.log(`Failed to create ${FILE}`)
            else console.log(`Created ${FILE}`);
        })
    } else {
        console.log(`Read ${FILE}`)
        model.cards = JSON.parse(data);
    }
});

/* Try to save {model} to {FILE} when program terminates */
process.on('SIGINT', async () => {
    console.log('Stopping server...')
    /* save the current state of model to file */
    function waitForFile() {
        return new Promise((resolve, reject) => {
            fs.writeFile(FILE, JSON.stringify(model.cards), (err) => {
                if (err) {
                    console.log(`Failed to write ${FILE}`);
                    reject();
                } else {
                    console.log(`Saved ${FILE}`)
                    resolve();
                }
            })
        });
    }
    /* wait for model to finish saving */
    try {
        await waitForFile();
    } catch (err) {
        console.log(err);
    }
    process.exit(0);
});

const cardRouter = express.Router({ caseSensitive: false });
cardRouter.use(card.verify)
/* Handle POST requests for Bonus Soul Cards */
cardRouter.post(`/${CardType.BSOUL}`, async (req, res) => {
    const card = req.body;
    if (card.cardType !== CardType.BSOUL)
        return res.status(400).json({ "message": 'Invalid "cardType" value' })
    model.cards.bsoul[card.id] = card;
    return res.json({ "message": "Created BonusSoulCard" });
});
/* Handle POST requests for Character Cards */
cardRouter.post(`/${CardType.CHARACTER}`, async (req, res) => {
    const card = req.body;
    if (card.cardType !== CardType.CHARACTER)
        return res.status(400).json({ "message": 'Invalid "cardType" value' })
    model.cards.character[card.id] = card;
    return res.json({ "message": "Created CharacterCard" });
});
/* Handle POST requests for Eternal Cards */
cardRouter.post(`/${CardType.ETERNAL}`, async (req, res) => {
    const card = req.body;
    switch (card.cardType) {
        case CardType.AETERNAL:
            model.cards.eternal.aeternal[card.id] = card;
            break;
        case CardType.PAIDETERNAL:
            model.cards.eternal.paideternal[card.id] = card;
            break;
        case CardType.PETERNAL:
            model.cards.eternal.peternal[card.id] = card;
            break;
        case CardType.SETERNAL:
            model.cards.eternal.seternal[card.id] = card;
            break;
        default:
            return res.status(404).json({ 'message': 'Invalid "cardType" value' })
    }
    return res.json({ "message": "Created EternalCard" });
});
/* Handle POST requests for Treasure Cards */
cardRouter.post(`/${CardType.TREASURE}`, async (req, res) => {
    const card = req.body;
    switch (card.cardType) {
        case CardType.ATREASURE:
            model.cards.treasure.atreasure[card.id] = card;
            break;
        case CardType.OTREASURE:
            model.cards.treasure.otreasure[card.id] = card;
            break;
        case CardType.PAIDTREASURE:
            model.cards.treasure.paidtreasure[card.id] = card;
            break;
        case CardType.PTREASURE:
            model.cards.treasure.ptreasure[card.id] = card;
            break;
        case CardType.STREASURE:
            model.cards.treasure.streasure[card.id] = card;
            break;
        default:
            return res.status(404).json({ 'message': 'Invalid "cardType" value' })
    }
    return res.json({ "message": "Created TreasureCard" });
});
/* Handle POST requests for Monster Cards */
cardRouter.post(`/${CardType.MONSTER}`, async (req, res) => {
    const card = req.body;
    switch (card.cardType) {
        case CardType.BMONSTER:
            model.cards.monster.bmonster[card.id] = card;
            break;
        case CardType.CMONSTER:
            model.cards.monster.cmonster[card.id] = card;
            break;
        case CardType.HMONSTER:
            model.cards.monster.hmonster[card.id] = card;
            break;
        case CardType.CHAMONSTER:
            model.cards.monster.chamonster[card.id] = card;
            break;
        case CardType.GEVENT:
            model.cards.monster.gevent[card.id] = card;
            break;
        case CardType.BEVENT:
            model.cards.monster.bevent[card.id] = card;
            break;
        case CardType.CURSE:
            model.cards.monster.curse[card.id] = card;
            break;
        case CardType.BOSS:
            model.cards.monster.boss[card.id] = card;
            break;
        case CardType.EPIC:
            model.cards.monster.epic[card.id] = card;
            break;
        default:
            return res.status(404).json({ 'message': 'Invalid "cardType" value' })
    }
    return res.json({ "message": "Created MonsterCard" });
});

/* Handle POST requests for Loot Cards */
cardRouter.post(`/${CardType.LOOT}`, async (req, res) => {
    const card = req.body;
    switch (card.cardType) {
        case CardType.CARDS:
            model.cards.loot.cards[card.id] = card;
            break;
        case CardType.TRINKETS:
            model.cards.loot.trinkets[card.id] = card;
            break;
        case CardType.PILLS:
            model.cards.loot.pills[card.id] = card;
            break;
        case CardType.RUNES:
            model.cards.loot.runes[card.id] = card;
            break;
        case CardType.BOMBS:
            model.cards.loot.bombs[card.id] = card;
            break;
        case CardType.BUTTER:
            model.cards.loot.butter[card.id] = card;
            break;
        case CardType.BATTERIES:
            model.cards.loot.batteries[card.id] = card;
            break;
        case CardType.KEYS:
            model.cards.loot.keys[card.id] = card;
            break;
        case CardType.DICE:
            model.cards.loot.dice[card.id] = card;
            break;
        case CardType.SHEART:
            model.cards.loot.sheart[card.id] = card;
            break;
        case CardType.BHEART:
            model.cards.loot.bheart[card.id] = card;
            break;
        case CardType.SACK:
            model.cards.loot.sack[card.id] = card;
            break;
        case CardType.LSOUL:
            model.cards.loot.lsoul[card.id] = card;
            break;
        case CardType.WILDCARD:
            model.cards.loot.wild[card.id] = card;
            break;
        case CardType.MONEY1C:
            model.cards.loot.money.money1c[card.id] = card;
            break;
        case CardType.MONEY2C:
            model.cards.loot.money.money2c[card.id] = card;
            break;
        case CardType.MONEY3C:
            model.cards.loot.money.money3c[card.id] = card;
            break;
        case CardType.MONEY4C:
            model.cards.loot.money.money4c[card.id] = card;
            break;
        case CardType.MONEY5C:
            model.cards.loot.money.money5c[card.id] = card;
            break;
        case CardType.MONEY10C:
            model.cards.loot.money.money10c[card.id] = card;
            break;
        default:
            return res.status(404).json({ 'message': 'Invalid "cardType" value' })
    }
    return res.json({ "message": "Created LootCard" });
});
/* Handle POST requests for Room Cards */
cardRouter.post(`/${CardType.ROOM}`, async (req, res) => {
    const card = req.body;
    if (card.cardType !== CardType.ROOM)
        return res.status(400).json({ "message": 'Invalid "cardType" value' })
    model.cards.room[card.id] = card;
    return res.json({ "message": "Created RoomCard" });
});
/* Handle POST requests for Outside Cards */
cardRouter.post(`/${CardType.OUTSIDE}`, async (req, res) => {
    const card = req.body;
    if (card.cardType !== CardType.OUTSIDE)
        return res.status(400).json({ "message": 'Invalid "cardType" value' })
    model.cards.outside[card.id] = card;
    return res.json({ "message": "Created OutsideCard" });
});

const apiRouter = express.Router();
apiRouter.use('/card', cardRouter);
/* Handle GET requests for Model */
apiRouter.get('/', async (req, res) => {
    if (!req) return res.status(400).json({ "message": "Bad Request" });
    return res.status(200).json(model.cards);
});

const port = 3000;
const app = express();
app.use(express.json());
app.use('/api', apiRouter);
app.listen(port, () => {
    console.log(`Server listening on port ${port}`)
});