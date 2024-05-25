import { Basement, Prisma } from "@prisma/client";

export default interface BasementORM {
    create(data: Prisma.BasementCreateInput): Promise<Basement>
    host(): Promise<Basement | null>
    join(basementId: string): Promise<Basement | null>
    deleteById(basementId: string): Promise<Basement | null>
}