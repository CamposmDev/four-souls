import { Chest } from "@prisma/client";

export default interface ChestORM {
    create(location: string, gate: number): Promise<Chest>
    exists(chestId: string): Promise<boolean>
    unlock(chestId: string, key: string): Promise<boolean>
    host(): Promise<Chest | null>
    join(chestId: string): Promise<Chest>
    deleteById(chestId: string): Promise<Chest | null>
}