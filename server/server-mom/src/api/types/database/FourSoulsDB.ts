import { UserORM } from "./orm";

export default interface FourSoulsDB {
    get user(): UserORM
    disconnect(): Promise<void>
}