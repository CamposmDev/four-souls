import FastifyBasementController from "./FastifyBasementController";
import FastifyChestController from "./FastifyChestController";
import FastifyDeckController from "./FastifyDeckController";
import FastifyUserController from "./FastifyUserController";

const UserController = new FastifyUserController()
const BasementController = new FastifyBasementController()
const ChestController = new FastifyChestController()
const DeckController = new FastifyDeckController()

export {
    UserController,
    BasementController,
    ChestController,
    DeckController,
}