import { $Enums } from "@prisma/client";
import { db } from "../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import jwt from "jsonwebtoken"

const JWT_SECRET: string = process.env.JWT_SECRET ? process.env.JWT_SECRET : "supersecret"

export default class FastifyJWTAuth {
    public async verifyJWT(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        try {
            let cookie: string | null | undefined = req.cookies.token
            if (!cookie) throw Error()
            jwt.verify(cookie, JWT_SECRET) as { userId: string }
            /* token is valid */
            done()
        } catch (err: any) {
            res.status(401).send({message: "Unauthorized"})
        }
        return res
    }

    public async isAdmin(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        try {
            const cookie = req.cookies.token
            if (!cookie) throw Error()
            const token = jwt.verify(cookie, JWT_SECRET) as { userId: string }
            const user = await db.user.getById(token.userId)
            if (!user || user.role !== $Enums.Role.ADMIN) throw Error()
            done()
            return res
        } catch (err: any) {
            return res.status(401).send({message: "Unauthorized"})
        }
    }

    public signJWT<T extends Object | string | Buffer>(data: T): string {
        return jwt.sign(data, JWT_SECRET)
    }
}