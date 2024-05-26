import { FastifyInstance, FastifyPluginAsync } from "fastify";
import { UserController } from "../controllers";
import { auth, checker } from "../hooks";

const UserPlugin: FastifyPluginAsync = async (app: FastifyInstance) => {
    app.post("/", { preHandler: checker.user.create }, UserController.create)
    app.get("/:id", { onRequest: auth.verifyJWT,  preHandler: checker.user.getById }, UserController.getById)
    app.post("/login", { preHandler: checker.user.login }, UserController.login)
    app.post("/logout", { preHandler: auth.verifyJWT }, UserController.logout)
}

export default UserPlugin