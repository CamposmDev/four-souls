import BasementRequestChecker from "./BasementRequestChecker"
import ChestRequestChecker from "./ChestRequestChecker"
import DeckRequestChecker from "./DeckRequestChecker"
import UserRequestChecker from "./UserRequestChecker"

export default class RequestChecker {
    private _user: UserRequestChecker
    private _basement: BasementRequestChecker
    private _chest: ChestRequestChecker
    private _deck: DeckRequestChecker

    constructor() {
        this._user = new UserRequestChecker()
        this._basement = new BasementRequestChecker()
        this._chest = new ChestRequestChecker()
        this._deck = new DeckRequestChecker()
    }

    public get user() {
        return this._user
    }

    public get basement() {
        return this._basement
    }

    public get chest() {
        return this._chest
    }

    public get deck() {
        return this._deck
    }
}

