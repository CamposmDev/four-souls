import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { DeckController } from "../controllers";
import { auth, checker } from "../hooks";
import { db } from "../../db/prisma";

const DeckPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.get("/", DeckController.getAll)
    app.get("/:name", DeckController.getByName)
    app.post("/:name", {onRequest: [], preHandler: checker.deck.append}, DeckController.append)
}

export default DeckPlugin