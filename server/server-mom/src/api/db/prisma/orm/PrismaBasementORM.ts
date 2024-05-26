import { Basement, PrismaClient } from "@prisma/client";
import { cipher } from "../../../util";
import { BasementORM } from "types/database";

export default class PrismaBasementORM implements BasementORM {
    private prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this.prisma = prisma
    }

    public async create(floor: string, level: number): Promise<Basement> {
        const basement = await this.prisma.basement.create({
            data: {
                key: cipher.generate(),
                floor: floor,
                level: level
            }
        })
        return basement
    }

    public async host(): Promise<Basement | null> {
        /* find a basement that is not occupied */
        const basement: Basement | null = await this.prisma.basement.findFirst({
            where: {
                occupied: false
            }
        })
        /* if such a basement exists, delete it and create a new basement with its fields */
        if (basement) {
            await this.deleteById(basement.id)
            const basementNew = await this.create(basement.floor, basement.level)
            return basementNew
        }
        /* otherwise no basements are available */
        return basement
    }

    public async join(basementId: string): Promise<Basement | null> {
        const basement: Basement | null = await this.prisma.basement.findUnique({
            where: {
                id: basementId
            }
        })
        return basement
    }

    public async deleteById(basementId: string): Promise<Basement | null> {
        const basement: Basement | null = await this.prisma.basement.delete({
            where: {
                id: basementId
            }
        })
        return basement
    }
}