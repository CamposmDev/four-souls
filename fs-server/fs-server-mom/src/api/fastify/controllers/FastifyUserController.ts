import { FastifyReply, FastifyRequest } from "fastify";
import { CreateUserBodyReq, LoginUserBodyReq } from "types/requests";
import { auth } from "../hooks";
import { bcrypt } from "../../util";
import { db } from "../../db/prisma";
import { CreateUserBodyRes, GetUserByIdBodyRes, LoginUserBodyRes } from "types/response";

export default class FastifyUserController {
    public async create(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const body = req.body as CreateUserBodyReq
        /* hash password */
        const passwordHash = await bcrypt.hash(body.password)
        /* create user */
        const user = await db.user.create({
            email: body.email,
            username: body.username,
            password: passwordHash
        })
        const token = auth.signJWT({userId: user.id})
        const json: CreateUserBodyRes = {
            id: user.id,
            username: user.username
        }
        res.setCookie('token', token, {
            path: '/',
            secure: true,
            httpOnly: true,
            sameSite: true,
            maxAge: (60*60*24*5) /* 5 days */
        }).status(201).send(json)
    }

    public async getById(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const params = req.params as {id: string}
        const user = await db.user.getById(params.id)
        if (user) {
            const json: GetUserByIdBodyRes = {
                id: user.id,
                username: user.username,
                role: user.role,
                createdAt: user.createdAt,
                updatedAt: user.updatedAt
            }
            res.send(json)
        } else res.status(404).send({message: "User not found"})
    }

    public async login(
        req: FastifyRequest,
        res: FastifyReply
    ) {
        const body = req.body as LoginUserBodyReq
        const user = await db.user.getByUsername(body.username)
        const passwordsMatch = await bcrypt.compare(body.password, user.password)
        if (passwordsMatch) {
            const token = auth.signJWT({userId: user.id})
            const json: LoginUserBodyRes = {
                id: user.id,
                username: user.username
            }
            res.setCookie('token', token, {
                path: '/',
                secure: true,
                httpOnly: true,
                sameSite: true
            }).send(json)
        } else {
            res.status(400).send({message: "Incorrect password. Please try again."})
        }
    }

    public async logout(req: FastifyRequest, res: FastifyReply) {
        res.clearCookie('token')
        res.send({message: "Logged out"})
    }
}