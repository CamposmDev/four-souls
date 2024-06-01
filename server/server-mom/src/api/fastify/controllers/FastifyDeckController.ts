import { Deck } from "@prisma/client";
import { db } from "../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";

export default class FastifyDeckController {
    public async getByName(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as { name: string }
        const deck: Deck | null = await db.deck.getByName(params.name)
        if (deck)
            return res.send({[params.name]: deck.cards})
        else return res.send({[params.name]: null})
    }

    public async getAll(req: FastifyRequest, res: FastifyReply) {
        const decks = await db.deck.getAll()
        res.send({decks: decks})
    }

    public async append(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as { name: string }
        await db.deck.append(params.name, req.body)
        return res.send({message: `Created '${params.name}' card`})
    }
}