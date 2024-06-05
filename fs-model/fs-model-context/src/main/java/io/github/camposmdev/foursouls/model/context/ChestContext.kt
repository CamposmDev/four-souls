package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.ChestStore
import io.github.camposmdev.foursouls.model.context.store.state.ChestState
import io.vertx.core.Vertx

class ChestContext(v: Vertx) : IContext<ChestState, ChestStore> {
    private val store = ChestStore(v)

    override fun state(): ChestState {
        return store.state()
    }

    override fun store(): ChestStore {
        return store
    }
}