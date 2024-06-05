import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { auth, checker } from "../hooks";
import { ChestController } from "../controllers";

const ChestPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", {onRequest: [auth.verifyJWT, auth.isAdmin]}, ChestController.create)
    app.post("/host", {onRequest: auth.verifyJWT, preHandler: checker.chest.create}, ChestController.host)
    app.get("/:id/join", {onRequest: auth.verifyJWT, preHandler: checker.chest.join}, ChestController.join)
    app.put("/:id/unlock", {onRequest: auth.verifyJWT, preHandler: checker.chest.unlock}, ChestController.unlock)
}

export default ChestPlugin