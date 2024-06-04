import { DeckType } from "../../../util";
import { FastifyReply, FastifyRequest } from "fastify";
import { db } from "../../../db/prisma";
import { Deck } from "@prisma/client";

export default class DeckRequestChecker {
    public async append(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as { name: string }
        /* ensure name is valid */
        if (!params.name || !(params.name in DeckType))
            return res.status(400).send({message: `Deck '${params.name}' does not exist`})
        /* check if deck exists */
        let deck: Deck | null = await db.deck.getByName(params.name)
        if (!deck) /* create the deck */
            deck = await db.deck.create(params.name)
        done()
        return res
    }
}