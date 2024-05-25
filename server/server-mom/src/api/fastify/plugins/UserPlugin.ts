import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { UserController } from "../controllers";
import { auth, validate } from "../hooks";

const UserPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", { preHandler: validate.user.create }, UserController.create)
    app.post("/login", { preHandler: validate.user.login }, UserController.login)
    app.get("/:id", { onRequest: auth.verifyJWT }, UserController.getById)
}

export default UserPlugin