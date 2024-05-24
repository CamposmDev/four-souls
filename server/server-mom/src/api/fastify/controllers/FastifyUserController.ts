import { FastifyReply, FastifyRequest } from "fastify";
import { CreateUserBodyReq, LoginUserBodyReq } from "types/requests";
import { auth, crypt } from "../hooks";
import { Prisma } from "@prisma/client";
import db from "../../db";

export default class FastifyUserController {
    public async create(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const body = req.body as CreateUserBodyReq
        /* hash password */
        const passwordHash = await crypt.hash(body.password)
        /* create user */
        const user = await db.user.create({
            email: body.email,
            username: body.username,
            password: passwordHash
        })
        if (user) {
            const token = auth.signJWT({userId: user.id})
            res.setCookie('token', token, {
                path: '/',
                secure: true,
                httpOnly: true,
                sameSite: true
            }).send({result: user})
        } else {
            res.status(500).send()
        }
    }

    public async getById(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const params = req.params as {id: string}
        console.log(params)
        console.log(await req.jwtDecode())
        return res.send()
    }

    public async loginUser(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const body = req.body as LoginUserBodyReq
        if (!body.username)
            return res.status(400).send({message: "Missing 'username' field"})
        if (!body.password)
            return res.status(400).send({message: "Missing 'password' field"})
    }
}