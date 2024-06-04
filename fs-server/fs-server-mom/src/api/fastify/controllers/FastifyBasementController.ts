import { FastifyReply, FastifyRequest } from "fastify";
import { db } from "../../db/prisma";
import { HostBasementBodyRes, JoinBasementBodyRes } from "types/response";
import { CreateBasementBodyReq, FreeBasementBodyReq, FreeBasementParams, JoinBasementParams } from "types/requests";

export default class FastifyBasementController {
    public async create(req: FastifyRequest, res: FastifyReply) {
        const body = req.body as CreateBasementBodyReq
        await db.basement.create(body.floor, body.level)
        res.status(201).send({message: "Created Basement"})
    }

    public async host(req: FastifyRequest, res: FastifyReply) {
        const basement = await db.basement.host()
        if (basement) {
            const json: HostBasementBodyRes = {
                id: basement.id,
                key: basement.key,
                floor: basement.floor,
                level: basement.level
            }
            res.status(200).send(json)
        } else {
            res.status(503).send({message: "No Available Basements"})
        }
    }

    public async join(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as JoinBasementParams
        const basement = await db.basement.join(params.id)
        const json: JoinBasementBodyRes = {
            id: basement.id,
            floor: basement.floor,
            level: basement.level
        }
        res.status(200).send(json)
    }

    public async free(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as FreeBasementParams
        const body = req.body as FreeBasementBodyReq
        const freed = db.basement.free(params.id, body.key)
        if (!freed)
            res.status(400).send({message: "Invalid key: the provided key does not match the expected key for this basement"})
        else
            res.status(200).send()
    }
}