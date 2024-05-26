import { db } from "../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { CreateChestBodyReq, UnlockChestBodyReq, UnlockChestParams, JoinChestParams } from "types/requests";
import { HostChestBodyRes, JoinChestBodyRes } from "types/response";

export default class FastifyChestController {
    public async create(req: FastifyRequest, res: FastifyReply) {
        const body = req.body as CreateChestBodyReq
        await db.chest.create(body.location, body.gate)
        res.status(201).send({message: "Created Chest"})
    }

    public async host(req: FastifyRequest, res: FastifyReply) {
        const chest = await db.chest.host()
        if (chest) {
            const json: HostChestBodyRes = {
                id: chest.id,
                key: chest.key,
                location: chest.location,
                gate: chest.gate
            }
            res.status(200).send(json)
        } else [
            res.status(503).send({message: "No Available Chests"})
        ]
    }

    public async join(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as JoinChestParams
        const chest = await db.chest.join(params.id)
        const json: JoinChestBodyRes = {
            id: chest.id,
            location: chest.location,
            gate: chest.gate
        }
        res.status(200).send(json)
    }

    public async unlock(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as UnlockChestParams
        const body = req.body as UnlockChestBodyReq
        const freed = db.chest.unlock(params.id, body.key)
        if (!freed)
            res.status(400).send({message: "Invalid key: the provided key does not match the expected key for this chest"})
        else
            res.status(200).send()
    }
}