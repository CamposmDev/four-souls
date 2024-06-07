import { Basement } from "@prisma/client";

export default interface BasementORM {
    create(floor: string, level: number): Promise<Basement>
    exists(basementId: string): Promise<boolean>
    free(basementId: string, key: string): Promise<boolean>
    host(): Promise<Basement | null>
    join(basementId: string): Promise<Basement>
    deleteById(basementId: string): Promise<Basement | null>
}