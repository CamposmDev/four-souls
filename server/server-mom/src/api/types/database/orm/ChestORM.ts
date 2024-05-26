import { Chest, Prisma } from "@prisma/client";

export default interface ChestORM {
    create(location: string, gate: number): Promise<Chest>
    host(): Promise<Chest | null>
    join(chestId: string): Promise<Chest | null>
    deleteById(chestId: string): Promise<Chest | null>
}