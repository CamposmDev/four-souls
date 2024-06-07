package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.context.store.ChestStore
import io.github.camposmdev.foursouls.core.context.store.state.ChestState
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

class ChestContext(v: Vertx) : IContext<ChestState, ChestStore> {
    private val store = ChestStore(v)

    override fun state(): ChestState {
        return store.state()
    }

    override fun store(): ChestStore {
        return store
    }

    override fun subscribe(): MessageConsumer<ChestState> {
        return store.subscribe()
    }
}