import FastifyJWTAuth from "./FastifyJWTAuth";
import { RequestValidator } from "./validate";

const auth = new FastifyJWTAuth()
const validate = new RequestValidator()

export {
    auth,
    validate
}