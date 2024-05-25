import { BasementORM, UserORM } from "./orm";

export default interface FourSoulsDB {
    get user(): UserORM
    get basement(): BasementORM
    disconnect(): Promise<void>
}