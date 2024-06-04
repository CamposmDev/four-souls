package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.ChestStore
import io.github.camposmdev.foursouls.model.context.store.state.ChestState
import io.vertx.core.Vertx

class ChestContext(v: Vertx, hostName: String = DEFAULT_HOST, port: Int = DEFAULT_PORT, jwt: String) : IContext<ChestState, ChestStore> {
    private val store = ChestStore(v, hostName, port, jwt)

    override fun state(): ChestState {
        return store.state()
    }

    override fun store(): ChestStore {
        return store
    }

    private companion object {
        private const val DEFAULT_HOST = "localhost"
        private const val DEFAULT_PORT = 6060
    }
}