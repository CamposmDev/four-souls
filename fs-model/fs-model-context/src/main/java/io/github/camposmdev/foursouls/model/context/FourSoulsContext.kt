package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.FourSoulsStore
import io.github.camposmdev.foursouls.model.context.store.state.FourSoulsState
import io.vertx.core.Vertx

class FourSoulsContext(v: Vertx, momHost: String, momPort: Int) : IContext<FourSoulsState, FourSoulsStore> {
    private val store = FourSoulsStore(v, momHost, momPort)

    fun mom(): MomContext {
        return store.state().mom
    }

    fun basement(): BasementContext {
        return store.state().basement
    }

    fun chest(): ChestContext {
        return store.state().chest
    }

    override fun state(): FourSoulsState {
        return store.state()
    }

    override fun store(): FourSoulsStore {
        return store
    }
}