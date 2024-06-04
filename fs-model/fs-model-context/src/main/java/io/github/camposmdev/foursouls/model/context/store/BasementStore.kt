package io.github.camposmdev.foursouls.model.context.store

import com.almasb.fxgl.dsl.getEventBus
import io.github.camposmdev.foursouls.model.context.event.BasementEvent
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.CHAT
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.CLOSED
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.DONE
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.ERROR
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.GREETING
import io.github.camposmdev.foursouls.model.context.event.BasementEvent.Companion.USERS
import io.github.camposmdev.foursouls.model.context.store.state.BasementState
import io.github.camposmdev.foursouls.model.net.api.BasementAPI
import io.github.camposmdev.foursouls.model.net.message.MType.*
import io.github.camposmdev.foursouls.model.net.message.payload.*
import io.vertx.core.Vertx

class BasementStore(v: Vertx, hostName: String, port: Int, jwt: String) : IStore<BasementState> {
    private val api = BasementAPI(v, hostName, port, jwt)
    private var state = BasementState()

    init {
        /* TODO - store subscriptions somewhere to unregister later */
        api.subscribeTo(BASEMENT_GREETING).handler {
            val payload = it.body() as BasementGreeting
            /* update state */
            state.host = payload.host
            state.users = payload.users
            /* forward to fxgl event bus */
            getEventBus().fireEvent(BasementEvent(GREETING, payload))
        }
        api.subscribeTo(BASEMENT_CHAT).handler {
            val payload = it.body() as BasementChat
            /* forward to fxgl event bus*/
            getEventBus().fireEvent(BasementEvent(CHAT, payload))
        }
        api.subscribeTo(BASEMENT_DONE).handler {
            val payload = it.body() as BasementDone
            /* forward to fxgl event bus*/
            getEventBus().fireEvent(BasementEvent(DONE, payload))
        }
        api.subscribeTo(BASEMENT_USERS).handler {
            val payload = it.body() as BasementUsers
            /* update state */
            state.users = payload.users
            /* forward to fxgl event bus*/
            getEventBus().fireEvent(BasementEvent(USERS, payload))
        }
        api.subscribeTo(BASEMENT_CLOSED).handler {
            val payload = it.body() as BasementClosed
            /* forward to fxgl event bus*/
            getEventBus().fireEvent(BasementEvent(CLOSED, payload))
        }
        api.subscribeTo(BASEMENT_ERROR).handler {
            val payload = it.body() as BasementError
            /* forward to fxgl event bus */
            getEventBus().fireEvent(BasementEvent(ERROR, payload))
        }
    }

    override fun state(): BasementState {
        return state
    }
}