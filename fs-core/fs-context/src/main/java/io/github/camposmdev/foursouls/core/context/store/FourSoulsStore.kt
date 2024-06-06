package io.github.camposmdev.foursouls.core.context.store

import io.github.camposmdev.foursouls.core.context.store.state.FourSoulsState
import io.vertx.core.Vertx

class FourSoulsStore(val v: Vertx, momHost: String, momPort: Int) : IStore<FourSoulsState> {
    private val state = FourSoulsState(v, momHost, momPort)

    override fun state(): FourSoulsState {
        return state
    }
}