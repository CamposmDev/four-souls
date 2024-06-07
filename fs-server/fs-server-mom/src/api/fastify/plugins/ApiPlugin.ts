import { FastifyPluginAsync } from "fastify";
import fastifyRequestLogger from "@mgcrea/fastify-request-logger";
import fastifyCookie from "@fastify/cookie";
import UserPlugin from "./UserPlugin";
import BasementPlugin from "./BasementPlugin";
import ChestPlugin from "./ChestPlugin";
import DeckPlugin from "./DeckPlugin";
import PingPlugin from "./PingPlugin";

const ApiPlugin: FastifyPluginAsync = async (app) => {
    app.register(fastifyRequestLogger)
    app.register(fastifyCookie)
    app.register(UserPlugin, {prefix: "/user"})
    app.register(BasementPlugin, {prefix: "/basement"})
    app.register(ChestPlugin, {prefix: "/chest"})
    app.register(DeckPlugin, {prefix: "/deck"})
    app.register(PingPlugin, {prefix: "/ping"})
}

export default ApiPlugin