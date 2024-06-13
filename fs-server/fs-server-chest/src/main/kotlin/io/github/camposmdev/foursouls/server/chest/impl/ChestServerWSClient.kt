package io.github.camposmdev.foursouls.server.chest.impl

import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.github.camposmdev.foursouls.core.api.logger.ChestLogger
import io.github.camposmdev.foursouls.core.api.message.ChestMType
import io.github.camposmdev.foursouls.core.api.message.ChestMType.*
import io.github.camposmdev.foursouls.core.api.message.PacketFactory
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.api.message.payload.ChestCharacterSelection
import io.github.camposmdev.foursouls.core.api.message.payload.ChestChat
import io.github.camposmdev.foursouls.core.api.message.payload.ChestDrawCharacter
import io.github.camposmdev.foursouls.core.api.message.payload.ChestDrawLoot
import io.github.camposmdev.foursouls.core.util.AbstractServerWSClient
import io.github.camposmdev.foursouls.server.chest.ChestServer
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.JsonObject

class ChestServerWSClient(
    ws: ServerWebSocket,
    userId: String
) : AbstractServerWSClient<ChestMType>(ws) {
    private lateinit var log: ChestLogger
    val state = ChestUser(id, userId)

    init {
        /* verify userId */
        ChestServer.Auth.verifyId(userId).onSuccess {
            state.username = it
            ws.binaryMessageHandler(::binaryMessageHandler)
            ws.textMessageHandler(::textMessageHandler)
            ws.closeHandler(::closeHandler)
            log = ChestLogger(state.username).apply {
                debug = true
            }
            log.debug("${state.username} joined the chest")
        }.onFailure {
            log.error(it)
            ws.close()
        }
    }

    override fun binaryMessageHandler(buffer: Buffer) {
        log.readBinary(buffer)
    }

    override fun textMessageHandler(text: String) {
        try {
            val body = WSPacket.decode(text)
            val mtype = ChestMType.valueOf(body.mtype)
            val payload = body.payload
            log.readText(text)
            decodeMessage(mtype, payload)
        } catch (ex: Exception) {
            ex.printStackTrace()
            sendText(PacketFactory.basement().err(ex.toString()))
        }
    }

    override fun closeHandler(arg0: Void?) {
        val removed = ChestRegistry.remove { x -> x.id == this.id }
        if (removed)
            log.debug("${state.username} left the chest")
        else
            log.error("Failed to remove ${state.username}?")
    }

    override fun decodeMessage(mtype: ChestMType, job: JsonObject) {
        when (mtype) {
            CHAT -> {
                val chat = job.mapTo(ChestChat::class.java)
                /* relay chat message to all users */
                ChestRegistry.emit(mtype, job)
            }
            DRAW_LOOT-> {
                /* user wants to draw x loot cards */
                val payload = job.mapTo(ChestDrawLoot::class.java)
            }
            DRAW_CHARACTER -> {
                /* user wants to draw x character cards */
                val payload = job.mapTo(ChestDrawCharacter::class.java)
            }
            CHARACTER_SELECTION -> {
                /* user wants to choose their character */
                val payload = job.mapTo(ChestCharacterSelection::class.java)
            }
            else -> sendText(PacketFactory.basement().err("Invalid MType: $mtype"))
        }
    }

    override fun sendText(text: String): Future<Void> {
        val promise = Promise.promise<Void>()
        ws.writeTextMessage(text).onSuccess {
            log.writeText(text)
            promise.complete()
        }.onFailure {
            it.printStackTrace()
            promise.fail(it)
        }
        return promise.future()
    }
}