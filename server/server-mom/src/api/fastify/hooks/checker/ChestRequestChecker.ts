import { FastifyReply, FastifyRequest } from "fastify";
import { ObjectId } from "mongodb";

export default class ChestRequestChecker {
    public async join(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as { id: string }
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        done()
        return res
    }
}