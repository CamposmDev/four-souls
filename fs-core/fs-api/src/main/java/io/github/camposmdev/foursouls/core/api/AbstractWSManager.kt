package io.github.camposmdev.foursouls.core.api

import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.api.message.payload.Payload
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.ClientWebSocket
import io.vertx.core.http.WebSocketConnectOptions
import io.vertx.core.json.JsonObject

abstract class AbstractWSManager<T : Enum<T>, U : Payload>(
    protected val vertx: Vertx,
    private val id: String,
    clazz: Class<*>
) : ISubscribeMType<T, U>, ISignMType<T> {
     private lateinit var ws: ClientWebSocket
     protected val log = WSLogger(clazz)

    protected abstract fun decodeMessage(mtype: T, payload: JsonObject)

    protected abstract fun decodeMType(mtype: String): T

    protected fun writeText(text: String): Future<Void> {
        val promise = Promise.promise<Void>()
        ws.writeTextMessage(text).onSuccess {
            log.write(text)
            promise.complete()
        }.onFailure {
            log.error("Failed to write $text")
            promise.fail(it)
        }
        return promise.future()
    }

    protected fun <T> registerDefaultCodec(clazz: Class<T>, codec: MessageCodec<T, *>) {
        try {
            vertx.eventBus().registerDefaultCodec(clazz, codec)
            log.debug("Registered MessageCodec<${codec.name()}>")
        } catch (ex: Exception) {
            log.warn("MessageCodec<${clazz.simpleName}> is already registered")
        }
    }


    fun connect(options: WebSocketConnectOptions): Future<Void> {
        val promise = Promise.promise<Void>()
        ws = vertx.createWebSocketClient().webSocket()
        ws.textMessageHandler(::textMessageHandler)
        ws.closeHandler(::closeHandler)
        ws.connect(options).onSuccess {
            log.debug("Connected to ${ws.remoteAddress()}")
            promise.complete()
        }.onFailure {
            promise.fail(it)
        }
        return promise.future()
    }

    private fun textMessageHandler(text: String) {
        try {
            val obj = WSPacket.decode(text)
            val mtype = decodeMType(obj.mtype)
            val payload = obj.payload
            log.read(text)
            decodeMessage(mtype, payload)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun closeHandler(arg0: Void?) {
        ws.close().onSuccess {
            log.debug("Disconnected")
        }.onFailure { it.printStackTrace() }
    }


    override fun subscribeTo(mtype: T): MessageConsumer<U> {
        return vertx.eventBus().consumer(sign(mtype))
    }

    override fun sign(mtype: T): String {
        return id.plus(".${mtype.name.lowercase()}")
    }
}
