import FastifyBasementController from "./FastifyBasementController";
import FastifyChestController from "./FastifyChestController";
import FastifyDeckController from "./FastifyDeckController";
import FastifyPingController from "./FastifyPingController";
import FastifyUserController from "./FastifyUserController";

const UserController = new FastifyUserController()
const BasementController = new FastifyBasementController()
const ChestController = new FastifyChestController()
const DeckController = new FastifyDeckController()
const PingController = new FastifyPingController()

export {
    UserController,
    BasementController,
    ChestController,
    DeckController,
    PingController
}