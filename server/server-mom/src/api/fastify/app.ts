import fastify, { FastifyInstance } from "fastify";
import { ApiPlugin } from "./plugins";
import fastifyRequestLogger from "@mgcrea/fastify-request-logger";
import db from "../db";


export default function build(): FastifyInstance {
    const app = fastify({
        logger: {
            level: "debug",
            timestamp: true,
            transport: {
                target: "@mgcrea/pino-pretty-compact",
                options: {
                    levelFirst: false,
                    colorize: true,
                    sync: true,
                    translateTime: "[yyyy/mm/dd HH:MM:ss Z]",
                    include: "level,time,body"
                },
            },
            crlf: false,
        },
        disableRequestLogging: true,
        caseSensitive: true
    })
    
    app.register(fastifyRequestLogger)
    app.register(ApiPlugin)
    app.addHook("onClose", async(it) => {
        db.disconnect()
    })
    return app
}