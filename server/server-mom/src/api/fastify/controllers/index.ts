import FastifyBasementController from "./FastifyBasementController";
import FastifyChestController from "./FastifyChestController";
import FastifyUserController from "./FastifyUserController";

const UserController = new FastifyUserController()
const BasementController = new FastifyBasementController()
const ChestController = new FastifyChestController()

export {
    UserController,
    BasementController,
    ChestController
}