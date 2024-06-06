package io.github.camposmdev.foursouls.core.context.store

import io.github.camposmdev.foursouls.core.context.store.state.ChestState
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

class ChestStore(val v: Vertx) : IStore<ChestState> {
//    private lateinit var api = ChestAPI()
    private var state = ChestState()
    private val eb = v.eventBus()

    fun connect(host: String, port: Int, userId: String): Future<Void> {
        TODO("Not yet implemented")
    }

    override fun state(): ChestState {
        return state
    }

    override fun subscribe(): MessageConsumer<ChestState> {
        return eb.consumer(toString())
    }
}