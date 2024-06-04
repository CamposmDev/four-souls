import { FourSoulsDB } from "types/database";
import { PrismaUserORM, PrismaBasementORM, PrismaChestORM, PrismaDeckORM } from "./orm";
import { PrismaClient } from "@prisma/client";

class PrismaDB implements FourSoulsDB {
    private _prisma: PrismaClient
    private _user: PrismaUserORM
    private _basement: PrismaBasementORM
    private _chest: PrismaChestORM
    private _deck: PrismaDeckORM

    constructor() {
        this._prisma = new PrismaClient()
        this._user = new PrismaUserORM(this._prisma)
        this._basement = new PrismaBasementORM(this._prisma)
        this._chest = new PrismaChestORM(this._prisma)
        this._deck = new PrismaDeckORM(this._prisma)
    }

    public get user(): PrismaUserORM {
        return this._user
    }

    public get basement(): PrismaBasementORM {
        return this._basement
    }

    public get chest(): PrismaChestORM {
        return this._chest
    }

    public get deck(): PrismaDeckORM {
        return this._deck
    }

    public async disconnect(): Promise<void> {
        await this._prisma.$disconnect()
    }
}

export default PrismaDB