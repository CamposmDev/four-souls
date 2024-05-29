import build from "./fastify/app"
import * as dotenv from "dotenv"

/* initialize environment variables */
dotenv.config()

/* initialize port */
const DEFAULT_PORT = 5000
const PORT: number = process.env.API_PORT ? Number(process.env.API_PORT) : DEFAULT_PORT

/* initialize server */
const LEVEL = "debug"
const server = build(LEVEL)
server.listen({port: PORT}, (err: Error | null, addr: string) => {
    if (err) {
        server.log.error("Server", err)
        process.exit(1)
    }
})
