import fastify, { FastifyInstance } from "fastify";
import { ApiPlugin } from "./plugins";
import { db } from "../db/prisma";

export default function build(level: string): FastifyInstance {
    const app = fastify({
        logger: {
            level: level,
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
    app.register(ApiPlugin, {prefix: "/api"})
    app.addHook("onClose", async() => {
        db.disconnect()
    })
    return app
}