import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { UserController } from "../controllers";
import { auth, validator } from "../hooks";

const UserPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", {preHandler: validator.user.create}, UserController.create)
    app.post("/login", UserController.loginUser)
    app.get("/:id", {onRequest: auth.verifyJWT}, UserController.getById)
}

export default UserPlugin