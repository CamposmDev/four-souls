package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.MomStore
import io.github.camposmdev.foursouls.model.context.store.state.MomState
import io.vertx.core.Vertx

class MomContext(v: Vertx, hostName: String = DEFAULT_HOST, port: Int = DEFAULT_PORT) : IContext<MomState, MomStore> {
    private val store = MomStore(v, hostName, port)

    override fun store(): MomStore {
        return store
    }

    override fun state(): MomState {
        return store.state()
    }

    private companion object {
        private const val DEFAULT_HOST = "localhost"
        private const val DEFAULT_PORT = 8080
    }
}
