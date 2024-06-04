package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.BasementStore
import io.github.camposmdev.foursouls.model.context.store.state.BasementState
import io.vertx.core.Vertx

class BasementContext(v: Vertx, hostName: String = DEFAULT_HOST, port: Int = DEFAULT_PORT, jwt: String) : IContext<BasementState, BasementStore> {
    private val store = BasementStore(v, hostName, port, jwt)

    override fun store(): BasementStore {
        return store
    }

    override fun state(): BasementState {
        return store.state()
    }

    private companion object {
        private const val DEFAULT_HOST = "localhost"
        private const val DEFAULT_PORT = 7070
    }
}