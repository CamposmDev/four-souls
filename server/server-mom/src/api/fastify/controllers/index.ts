import FastifyLobbyController from "./FastifyLobbyController";
import FastifyUserController from "./FastifyUserController";

const UserController = new FastifyUserController()
const LobbyController = new FastifyLobbyController()

export {
    UserController,
    LobbyController
}