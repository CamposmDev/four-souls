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

/**
 * Abstract base class for WebSocket API handling using Vert.x.
 *
 * This class provides a framework for connecting to WebSocket servers,
 * sending and receiving messages, and decoding those messages based on
 * message types and payloads.
 *
 * @param T The enum type representing message types.
 * @param U The payload type for the messages.
 * @property vertx The Vert.x instance.
 * @property id The unique identifier for this API instance.
 * @property clazz THe class type for logging purposes
 */
abstract class VertxAPI<T : Enum<T>, U : Payload>(
    protected val vertx: Vertx,
    private val id: String,
    clazz: Class<*>
) : ISubscribeMType<T, U>, ISignMType<T> {
     private lateinit var ws: ClientWebSocket
     protected val log = WSLogger(clazz)

    /**
     * Abstract method to decode a received message.
     *
     * @param mtype The message type.
     * @param payload The payload of the message as a JsonObject.
     */
    protected abstract fun decodeMessage(mtype: T, payload: JsonObject)

    /**
     * Abstract method to decode the message type from a string.
     *
     * @param mtype The message type as a string.
     * @return The decoded message type.
     */
    protected abstract fun decodeMType(mtype: String): T

    /**
     * Sends a text message over the WebSocket connection.
     *
     * @param text The text message to send.
     * @return A Future indicating the success or failure of the send operation.
     */
    protected fun writeText(text: String): Future<Void> {
        val promise = Promise.promise<Void>()
        ws.writeTextMessage(text).onSuccess {
            log.sendText(text)
            promise.complete()
        }.onFailure {
            log.error("Failed to write $text")
            promise.fail(it)
        }
        return promise.future()
    }

    /**
     * Registers a default codec for a specific class type.
     *
     * @param T The class type.
     * @param clazz The class to register the codec for.
     * @param codec The codec to register.
     */
    protected fun <T> registerDefaultCodec(clazz: Class<T>, codec: MessageCodec<T, *>) {
        try {
            vertx.eventBus().registerDefaultCodec(clazz, codec)
            log.debug("Registered MessageCodec<${codec.name()}>")
        } catch (ex: Exception) {
            log.warn("MessageCodec<${clazz.simpleName}> is already registered")
        }
    }

    /**
     * Connects to the WebSocket server with the given options.
     *
     * @param options The WebSocket connection options.
     * @return A Future indicating the success or failure of the connection.
     */
    fun connect(options: WebSocketConnectOptions): Future<Void> {
        /**
         * Handles received text messages.
         *
         * @param text The received text message.
         */
        fun textMessageHandler(text: String) {
            try {
                val packet = WSPacket.decode(text)
                val mtype = decodeMType(packet.mtype)
                val payload = packet.payload
                log.receiveText(text)
                decodeMessage(mtype, payload)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        /**
         * Handles WebSocket close events.
         *
         * @param arg0 The close event argument.
         */
        @Suppress("UNUSED_PARAMETER")
        fun closeHandler(arg0: Void?) {
            ws.close().onSuccess {
                log.debug("Disconnected")
            }.onFailure { it.printStackTrace() }
        }
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

    /**
     * Subscribes to a specific message type on the event bus.
     *
     * @param mtype The message type to subscribe to.
     * @return The message consumer for the specified message type.
     */
    override fun subscribeTo(mtype: T): MessageConsumer<U> {
        return vertx.eventBus().consumer(sign(mtype))
    }

    /**
     * Signs a message type with the API instance ID.
     *
     * @param mtype The message type to sign.
     * @return The signed message type as a string.
     */
    override fun sign(mtype: T): String {
        return id.plus(".${mtype.name.lowercase()}")
    }
}
