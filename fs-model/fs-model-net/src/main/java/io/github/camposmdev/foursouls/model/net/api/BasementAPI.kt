package io.github.camposmdev.foursouls.model.net.api

import io.github.camposmdev.foursouls.model.net.message.MType
import io.github.camposmdev.foursouls.model.net.message.MType.*
import io.github.camposmdev.foursouls.model.net.message.Message
import io.github.camposmdev.foursouls.model.net.message.payload.*
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.ClientWebSocket
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.WebSocketConnectOptions
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject

class BasementAPI(v: Vertx, hostName: String, port: Int, jwt: String) : BasementWSClient {
    companion object {
        private const val WS_ROUTE = "/"
        @JvmStatic
        fun channel(mtype: MType): String {
            val postfix = "[$mtype]"
            return BasementAPI::class.java.name.plus(postfix)
        }
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

    override fun subscribeTo(mtype: MType): MessageConsumer<BasementPayload> {
        return eb.consumer(channel(mtype))
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = PayloadBuilder.parse(obj)
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
            BASEMENT_GREETING -> { /* 1st expected message when joining basement */
                eb.send(
                    channel(BASEMENT_GREETING),
                    payload.mapTo(BasementGreeting::class.java))
            }
            BASEMENT_CHAT -> {
                eb.send(
                    channel(BASEMENT_CHAT),
                    payload.mapTo(BasementChat::class.java))
            }
            BASEMENT_DONE -> {
                eb.send(
                    channel(BASEMENT_DONE),
                    payload.mapTo(BasementDone::class.java))
            }
            BASEMENT_USERS -> {
            /* update the state */
            eb.send(
                channel(BASEMENT_USERS),
                payload.mapTo(BasementUsers::class.java))
            }
            BASEMENT_CLOSED -> {
                /* host closed the basement */
                eb.send(
                    channel(BASEMENT_CLOSED),
                    payload.mapTo(BasementClosed::class.java))
            }
            BASEMENT_ERROR -> {
                eb.send(
                    channel(BASEMENT_ERROR),
                    payload.mapTo(BasementError::class.java))
            }
            else -> {
                throw Error(mtype.name)
            }
        }
    }

    override fun sendChatMessage(username: String, message: String) {
        val chat = PayloadBuilder.basementChat(username, message)
        ws.writeTextMessage(Message.basementChat(chat))
    }

    override fun sendFinishedMessage() {
        TODO("Not yet implemented")
    }

    override fun sendLeaveMessage() {
        TODO("Not yet implemented")
    }
}