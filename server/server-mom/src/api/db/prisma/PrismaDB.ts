import { FourSoulsDB } from "../../types";
import { PrismaUserORM } from "./orm";
import { PrismaClient } from "@prisma/client";
import PrismaBasementORM from "./orm/PrismaBasementORM";

class PrismaDB implements FourSoulsDB {
    private _prisma: PrismaClient
    private _user: PrismaUserORM
    private _basement: PrismaBasementORM

    constructor() {
        this._prisma = new PrismaClient()
        this._user = new PrismaUserORM(this._prisma)
        this._basement = new PrismaBasementORM(this._prisma)
    }

    public get user() {
        return this._user
    }

    public get basement() {
        return this._basement
    }

    public async disconnect(): Promise<void> {
        await this._prisma.$disconnect()
    }
}

export default PrismaDB