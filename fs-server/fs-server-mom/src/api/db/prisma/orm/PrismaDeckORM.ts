import { Deck, PrismaClient } from "@prisma/client";
import { CardType, DeckType } from "../../../util";
import { DeckORM } from "types/database";
import { JsonObject, JsonValue } from "@prisma/client/runtime/library";
import { BaseCard } from "types/common";

export default class PrismaDeckORM implements DeckORM {
    private prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this.prisma = prisma
    }

    public async create(name: keyof DeckType): Promise<Deck> {
        let cards = DeckType[name] as JsonObject
        const deck = await this.prisma.deck.create({
            data: {
                name: name,
                cards: cards
            }
        })
        return deck
    }

    public async getByName(name: string): Promise<Deck | null> {
        let deck: Deck | null = null
        deck = await this.prisma.deck.findUnique({
            where: {
                name: name
            }
        })
        return deck
    }

    public async getAll(): Promise<{name: string, cards: JsonValue}[]> {
        const decks = await this.prisma.deck.findMany({
            select: {
                name: true,
                cards: true
            }
        })
        return decks
    }

    public async append(name: string, card: BaseCard): Promise<Deck> {
        const deck = await this.getByName(name)
        if (!deck) throw Error(`Deck '${name}' does not exist`)
        const cards: any = deck.cards
        const cardType = card.cardType
        switch (cardType) {
            case CardType.BSOUL:
            case CardType.CHARACTER:
            case CardType.ROOM:
            case CardType.OUTSIDE:
                cards[card.id] = card
                break;
            case CardType.AETERNAL:
            case CardType.PAIDETERNAL:
            case CardType.PETERNAL:
            case CardType.SETERNAL:
                const ekey = cardType.toLowerCase()
                cards[ekey][card.id] = card;
                break;
            case CardType.ATREASURE:
            case CardType.OTREASURE:
            case CardType.PAIDTREASURE:
            case CardType.PTREASURE:
            case CardType.STREASURE:
                const tkey = cardType.toLowerCase()
                cards[tkey][card.id] = card;
                break;
            case CardType.BMONSTER:
            case CardType.CMONSTER:
            case CardType.HMONSTER:
            case CardType.CHAMONSTER:
            case CardType.GEVENT:
            case CardType.BEVENT:
            case CardType.CURSE:
            case CardType.BOSS:
            case CardType.EPIC:
                const mkey = cardType.toLowerCase()
                cards[mkey][card.id] = card
                break;
            case CardType.CARDS:
            case CardType.TRINKETS:
            case CardType.PILLS:
            case CardType.RUNES:
            case CardType.BOMBS:
            case CardType.BUTTER:
            case CardType.BATTERIES:
            case CardType.KEYS:
            case CardType.DICE:
            case CardType.SHEART:
            case CardType.BHEART:
            case CardType.SACK:
            case CardType.LSOUL:
            case CardType.WILDCARD:
                const lkey = cardType.toLowerCase()
                cards[lkey][card.id] = card
                break
            case CardType.MONEY1C:
            case CardType.MONEY2C:
            case CardType.MONEY3C:
            case CardType.MONEY4C:
            case CardType.MONEY5C:
            case CardType.MONEY10C:
                const $key = cardType.toLowerCase()
                cards.money[$key][card.id] = card
                break
            default: throw Error("Invalid CardType")
        }
        const updatedDeck = await this.prisma.deck.update({
            where: {
                name: name
            },
            data: {
                cards: cards
            }
        })
        return updatedDeck
    }
}