package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.context.store.state.BasementState
import io.github.camposmdev.foursouls.model.net.api.BasementApi
import io.github.camposmdev.foursouls.model.net.message.MType.*
import io.github.camposmdev.foursouls.model.net.message.payload.BasementGreeting
import io.vertx.core.Vertx

class BasementStore(v: Vertx, hostName: String, port: Int, jwt: String) : IStore<BasementState> {
    private val api = BasementApi(v, hostName, port, jwt)
    private var currState = BasementState()

    init {
        api.subscribeTo(BASEMENT_GREETING).handler {
            val payload = it.body() as BasementGreeting
        }
        api.subscribeTo(BASEMENT_CHAT).handler {
            /* forward to fxgl event bus */
            
        }
        api.subscribeTo(BASEMENT_FINISHED).handler {
            /* forward to fxgl event bus */
        }
        api.subscribeTo(BASEMENT_PLAYERS).handler {
            /* forward to fxgl event bus */
        }
        api.subscribeTo(BASEMENT_CLOSED).handler {
            /* forward to fxgl event bus */
        }
        api.subscribeTo(BASEMENT_ERROR).handler {
            /* forward to fxgl event bus */
        }
    }

    override fun state(): BasementState {
        return currState
    }

    override fun setState(state: BasementState) {

    }
}