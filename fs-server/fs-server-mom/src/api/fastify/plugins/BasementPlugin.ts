import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { BasementController } from "../controllers";
import { auth, checker } from "../hooks";

const BasementPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", {onRequest: [auth.verifyJWT, auth.isAdmin], preHandler: checker.basement.create}, BasementController.create)
    app.post("/host", {onRequest: auth.verifyJWT}, BasementController.host)
    app.get("/:id/join", {onRequest: auth.verifyJWT, preHandler: checker.basement.join}, BasementController.join)
    app.put("/:id/free", {onRequest: auth.verifyJWT, preHandler: checker.basement.free}, BasementController.free)
}

export default BasementPlugin