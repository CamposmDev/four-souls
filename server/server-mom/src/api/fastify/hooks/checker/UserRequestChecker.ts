import { CreateUserBodyReq, LoginUserBodyReq } from "types/requests";
import { db } from "../../../db/prisma";
import { FastifyReply, FastifyRequest } from "fastify";
import { ObjectId } from "mongodb";

const PWD_LEN = 7

export default class UserRequestChecker {
    public async create(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const body = req.body as CreateUserBodyReq
        if (!body.email)
            return res.status(400).send({message: "Missing 'email' field"})
        if (!body.username)
            return res.status(400).send({message: "Missing 'username' field"})
        if (!body.password)
            return res.status(400).send({message: "Missing 'password' field"})
        /* ensure the password is of valid length */
        if (body.password.length < PWD_LEN)
            return res.status(400).send({message: `Password must be at least ${PWD_LEN} characters`})
        /* ensure the email is already taken */
        if (await db.user.isEmailTaken(body.email))
            return res.status(400).send({message: `Email '${body.email}' is already taken`})
        /* ensure the username is already taken */
        if (await db.user.isUsernameTaken(body.username))
            return res.status(400).send({message: `Email '${body.username}' is already taken`})
        done()
        return res
    }

    public async login(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const body = req.body as LoginUserBodyReq
        if (!body.username)
            return res.status(400).send({message: "Missing 'username' field"})
        if (!body.password)
            return res.status(400).send({message: "Missing 'password' field"})
        /* ensure the username is taken */
        if (!(await db.user.isUsernameTaken(body.username))) {
            return res.status(400).send({message: `Username not found. Please check and try again.`})
        }
        done()
        return res
    }

    public async getById(req: FastifyRequest, res: FastifyReply, done: (err?: Error | undefined) => void) {
        const params = req.params as { id: string }
        if (!params.id)
            return res.status(400).send({message: "Missing 'id' param"})
        if (!ObjectId.isValid(params.id))
            return res.status(400).send({message: "Invalid 'id' param"})
        done()
        return res
    }
}