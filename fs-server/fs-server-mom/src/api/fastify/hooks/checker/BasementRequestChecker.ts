import { db } from "../../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { ObjectId } from "bson";
import { FreeBasementBodyReq, FreeBasementParams, JoinBasementParams } from "types/requests";

export default class BasementRequestChecker {
    public async join(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as JoinBasementParams
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        /* ensure the basement exists */
        const exists = await db.basement.exists(params.id)
        if (!exists)
            return res.status(404).send({message: "Basement Not Found"})
        done()
        return res
    }

    public async free(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as FreeBasementParams
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        /* ensure the basement exists */
        const exists = await db.basement.exists(params.id)
        if (!exists)
            return res.status(404).send({message: "Basement Not Found"})
        const body = req.body as FreeBasementBodyReq
        if (!body.key)
            return res.status(400).send({message: "Missing 'key' field"})
        done()
        return res
    }
}