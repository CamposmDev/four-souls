package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.api.basement.BasementAPI
import io.github.camposmdev.foursouls.model.api.message.MType.*
import io.github.camposmdev.foursouls.model.api.message.payload.*
import io.github.camposmdev.foursouls.model.context.store.state.BasementState
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.Vertx

class BasementStore(val v: Vertx) : IStore<BasementState> {
    private val api = BasementAPI(v)
    private var state = BasementState()

    fun connect(floor: String, level: Int, userId: String): Future<Void> {
        val promise = Promise.promise<Void>()
        /* unregister and clear subscriptions */
        state.subs.forEach { it.unregister() }
        state.subs.clear()
        /* try and connect */
        api.connect(floor, level, userId).onSuccess {
            state.subs.add(api.subscribeTo(BASEMENT_GREETING).handler {
                val payload = it.body().mapTo(BasementGreeting::class.java)
                /* update state */
                state.host = payload.host
                state.users = payload.users
                /* forward to fxgl event bus */

//                Platform.runLater { getEventBus().fireEvent(BasementEvent(GREETING, payload)) }
            })
            state.subs.add(api.subscribeTo(BASEMENT_CHAT).handler {
                val payload = it.body().mapTo(BasementChat::class.java)
                /* forward to fxgl event bus*/
//                Platform.runLater { getEventBus().fireEvent(BasementEvent(CHAT, payload)) }
            })
            state.subs.add(api.subscribeTo(BASEMENT_DONE).handler {
                val payload = it.body().mapTo(BasementDone::class.java)
                /* forward to fxgl event bus*/
//                Platform.runLater { getEventBus().fireEvent(BasementEvent(DONE, payload)) }
            })
            state.subs.add(api.subscribeTo(BASEMENT_USERS).handler {
                val payload = it.body().mapTo(BasementUsers::class.java)
                /* update state */
                state.users = payload.users
                /* forward to fxgl event bus*/
//                Platform.runLater { getEventBus().fireEvent(BasementEvent(USERS, payload)) }
            })
            state.subs.add(api.subscribeTo(BASEMENT_CLOSED).handler {
                val payload = it.body().mapTo(BasementClosed::class.java)
                /* forward to fxgl event bus*/
//                Platform.runLater { getEventBus().fireEvent(BasementEvent(CLOSED, payload)) }
            })
            state.subs.add(api.subscribeTo(BASEMENT_ERROR).handler {
                val payload = it.body().mapTo(BasementError::class.java)
                /* forward to fxgl event bus */
//                Platform.runLater { getEventBus().fireEvent(BasementEvent(ERROR, payload)) }
            })
            promise.complete()
        }.onFailure { promise.fail(it.cause) }
        return promise.future()
    }

    override fun state(): BasementState {
        return state
    }
}