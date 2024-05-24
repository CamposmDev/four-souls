import { FastifyInstance, FastifyReply, FastifyRequest } from "fastify";

export interface MomServerInstance extends FastifyInstance {
    verifyJWT?(req: FastifyRequest, res: FastifyReply): Promise<void>
}