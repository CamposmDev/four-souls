import { FastifyInstance, FastifyPluginAsync } from "fastify"
import { PingController } from "../controllers"

const PingPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.get("/", PingController.ping)
}

export default PingPlugin