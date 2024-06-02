package org.camposmdev.model.net.api

import io.vertx.core.Vertx
import io.vertx.core.http.ClientWebSocket
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.WebSocketConnectOptions
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.message.MType
import org.camposmdev.model.net.message.Message
import org.camposmdev.model.net.message.payload.*

class BasementApi(v: Vertx, hostName: String, port: Int, jwt: String) : BasementWSClient {
    private companion object {
        private const val WS_ROUTE = "/"
    }
    private var ws: ClientWebSocket
    private var eb = v.eventBus()

    init {
        val options = WebSocketConnectOptions()
            .setHost(hostName)
            .setPort(port)
            .setURI(WS_ROUTE)
            .putHeader(HttpHeaders.COOKIE, jwt)
        ws = v.createWebSocketClient().webSocket()
        ws.textMessageHandler(::textMessageHandler).connect(options)
        ws.closeHandler(::closeHandler)
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = Payload.parse(obj)
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            println(err)
        } catch (err: NullPointerException) {
            println(err)
        } catch (err: IllegalArgumentException) {
            println(err)
        }
    }

    override fun closeHandler(arg0: Void?) {
        ws.close()
    }

    override fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            MType.BASEMENT_GREETING -> { /* 1st expected message when joining basement */
                eb.publish(MType.BASEMENT_GREETING.name,
                    payload.mapTo(BasementGreeting::class.java))
            }
            MType.BASEMENT_CHAT -> {
                eb.publish(MType.BASEMENT_CHAT.name,
                    payload.mapTo(BasementChat::class.java))
            }
            MType.BASEMENT_FINISHED -> {
                eb.publish(MType.BASEMENT_FINISHED.name,
                    payload.mapTo(BasementFinished::class.java))
            }
            MType.BASEMENT_UPDATE_PLAYERS -> {
                /* update the state */
                eb.publish(MType.BASEMENT_UPDATE_PLAYERS.name,
                    payload.mapTo(BasementUpdatePlayers::class.java))
            }
            MType.BASEMENT_CLOSED -> {
                /* host closed the basement */
                eb.publish(MType.BASEMENT_CLOSED.name,
                    payload.mapTo(BasementClosed::class.java))
            }
            MType.BASEMENT_ERROR -> {
                eb.publish(MType.BASEMENT_ERROR.name,
                    payload.mapTo(BasementError::class.java))
            }
            else -> {
                throw Error(mtype.name)
            }
        }
    }

    override fun sendChatMessage(username: String?, message: String) {
        val chat = Payload.basementChat(username, message)
        ws.writeTextMessage(Message.basementChat(chat))
    }

    override fun sendFinishedMessage() {
        TODO("Not yet implemented")
    }

    override fun sendLeaveMessage() {
        TODO("Not yet implemented")
    }
}