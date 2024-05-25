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

    public signJWT<T extends Object | string | Buffer>(data: T): string {
        if (!this.app)
            throw new Error("Fastify instance is not set.")
        return this.app.jwt.sign(data)
    }
}