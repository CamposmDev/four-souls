package io.github.camposmdev.foursouls.core.api.basement

import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.MType.*
import io.github.camposmdev.foursouls.core.api.message.MessageCodec
import io.github.camposmdev.foursouls.core.api.message.MessageFactory
import io.github.camposmdev.foursouls.core.api.message.payload.*
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.ClientWebSocket
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.WebSocketConnectOptions
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import java.util.*

class BasementAPI(val v: Vertx) : BasementWS {
    private val id = "basement.api.${UUID.randomUUID()}."
    private lateinit var ws: ClientWebSocket
    private val log = WSLogger(BasementAPI::class.java)
    private var eb = v.eventBus()

    init {
        eb.registerDefaultCodec(BasementGreeting::class.java, MessageCodec.BasementGreetingCodec())
        eb.registerDefaultCodec(BasementChat::class.java, MessageCodec.BasementChatCodec())
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = PayloadFactory.parse(obj)
            log.read(text)
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            log.error(err)
        } catch (err: NullPointerException) {
            log.error(err)
        } catch (err: IllegalArgumentException) {
            log.error(err)
        }
    }

    override fun closeHandler(arg0: Void?) {
        ws.close().onComplete {
            if (it.succeeded())
                log.debug("Disconnected")
            else
                log.error(it.cause())
        }
    }

    override fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            BASEMENT_GREETING -> eb.send(sign(mtype), payload.mapTo(BasementGreeting::class.java))
            BASEMENT_CHAT -> eb.send(sign(mtype), payload.mapTo(BasementChat::class.java))
            BASEMENT_DONE -> eb.send(sign(mtype), payload.mapTo(BasementDone::class.java))
            BASEMENT_USERS -> eb.send(sign(mtype), payload.mapTo(BasementUsers::class.java))
            BASEMENT_CLOSED -> eb.send(sign(mtype), payload.mapTo(BasementClosed::class.java))
            BASEMENT_ERROR -> eb.send(sign(mtype), payload.mapTo(BasementError::class.java))
            else -> {
                throw Exception("This mtype shouldn't be here: $mtype")
            }
        }
    }

    override fun chat(username: String?, message: String): Future<Void> {
        val chat = PayloadFactory.basement().chat(username, message)
        return sendText(MessageFactory.basement().chat(chat))
    }

    override fun done(chestId: String): Future<Void> {
        return sendText(MessageFactory.basement().done(chestId))
    }

    override fun leave(): Future<Void> {
        return sendText(MessageFactory.basement().leave())
    }

    override fun connect(host: String, port: Int, userId: String): Future<Void> {
        val promise = Promise.promise<Void>()
        val options = WebSocketConnectOptions()
            .setHost(host).setPort(port).setURI(WS_ROUTE)
            .putHeader(HttpHeaders.COOKIE, "$USER_ID_FIELD=$userId")
        ws = v.createWebSocketClient().webSocket()
        ws.textMessageHandler(::textMessageHandler)
            .closeHandler(::closeHandler)
            .connect(options).onComplete {
                if (it.succeeded()) {
                    log.debug("Connected to ${ws.remoteAddress()}")
                    promise.complete()
                } else {
                    promise.fail(it.cause())
                }
            }
        return promise.future()
    }

    override fun sendText(text: String): Future<Void> {
        val promise = Promise.promise<Void>()
        ws.writeTextMessage(text).onComplete {
            if (it.succeeded()) {
                log.write(text)
                promise.complete()
            } else {
                log.error(it.cause())
                promise.fail(it.cause())
            }
        }
        return promise.future()
    }

    override fun subscribeTo(mtype: MType): MessageConsumer<BasementPayload> {
        /* sign the mtype with BasementAPI class for uniqueness */
        val name = sign(mtype)
        return eb.consumer(name)
    }


    override fun sign(mtype: MType): String {
        val postfix = mtype.name.lowercase()
        return id.plus(postfix)
    }


    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val WS_ROUTE = "/"
    }
}