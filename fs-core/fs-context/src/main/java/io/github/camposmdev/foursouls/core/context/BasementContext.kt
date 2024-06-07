package io.github.camposmdev.foursouls.core.context

import io.github.camposmdev.foursouls.core.api.ISubscribeMType
import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.payload.BasementPayload
import io.github.camposmdev.foursouls.core.context.store.BasementStore
import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageConsumer

class BasementContext(vertx: Vertx) : IContext<BasementState, BasementStore>, ISubscribeMType<BasementMType, BasementPayload> {
    private val store = BasementStore(vertx)

    override fun store(): BasementStore {
        return store
    }

    override fun state(): BasementState {
        return store.state()
    }

    override fun subscribe(): MessageConsumer<BasementState> {
        return store.subscribe()
    }

    override fun subscribeTo(mtype: BasementMType): MessageConsumer<BasementPayload> {
        return store.subscribeTo(mtype)
    }
}