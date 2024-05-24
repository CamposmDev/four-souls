import { Prisma, PrismaClient, User } from "@prisma/client";
import { UserORM } from "api/types";

export default class PrismaUserORM implements UserORM {
    private _prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this._prisma = prisma
    }

    public async create(data: Prisma.UserCreateInput): Promise<User> {
        const user = await this._prisma.user.create({
            data: data
        })
        return user
    }

    public async isEmailTaken(email: string): Promise<Boolean> {
        const user = await this._prisma.user.findFirst({
            where: {
                email: email
            }
        })
        return Boolean(user)
    }

    public async isUsernameTaken(username: string): Promise<Boolean> {
        const user = await this._prisma.user.findFirst({
            where: {
                username: username
            }
        })
        return Boolean(user)
    }
}