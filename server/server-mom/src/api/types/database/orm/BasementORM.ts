import { Basement, Prisma } from "@prisma/client";

export default interface BasementORM {
    create(floor: string, level: number): Promise<Basement>
    host(): Promise<Basement | null>
    join(basementId: string): Promise<Basement | null>
    deleteById(basementId: string): Promise<Basement | null>
}