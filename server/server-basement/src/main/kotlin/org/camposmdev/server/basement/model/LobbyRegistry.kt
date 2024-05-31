package org.camposmdev.server.basement.model

import org.camposmdev.model.net.ClientRegistry

object LobbyRegistry : ClientRegistry<LobbyClient>() {
    private const val SZ = 4
    var hostId: String? = null

    fun sendLocalChatMessageToAll(message: String) {
        clients.forEach {
            it.sendLocalChatMessage(message)
        }
    }

    /**
     * Send a message to a client to notify them that they're the host of the lobby
     */
    fun sendHostMessage(client: LobbyClient) {
        client.sendHostMessage()
    }

    fun isFull(): Boolean {
        return clients.size > SZ
    }
}