package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.api.ISubscribeMType
import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.payload.BasementPayload
import io.github.camposmdev.foursouls.core.context.store.BasementStore
import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

class BasementContext(v: Vertx) : IContext<BasementState, BasementStore>, ISubscribeMType<BasementPayload> {
    private val store = BasementStore(v)

    override fun store(): BasementStore {
        return store
    }

    override fun state(): BasementState {
        return store.state()
    }

    override fun subscribeTo(mtype: MType): MessageConsumer<BasementPayload> {
        return store.subscribeTo(mtype)
    }
}