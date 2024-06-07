import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { DeckController } from "../controllers";
import { auth, checker } from "../hooks";

const DeckPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.get("/", DeckController.getAll)
    app.get("/:name", DeckController.getByName)
    app.post("/:name", {onRequest: [auth.verifyJWT, auth.isAdmin], preHandler: checker.deck.append}, DeckController.append)
}

export default DeckPlugin