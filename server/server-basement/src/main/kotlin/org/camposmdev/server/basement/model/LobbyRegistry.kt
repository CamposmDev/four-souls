package org.camposmdev.server.basement.model

import org.camposmdev.model.net.ClientRegistry
import org.camposmdev.model.net.message.payload.BasementChat

object LobbyRegistry : ClientRegistry<LobbyClient>() {
    private const val SZ = 4
    var hostId: String? = null

    fun sendChatMessageToAll(chat: BasementChat) {
        clients.forEach {
            it.sendBasementChatMessage(chat)
        }
    }

    fun isFull(): Boolean {
        return clients.size > SZ
    }
}