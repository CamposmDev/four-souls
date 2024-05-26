import { Chest, PrismaClient } from "@prisma/client";
import { cipher } from "../../../util";
import { ChestORM } from "types/database";

export default class PrismaChestORM implements ChestORM {
    private prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this.prisma = prisma
    }

    public async create(location: string, gate: number): Promise<Chest> {
        const chest = await this.prisma.chest.create({
            data: {
                key: cipher.generate(),
                location: location,
                gate: gate
            }
        })
        return chest
    }

    public async host(): Promise<Chest | null> {
        /* find a chest that is not locked */
        const chest: Chest | null = await this.prisma.chest.findFirst({
            where: {
                locked: false
            }
        })
        /* if an unlocked chest exists, delete it and create a new chest with its fields */
        if (chest) {
            await this.deleteById(chest.id)
            const chestNew = await this.create(chest.location, chest.gate)
            return chestNew
        }
        /* otherwise no chests are available */
        return chest
    }

    public async join(chestId: string): Promise<Chest | null> {
        const chest = await this.prisma.chest.findUnique({
            where: {
                id: chestId
            }
        })
        return chest
    }

    public async deleteById(chestId: string): Promise<Chest | null> {
        const chest: Chest | null = await this.prisma.chest.delete({
            where: {
                id: chestId
            }
        })
        return chest
    }
}