import BasementRequestChecker from "./BasementRequestChecker"
import ChestRequestChecker from "./ChestRequestChecker"
import UserRequestChecker from "./UserRequestChecker"

export default class RequestChecker {
    private _user: UserRequestChecker
    private _basement: BasementRequestChecker
    private _chest: ChestRequestChecker

    constructor() {
        this._user = new UserRequestChecker()
        this._basement = new BasementRequestChecker()
        this._chest = new ChestRequestChecker()
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
}

