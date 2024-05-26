import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { BasementController } from "../controllers";
import { auth, checker } from "../hooks";

const BasementPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", {onRequest: [auth.verifyJWT, auth.isAdmin]}, BasementController.create)
    app.post("/host", {onRequest: auth.verifyJWT}, BasementController.host)
    app.post("/:id/join", {onRequest: auth.verifyJWT, preHandler: checker.basement.join}, BasementController.join)
}

export default BasementPlugin