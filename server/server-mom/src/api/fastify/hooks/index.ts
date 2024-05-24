import FastifyJWTAuth from "./FastifyJWTAuth";
import BcryptUtil from "./BcryptUtil";
import { RequestValidator } from "./validator";

const auth = new FastifyJWTAuth()
const validator = new RequestValidator()
const crypt = new BcryptUtil()

export {
    auth,
    validator,
    crypt
}