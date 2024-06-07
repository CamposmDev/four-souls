import { Deck } from "@prisma/client";
import { JsonValue } from "@prisma/client/runtime/library";

export default interface DeckORM {
    create(name: string): Promise<Deck>
    getByName(name: string): Promise<Deck | null>
    getAll(): Promise<{name: string, cards: JsonValue}[]>
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    append(name: string, card: any): Promise<Deck>
}