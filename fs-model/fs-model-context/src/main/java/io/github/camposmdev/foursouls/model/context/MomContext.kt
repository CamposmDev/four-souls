package io.github.camposmdev.foursouls.model.context

import io.github.camposmdev.foursouls.model.context.store.MomStore
import io.github.camposmdev.foursouls.model.context.store.state.MomState
import io.vertx.core.Vertx

class MomContext(v: Vertx, hostName: String, port: Int) : IContext<MomState, MomStore> {
    private val store = MomStore(v, hostName, port)

    override fun store(): MomStore {
        return store
    }

    override fun state(): MomState {
        return store.state()
    }
}
