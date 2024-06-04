package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.context.store.state.ChestState
import io.vertx.core.Vertx

class ChestStore(v: Vertx, hostName: String, port: Int, jwt: String) : IStore<ChestState> {
    private var currState = ChestState()

    override fun state(): ChestState {
        return currState
    }

    override fun setState(state: ChestState) {
        currState = state
    }
}