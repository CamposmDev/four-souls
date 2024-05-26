import { FastifyReply, FastifyRequest } from "fastify";
import { db } from "../../db/prisma";
import { HostBasementBodyRes, JoinBasementBodyRes } from "types/response/basement";

export default class FastifyBasementController {
    public async create(req: FastifyRequest, res: FastifyReply) {
        const body = req.body as { ip: string, port: number }
        const basement = await db.basement.create(body.ip, body.port)
        if (basement) {
            res.status(201).send({message: "Created Basement"})
        } else {
            throw new Error("Create Basement: Failed")
        }
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
        const params = req.params as { id: string }
        const basement = await db.basement.join(params.id)
        if (basement) {
            const json: JoinBasementBodyRes = {
                id: basement.id,
                floor: basement.floor,
                level: basement.level
            }
            res.status(200).send(json)
        } else {
            res.status(404).send({message: "Basement ID Not Found"})
        }
    }
}