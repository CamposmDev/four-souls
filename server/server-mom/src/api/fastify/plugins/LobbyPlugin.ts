import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { LobbyController } from "../controllers";
import { auth } from "../hooks";

const LobbyPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/host", {onRequest: auth.verifyJWT}, LobbyController.host)
    app.post("/:id/join", {onRequest: auth.verifyJWT}, LobbyController.join)
}

export default LobbyPlugin