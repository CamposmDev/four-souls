import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { auth } from "../hooks";

const MiddlewarePlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    auth.setAppInstance(app)
}

export default MiddlewarePlugin