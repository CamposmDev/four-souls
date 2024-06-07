package io.github.camposmdev.foursouls.core.context.store

import io.github.camposmdev.foursouls.core.api.ISubscribeMType
import io.github.camposmdev.foursouls.core.api.basement.BasementAPI
import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.payload.*
import io.github.camposmdev.foursouls.core.context.impl.StateCodec
import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.WebSocketConnectOptions
import java.util.*

class BasementStore(val vertx: Vertx) : IStore<BasementState>, ISubscribeMType<BasementMType, BasementPayload> {
    private val id = "basement.store.${UUID.randomUUID()}"
    private val api = BasementAPI(vertx)
    private var state = BasementState()
    private val eb = vertx.eventBus()
    private val log = Logger(BasementStore::class.java)

    init {
        try {
            eb.registerDefaultCodec(BasementState::class.java, StateCodec.Basement())
            log.info("Registered message codec: ${StateCodec.Basement::class.java}")
        } catch (err: Exception) {
            log.error(err)
        }
    }

    fun connect(floor: String, level: Int, userId: String): Future<Void> {
        val promise = Promise.promise<Void>()
        /* try and connect */
        val options = WebSocketConnectOptions().apply {
            host = floor
            port = level
            uri = WS_ROUTE
            putHeader(HttpHeaders.COOKIE, "$USERID_FIELD=$userId")
        }
        api.connect(options).onSuccess {
            state.subs.add(api.subscribeTo(BasementMType.GREETING).handler {
                val payload = it.body() as BasementGreeting
                state.connected = true
                state.host = payload.host
                state.username = payload.username
                state.users = payload.users
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BasementMType.CHAT).handler {
                val payload = it.body() as BasementChat
                state.chat.add(payload)
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BasementMType.DONE).handler {
                val payload = it.body() as BasementDone
                state.chestId = payload.chestId
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BasementMType.USERS).handler {
                val payload = it.body() as BasementUsers
                state.users = payload.users
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BasementMType.CLOSED).handler {
                state.connected = false
                state.host = false
                state.username = null
                state.users = listOf()
                state.chat = mutableListOf()
                state.subs.forEach { it.unregister() }
                state.subs = mutableListOf()
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            promise.complete()
        }.onFailure { promise.fail(it.cause) }
        return promise.future()
    }

    override fun state(): BasementState {
        return state
    }

    override fun subscribe(): MessageConsumer<BasementState> {
        return eb.consumer(id)
    }

    fun chat(text: String): Future<Void> {
        return api.chat(state.username, text)
    }

    override fun subscribeTo(mtype: BasementMType): MessageConsumer<BasementPayload> {
        return api.subscribeTo(mtype)
    }

    companion object {
        private const val WS_ROUTE = "/ws"
        private const val USERID_FIELD = "userId"
    }
}