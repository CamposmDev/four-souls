import { db } from "../../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { ObjectId } from "mongodb";
import { UnlockChestBodyReq, UnlockChestParams, JoinChestParams } from "types/requests";

export default class ChestRequestChecker {
    public async join(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as JoinChestParams
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        /* ensure the chest exists */
        const exists = await db.chest.exists(params.id)
        if (!exists)
            return res.status(404).send({message: "Chest Not Found"})
        done()
        return res
    }

    public async unlock(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as UnlockChestParams
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        /* ensure the basement exists */
        const exists = await db.chest.exists(params.id)
        if (!exists)
            return res.status(404).send({message: "Chest Not Found"})
        const body = req.body as UnlockChestBodyReq
        if (!body.key)
            return res.status(400).send({message: "Missing 'key' field"})
        done()
        return res
    }
}