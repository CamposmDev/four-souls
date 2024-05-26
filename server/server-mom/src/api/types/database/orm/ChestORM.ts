import { Chest } from "@prisma/client";

export default interface ChestORM {
    create(location: string, gate: number): Promise<Chest>
    exists(chestId: string): Promise<Boolean>
    unlock(chestId: string, key: string): Promise<Boolean>
    host(): Promise<Chest | null>
    join(chestId: string): Promise<Chest>
    deleteById(chestId: string): Promise<Chest | null>
}