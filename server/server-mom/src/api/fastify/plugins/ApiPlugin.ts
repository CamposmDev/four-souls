import { FastifyPluginAsync } from "fastify";
import MiddlewarePlugin from "./MiddlewarePlugin";
import UserPlugin from "./UserPlugin";
import fastifyCookie from "@fastify/cookie";
import fastifyJwt from "@fastify/jwt";

const JWT_SECRET: string = process.env.JWT_SECRET ? process.env.JWT_SECRET : "secret-key"

const ApiPlugin: FastifyPluginAsync = async (app) => {
    await app.register(fastifyCookie)
    await app.register(fastifyJwt, { 
        secret: JWT_SECRET,
        cookie: {
            cookieName: 'token',
            signed: false
        }
    })
    await app.register(MiddlewarePlugin)
    await app.register(UserPlugin, {prefix: "/user"})
}

export default ApiPlugin