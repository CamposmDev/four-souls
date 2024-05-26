import { BasementORM, UserORM, ChestORM } from "./orm";

export default interface FourSoulsDB {
    get user(): UserORM
    get basement(): BasementORM
    get chest(): ChestORM
    disconnect(): Promise<void>
}