import { $Enums } from "@prisma/client";
import { db } from "../../db/prisma";
import { FastifyInstance, FastifyReply, FastifyRequest } from "fastify";

export default class FastifyJWTAuth {
    private app: FastifyInstance | null = null

    public setAppInstance(app: FastifyInstance) {
        this.app = app
    }

    public async verifyJWT(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        try {
            await req.jwtVerify()
            done()
        } catch (err: any) {
            res.status(401).send({message: "Unauthorized"})
        }
        return res
    }

    public async isAdmin(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const token = await req.jwtDecode() as {userId: string}
        const user = await db.user.getById(token.userId)
        if (user && user.role === $Enums.Role.ADMIN) {
            done()
            return res
        } else {
            return res.status(401).send({message: "Unauthorized"})
        }
    }

    public signJWT<T extends Object | string | Buffer>(data: T): string {
        if (!this.app)
            throw new Error("Fastify instance is not set.")
        return this.app.jwt.sign(data)
    }
}