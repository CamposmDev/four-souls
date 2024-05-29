package org.camposmdev.server.basement.model

import org.camposmdev.model.net.ClientRegistry

object LobbyRegistry : ClientRegistry<LobbyClient>() {
    var hostId: String? = null

    fun sendChatMessage() {
        clients.forEach {
            it.sendChatMessage()
        }
    }
}