import { FastifyPluginAsync } from "fastify";
import fastifyRequestLogger from "@mgcrea/fastify-request-logger";
import fastifyCookie from "@fastify/cookie";
import fastifyJwt from "@fastify/jwt";
import UserPlugin from "./UserPlugin";
import BasementPlugin from "./BasementPlugin";
import ChestPlugin from "./ChestPlugin";
import { auth } from "../hooks";
import DeckPlugin from "./DeckPlugin";

const JWT_SECRET: string = process.env.JWT_SECRET ? process.env.JWT_SECRET : "secret-key"

const ApiPlugin: FastifyPluginAsync = async (app) => {
    auth.setAppInstance(app)
    app.register(fastifyRequestLogger)
    app.register(fastifyCookie)
    app.register(fastifyJwt, { 
        secret: JWT_SECRET,
        cookie: {
            cookieName: 'token',
            signed: false
        }
    })
    app.register(UserPlugin, {prefix: "/user"})
    app.register(BasementPlugin, {prefix: "/basement"})
    app.register(ChestPlugin, {prefix: "/chest"})
    app.register(DeckPlugin, {prefix: "/deck"})
}

export default ApiPlugin