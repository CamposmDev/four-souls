package io.github.camposmdev.foursouls.core.context.store

import io.github.camposmdev.foursouls.core.context.store.state.FourSoulsState
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

class FourSoulsStore(val v: Vertx, momHost: String, momPort: Int) : IStore<FourSoulsState> {
    private val state = FourSoulsState(v, momHost, momPort)
    private val eb = v.eventBus()

    override fun state(): FourSoulsState {
        return state
    }

    override fun subscribe(): MessageConsumer<FourSoulsState> {
        return eb.consumer(toString())
    }
}