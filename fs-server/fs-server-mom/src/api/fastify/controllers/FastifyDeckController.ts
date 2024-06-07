import { Deck } from "@prisma/client";
import { db } from "../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { JsonValue } from "@prisma/client/runtime/library";
import { DeckType } from "../../util";
import { DeckAppendParams, DeckGetAllQuery, DeckGetByNameParams } from "types/requests";
import { BaseCard } from "types/common";

export default class FastifyDeckController {
    public async getAll(req: FastifyRequest, res: FastifyReply) {
        const query = req.query as DeckGetAllQuery
        const decks: {
            name: string, 
            cards: JsonValue
        }[] = await db.deck.getAll()
        if (query.pretty) {
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            const cards: any = {}
            Object.keys(DeckType).forEach(key => {
                const deck = decks.find(x => x.name === key)
                cards[key] = deck ? deck.cards : DeckType[key as keyof DeckType]
            })
            return res.send(cards)
        } else {
            return res.send(decks)
        }
    }

    public async getByName(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as DeckGetByNameParams
        const deck: Deck | null = await db.deck.getByName(params.name)
        if (deck)
            return res.send({[params.name]: deck.cards})
        else return res.send({[params.name]: null})
    }

    public async append(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as DeckAppendParams
        const card = req.body as BaseCard
        await db.deck.append(params.name, card)
        return res.send({message: `Created ${params.name} card`})
    }
}