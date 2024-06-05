import { FastifyReply, FastifyRequest } from "fastify";

export default class FastifyPingController {
    public async ping(req: FastifyRequest, res: FastifyReply) {
        return {message: "pong"}
    }
}