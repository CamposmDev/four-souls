import { Basement, Prisma, PrismaClient } from "@prisma/client";
import { BasementORM } from "types/database";

export default class PrismaBasementORM implements BasementORM {
    private _prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this._prisma = prisma
    }

    public async create(data: Prisma.BasementCreateInput): Promise<Basement> {
        const basement = await this._prisma.basement.create({
            data: data
        })
        return basement
    }

    public async host(): Promise<Basement | null> {
        /* find a basement that is not occupied */
        const basement: Basement | null = await this._prisma.basement.findFirst({
            where: {
                occupied: false
            }
        })
        /* if such a basement exists, delete it and create a new basement with its fields */
        if (basement) {
            await this.deleteById(basement.id)
            const basementNew = await this.create({
                floor: basement.floor,
                level: basement.level,
                occupied: true
            })
            return basementNew
        }
        return null
    }

    public async join(basementId: string): Promise<Basement | null> {
        const basement: Basement | null = await this._prisma.basement.findUnique({
            where: {
                id: basementId
            }
        })
        return basement
    }

    public async deleteById(basementId: string): Promise<Basement | null> {
        const basement: Basement | null = await this._prisma.basement.delete({
            where: {
                id: basementId
            }
        })
        return basement
    }
}