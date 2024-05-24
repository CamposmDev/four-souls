import { FastifyInstance, FastifyReply, FastifyRequest } from "fastify";

export default class FastifyJWTAuth {
    private app: FastifyInstance | null = null

    public setAppInstance(app: FastifyInstance) {
        this.app = app
    }

    public async verifyJWT(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        try {
            await req.jwtVerify()
            console.log('valid JWT')
            done()
        } catch (err: any) {
            console.log('invalid JWT')
            return res.status(401).send({message: "Unauthorized"})
        }
        // let token: string | null | undefined = req.cookies.token
        // if (token === null || token === undefined)
        //     return res.status(401).send({message: "Unauthorized"})
        // try {
        //     let verified = jwt.verify(token, JWT_SECRET)
        // } catch (err: any) {
        //     return res.status(401).send({message: "Unauthroized"})
        // }
    }

    public signJWT<T extends Object | string | Buffer>(data: T): string {
        if (!this.app)
            throw new Error("Fastify instance is not set.")
        return this.app.jwt.sign(data)
    }
}