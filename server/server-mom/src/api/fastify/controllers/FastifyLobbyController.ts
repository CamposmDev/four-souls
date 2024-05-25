import { FastifyReply, FastifyRequest } from "fastify";
import { auth } from "../hooks";
import db from "../../db";
import { cipher } from "../../util";
import { HostBasementBodyRes, JoinBasementBodyRes } from "types/response/basement";

export default class FastifyLobbyController {
    public async host(req: FastifyRequest, res: FastifyReply) {
        const token = await req.jwtDecode() as {userId: string}
        const basement = await db.basement.host()
        if (basement) {
            const json: HostBasementBodyRes = {
                id: basement.id,
                floor: cipher.decrypt(basement.floor),
                level: cipher.decrypt(basement.level)
            }
            res.status(200).send(json)
        } else {
            res.status(503).send({messge: "No Available Basements"})
        }
    }

    public async join(req: FastifyRequest, res: FastifyReply) {
        const params = req.params as { id: string }
        const basement = await db.basement.join(params.id)
        if (basement) {
            const json: JoinBasementBodyRes = {
                id: basement.id,
                floor: cipher.decrypt(basement.floor),
                level: cipher.decrypt(basement.level)
            }
            res.status(200).send(json)
        } else {
            res.status(404).send({message: "Basement ID Not Found"})
        }
    }
}