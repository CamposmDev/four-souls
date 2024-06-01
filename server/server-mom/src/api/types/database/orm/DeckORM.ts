import { Deck, Prisma } from "@prisma/client";

export default interface DeckORM {
    create(name: string): Promise<Deck>
    getByName(name: string): Promise<Deck | null>
    getAll(): Promise<{name: string, cards: Prisma.JsonValue}[]>
    append(name: string, card: any): Promise<Deck>
}