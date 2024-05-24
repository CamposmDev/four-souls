import UserRequestValidator from "./UserRequestValidator"

export default class RequestValidator {
    private _user: UserRequestValidator

    constructor() {
        this._user = new UserRequestValidator()
    }

    public get user() {
        return this._user
    }
}

