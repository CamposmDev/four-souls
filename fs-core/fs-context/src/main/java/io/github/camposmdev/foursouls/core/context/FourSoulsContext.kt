package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.context.store.FourSoulsStore
import io.github.camposmdev.foursouls.core.context.store.state.FourSoulsState
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

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

    override fun subscribe(): MessageConsumer<FourSoulsState> {
        return store.subscribe()
    }
}