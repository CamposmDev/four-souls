import { db } from "../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { HostChestBodyRes, JoinChestBodyRes } from "types/response";

export default class FastifyChestController {
    public async create(req: FastifyRequest, res: FastifyReply) {
        const body = req.body as { ip: string, port: number }
        const chest = await db.chest.create(body.ip, body.port)
        if (chest) {
            res.status(201).send({message: "Created Chest"})
        } else {
            throw new Error("Create Chest: Failed")
        }
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
        const params = req.params as { id: string }
        const chest = await db.chest.join(params.id)
        if (chest) {
            const json: JoinChestBodyRes = {
                id: chest.id,
                location: chest.location,
                gate: chest.gate
            }
            res.status(200).send(json)
        } else {
            res.status(404).send({message: "Chest ID Not Found"})
        }
    }
}