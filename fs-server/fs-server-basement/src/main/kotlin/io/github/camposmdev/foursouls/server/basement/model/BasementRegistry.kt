package io.github.camposmdev.foursouls.server.basement.model

import io.github.camposmdev.foursouls.model.net.ClientRegistry
import io.github.camposmdev.foursouls.model.net.message.payload.BasementChat

object BasementRegistry : ClientRegistry<BasementClient>() {
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