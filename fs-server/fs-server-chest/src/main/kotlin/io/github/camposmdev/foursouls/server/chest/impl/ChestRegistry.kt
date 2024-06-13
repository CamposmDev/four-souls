package io.github.camposmdev.foursouls.server.chest.impl

import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.github.camposmdev.foursouls.core.api.message.ChestMType
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.util.ClientRegistry
import io.vertx.core.json.JsonObject

object ChestRegistry : ClientRegistry<ChestServerWSClient, ChestMType>() {
    private const val SZ = 4
    private var hostId: String? = null

    fun isFull(): Boolean {
        return clients.size >= SZ
    }

    override fun add(client: ChestServerWSClient): Boolean {
        if (isEmpty()) {
            hostId = client.id
            TODO("Not yet implemented")
//            client.state.host = true
        }
        return super.add(client)
    }

    fun users(): List<ChestUser> {
        val lst = mutableListOf<ChestUser>()
        clients.forEach {
            lst.add(it.state)
        }
        return lst
    }

    fun emit(mtype: ChestMType, payload: JsonObject) {
        clients.forEach {
            it.sendText(WSPacket.encode(mtype, payload))
        }
    }
}