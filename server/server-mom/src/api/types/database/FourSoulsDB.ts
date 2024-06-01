import { BasementORM, UserORM, ChestORM, DeckORM } from "./orm";

export default interface FourSoulsDB {
    get user(): UserORM
    get basement(): BasementORM
    get chest(): ChestORM
    get deck(): DeckORM
    disconnect(): Promise<void>
}