package io.github.camposmdev.foursouls.model.context.store

import io.github.camposmdev.foursouls.model.context.store.state.ChestState
import io.vertx.core.Future
import io.vertx.core.Vertx

class ChestStore(val v: Vertx) : IStore<ChestState> {
//    private lateinit var api = ChestAPI()
    private var state = ChestState()

    fun connect(host: String, port: Int, userId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun state(): ChestState {
        return state
    }
}