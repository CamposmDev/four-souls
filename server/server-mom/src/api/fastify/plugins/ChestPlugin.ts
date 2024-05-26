import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { auth, checker } from "../hooks";
import { ChestController } from "../controllers";

const ChestPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", {onRequest: [auth.verifyJWT, auth.isAdmin]}, ChestController.create)
    app.post("/host", {onRequest: auth.verifyJWT}, ChestController.host)
    app.post("/:id/join", {onRequest: auth.verifyJWT, preHandler: checker.chest.join}, ChestController.join)
}

export default ChestPlugin