package io.github.camposmdev.foursouls.core.context.store

import io.github.camposmdev.foursouls.core.api.ISubscribeMType
import io.github.camposmdev.foursouls.core.api.basement.BasementAPI
import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.MType.*
import io.github.camposmdev.foursouls.core.api.message.payload.*
import io.github.camposmdev.foursouls.core.context.impl.StateCodec
import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer
import java.util.*

class BasementStore(val v: Vertx) : IStore<BasementState>, ISubscribeMType<BasementPayload> {
    private val id = "basement.store.${UUID.randomUUID()}"
    private val api = BasementAPI(v)
    private var state = BasementState()
    private val eb = v.eventBus()
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
        api.connect(floor, level, userId).onSuccess {
            state.subs.add(api.subscribeTo(BASEMENT_GREETING).handler {
                val payload = it.body() as BasementGreeting
                state.connected = true
                state.host = payload.host
                state.username = payload.username
                state.users = payload.users
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BASEMENT_CHAT).handler {
                val payload = it.body() as BasementChat
                state.chat.add(payload)
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BASEMENT_DONE).handler {
                val payload = it.body() as BasementDone
                state.chestId = payload.chestId
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BASEMENT_USERS).handler {
                val payload = it.body() as BasementUsers
                state.users = payload.users
                /* notify subscribers state updated */
                eb.publish(id, state)
            })
            state.subs.add(api.subscribeTo(BASEMENT_CLOSED).handler {
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

    override fun subscribeTo(mtype: MType): MessageConsumer<BasementPayload> {
        return api.subscribeTo(mtype)
    }
}