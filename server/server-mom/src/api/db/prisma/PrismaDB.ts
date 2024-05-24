import { FourSoulsDB } from "../../types";
import { PrismaUserORM } from "./orm";
import { PrismaClient } from "@prisma/client";

class PrismaDB implements FourSoulsDB {
    private _prisma: PrismaClient
    private _user: PrismaUserORM

    constructor() {
        this._prisma = new PrismaClient()
        this._user = new PrismaUserORM(this._prisma)
    }

    public get user() {
        return this._user
    }

    public async disconnect(): Promise<void> {
        await this._prisma.$disconnect()
    }
}

export default PrismaDB