package io.github.camposmdev.foursouls.model.api.basement

import io.github.camposmdev.foursouls.model.api.message.MType
import io.github.camposmdev.foursouls.model.api.message.MType.*
import io.github.camposmdev.foursouls.model.api.message.MessageFactory
import io.github.camposmdev.foursouls.model.api.message.payload.PayloadFactory
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.ClientWebSocket
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.WebSocketConnectOptions
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject

class BasementAPI(val v: Vertx) : BasementWS {
    private lateinit var ws: ClientWebSocket
    private var eb = v.eventBus()

    override fun subscribeTo(mtype: MType): MessageConsumer<JsonObject> {
        /* sign the mtype with BasementAPI class for uniqueness */
        val name = sign(mtype)
        return eb.consumer(name)
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = PayloadFactory.parse(obj)
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
//        println("[${BasementAPI::class.java.simpleName}]: mtype=$mtype")
//        println("[${BasementAPI::class.java.simpleName}]: payload=$payload")
        when (mtype) {
            BASEMENT_GREETING -> eb.send(sign(BASEMENT_GREETING), payload)
            BASEMENT_CHAT -> eb.send(sign(BASEMENT_CHAT), payload)
            BASEMENT_DONE -> eb.send(sign(BASEMENT_DONE), payload)
            BASEMENT_USERS -> eb.send(sign(BASEMENT_USERS), payload)
            BASEMENT_CLOSED -> eb.send(sign(BASEMENT_CLOSED), payload)
            BASEMENT_ERROR -> eb.send(sign(BASEMENT_ERROR), payload)
            else -> {
                throw Error(mtype.name)
            }
        }
    }

    override fun sendChatMessage(username: String, message: String) {
        val chat = PayloadFactory.basementChat(username, message)
        ws.writeTextMessage(MessageFactory.basementChat(chat))
    }

    override fun sendFinishedMessage() {
        TODO("Not yet implemented")
    }

    override fun sendLeaveMessage() {
        TODO("Not yet implemented")
    }

    override fun connect(host: String, port: Int, userId: String): Future<Void> {
        val promise = Promise.promise<Void>()
        val options = WebSocketConnectOptions()
            .setHost(host)
            .setPort(port)
            .setURI(WS_ROUTE)
            .putHeader(HttpHeaders.COOKIE, "$USER_ID_FIELD=$userId")
        ws = v.createWebSocketClient().webSocket()
        ws.textMessageHandler(::textMessageHandler)
            .closeHandler(::closeHandler)
            .connect(options).onComplete {
                if (it.succeeded())
                    promise.complete()
                else
                    promise.fail(it.cause())
            }
        return promise.future()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val WS_ROUTE = "/"
        @JvmStatic
        fun sign(mtype: MType): String {
            val postfix = "[$mtype]"
            return BasementAPI::class.java.name.plus(postfix)
        }
    }
}