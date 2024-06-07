import { Prisma, PrismaClient, User } from "@prisma/client";
import { UserORM } from "api/types";

export default class PrismaUserORM implements UserORM {
    private prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this.prisma = prisma
    }

    public async create(data: Prisma.UserCreateInput): Promise<User> {
        const user = await this.prisma.user.create({
            data: data
        })
        return user
    }

    public async getById(id: string): Promise<User | null> {
        const user = await this.prisma.user.findUnique({
            where: { id : id }
        })
        return user
    }

    public async getByUsername(username: string): Promise<User> {
        const user = await this.prisma.user.findFirstOrThrow({
            where: {
                username: username
            }
        })
        return user
    }

    public async isEmailTaken(email: string): Promise<boolean> {
        const user = await this.prisma.user.findFirst({
            where: {
                email: email
            }
        })
        return Boolean(user)
    }

    public async isUsernameTaken(username: string): Promise<boolean> {
        const user = await this.prisma.user.findFirst({
            where: {
                username: username
            }
        })
        return Boolean(user)
    }

    public async deleteById(userId: string): Promise<User | null> {
        const user: User | null = await this.prisma.user.delete({
            where: {
                id: userId
            }
        })
        return user
    }

    public async deleteByUsername(username: string): Promise<User | null> {
        const user: User | null = await this.prisma.user.delete({
            where: {
                username: username
            }
        })
        return user
    }
}