import FastifyJWTAuth from "./FastifyJWTAuth";
import { RequestChecker } from "./checker";

const auth = new FastifyJWTAuth()
const checker = new RequestChecker()

export {
    auth,
    checker
}