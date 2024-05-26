import { Basement, Prisma } from "@prisma/client";

export default interface BasementORM {
    create(floor: string, level: number): Promise<Basement>
    exists(basementId: string): Promise<Boolean>
    free(basementId: string, key: string): Promise<Boolean>
    host(): Promise<Basement | null>
    join(basementId: string): Promise<Basement>
    deleteById(basementId: string): Promise<Basement | null>
}